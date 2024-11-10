package top.yjzloveyzh.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import top.yjzloveyzh.common.Constants;
import top.yjzloveyzh.common.exception.RecordException;
import top.yjzloveyzh.common.pojo.DeletedRecord;
import top.yjzloveyzh.common.pojo.Pagination;
import top.yjzloveyzh.common.pojo.User;
import top.yjzloveyzh.common.utils.PaginationUtil;
import top.yjzloveyzh.common.utils.PropertyUtil;
import top.yjzloveyzh.dao.DeletedRecordDao;
import top.yjzloveyzh.services.DeletedRecordService;

@Service(value="deletedRecordServiceImpl")
public class DeletedRecordServiceImpl implements DeletedRecordService {

    @Qualifier(value="deletedRecordDaoImpl")
    @Autowired
    DeletedRecordDao deletedRecordDao;

    @Override
    public Pagination<DeletedRecord> getPaginationDeletedRecord(User user, String keyword, String currentPage, String orderBy) throws RecordException {

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


        Pagination<DeletedRecord> pagination = new Pagination<DeletedRecord>();
        pagination.setSearchKeyWord(keyword);
        orderBy = order == 0 ? "ASC" : "DESC";

        keyword = "%" + keyword + "%";
        int totalCount = deletedRecordDao.getDeletedRecordCount(keyword, user.getId());
        int start = (page - 1) * count;
        int offset = count;
        int maxPage = (int) Math.ceil(totalCount * 1.0 / count);

        if (page > maxPage) {
            page = 1;
        }
        List<Integer> pageIndexList = PaginationUtil.makePageIndexList(page, count, maxPage);

        pagination.setCurrentPage(page);
        pagination.setResults(deletedRecordDao.getDeletedRecordPagination(keyword, start, offset, orderBy, user.getId()));
        pagination.setTotalCount(count);
        pagination.setTotalPage(maxPage);
        pagination.setPagesList(pageIndexList);
        pagination.setOrderBy(order);

        return pagination;
    }
}
