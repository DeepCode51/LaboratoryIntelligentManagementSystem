package top.yjzloveyzh.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import top.yjzloveyzh.common.Constants;
import top.yjzloveyzh.common.exception.EquipmentException;
import top.yjzloveyzh.common.exception.RecordException;
import top.yjzloveyzh.common.pojo.Equipment;
import top.yjzloveyzh.common.pojo.Pagination;
import top.yjzloveyzh.common.pojo.RequestBuyRecord;
import top.yjzloveyzh.common.pojo.RequestEquipment;
import top.yjzloveyzh.common.pojo.User;
import top.yjzloveyzh.common.utils.PaginationUtil;
import top.yjzloveyzh.common.utils.PropertyUtil;
import top.yjzloveyzh.dao.RecordDao;
import top.yjzloveyzh.services.EquipmentService;
import top.yjzloveyzh.services.RecordService;

@Service(value = "recordServiceImpl")
public class RecordServiceImpl implements RecordService{

    @Qualifier(value = "recordDaoImpl")
    @Autowired
    private RecordDao recordDao;

    @Qualifier(value = "equipmentServiceImpl")
    @Autowired
    private EquipmentService equipmentService;

    @Override
    public Pagination<RequestBuyRecord> getPaginationRequestBuyRecord(User user, String keyword, String currentPage, String orderBy) throws RecordException {

        if (user == null) {
            throw new RecordException(Constants.ErrorCode.ERROR_USER_NOT_EXIST, "对不起, 请先登录.");
        }

        int page = 1;
        int order = 1;
        int count = 6;

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


        Pagination<RequestBuyRecord> pagination = new Pagination<RequestBuyRecord>();
        pagination.setSearchKeyWord(keyword);
        orderBy = order == 0 ? "ASC" : "DESC";

        keyword = "%" + keyword + "%";
        int totalCount = recordDao.getRequestBuyRecordCount(keyword, user.getId());
        int start = (page - 1) * count;
        int offset = count;
        int maxPage = (int) Math.ceil(totalCount * 1.0 / count);

        if (page > maxPage) {
            page = 1;
        }
        List<Integer> pageIndexList = PaginationUtil.makePageIndexList(page, count, maxPage);

        pagination.setCurrentPage(page);
        pagination.setResults(recordDao.getRequestBuyRecordPagination(keyword, start, offset, orderBy, user.getId()));
        pagination.setTotalCount(count);
        pagination.setTotalPage(maxPage);
        pagination.setPagesList(pageIndexList);
        pagination.setOrderBy(order);

        return pagination;
    }

    @Override
    public Pagination<RequestBuyRecord> getNotReplyRequestBuyRecord(User user, String keyword, String currentPage, String orderBy) throws RecordException {

        if (user == null) {
            throw new RecordException(Constants.ErrorCode.ERROR_USER_NOT_EXIST, "对不起, 请先登录.");
        }

        int page = 1;
        int order = 1;
        int count = 6;

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


        Pagination<RequestBuyRecord> pagination = new Pagination<RequestBuyRecord>();
        pagination.setSearchKeyWord(keyword);
        orderBy = order == 0 ? "ASC" : "DESC";

        keyword = "%" + keyword + "%";
        int totalCount = recordDao.getNotReplyAllRequestBuyRecordCount(keyword);
        int start = (page - 1) * count;
        int offset = count;
        int maxPage = (int) Math.ceil(totalCount * 1.0 / count);

        if (page > maxPage) {
            page = 1;
        }

        List<Integer> pageIndexList = PaginationUtil.makePageIndexList(page, count, maxPage);

        pagination.setCurrentPage(page);
        pagination.setResults(recordDao.getNotReplyRequestBuyRecordPagination(keyword, start, offset, orderBy));
        pagination.setTotalCount(count);
        pagination.setTotalPage(maxPage);
        pagination.setPagesList(pageIndexList);
        pagination.setOrderBy(order);

        return pagination;
    }

    @Override
    public Pagination<RequestBuyRecord> getRepliedRequestBuyRecord(User user, String keyword, String currentPage, String orderBy) throws RecordException {

        if (user == null) {
            throw new RecordException(Constants.ErrorCode.ERROR_USER_NOT_EXIST, "对不起, 请先登录.");
        }

        int page = 1;
        int order = 1;
        int count = 6;

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


        Pagination<RequestBuyRecord> pagination = new Pagination<RequestBuyRecord>();
        pagination.setSearchKeyWord(keyword);
        orderBy = order == 0 ? "ASC" : "DESC";

        keyword = "%" + keyword + "%";
        int totalCount = recordDao.getRepliedRequestBuyRecordCount(keyword);
        int start = (page - 1) * count;
        int offset = count;
        int maxPage = (int) Math.ceil(totalCount * 1.0 / count);

        if (page > maxPage) {
            page = 1;
        }

        List<Integer> pageIndexList = PaginationUtil.makePageIndexList(page, count, maxPage);

        pagination.setCurrentPage(page);
        pagination.setResults(recordDao.getRepliedRequestBuyRecordPagination(keyword, start, offset, orderBy));
        pagination.setTotalCount(count);
        pagination.setTotalPage(maxPage);
        pagination.setPagesList(pageIndexList);
        pagination.setOrderBy(order);

        return pagination;
    }

    @Override
    public RequestBuyRecord findRequestBuyRecordById(String id) throws RecordException {

        int recordId = 0;

        try {
            recordId = Integer.parseInt(id);
        } catch (NumberFormatException numberFormatException) {

            throw new RecordException(Constants.ErrorCode.ERROR_PARAMETER_ILLEGAL, numberFormatException.getMessage());
        }

        RequestBuyRecord requestBuyRecord = recordDao.findRequestBuyRecordById(recordId);

        return requestBuyRecord;
    }

    @Override
    public int approveRequestBuyRecord(String id, String operation, User user) throws RecordException, EquipmentException {

        int recordId = -1;
        int recordOperation = -2;

        try {
            recordId = Integer.parseInt(id);

            if (recordId <= 0) {
                throw new RecordException(Constants.ErrorCode.ERROR_PARAMETER_ILLEGAL, "参数异常");
            }

            recordOperation = Integer.parseInt(operation);
        } catch (NumberFormatException numberFormatException) {
            throw new RecordException(Constants.ErrorCode.ERROR_PARAMETER_ILLEGAL, numberFormatException.getMessage());
        }

        if (recordOperation > 1 || recordOperation < -1) {
            throw new RecordException(Constants.ErrorCode.ERROR_PARAMETER_ILLEGAL, "参数错误");
        }

        int operationCount = recordDao.updateRequestBuyRecordAllowedById(recordId, recordOperation, user.getId());

        RequestBuyRecord requestBuyRecord = recordDao.findRepliedBuyRecordById(recordId);

        List<RequestEquipment> equipments = requestBuyRecord.getEquipments();

        for (RequestEquipment requestEquipment : equipments) {
            for (int i = 0; i < requestEquipment.getCount(); i++) {
                Equipment equipment = new Equipment();
                equipment.setName(requestEquipment.getName());
                equipment.setLabId(requestEquipment.getLabId());
                equipment.setOrigin(requestEquipment.getOrigin());
                equipment.setPrice(requestEquipment.getPrice());
                equipment.setRequestBuyRecordId(requestEquipment.getRequestBuyRecordId());
                equipmentService.insertEquipmentByEquipment(equipment, user);
            }
        }

        if (operationCount == 0) {
            throw new RecordException(Constants.ErrorCode.ERROR_RESOURCE_NOT_FOUND, "找不到对应的记录");
        }

        return operationCount;
    }

    @Override
    public RequestBuyRecord findRepliedBuyRecordById(String id) throws RecordException {

        int recordId = 0;

        try {
            recordId = Integer.parseInt(id);
        } catch (NumberFormatException numberFormatException) {

            throw new RecordException(Constants.ErrorCode.ERROR_PARAMETER_ILLEGAL, numberFormatException.getMessage());
        }

        RequestBuyRecord requestBuyRecord = recordDao.findRepliedBuyRecordById(recordId);

        return requestBuyRecord;
    }
}
