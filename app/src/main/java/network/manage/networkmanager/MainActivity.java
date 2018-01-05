package network.manage.networkmanager;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import network.manage.networkhelper.common.NetworkError;
import network.manage.networkmanager.adapter.FeedAdapter;
import network.manage.networkmanager.common.Callback;
import network.manage.networkmanager.databinding.ActivityMainBinding;
import network.manage.networkmanager.model.datamodel.FeedDataModel;
import network.manage.networkmanager.model.viewmodel.FeedListViewModel;
import network.manage.networkmanager.model.viewmodel.FeedViewModel;
import network.manage.networkmanager.remote.RemoteDataSource;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ActivityMainBinding binding;
    private FeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.feedList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.feedList.setAdapter(adapter = new FeedAdapter(this, null));
        sampleGetRequest();

        binding.container.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        sampleGetRequest();
    }


    public void sampleGetRequest() {
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        remoteDataSource.getFeedDataList(callback);
        binding.container.setRefreshing(true);
    }

    public void samplePostRequest() {
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        remoteDataSource.postFeed(call, new FeedDataModel(1, 2, "Title", "Body"));
    }

    private Callback<FeedListViewModel> callback = new Callback<FeedListViewModel>() {
        @Override
        public void onSuccess(FeedListViewModel postListViewModel) {
            if (postListViewModel != null)
                adapter.addItems(postListViewModel.getProductModels());

            binding.container.setRefreshing(false);
        }

        @Override
        public void onFailure(NetworkError error) {
            binding.container.setRefreshing(false);

        }
    };


    private Callback<FeedViewModel> call = new Callback<FeedViewModel>() {
        @Override
        public void onSuccess(FeedViewModel feedViewModel) {

        }

        @Override
        public void onFailure(NetworkError error) {

        }
    };

}
