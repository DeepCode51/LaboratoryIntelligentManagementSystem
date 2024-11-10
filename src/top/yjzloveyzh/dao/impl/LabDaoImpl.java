package top.yjzloveyzh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.yjzloveyzh.common.pojo.Lab;
import top.yjzloveyzh.common.pojo.LabEquipmentCount;
import top.yjzloveyzh.dao.LabDao;
import top.yjzloveyzh.dao.MySqlSessionDaoSupport;

@Repository(value = "labDaoImpl")
public class LabDaoImpl extends MySqlSessionDaoSupport implements LabDao{

    @Override
    public List<Lab> getLab() {
        return getSqlSession().getMapper(LabDao.class).getLab();
    }

    @Override
    public List<LabEquipmentCount> getStatisticsInfo(int labId) {

        return getSqlSession().getMapper(LabDao.class).getStatisticsInfo(labId);
    }

    @Override
    public int getLabEquipmentTotalCount(int labId) {

        return getSqlSession().getMapper(LabDao.class).getLabEquipmentTotalCount(labId);
    }

    @Override
    public Lab getLabInfoById(int labId) {

        return getSqlSession().getMapper(LabDao.class).getLabInfoById(labId);
    }
}
