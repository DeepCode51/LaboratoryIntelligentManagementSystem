package top.yjzloveyzh.common.pojo;

public class LabEquipmentCount {
    String equipmentName;
    int count;

    public LabEquipmentCount() {
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getCount() {
        return count;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "{name:\'" + equipmentName + "\', value:\'" + count + "\'}";
    }
}
