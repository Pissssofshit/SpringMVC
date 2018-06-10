package cn.edu.zucc.news.model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateUtil {
    private static SessionFactory sessionFactory
            = new Configuration().configure().buildSessionFactory();
    public static Session getSession(){
        Session session = sessionFactory.openSession();
        return session;
    }
    public static Session getSession2(){
        Configuration config=new Configuration().configure();
        //读取并解析映射文件(Users.hbm.xml)，创建sessionFactory
        SessionFactory sessionFactory=config.buildSessionFactory();
        //打开session
        Session session=sessionFactory.openSession();
        return session;
    }
    public static void test(){
        Session session=getSession();
        List<NewsType> pubs
                =session.createQuery("from News").list();
        System.out.println(pubs.size());
    }
    public static void main(String[] args){
        Session session=getSession();
        List<NewsType> pubs
                =session.createQuery("from News").list();
        System.out.println(pubs.size());
    }
}