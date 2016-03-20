package vn.creative.news.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.creative.news.search.R;
import vn.creative.news.search.model.ArticleModel;
import vn.creative.news.search.model.ArticleWithoutCoverModel;

/**
 * Created by TanLe on 3/19/16.
 */
public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int COVER = 0, WITHOUT_COVER = 1;

    private Context mContext;
    private List<Object> articles;

    public ArticleAdapter(Context context, List<Object> articleList) {
        mContext = context;
        articles = articleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case COVER:
                View coverView = inflater.inflate(R.layout.item_article_cover, viewGroup, false);
                viewHolder = new CoverHolder(coverView);
                break;

            case WITHOUT_COVER:
                View withoutCoverView = inflater.inflate(R.layout.item_article_without_cover, viewGroup, false);
                viewHolder = new SnippetHolder(withoutCoverView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case COVER:
                CoverHolder coverHolder = (CoverHolder) viewHolder;
                configureCoverHolder(coverHolder, position);
                break;

            case WITHOUT_COVER:
                SnippetHolder snippetHolder = (SnippetHolder) viewHolder;
                configureSnippetHolder(snippetHolder, position);
                break;
        }
    }

    private void configureCoverHolder(CoverHolder coverHolder, int position) {
        ArticleModel article = (ArticleModel) articles.get(position);
        coverHolder.getArticleSnippet().setText(article.getSnippet());
        Glide.with(mContext)
                .load("http://nytimes.com/" + article.getMultimedia().get(1).getUrl())
                .asBitmap()
                .into(coverHolder.getArticleCover());
    }

    private void configureSnippetHolder(SnippetHolder snippetHolder, int position) {
        ArticleWithoutCoverModel article = (ArticleWithoutCoverModel) articles.get(position);
        snippetHolder.getArticleSnippet().setText(article.getSnippet());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (articles.get(position) instanceof ArticleModel) {
            return COVER;

        } else if (articles.get(position) instanceof ArticleWithoutCoverModel) {
            return WITHOUT_COVER;
        }

        return -1;
    }

    public void update(List<Object> items) {
        articles.clear();
        articles.addAll(items);
        notifyDataSetChanged();
    }

    public void updateMore(List<Object> items) {
        int curSize = getItemCount();
        articles.addAll(items);
        notifyItemRangeInserted(curSize, articles.size() -1);
    }
}
