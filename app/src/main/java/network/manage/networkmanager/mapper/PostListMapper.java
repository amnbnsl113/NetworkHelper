package network.manage.networkmanager.mapper;

import java.util.List;

import network.manage.networkmanager.model.datamodel.PostDataModel;
import network.manage.networkmanager.model.viewmodel.PostListViewModel;

/**
 * Created by aman on 28/12/17.
 */

public class PostListMapper implements IMapper<PostListViewModel, List<PostDataModel>> {

    @Override
    public PostListViewModel convert(List<PostDataModel> dataModel) {
        return null;
    }
}
