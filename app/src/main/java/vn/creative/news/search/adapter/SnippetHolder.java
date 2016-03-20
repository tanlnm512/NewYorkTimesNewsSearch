package vn.creative.news.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.news.search.R;

/**
 * Created by TanLe on 3/19/16.
 */
public class SnippetHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.tv_article_without_cover_snippet)
    TextView tvArticleSnippet;

    public SnippetHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getArticleSnippet() {
        return tvArticleSnippet;
    }
}
