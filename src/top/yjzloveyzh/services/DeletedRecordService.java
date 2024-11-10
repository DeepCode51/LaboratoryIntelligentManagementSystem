package top.yjzloveyzh.services;

import top.yjzloveyzh.common.exception.RecordException;
import top.yjzloveyzh.common.pojo.DeletedRecord;
import top.yjzloveyzh.common.pojo.Pagination;
import top.yjzloveyzh.common.pojo.User;

public interface DeletedRecordService {

    /**
     * @param user
     * @param keyword
     * @param currentPage
     * @param orderBy
     * @return
     * @throws RecordException
     */
    Pagination<DeletedRecord> getPaginationDeletedRecord(User user, String keyword, String currentPage, String orderBy) throws RecordException;

}
