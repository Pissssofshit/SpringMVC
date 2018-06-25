package cn.edu.zucc.news.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class PaperTypeDao {
    PaperType paperType;
    public void updateTypename(int typeid,String newname){
        Session session=HibernateUtil.getSession2();
        Transaction tx=null;
//        Paper paper=(Paper)session.get(Paper.class, checkPaper.getPaperid());// ？为什么要这样？这样可以吗？　我觉得我还是换一个比较保险．．．
        PaperType paperType=new PaperType();
        paperType.setTypeid(typeid);
        paperType.setType(newname);
       HibernateUtil.update(paperType);

    }
    public List<PaperType> getAllpapertye(){
        Session session=HibernateUtil.getSession2();
        String hql = "from PaperType";

        Query query = session.createQuery(hql);

        List<PaperType> paperTypes=query.list();
        session.close();
        return paperTypes;

    }
    public StatusObj addPapertype(PaperType paperType){
        StatusObj statusObj=new StatusObj();
        PaperTypeDao paperTypeDao=new PaperTypeDao();
        List<PaperType> paperTypes=paperTypeDao.getAllpapertye();
        for(int i=0;i<paperTypes.size();i++){
            if((paperTypes.get(i).getType()!=null)&&(paperTypes.get(i).getType().equals(paperType.getType()))){ //这里出错的原因是数据库的表中莫名奇妙多了一条空数据，　再遇到这种情况该怎么办呢？每次都要检查是是否为空吗？好像也可以
                statusObj.status=StatusObj.statusenum.error;
                statusObj.msg="已经有同名类型存在！";
                return statusObj;
            }
        }
        HibernateUtil.save(paperType);
        statusObj.status=StatusObj.statusenum.sucess;
        return statusObj;
    }

}
