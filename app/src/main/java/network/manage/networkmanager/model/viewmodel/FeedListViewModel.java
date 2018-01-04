package network.manage.networkmanager.model.viewmodel;

import java.util.List;

/**
 * Created by aman on 28/12/17.
 */

public class FeedListViewModel implements IViewModel {

    private List<ProductModel> productModels;

    public FeedListViewModel() {
    }

    public List<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.productModels = productModels;
    }
}
