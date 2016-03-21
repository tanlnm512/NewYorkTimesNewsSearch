package vn.creative.news.search.view;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.creative.news.search.R;
import vn.creative.news.search.common.PrefUtils;
import vn.creative.news.search.model.SearchSettingModel;

/**
 * Created by minhtan512 on 3/20/2016.
 */
public class SettingFrg extends Fragment implements DatePickerDialog.OnDateSetListener {
    @Bind(R.id.et_time)
    EditText etTime;

    @Bind(R.id.sp_sort)
    Spinner spSortOrder;

    @Bind(R.id.cb_tech)
    CheckBox cbTech;

    @Bind(R.id.cb_fashion)
    CheckBox cbFashion;

    @Bind(R.id.cb_natural_world)
    CheckBox cbNaturalWorld;

    @Bind(R.id.btn_save)
    Button btnSave;

    private String sortOrder = "newest";
    private long beginTime;

    private ActionBar actionBar;
    private SearchSettingModel searchSetting;
    private SimpleDateFormat ddmmyyyy = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, null);

        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);

        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        List<String> categories = new ArrayList<>();
        categories.add("Newest");
        categories.add("Oldest");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spSortOrder.setAdapter(dataAdapter);
        spSortOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sortOrder = adapterView.getItemAtPosition(i).toString().toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sortOrder = "newest";
            }
        });

        searchSetting = new Gson().fromJson(PrefUtils.getInstance(getActivity()).readString("setting"), SearchSettingModel.class);
        if (searchSetting != null) {
            beginTime = searchSetting.getBeginTime();
            etTime.setText(ddmmyyyy.format(new Date(beginTime)));
            spSortOrder.setSelection(categories.indexOf(searchSetting.getSortOrder()));
            cbFashion.setChecked(searchSetting.isFashion());
            cbNaturalWorld.setChecked(searchSetting.isNaturalWorld());
            cbTech.setChecked(searchSetting.isTech());
        }

        return view;
    }

    @OnClick(R.id.et_time)
    void getTime() {
        DatePickerFrg datePickerFrg = new DatePickerFrg();
        datePickerFrg.show(getFragmentManager(), "DatePickerFrg");
    }

    @OnClick(R.id.btn_save)
    void saveSetting() {
        if (etTime.getText().length() == 0) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Error!")
                    .setMessage("Please choose begin time!!!")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }

        if (searchSetting == null) {
            searchSetting = new SearchSettingModel();
        }
        searchSetting.setBeginTime(beginTime);
        searchSetting.setTech(cbTech.isChecked());
        searchSetting.setFashion(cbFashion.isChecked());
        searchSetting.setNaturalWorld(cbNaturalWorld.isChecked());

        PrefUtils.getInstance(getActivity()).writeString("setting", new Gson().toJson(searchSetting));
        getActivity().onBackPressed();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        etTime.setText(ddmmyyyy.format(c.getTime()));
        beginTime = c.getTime().getTime();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
