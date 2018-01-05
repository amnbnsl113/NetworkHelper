package network.manage.networkmanager.model.datamodel;

import network.manage.networkhelper.model.BaseRequest;
import network.manage.networkhelper.model.BaseResponse;

/**
 * Created by aman on 28/12/17.
 */

public class FeedDataModel implements IDataModel, BaseResponse, BaseRequest {

    private int userId;
    private int id;
    private String title;
    private String body;

    public FeedDataModel() {
    }

    public FeedDataModel(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
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

    @Override
    public String toString() {
        return "FeedDataModel{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
