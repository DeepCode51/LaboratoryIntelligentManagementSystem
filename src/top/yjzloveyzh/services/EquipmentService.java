package top.yjzloveyzh.services;

import java.util.Map;

import top.yjzloveyzh.common.exception.EquipmentException;
import top.yjzloveyzh.common.pojo.Equipment;
import top.yjzloveyzh.common.pojo.Pagination;
import top.yjzloveyzh.common.pojo.User;

public interface EquipmentService {

    /**
     * 申请购置设备
     * @param equipments
     * @param user
     * @throws EquipmentException
     */
    public void requestBuyEquipment(Map<String, Object> equipments, User user) throws EquipmentException;

    /**
     * 根据当前页，以及搜索内容还有排序顺序进行显示设备列表
     * @param searchKeyWord
     * @param page
     * @param orderBy
     * @param labId
     * @param user
     * @return 分好页的设备列表
     * @throws EquipmentException
     */
    public Pagination<Equipment> listEquipmentsByPagination(String searchKeyWord, String page, String orderBy, String labId, User user) throws EquipmentException;

    public int insertEquipmentByEquipment(Equipment equipment, User user) throws EquipmentException;

    /**
     * @param user
     * @param deletedIds
     * @return
     * @throws EquipmentException
     */
    int deleteEquipmentByIdArray(User user, String[] deletedIds) throws EquipmentException;
}
