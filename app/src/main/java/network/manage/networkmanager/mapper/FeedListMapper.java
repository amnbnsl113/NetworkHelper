package network.manage.networkmanager.mapper;

import java.util.List;

import network.manage.networkmanager.model.datamodel.FeedDataModel;
import network.manage.networkmanager.model.viewmodel.FeedListViewModel;

/**
 * Created by aman on 28/12/17.
 */

public class FeedListMapper implements IMapper<FeedListViewModel, List<FeedDataModel>> {

    @Override
    public FeedListViewModel convert(List<FeedDataModel> dataModel) {
        return null;
    }
}
