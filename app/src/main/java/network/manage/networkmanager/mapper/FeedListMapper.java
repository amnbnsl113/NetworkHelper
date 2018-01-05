package network.manage.networkmanager.mapper;

import java.util.ArrayList;
import java.util.List;

import network.manage.networkmanager.model.datamodel.FeedDataModel;
import network.manage.networkmanager.model.viewmodel.FeedListViewModel;
import network.manage.networkmanager.model.viewmodel.FeedViewModel;

/**
 * Created by aman on 28/12/17.
 */

public class FeedListMapper implements IMapper<FeedListViewModel, List<FeedDataModel>> {

    @Override
    public FeedListViewModel convert(List<FeedDataModel> dataModel) {
        FeedListViewModel feedListViewModel = new FeedListViewModel();
        List<FeedViewModel> feedList = new ArrayList<>();
        for (FeedDataModel feedDataModel : dataModel) {
            FeedViewModel feedViewModel = new FeedViewModel();
            feedViewModel.setBody(feedDataModel.getBody());
            feedViewModel.setId(feedDataModel.getId());
            feedViewModel.setTitle(feedDataModel.getTitle());
            feedViewModel.setUserId(feedDataModel.getUserId());
            feedList.add(feedViewModel);
        }
        feedListViewModel.setProductModels(feedList);
        return feedListViewModel;
    }
}
