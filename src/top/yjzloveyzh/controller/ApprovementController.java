package top.yjzloveyzh.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import top.yjzloveyzh.common.Constants;
import top.yjzloveyzh.common.exception.EquipmentException;
import top.yjzloveyzh.common.exception.RecordException;
import top.yjzloveyzh.common.pojo.Pagination;
import top.yjzloveyzh.common.pojo.RequestBuyRecord;
import top.yjzloveyzh.common.pojo.User;
import top.yjzloveyzh.services.RecordService;

@Controller
@RequestMapping(value = "/approvement")
public class ApprovementController {

    @Qualifier(value = "recordServiceImpl")
    @Autowired
    RecordService recordService;

    @RequestMapping(value = "/m/buy-record")
    public String toApprovementManagement(String searchKeyWord, String page, String orderBy, HttpSession session, HttpServletRequest request) {

        User user = (User) session.getAttribute(Constants.User.SESSION_USER_KEY);

        try {
            Pagination<RequestBuyRecord> pagination = recordService.getNotReplyRequestBuyRecord(user, searchKeyWord, page, orderBy);
            request.setAttribute("pagination", pagination);
        } catch (RecordException recordException) {

            if (recordException.getErrorCode().equals(Constants.ErrorCode.ERROR_USER_NOT_EXIST)) {

                return "redirect:/user/toLogin";
            }
        }

        return "approvement/approve_buy_record";
    }

    @RequestMapping(value = "/m/buy-record/{id}")
    public String toApprovementBuyRecordDetail(@PathVariable("id") String id, HttpServletRequest request) {

        try {
            RequestBuyRecord requestBuyRecord = recordService.findRequestBuyRecordById(id);

            if (requestBuyRecord == null
                || requestBuyRecord.getEquipments() == null
                || requestBuyRecord.getEquipments().size() == 0) {

                return "redirect:/approvement/buy-record";
            }

            request.setAttribute("record", requestBuyRecord);

            return "approvement/approve_buy_record_detail";
        } catch (RecordException recordException) {

            return "redirect:/approvement/buy-record";
        }
    }

    @RequestMapping(value = "/r/buy-record/{id}")
    public String toRecordApprovementBuyRecordDetail(@PathVariable("id") String id, HttpServletRequest request) {

        try {
            RequestBuyRecord requestBuyRecord = recordService.findRepliedBuyRecordById(id);

            if (requestBuyRecord == null
                    || requestBuyRecord.getEquipments() == null
                    || requestBuyRecord.getEquipments().size() == 0) {

                return "redirect:/approvement/buy-record";
            }

            request.setAttribute("record", requestBuyRecord);

            return "approvement/applied_buy_record_detail";
        } catch (RecordException recordException) {

            return "redirect:/approvement/buy-record";
        }
    }

    @RequestMapping(value="/m/buy-record/approve", method={RequestMethod.POST})
    public @ResponseBody Map<String, String> toApproveBuyRecord(String id, String operation, HttpServletRequest request, HttpSession session) {

        Map<String, String> result = new HashMap<String, String>();
        User user = (User) session.getAttribute(Constants.User.SESSION_USER_KEY);

        try {
            recordService.approveRequestBuyRecord(id, operation, user);
            result.put("code", Constants.ErrorCode.SUCCESS);
        } catch (RecordException recordException) {
            result.put("code", recordException.getErrorCode());
        } catch (EquipmentException equipmentException) {
            result.put("code", equipmentException.getErrorCode());
        }

        return result;
    }

    @RequestMapping(value = "/r/buy-record")
    public String toApprovementRecordManagement(String searchKeyWord, String page, String orderBy, HttpSession session, HttpServletRequest request) {

        User user = (User) session.getAttribute(Constants.User.SESSION_USER_KEY);

        try {
            Pagination<RequestBuyRecord> pagination = recordService.getRepliedRequestBuyRecord(user, searchKeyWord, page, orderBy);
            request.setAttribute("pagination", pagination);
        } catch (RecordException recordException) {

            if (recordException.getErrorCode().equals(Constants.ErrorCode.ERROR_USER_NOT_EXIST)) {

                return "redirect:/user/toLogin";
            }
        }

        return "approvement/applied_buy_record";
    }
}
