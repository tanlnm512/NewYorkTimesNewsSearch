package vn.creative.news.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.news.search.R;
import vn.creative.news.search.listener.IArticleListener;

/**
 * Created by TanLe on 3/19/16.
 */
public class CoverHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @Bind(R.id.iv_article_cover)
    ImageView ivArticleCover;

    @Bind(R.id.tv_article_cover_snippet)
    TextView tvArticleSnippet;

    private IArticleListener listener;

    public CoverHolder(View itemView, IArticleListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.listener = listener;
    }

    public ImageView getArticleCover() {
        return ivArticleCover;
    }

    public TextView getArticleSnippet() {
        return tvArticleSnippet;
    }

    @Override
    public void onClick(View v) {
        listener.onArticleClick();
    }
}
