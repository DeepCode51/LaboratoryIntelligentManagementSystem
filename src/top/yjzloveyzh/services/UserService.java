package top.yjzloveyzh.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import top.yjzloveyzh.common.exception.UserException;
import top.yjzloveyzh.common.pojo.User;

public interface UserService {

    public User login(User user, String rememberMe, HttpServletResponse response) throws UserException;

    public void register(User user) throws UserException;

    public User getUserById(int id);

    public int updateUser(int id, String gender, String name, String province,
            String city, String area, String cellphone, String zipCode, String address, String email) throws UserException;

    public User loginByCookie(HttpServletRequest request, HttpServletResponse response);

    public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response);
}