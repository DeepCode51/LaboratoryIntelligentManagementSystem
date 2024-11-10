package top.yjzloveyzh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.yjzloveyzh.common.pojo.DeletedRecord;

public interface DeletedRecordDao {

    public int insertDeletedRecordByRecord(DeletedRecord record);

    public int getDeletedRecordCount(@Param("keyword") String keyword, @Param("requestUserId") int requestUserId);

    public List<DeletedRecord> getDeletedRecordPagination(
        @Param("keyword") String keyword,
        @Param("start") int start,
        @Param("offset") int offset,
        @Param("orderBy") String orderBy,
        @Param("requestUserId") int requestUserId
    );
}
