package network.manage.networkmanager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

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
    private RemoteDataSource remoteDataSource;

    private static final int READ_REQUEST_CODE = 42;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fetch from Dependency Injection
        remoteDataSource = new RemoteDataSource();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.feedList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.feedList.setAdapter(adapter = new FeedAdapter(this, null));
        binding.container.setOnRefreshListener(this);

        fetchFeed();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {


        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                uploadImage(uri);
            }
        }
    }

    private void uploadImage(Uri uri) {
        remoteDataSource.uploadImage(uri);
    }


    public void performFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("image/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onRefresh() {
        fetchFeed();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
        } else {
            performFileSearch();
        }

    }


    public void fetchFeed() {

        remoteDataSource.getFeedDataList(callback, false);
//        samplePostRequest();
        binding.container.setRefreshing(true);
    }

    public void samplePostRequest() {
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
            binding.container.setRefreshing(false);

        }

        @Override
        public void onFailure(NetworkError error) {
            binding.container.setRefreshing(false);

        }
    };

}
