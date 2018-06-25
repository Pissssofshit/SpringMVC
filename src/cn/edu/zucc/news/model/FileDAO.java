package cn.edu.zucc.news.model;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class FileDAO {
    File file;
    public File getFlieByPaperid(int paperid){
        Session session=HibernateUtil.getSession2();
        String hql = "from File where  paperid= ?";

        Query query = session.createQuery(hql).setParameter(0, paperid);

        List<File> files = query.list();

        session.close();   //7.关闭session
        if(files.size()!=0){
            return files.get(0);
        }else
            return null;
    }
}
