package cn.edu.zucc.news.model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
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
    public static <T> int save(T object){
//        System.out.println("ss");
        Session session=HibernateUtil.getSession2();
        Transaction tx=null;
//        Serializable serializableid=null;
        int id=0;
        try {
            tx=session.beginTransaction();    //4.开始一个事务
            id=(Integer)session.save(object);      //5.持久化操作
            tx.commit();    //6.提交事务
        } catch (Exception e) {
            if(tx!=null){
                tx.rollback();  //事务回滚
            }
            e.printStackTrace();
        }finally{
            session.close();   //7.关闭session
        }
        return id;
    }

    public static <T> void update(T object){
        Session session=HibernateUtil.getSession2();
        Transaction tx=null;
//        Paper paper=(Paper)session.get(Paper.class, checkPaper.getPaperid());// ？为什么要这样？这样可以吗？　我觉得我还是换一个比较保险．．．
//        PaperType paperType=new PaperType();
//        paperType.setTypeid(typeid);
//        paperType.setType(newname);
//        paper.setPaperid(checkPaper.getPaperid());
//        paper.setState(checkPaper.getDecide());
//        paper.setEditorid(checkPaper.getEditorid());
        try {
            tx=session.beginTransaction();    //4.开始一个事务
            session.update(object);      //5.持久化操作
            tx.commit();    //6.提交事务
        } catch (Exception e) {
            System.out.println(e);
            if(tx!=null){
                tx.rollback();  //事务回滚
            }
            e.printStackTrace();
        }finally{
            session.close();   //7.关闭session
        }
    }
    public static void test(){

    }
    public static void main(String[] args){

    }
}