package network.manage.networkmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import network.manage.networkhelper.Callback;
import network.manage.networkhelper.NetworkInteractor;
import network.manage.networkhelper.NetworkManager;
import network.manage.networkhelper.common.NetworkError;
import network.manage.networkhelper.model.BaseResponse;
import network.manage.networkhelper.retrofit.RetrofitManager;

public class MainActivity extends AppCompatActivity {
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
    }

    public void buttonClicked(View view) {
        NetworkInteractor.getInstance().getNetworkManager().getList("https://jsonplaceholder.typicode.com/posts", new WeakReference<>(callback));
    }


    private Callback<List<BaseResponse>> callback = new Callback<List<BaseResponse>>() {
        @Override
        public void onSuccess(List<BaseResponse> baseResponses) {
            textView.setText("Positive Response");
        }

        @Override
        public void onFailure(NetworkError error) {
            textView.setText("Negative Response");
        }
    };

}
