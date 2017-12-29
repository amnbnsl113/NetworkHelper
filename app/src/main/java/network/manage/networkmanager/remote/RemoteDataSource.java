package network.manage.networkmanager.remote;

import java.lang.ref.WeakReference;
import java.util.List;

import network.manage.networkhelper.NetworkInteractor;
import network.manage.networkhelper.NetworkManager;
import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.common.NetworkError;
import network.manage.networkmanager.common.Callback;
import network.manage.networkmanager.common.NetworkUrl;
import network.manage.networkmanager.mapper.PostListMapper;
import network.manage.networkmanager.model.datamodel.PostDataModel;
import network.manage.networkmanager.model.viewmodel.PostListViewModel;

/**
 * Created by aman on 28/12/17.
 */

public class RemoteDataSource {
    NetworkManager networkManager = NetworkInteractor.getInstance().getNetworkManager();


    public void getPostDataList(Callback<PostListViewModel> callback) {

        String url = NetworkUrl.getPostListUrl();
        final WeakReference<Callback<PostListViewModel>> reference = new WeakReference<>(callback);

        AbstractObserver<List<PostDataModel>> observer = new AbstractObserver<List<PostDataModel>>() {
            @Override
            public void onSuccess(List<PostDataModel> postDataModels) {
                Callback<PostListViewModel> modelCallback = reference.get();
                if (modelCallback != null) {
                    PostListViewModel postListViewModel = new PostListMapper().convert(postDataModels);
                    modelCallback.onSuccess(postListViewModel);
                }
            }

            @Override
            public void onFailure(NetworkError error) {
                Callback<PostListViewModel> modelCallback = reference.get();
                if (modelCallback != null) {
                    modelCallback.onFailure(error);
                }
            }
        };
        networkManager.getList(url, observer, PostDataModel.class);
    }


}
