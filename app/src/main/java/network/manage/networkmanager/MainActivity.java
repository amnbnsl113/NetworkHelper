package network.manage.networkmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import network.manage.networkhelper.NetworkInteractor;
import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.common.NetworkError;
import network.manage.networkmanager.common.Callback;
import network.manage.networkmanager.model.datamodel.PostDataModel;
import network.manage.networkmanager.model.viewmodel.PostListViewModel;
import network.manage.networkmanager.remote.RemoteDataSource;

public class MainActivity extends AppCompatActivity {
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
    }

    public void buttonClicked(View view) {
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        remoteDataSource.getPostDataList(callback);
    }

    private Callback<PostListViewModel> callback = new Callback<PostListViewModel>() {
        @Override
        public void onSuccess(PostListViewModel postListViewModel) {
            textView.setText("Positive");
        }

        @Override
        public void onFailure(NetworkError error) {
            textView.setText("Negative");
        }
    };

}
