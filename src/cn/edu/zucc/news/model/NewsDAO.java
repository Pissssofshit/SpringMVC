/*
 * Created on 2007-4-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cn.edu.zucc.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NewsDAO {
    private Connection conn = null;

    private Statement st = null;

    private Statement st2 = null;

    public NewsDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/j2ee_news?user=root&password=123456"
                            );
            st = conn.createStatement();
            st2 = conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void release() {
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
            }
        }
        if (st2 != null) {
            try {
                st2.close();
            } catch (Exception e) {
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }

    public synchronized List loadAllNewsType() throws Exception {
        List result = new ArrayList();
        try {
            ResultSet rs = st.executeQuery("select * from tb_news_types");
            while (rs.next()) {
                NewsType newstype = new NewsType();
                newstype.setNewsTypeId(rs.getString("news_type_id"));
                newstype.setNewsTypeName(rs.getString("news_type_name"));
                newstype.setListOrd(rs.getInt("list_ord"));
                result.add(newstype);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ��ѯ����");
        }
        return result;
    }

    public synchronized List loadAllNews() throws Exception {
        List result = new ArrayList();
        try {
            ResultSet rs = st.executeQuery("select * from tb_news_types");
            while (rs.next()) {
                NewsType newstype = new NewsType();
                newstype.setNewsTypeId(rs.getString("news_type_id"));
                newstype.setNewsTypeName(rs.getString("news_type_name"));
                newstype.setListOrd(rs.getInt("list_ord"));
                ResultSet rs2 = st2
                        .executeQuery("select  * from tb_news_list where remove_date is  null and news_type_id='"
                                + rs.getString("news_type_id")
                                + "' order by create_date desc");
                while (rs2.next()) {
                    News news = new News();
                    news.setCreateDate(rs2.getDate("create_date"));
                    news.setIsurl(rs2.getString("is_url"));
                    news.setNewsAutor(rs2.getString("news_author"));
                    news.setNewsContent(rs2.getString("news_content"));
                    news.setNewsid(rs2.getString("news_id"));
                    news.setNewsTitle(rs2.getString("news_title"));
                    news.setNewsTypeId(rs2.getString("news_type_id"));
                    news.setReadcount(rs2.getInt("read_count"));
                    news.setRecentReadDate(rs2.getDate("recent_read_date"));
                    newstype.getNewsList().add(news);
                }
                result.add(newstype);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ��ѯ����");
        }
        return result;
    }

    public synchronized void addNewstype(NewsType newstype) throws Exception {
        try {

            String sql = "insert into tb_news_types(news_type_id,news_type_name,list_ord) values('"
                    + newstype.getNewsTypeId()
                    + "','"
                    + newstype.getNewsTypeName()
                    + "',"
                    + newstype.getListOrd()
                    + ")";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ����");
        }

    }

    public synchronized void saveNewstype(NewsType newstype) throws Exception {
        try {

            String sql = "update tb_news_types set news_type_name='"
                    + newstype.getNewsTypeName() + "',list_ord="
                    + newstype.getListOrd() + " where news_type_id='"
                    + newstype.getNewsTypeId() + "'";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ����");
        }

    }

    public synchronized void delNewstype(String newstypeid) throws Exception {
        try {
            ResultSet rs = st
                    .executeQuery("select * from tb_news_types where news_type_id='"
                            + newstypeid + "'");
            if (!rs.next()) {
                throw new Exception("������𲻴���");
            }
            String sql = "delete from tb_news_types where news_type_id='"
                    + newstypeid + "'";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ����");
        }
    }

    public synchronized NewsType readNewstype(String newstypeid)
            throws Exception {
        NewsType result = null;
        try {
            ResultSet rs = st
                    .executeQuery("select * from tb_news_types where news_type_id='"
                            + newstypeid + "'");
            if (rs.next()) {
                result = new NewsType();
                result.setNewsTypeId(rs.getString("news_type_id"));
                result.setNewsTypeName(rs.getString("news_type_name"));
                result.setListOrd(rs.getInt("list_ord"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ��ѯ����");
        }

        return result;

    }

    public synchronized void addNews(News news) throws Exception {
        try {

            String sql = "insert into tb_news_list(news_id,news_title,news_author,create_date,news_content,is_url,read_count,news_type_id)"
                    + " values('"
                    + news.getNewsid()
                    + "','"
                    + news.getNewsTitle()
                    + "','"
                    + news.getNewsAutor()
                    + "',getdate(),'"
                    + news.getNewsContent()
                    + "','"
                    + news.getIsurl() + "',0,'" + news.getNewsTypeId() + "')";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ����");
        }

    }

    public synchronized void saveNews(News news) throws Exception {
        try {
            String sql = sql = "update tb_news_list set news_title='"
                    + news.getNewsTitle() + "',news_content='"
                    + news.getNewsContent() + "',is_url='" + news.getIsurl()
                    + "' where news_id='" + news.getNewsid() + "'";
            System.out.println(sql);
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ����");
        }

    }

    public synchronized void delNews(String newsid) throws Exception {
        try {
            ResultSet rs = st
                    .executeQuery("select * from tb_news_list where news_id='"
                            + newsid + "'");
            if (!rs.next()) {
                throw new Exception("������Ŀ������");
            }
            String sql = "delete from tb_news_list where news_id='" + newsid
                    + "'";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ����");
        }
    }

    public synchronized News readNews(String newsid) throws Exception {
        News result = null;
        try {
            ResultSet rs = st
                    .executeQuery("select * from tb_news_list where news_id='"
                            + newsid + "'");
            if (rs.next()) {
                result = new News();
                result.setCreateDate(rs.getDate("create_date"));
                result.setIsurl(rs.getString("is_url"));
                result.setNewsAutor(rs.getString("news_author"));
                result.setNewsContent(rs.getString("news_content"));
                result.setNewsid(rs.getString("news_id"));
                result.setNewsTitle(rs.getString("news_title"));
                result.setNewsTypeId(rs.getString("news_type_id"));
                result.setReadcount(rs.getInt("read_count"));
                result.setRecentReadDate(rs.getDate("recent_read_date"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ��ѯ����");
        }

        return result;

    }
    
    public synchronized void incReadCount(String newsid) throws Exception {
        try {
            st.execute("update tb_news_list set read_count=read_count+1,recent_read_date=NOW() where news_id='"
                            + newsid + "'");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ��ѯ����");
        }
    }


}
