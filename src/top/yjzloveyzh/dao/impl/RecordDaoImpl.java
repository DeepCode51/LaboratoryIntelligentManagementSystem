package top.yjzloveyzh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.yjzloveyzh.common.pojo.RequestBuyRecord;
import top.yjzloveyzh.dao.MySqlSessionDaoSupport;
import top.yjzloveyzh.dao.RecordDao;

@Repository(value="recordDaoImpl")
public class RecordDaoImpl extends MySqlSessionDaoSupport implements RecordDao {

    @Override
    public int insertRequestRecordByRecord(RequestBuyRecord record) {

        return getSqlSession().getMapper(RecordDao.class).insertRequestRecordByRecord(record);
    }

    @Override
    public List<RequestBuyRecord> getRequestBuyRecordPagination(String keyword, int start, int offset, String orderBy, int requestUserId) {

        return getSqlSession().getMapper(RecordDao.class).getRequestBuyRecordPagination(keyword, start, offset, orderBy, requestUserId);
    }

    @Override
    public int getRequestBuyRecordCount(String keyword, int requestUserId) {

        return getSqlSession().getMapper(RecordDao.class).getRequestBuyRecordCount(keyword, requestUserId);
    }

    @Override
    public List<RequestBuyRecord> getNotReplyRequestBuyRecordPagination(String keyword, int start, int offset, String orderBy) {

        return getSqlSession().getMapper(RecordDao.class).getNotReplyRequestBuyRecordPagination(keyword, start, offset, orderBy);
    }

    @Override
    public int getNotReplyAllRequestBuyRecordCount(String keyword) {

        return getSqlSession().getMapper(RecordDao.class).getNotReplyAllRequestBuyRecordCount(keyword);
    }

    @Override
    public int getRepliedRequestBuyRecordCount(String keyword) {

        return getSqlSession().getMapper(RecordDao.class).getRepliedRequestBuyRecordCount(keyword);
    }

    @Override
    public RequestBuyRecord findRequestBuyRecordById(int id) {

        return getSqlSession().getMapper(RecordDao.class).findRequestBuyRecordById(id);
    }

    @Override
    public int updateRequestBuyRecordAllowedById(int id, int opearation, int allowUserId) {

        return getSqlSession().getMapper(RecordDao.class).updateRequestBuyRecordAllowedById(id, opearation, allowUserId);
    }

    @Override
    public List<RequestBuyRecord> getRepliedRequestBuyRecordPagination(String keyword, int start, int offset, String orderBy) {

        return getSqlSession().getMapper(RecordDao.class).getRepliedRequestBuyRecordPagination(keyword, start, offset, orderBy);
    }

    @Override
    public RequestBuyRecord findRepliedBuyRecordById(int id) {

        return getSqlSession().getMapper(RecordDao.class).findRepliedBuyRecordById(id);
    }
}
