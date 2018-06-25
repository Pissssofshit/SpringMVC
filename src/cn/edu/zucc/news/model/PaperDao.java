package cn.edu.zucc.news.model;

import org.aspectj.weaver.ast.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import sun.security.krb5.internal.PAData;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaperDao {

    public synchronized List<Paper> searchPaper(String time,String keyword){
        List<Paper> papers=this.searchPaperbytime(time);
        for(int i=0;i<papers.size();i++){
            if(papers.get(i).getTitle().indexOf(keyword)<0){
                papers.remove(i);
                i--;
            }
        }
        return  papers;
    }
    public synchronized  List<Paper> searchPaperbytime(String time){

        Session session=HibernateUtil.getSession2();
        // hibernate hql不是跟表相关的，是操作类的，所以mysql 的函数当然没用
//        String hql="from Paper where DATE_SUB(CURDATE(),INTERVAL 1 DAY) <= updatetime";

        String str1=null;
        if(time.equals("week")){
            str1=TestDate.getTheWeek();
        }else if(time.equals("month")){
            str1=TestDate.getTheMonth();
        }else if(time.equals("today")){
            str1=TestDate.gettodayday();
        }else{
            str1="1997-01-22";//反正系统里的数据不会早于那一天的
        }
        /*
        第一　搞清楚怎么获得的月初的日期（周　日　同理）

         */
        String str2=TestDate.gettodayday();
        Date begindate = java.sql.Date.valueOf(str1);
        Date enddate = java.sql.Date.valueOf(str2);
        System.out.println("begindate"+begindate);
        System.out.println("enddate"+enddate);
        String hql="from Paper  where date>=:begindate and date <=:enddate";
        Query query = session.createQuery(hql);
        query.setDate("begindate",begindate);
        query.setParameter("enddate",enddate);
        List<Paper> papers = query.list();
        return papers;
    }
    public synchronized void uploadPaper(Paper paper, Blob blob) throws Exception {

        Session session=HibernateUtil.getSession2();
        File file=new File();
        java.util.Date  date=new java.util.Date();

        java.sql.Date  data1=new java.sql.Date(date.getTime());
//        System.out.println("data1="+data1);
        paper.setDate(data1);
        Transaction tx=null;
        try {
            tx=session.beginTransaction();    //4.开始一个事务
            Serializable result=session.save(paper);      //5.持久化操作
//            paper.setPaperid();


            file.setPaperid((Integer) result);
//            System.out.println("pa"+paper.getPaperid());
            file.setFile(blob);
//            file.setPaperid(paper.getPaperid());
            session.save(file);
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
//        Paper paper=(Paper)session.get(Paper.class, checkPaper.getPaperid());// ？为什么要这样？这样可以吗？　我觉得我还是换一个比较保险．．．
        Paper paper=new PaperDao().showPaperBypid(checkPaper.getPaperid());
        paper.setPaperid(checkPaper.getPaperid());
        paper.setState(checkPaper.getDecide());
        paper.setEditorid(checkPaper.getEditorid());
        HibernateUtil.update(paper);

    }
    //删除一个上传这的论文
    //这spring 好只能啊，尽然连字段便了都能检测出来，掉的不行
    public synchronized  void delUppaper(int uploaderid){
        Session session=HibernateUtil.getSession2();
        String hql = "from Paper where uploaderid = ?";
        Query query = session.createQuery(hql).setParameter(0,uploaderid);

        List<Paper> papers = query.list();
        for(Paper paper : papers){
            System.out.println("将要删除的paperid是"+paper.getPaperid());
            deletePaper(paper);
        }
        session.close();
    }
    //删除一片论文，似乎传一个paperid就可以了，这里用的session.delete应该使用一个只包含了paperid的对象就可以了
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
    //返回一个上传这的所有上传论文的数据
    public synchronized List<Paper> showPaper(int uploaderid){
        Session session=HibernateUtil.getSession();
        String hql = "from Paper where uploaderid = ?";

        Query query = session.createQuery(hql).setParameter(0,uploaderid);

        List<Paper> papers = query.list();

            session.close();   //7.关闭session
        return papers;
    }
    //根据论文id返回一个论文对象
    public synchronized List<Paper> showPaperBystate(String state){
        Session session=HibernateUtil.getSession2();
        List<Paper> paper=null;
        if(state.equals("uncheck")){
            String hql = "from Paper where state = ?";

            Query query = session.createQuery(hql).setParameter(0,state);

            paper=query.list();//query有没有其他方法，都是list吗，获取单个对象有点尴尬

            session.close();   //7.关闭session
        }else{
            String hql = "from Paper where state != ?";

            Query query = session.createQuery(hql).setParameter(0,"uncheck");

            paper=query.list();//query有没有其他方法，都是list吗，获取单个对象有点尴尬

            session.close();   //7.关闭session
        }

        return paper;
    }
    //根据论文id返回一个论文对象
    public synchronized Paper showPaperBypid(int paperid){
        Session session=HibernateUtil.getSession();
        String hql = "from Paper where paperid = ?";

        Query query = session.createQuery(hql).setParameter(0,paperid);

        List<Paper> paper=query.list();//query有没有其他方法，都是list吗，获取单个对象有点尴尬

        session.close();   //7.关闭session
        return paper.get(0);
    }
}
