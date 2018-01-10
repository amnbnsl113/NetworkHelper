package network.manage.networkmanager.remote;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import network.manage.networkhelper.NetworkInteractor;
import network.manage.networkhelper.NetworkManager;
import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.common.NetworkError;
import network.manage.networkmanager.common.Callback;
import network.manage.networkmanager.common.NetworkUrl;
import network.manage.networkmanager.mapper.FeedListMapper;
import network.manage.networkmanager.mapper.FeedMapper;
import network.manage.networkmanager.model.datamodel.FeedDataModel;
import network.manage.networkmanager.model.viewmodel.FeedListViewModel;
import network.manage.networkmanager.model.viewmodel.FeedViewModel;

/**
 * Created by aman on 28/12/17.
 */

public class RemoteDataSource {
    private NetworkManager networkManager = NetworkInteractor.getInstance().getNetworkManager();

    public void getFeedDataList(Callback<FeedListViewModel> callback, boolean sync) {

        String url = NetworkUrl.getPostListUrl();

        final WeakReference<Callback<FeedListViewModel>> reference = new WeakReference<>(callback);

        AbstractObserver<List<FeedDataModel>> observer = new AbstractObserver<List<FeedDataModel>>() {
            @Override
            public void onSuccess(List<FeedDataModel> postDataModels) {
                Callback<FeedListViewModel> modelCallback = reference.get();
                if (modelCallback != null) {
                    FeedListViewModel postListViewModel = new FeedListMapper().convert(postDataModels);
                    modelCallback.onSuccess(postListViewModel);
                }
            }

            @Override
            public void onFailure(NetworkError error) {
                Callback<FeedListViewModel> modelCallback = reference.get();
                if (modelCallback != null) {
                    modelCallback.onFailure(error);
                }
            }
        };
        networkManager.getList(url, observer, FeedDataModel.class, getHeaders(), sync);
    }


    public void postFeed(Callback<FeedViewModel> callback, FeedDataModel body) {
        String url = NetworkUrl.getPostListUrl();
        final WeakReference<Callback<FeedViewModel>> reference = new WeakReference<>(callback);

        AbstractObserver<FeedDataModel> observer = new AbstractObserver<FeedDataModel>() {
            @Override
            public void onSuccess(FeedDataModel postDataModels) {
                Callback<FeedViewModel> modelCallback = reference.get();
                if (modelCallback != null) {
                    FeedViewModel postListViewModel = new FeedMapper().convert(postDataModels);
                    modelCallback.onSuccess(postListViewModel);
                }
            }

            @Override
            public void onFailure(NetworkError error) {
                Callback<FeedViewModel> modelCallback = reference.get();
                if (modelCallback != null) {
                    modelCallback.onFailure(error);
                }
            }
        };
        networkManager.post(url, observer, FeedDataModel.class, body, getHeaders(), false);

    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
