package network.manage.networkmanager.model.viewmodel;

import java.util.List;

/**
 * Created by aman on 28/12/17.
 */

public class FeedListViewModel implements IViewModel {

    private List<FeedViewModel> productModels;

    public FeedListViewModel() {
    }

    public List<FeedViewModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<FeedViewModel> productModels) {
        this.productModels = productModels;
    }
}
