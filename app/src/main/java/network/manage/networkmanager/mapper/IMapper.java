package network.manage.networkmanager.mapper;

import network.manage.networkmanager.model.datamodel.IDataModel;
import network.manage.networkmanager.model.viewmodel.IViewModel;

/**
 * Created by aman on 28/12/17.
 */

public interface IMapper<V extends IViewModel, D> {

    public V convert(D dataModel);

}
