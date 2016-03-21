package vn.creative.news.search.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import vn.creative.news.search.R;
import vn.creative.news.search.adapter.ArticleAdapter;
import vn.creative.news.search.common.CommonUtils;
import vn.creative.news.search.listener.EndlessRecyclerViewScrollListener;
import vn.creative.news.search.model.ArticleModel;
import vn.creative.news.search.model.ArticleWithoutCoverModel;

/**
 * Created by TanLe on 3/19/16.
 */
public class ArticleFrg extends Fragment {
    private static final String TAG = "ArticleFrg";

    @Bind(R.id.rv_article_list)
    RecyclerView rvArticleList;

    private int curPage = 0;
    private String mQuery;

    private ArticleAdapter articleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, null);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvArticleList.setLayoutManager(staggeredGridLayoutManager);

        List<Object> adapterItems = new ArrayList<>();
        articleAdapter = new ArticleAdapter(getContext(), adapterItems);
        rvArticleList.setAdapter(articleAdapter);

        rvArticleList.setOnScrollListener(new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                fetchNews(++curPage);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mQuery = query;
                curPage = 0;

                searchView.clearFocus();
                fetchNews(curPage);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new SettingFrg();
                fragmentTransaction.replace(R.id.frg_container, fragment, "SettingFrg").addToBackStack("SettingFrg").commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fetchNews(final int page) {
        if (!CommonUtils.isNetworkAvailable(getContext())) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Error!")
                    .setMessage("No connection!!!")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        } else try {
            mQuery = URLEncoder.encode(mQuery, "UTF-8");
            String url = CommonUtils.getSearchLink(getActivity(), mQuery, page);
            System.out.println(url);

            AsyncHttpClient httpClient = new AsyncHttpClient();
            httpClient.get(url, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        Type type = new TypeToken<List<ArticleModel>>() {
                        }.getType();
                        List<ArticleModel> articles = new Gson().fromJson(response.getJSONObject("response").getJSONArray("docs").toString(), type);

                        List<Object> adapterItems = new ArrayList<>();
                        for (ArticleModel article : articles) {
                            if (article.getMultimedia() == null || article.getMultimedia().size() == 0) {
                                adapterItems.add(new ArticleWithoutCoverModel(article.getWebUrl(), article.getSnippet()));

                            } else {
                                adapterItems.add(article);
                            }
                        }

                        if (page == 0) {
                            articleAdapter.update(adapterItems);

                        } else {
                            articleAdapter.updateMore(adapterItems);
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, "fetchNews error!", e);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.e(TAG, "fetchNews error!", throwable);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "fetchNews error!", e);
        }
    }
}
