package vn.creative.news.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.news.search.R;

/**
 * Created by TanLe on 3/19/16.
 */
public class CoverHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.iv_article_cover)
    ImageView ivArticleCover;

    @Bind(R.id.tv_article_cover_snippet)
    TextView tvArticleSnippet;

    public CoverHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getArticleCover() {
        return ivArticleCover;
    }

    public TextView getArticleSnippet() {
        return tvArticleSnippet;
    }
}
