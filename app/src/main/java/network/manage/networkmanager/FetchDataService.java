package network.manage.networkmanager;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import network.manage.networkhelper.common.NetworkError;
import network.manage.networkmanager.common.Callback;
import network.manage.networkmanager.model.viewmodel.FeedListViewModel;
import network.manage.networkmanager.remote.RemoteDataSource;

public class FetchDataService extends IntentService {


    public FetchDataService() {
        super("FetchDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        remoteDataSource.getFeedDataList(callback,true);

    }

    private Callback<FeedListViewModel> callback = new Callback<FeedListViewModel>() {
        @Override
        public void onSuccess(FeedListViewModel feedListViewModel) {
//            Toast.makeText(getApplicationContext(), "Positive", Toast.LENGTH_LONG).show();
            Log.e("pass", feedListViewModel.toString());
        }

        @Override
        public void onFailure(NetworkError error) {
            Toast.makeText(getApplicationContext(), "Negative", Toast.LENGTH_LONG).show();
        }
    };

}
