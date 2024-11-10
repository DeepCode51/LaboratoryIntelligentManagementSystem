package top.yjzloveyzh.common.pojo;

import java.util.Date;
import java.util.List;

public class RequestBuyRecord {

    private int id;
    private Date createAt;
    private Date updateAt;
    private Date allowAt;
    private boolean isDeleted;
    private int isAllowed;
    private boolean isDone;
    private double cost;
    private String title;
    private int allowedUserId;
    private int requestedUserId;
    private User allowedUser;
    private User requestedUser;
    private List<RequestEquipment> equipments;

    public RequestBuyRecord() {
    }

    public int getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public User getAllowedUser() {
        return allowedUser;
    }

    public User getRequestedUser() {
        return requestedUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setAllowedUser(User allowedUser) {
        this.allowedUser = allowedUser;
    }

    public void setRequestedUser(User requestedUser) {
        this.requestedUser = requestedUser;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(int isAllowed) {
        this.isAllowed = isAllowed;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getAllowedUserId() {
        return allowedUserId;
    }

    public int getRequestedUserId() {
        return requestedUserId;
    }

    public void setAllowedUserId(int allowedUserId) {
        this.allowedUserId = allowedUserId;
    }

    public void setRequestedUserId(int requestedUserId) {
        this.requestedUserId = requestedUserId;
    }

    public List<RequestEquipment> getEquipments() {
        return equipments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEquipments(List<RequestEquipment> equipments) {
        this.equipments = equipments;
    }

    public Date getAllowAt() {
        return allowAt;
    }

    public void setAllowAt(Date allowAt) {
        this.allowAt = allowAt;
    }
}
