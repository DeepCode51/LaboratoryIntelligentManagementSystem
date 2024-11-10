package top.yjzloveyzh.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository(value = "mySqlSessionFactory")
public class MySqlSessionDaoSupport extends SqlSessionDaoSupport {

    @Resource(name="sqlSessionFactoryBean")
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}