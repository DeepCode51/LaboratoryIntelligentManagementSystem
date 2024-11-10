package top.yjzloveyzh.common.pojo;

import java.util.Date;

public class Equipment {
    int id;
    String name;
    double price;
    int labId;
    String origin;
    Date createdAt;
    Date updatedAt;
    int requestBuyRecordId;

    boolean isDeleted;
    Lab lab;

    public Equipment() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getLabId() {
        return labId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public int getRequestBuyRecordId() {
        return requestBuyRecordId;
    }

    public void setRequestBuyRecordId(int requestBuyRecordId) {
        this.requestBuyRecordId = requestBuyRecordId;
    }
}
