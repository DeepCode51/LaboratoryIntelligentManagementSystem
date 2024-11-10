package top.yjzloveyzh.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.yjzloveyzh.common.Constants;
import top.yjzloveyzh.common.pojo.User;
import top.yjzloveyzh.services.LabService;
import top.yjzloveyzh.services.UserService;

@Controller
@RequestMapping("/lab")
public class LabController {

    @Qualifier(value = "userServiceImpl")
    @Autowired
    UserService userService;

    @Qualifier(value = "labServiceImpl")
    @Autowired
    LabService labService;

    @RequestMapping("/toContent")
    public String toContent(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        User user = userService.loginByCookie(request, response);

        if (user != null) {
            user.setPassword("");
            session.setAttribute(Constants.User.SESSION_USER_KEY, user);
        }

        return "lab/content";
    }

    @RequestMapping("/info")
    public String toInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response, String labId) {

        User user = (User) session.getAttribute(Constants.User.SESSION_USER_KEY);

        Map<String, Object> labInfo = labService.statisticsById(user, labId);

        request.setAttribute("labInfo", labInfo);

        return "lab/lab_info";
    }

    @RequestMapping("/ajax/info")
    public @ResponseBody Map<String, Object> toLabInfoAjax(HttpSession session, HttpServletRequest request, HttpServletResponse response, String labId) {

        User user = (User) session.getAttribute(Constants.User.SESSION_USER_KEY);

        Map<String, Object> labInfo = labService.statisticsById(user, labId);

        return labInfo;
    }
}