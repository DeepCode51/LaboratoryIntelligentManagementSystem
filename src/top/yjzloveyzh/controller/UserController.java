package top.yjzloveyzh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import top.yjzloveyzh.common.Constants;
import top.yjzloveyzh.common.exception.UserException;
import top.yjzloveyzh.common.pojo.User;
import top.yjzloveyzh.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier(value = "userServiceImpl")
    UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        if (session.getAttribute(Constants.User.SESSION_USER_KEY) != null) {

            return "redirect:/lab/toContent";
        } else {
            User user = userService.loginByCookie(request, response);

            if (user != null) {
                user.setPassword("");
                session.setAttribute(Constants.User.SESSION_USER_KEY, user);

                return "redirect:/lab/toContent";
            }
        }

        return Constants.UserControllerUrl.USER_CONTROLLER_LOGIN_PAGE_URL;
    }

    @RequestMapping("/register")
    public String register(User user, HttpServletResponse response, HttpSession session) {
        PrintWriter out = null;

        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (out == null) {

            return null;
        }

        if (user == null) {
            out.println("注册失败,请重新注册");
        } else {

            try {
                userService.register(user);
                session.setAttribute(Constants.User.SESSION_USER_KEY, user);

                return "redirect:toContent";
            } catch (UserException e) {
                out.println("注册失败,请重新注册");
            }
        }

        return null;
    }

    @RequestMapping("/login")
    public String login(User labUser, String rememberMe, HttpSession session, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String errorMessage = "";

        try {
            User user = userService.login(labUser, rememberMe, response);
            user = userService.getUserById(user.getId());
            user.setPassword("");
            session.setAttribute(Constants.User.SESSION_USER_KEY, user);

            return "redirect:/lab/toContent";
        } catch (UserException userException) {
            errorMessage = userException.getMessage();
            redirectAttributes.addFlashAttribute("error", errorMessage);

            return "redirect:toLogin";
        }
    }

    @RequestMapping("/toUserInfo")
    public String toUserInfo(HttpSession session, HttpServletRequest request) {

        User user = (User) session.getAttribute(Constants.User.SESSION_USER_KEY);

        if (user != null) {
            User userInfo = userService.getUserById(user.getId());
            userInfo.setPassword("");
            request.setAttribute(Constants.User.USER_INFO, userInfo);
            System.out.println(userInfo);
        }

        return "user/userInfo";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute(Constants.User.SESSION_USER_KEY);

        if (user != null) {
            userService.logout(session, request, response);
            redirectAttributes.addFlashAttribute("error", "登出成功。");

            return "redirect:/user/toLogin";
        } else {
            redirectAttributes.addFlashAttribute("error", "您还未登陆，请登录。");

            return "redirect:/user/toLogin";
        }
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public String edit(HttpSession session, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        User user = (User) session.getAttribute(Constants.User.SESSION_USER_KEY);

        if (user != null) {
            int id = user.getId();
            String gender = request.getParameter("gender");
            String name = request.getParameter("name");
            String province = request.getParameter("province");
            String city = request.getParameter("city");
            String area = request.getParameter("area");
            String cellphone = request.getParameter("cellphone");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String zipCode = request.getParameter("zip");

            try {
                int count = userService.updateUser(id, gender, name, province, city, area, cellphone,zipCode, address, email);

                if (count > 0) {
                    redirectAttributes.addFlashAttribute("success", "修改成功。");

                    return "redirect:/user/toUserInfo";
                } else {
                    redirectAttributes.addFlashAttribute("error", "修改失败。");

                    return "redirect:/user/toUserInfo";
                }
            } catch (UserException userException) {
                redirectAttributes.addFlashAttribute("error", userException.getMessage());

                return "redirect:/user/toUserInfo";
            }

        } else {
            redirectAttributes.addFlashAttribute("error", "您还未登录，请先登录。");

            return "redirect:/user/toUserInfo";
        }
    }
}
