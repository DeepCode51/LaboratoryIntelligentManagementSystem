package top.yjzloveyzh.dao;

import org.apache.ibatis.annotations.Param;

import top.yjzloveyzh.common.pojo.User;

public interface LabUserDao {

    public long insertLabUser(User user);

    public User findUserByUserName(String username);

    public User findUserById(int id);

    public int editByUser(User user);

    public int updateUserToken(@Param("id") int id, @Param("token") String token);

    public String getUserToken(int id);

    public String getTokenByUsername(String username);
}