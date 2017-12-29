package network.manage.networkmanager.model.viewmodel;

import java.util.List;

/**
 * Created by aman on 28/12/17.
 */

public class PostListViewModel implements IViewModel {

    private List<ProductModel> productModels;

    public PostListViewModel() {
    }

    public List<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.productModels = productModels;
    }
}
