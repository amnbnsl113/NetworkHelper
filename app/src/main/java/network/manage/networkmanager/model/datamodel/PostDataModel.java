package network.manage.networkmanager.model.datamodel;

/**
 * Created by aman on 28/12/17.
 */

public class PostDataModel implements IDataModel {

    private int userId;
    private int id;
    private String title;
    private String body;

    public PostDataModel() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
