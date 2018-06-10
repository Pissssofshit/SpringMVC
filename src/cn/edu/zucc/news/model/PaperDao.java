package cn.edu.zucc.news.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import sun.security.krb5.internal.PAData;

import java.sql.SQLException;
import java.util.List;

public class PaperDao {

    public synchronized void uploadPaper(Paper paper) throws Exception {

        Session session=HibernateUtil.getSession2();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();    //4.开始一个事务
            session.save(paper);      //5.持久化操作
            tx.commit();    //6.提交事务
        } catch (Exception e) {
            if(tx!=null){
                tx.rollback();  //事务回滚
            }
            e.printStackTrace();
        }finally{
            session.close();   //7.关闭session
        }
    }
    public synchronized  void changePaperState(CheckPaper checkPaper){
        Session session=HibernateUtil.getSession2();
        Transaction tx=null;
        Paper paper=(Paper)session.get(Paper.class, checkPaper.getPaperid());
        paper.setState(checkPaper.getDecide());
        try {
            tx=session.beginTransaction();    //4.开始一个事务
            session.update(paper);      //5.持久化操作
            tx.commit();    //6.提交事务
        } catch (Exception e) {
            if(tx!=null){
                tx.rollback();  //事务回滚
            }
            e.printStackTrace();
        }finally{
            session.close();   //7.关闭session
        }

    }
    public synchronized  void delAupaper(String authorid){
        Session session=HibernateUtil.getSession2();
        String hql = "from Paper where authorid = ?";
        Query query = session.createQuery(hql).setParameter(0,authorid);

        List<Paper> papers = query.list();
        for(Paper paper : papers){
            System.out.println("将要删除的paperid是"+paper.getPaperid());
            deletePaper(paper);
        }
        session.close();
    }
    public synchronized  void deletePaper(Paper paper){
        Session session=HibernateUtil.getSession2();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();    //4.开始一个事务
            session.delete(paper);      //5.持久化操作
            tx.commit();    //6.提交事务
        } catch (Exception e) {
            if(tx!=null){
                tx.rollback();  //事务回滚
            }
            e.printStackTrace();
        }finally{
            session.close();   //7.关闭session
        }

    }
    public synchronized List<Paper> showPaper(String authorid){
        Session session=HibernateUtil.getSession();
        String hql = "from Paper where authorid = ?";

        Query query = session.createQuery(hql).setParameter(0,authorid);

        List<Paper> papers = query.list();

            session.close();   //7.关闭session
        return papers;
    }
}
