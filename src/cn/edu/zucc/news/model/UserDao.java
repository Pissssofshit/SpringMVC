package cn.edu.zucc.news.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserDao {
    public synchronized int register(User user) throws Exception {

        Session session=HibernateUtil.getSession2();
        String hql = "from User where  username= ?";

        Query query = session.createQuery(hql).setParameter(0,user.getUsername());

        List<User> user1=query.list();
        session.close();
        if(user1.size()!=0){
            return -1;
        }else{
           HibernateUtil.save(user);
            return 1;
        }

    }
    //无奈　如果帐号密码正确就返回该用户的用户ｉd 　所以有时候也用来在知道帐号密码的时候查询用户id....
    public synchronized int checkUser(User user) throws Exception {

        Session session=HibernateUtil.getSession2();
        String hql = "from User where  username= ?";

        Query query = session.createQuery(hql).setParameter(0,user.getUsername());

        List<User> user1=query.list();
        session.close();
        if(user1.size()==0){
            return -1;
        }else{
            if(user1.get(0).getPassword().equals(user.getPassword())){
                if(!user1.get(0).getUsertype().equals(user.getUsertype()))
                    return -3;
                return user1.get(0).getUserid();
            }else{
                return -2;
            }


        }

    }
}
