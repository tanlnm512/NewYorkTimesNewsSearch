package vn.creative.news.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.news.search.R;
import vn.creative.news.search.listener.IArticleListener;

/**
 * Created by TanLe on 3/19/16.
 */
public class SnippetHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @Bind(R.id.tv_article_without_cover_snippet)
    TextView tvArticleSnippet;

    private IArticleListener listener;

    public SnippetHolder(View itemView, IArticleListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.listener = listener;
    }

    public TextView getArticleSnippet() {
        return tvArticleSnippet;
    }

    @Override
    public void onClick(View v) {
        listener.onArticleClick();
    }
}
