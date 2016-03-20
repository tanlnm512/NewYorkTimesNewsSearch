package vn.creative.news.search.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.creative.news.search.R;

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

    @Bind(R.id.cb_sports)
    CheckBox cbSports;

    @Bind(R.id.cb_fashion)
    CheckBox cbFashion;

    @Bind(R.id.btn_save)
    Button btnSave;

    private String sortOrder, beginTime, newsDesk;

    private ActionBar actionBar;
    SimpleDateFormat ddmmyyyy = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd", Locale.US);

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
        return view;
    }

    @OnClick(R.id.et_time)
    void getTime() {
        DatePickerFrg datePickerFrg = new DatePickerFrg();
        datePickerFrg.show(getFragmentManager(), "DatePickerFrg");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        etTime.setText(ddmmyyyy.format(c.getTime()));
        beginTime = yyyymmdd.format(c.getTime());
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
            // This is the up button
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
