package top.yjzloveyzh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.yjzloveyzh.common.pojo.Equipment;
import top.yjzloveyzh.common.pojo.RequestEquipment;
import top.yjzloveyzh.dao.EquipmentDao;
import top.yjzloveyzh.dao.MySqlSessionDaoSupport;

@Repository(value="equipmentDaoImpl")
public class EquipmentDaoImpl extends MySqlSessionDaoSupport implements EquipmentDao{

    @Override
    public int insertRequestEquipment(RequestEquipment requestEquipment) {
        int id = getSqlSession().getMapper(EquipmentDao.class).insertRequestEquipment(requestEquipment);

        return id;
    }

    @Override
    public List<Equipment> getByPagination(String keyword, int start, int offset, int labId, String orderBy) {

        return getSqlSession().getMapper(EquipmentDao.class).getByPagination(keyword, start, offset, labId, orderBy);
    }

    @Override
    public int getCountBySearchWord(String keyword, int labId) {

        return getSqlSession().getMapper(EquipmentDao.class).getCountBySearchWord(keyword, labId);
    }

    @Override
    public int insertEquipment(Equipment equipment) {

        return getSqlSession().getMapper(EquipmentDao.class).insertEquipment(equipment);
    }


    @Override
    public int deleteEquipmentByIdArray(int[] idArray, int deleteRecordId) {

        return getSqlSession().getMapper(EquipmentDao.class).deleteEquipmentByIdArray(idArray, deleteRecordId);
    }
}
