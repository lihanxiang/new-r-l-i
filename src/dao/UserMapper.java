package dao;

import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserMapper{


    private SqlSession init() throws IOException{
        String resource = "config/sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();

    }

    public void add(User user){
        try{
            SqlSession sqlSession = init();
            sqlSession.insert("UserMapper.insert", user);
            sqlSession.commit();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public User findUser(String username){
        User user = new User();
        try{
            SqlSession sqlSession = init();
            user = sqlSession.selectOne("UserMapper.select", username);
            sqlSession.commit();
        } catch (IOException e){
            e.printStackTrace();
        }
        return user;
    }

    @Test
    public void test(){
        User user = findUser("1");
        System.out.println(user.getPassword());
    }
}
