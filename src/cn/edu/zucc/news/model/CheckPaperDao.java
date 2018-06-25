package cn.edu.zucc.news.model;

import javax.servlet.http.HttpServletRequest;

public class CheckPaperDao {
    public CheckPaper CreateByRequest(HttpServletRequest httpServletRequest){
       String decide=httpServletRequest.getParameter("decide");
       int paperid=Integer.parseInt(httpServletRequest.getParameter("paperid"));
       String advice= httpServletRequest.getParameter("advice");
       int userid= (Integer)httpServletRequest.getSession().getAttribute("userid");//然而我已经忘记另一边是怎么设置session的了，要过去看好麻烦，，，
        CheckPaper checkPaper=new CheckPaper();
        checkPaper.setDecide(decide);
        checkPaper.setEditorid(userid);
        checkPaper.setPaperid(paperid);
        checkPaper.setAdvide(advice);
        return checkPaper;
    }
    public void save(CheckPaper checkPaper){
        int checkpapaerid=HibernateUtil.save(checkPaper);

    }

}
