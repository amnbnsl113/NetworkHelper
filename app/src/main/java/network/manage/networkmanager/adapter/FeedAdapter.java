package network.manage.networkmanager.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import network.manage.networkmanager.R;
import network.manage.networkmanager.databinding.FeedItemBinding;
import network.manage.networkmanager.model.viewmodel.FeedViewModel;

/**
 * Created by aman on 5/1/18.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private List<FeedViewModel> feedViewModels;

    public FeedAdapter(Context context, List<FeedViewModel> feedViewModels) {
        this.feedViewModels = feedViewModels;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FeedItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.feed_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FeedViewModel feedViewModel = feedViewModels.get(position);
        if (feedViewModel != null) {
            holder.binding.setFeed(feedViewModel);
        }
    }

    @Override
    public int getItemCount() {
        return feedViewModels == null ? 0 : feedViewModels.size();
    }

    public void addItems(List<FeedViewModel> feedViewModels) {
        if (this.feedViewModels == null) {
            this.feedViewModels = new ArrayList<>();
        }
        if (feedViewModels != null) {
            this.feedViewModels.addAll(feedViewModels);
            notifyDataSetChanged();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        FeedItemBinding binding;

        public MyViewHolder(FeedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
