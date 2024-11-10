package top.yjzloveyzh.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import top.yjzloveyzh.common.Constants;
import top.yjzloveyzh.common.exception.EquipmentException;
import top.yjzloveyzh.common.pojo.DeletedRecord;
import top.yjzloveyzh.common.pojo.Equipment;
import top.yjzloveyzh.common.pojo.Pagination;
import top.yjzloveyzh.common.pojo.RequestBuyRecord;
import top.yjzloveyzh.common.pojo.RequestEquipment;
import top.yjzloveyzh.common.pojo.User;
import top.yjzloveyzh.common.utils.PaginationUtil;
import top.yjzloveyzh.common.utils.PropertyUtil;
import top.yjzloveyzh.common.utils.StringUtil;
import top.yjzloveyzh.dao.DeletedRecordDao;
import top.yjzloveyzh.dao.EquipmentDao;
import top.yjzloveyzh.dao.RecordDao;
import top.yjzloveyzh.services.EquipmentService;

@Service(value = "equipmentServiceImpl")
public class EquipmentServiceImpl implements EquipmentService{

    @Qualifier(value="equipmentDaoImpl")
    @Autowired
    EquipmentDao equipmentDaoImpl;

    @Qualifier(value="recordDaoImpl")
    @Autowired
    RecordDao recordDaoImpl;

    @Qualifier(value="deletedRecordDaoImpl")
    @Autowired
    DeletedRecordDao deletedRecordDaoImpl;

    @Override
    public void requestBuyEquipment(Map<String, Object> equipments, User user) throws EquipmentException {
        ArrayList<Map<String, String>> equipmentList = (ArrayList<Map<String, String>>) equipments.get(
            Constants.EquipmentController.KEY_REQUEST_BUY_EQUIPMENTS_JSON
        );

        String title = (String) equipments.get("title");

        List<RequestEquipment> requestRequipments = new ArrayList<RequestEquipment>();

        RequestBuyRecord buyRecord = new RequestBuyRecord();
        buyRecord.setRequestedUser(user);
        buyRecord.setTitle(title);
        double cost = 0;

        for(Map<String, String> equipment : equipmentList) {

            String count = equipment.get("count");
            String price = equipment.get("price");

            try {
                cost += Integer.parseInt(count) * Double.parseDouble(price);
            } catch (NumberFormatException numberFormatException) {
                throw new EquipmentException(Constants.ErrorCode.ERROR_PARAMETER_ILLEGAL, numberFormatException.getMessage());
            }
        }

        buyRecord.setCost(cost);
        buyRecord.setRequestedUserId(user.getId());

        recordDaoImpl.insertRequestRecordByRecord(buyRecord);
        int buyRecordId = buyRecord.getId();

        for (Map<String, String> equipment : equipmentList) {
            String name = equipment.get("name");
            String count = equipment.get("count");
            String price = equipment.get("price");
            String shop = equipment.get("shop");
            String labId = equipment.get("lab");

            RequestEquipment requestEquipment = new RequestEquipment();

            try {
                requestEquipment.setLabId(Integer.parseInt(labId));
                requestEquipment.setName(name);
                requestEquipment.setOrigin(shop);
                requestEquipment.setCount(Integer.parseInt(count));
                requestEquipment.setPrice(Double.parseDouble(price));
                requestEquipment.setRequestBuyRecordId(buyRecordId);

                equipmentDaoImpl.insertRequestEquipment(requestEquipment);


            } catch (NumberFormatException numberFormatException) {
                throw new EquipmentException(Constants.ErrorCode.ERROR_PARAMETER_ILLEGAL, numberFormatException.getMessage());
            }
        }
    }

    @Override
    public Pagination<Equipment> listEquipmentsByPagination(String keyword, String currentPage, String orderBy, String labIdS, User user) throws EquipmentException {

        int page = 1;
        int order = 1;
        int count = 6;
        int labId = 0;

        if (keyword == null) {
            keyword = new String();
        }

        try {
            page = Integer.parseInt(currentPage);

            if (page <= 0) {
                page = 1;
            }

        } catch (NumberFormatException numberFormatException) {
        }

        try {
            order = Integer.parseInt(orderBy);
            order = order == 0 ? order : 1;
        } catch (NumberFormatException e) {
        }

        try {
            count = Integer.parseInt(PropertyUtil.getRequestBuyRecordPerPageCount());
        } catch (NumberFormatException e) {
        }

        try {
            labId = Integer.parseInt(labIdS);
        } catch (NumberFormatException e) {
        }

        Pagination<Equipment> pagination = new Pagination<Equipment>();
        pagination.setSearchKeyWord(keyword);
        orderBy = order == 0 ? "ASC" : "DESC";

        keyword = "%" + keyword + "%";
        int totalCount = equipmentDaoImpl.getCountBySearchWord(keyword, labId);
        int maxPage = (int) Math.ceil(totalCount * 1.0 / count);

        if (page > maxPage) {
            page = 1;
        }

        int start = (page - 1) * count;
        int offset = count;

        List<Integer> pageIndexList = PaginationUtil.makePageIndexList(page, count, maxPage);

        pagination.setCurrentPage(page);
        pagination.setResults(equipmentDaoImpl.getByPagination(keyword, start, offset, labId, orderBy));
        pagination.setTotalCount(count);
        pagination.setTotalPage(maxPage);
        pagination.setPagesList(pageIndexList);
        pagination.setOrderBy(order);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("labId", labId);
        pagination.setExtra(map);

        return pagination;
    }

    @Override
    public int insertEquipmentByEquipment(Equipment equipment, User user) throws EquipmentException {

        if (equipment != null && StringUtil.isNotEmpty(equipment.getName())) {
            return equipmentDaoImpl.insertEquipment(equipment);
        } else {
            throw new EquipmentException(Constants.ErrorCode.ERROR_PARAMETER_ILLEGAL, "参数不完整");
        }
    }

    @Override
    public int deleteEquipmentByIdArray(User user, String[] deletedIds) throws EquipmentException {

        DeletedRecord deletedRecord = new DeletedRecord();
        deletedRecord.setUserId(user.getId());

        if (deletedIds == null || deletedIds.length == 0) {

            return 0;
        }

        int idArray[] = new int[deletedIds.length];

        try {
            for (int i = 0; i < deletedIds.length; i++) {
                int id = Integer.parseInt(deletedIds[i]);
                idArray[i] = id;
            }
        }catch (NumberFormatException e) {
            throw new EquipmentException(Constants.ErrorCode.ERROR_PARAMETER_ILLEGAL, "参数不完整");
        }

        deletedRecordDaoImpl.insertDeletedRecordByRecord(deletedRecord);
        int deletedRecordId = deletedRecord.getId();

        int count = equipmentDaoImpl.deleteEquipmentByIdArray(idArray, deletedRecordId);

        return count;
    }
}
