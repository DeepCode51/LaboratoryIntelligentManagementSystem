package top.yjzloveyzh.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import top.yjzloveyzh.common.pojo.Lab;
import top.yjzloveyzh.common.pojo.LabEquipmentCount;
import top.yjzloveyzh.common.pojo.User;
import top.yjzloveyzh.dao.LabDao;
import top.yjzloveyzh.services.LabService;

@Service(value = "labServiceImpl")
public class LabServiceImpl implements LabService {

    @Qualifier(value = "labDaoImpl")
    @Autowired
    LabDao labDao;

    @Override
    public List<Lab> getLabs() {
        List<Lab> labs = labDao.getLab();

        return labs;
    }

    @Override
    public Map<String, Object> statisticsById(User user, String labId) {

        if (labId == null) {
            labId = new String();
        }

        int id = 0;

        try {
            id = Integer.parseInt(labId);
        } catch (NumberFormatException numberFormatException) {
        }

        List<LabEquipmentCount> statisticsInfo = labDao.getStatisticsInfo(id);
        Map<String, Object> labInfo = new HashMap<String, Object>();
        int totalCount = labDao.getLabEquipmentTotalCount(id);
        labInfo.put("totalCount", totalCount);

        if (statisticsInfo == null) {

            return null;
        } else {
            List<String> equipmentNameList = new ArrayList<String>();
            List<String> equipmentCountList = new ArrayList<String>();

            for (LabEquipmentCount labEquipmentCount : statisticsInfo) {
                equipmentNameList.add("\'" + labEquipmentCount.getEquipmentName() + "\'");
                equipmentCountList.add(String.valueOf(labEquipmentCount.getCount()));
                totalCount -= labEquipmentCount.getCount();
            }

            if (statisticsInfo.size() >= 9 ) {
                equipmentNameList.add("\'其他\'");
                LabEquipmentCount qita = new LabEquipmentCount();
                qita.setCount(totalCount);
                qita.setEquipmentName("其他");
                statisticsInfo.add(qita);
            }

            labInfo.put("names", equipmentNameList.toArray());
            labInfo.put("counts", statisticsInfo.toArray());
            labInfo.put("labId", id);

            Lab lab = labDao.getLabInfoById(id);
            List<Lab> labList = labDao.getLab();
            labInfo.put("lab", lab);
            labInfo.put("labs", labList);

            return labInfo;
        }
    }

}
