package top.yjzloveyzh.common.pojo;

public class RequestEquipment {
    int id;
    String name;
    int count;
    double price;
    int labId;
    String origin;
    int requestBuyRecordId;
    Lab lab;

    public RequestEquipment() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
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

    public void setCount(int count) {
        this.count = count;
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

    public int getRequestBuyRecordId() {
        return requestBuyRecordId;
    }

    public void setRequestBuyRecordId(int requestBuyRecordId) {
        this.requestBuyRecordId = requestBuyRecordId;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }
}
