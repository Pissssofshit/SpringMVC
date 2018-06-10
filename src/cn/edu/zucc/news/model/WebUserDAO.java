/*
 * Created on 2007-4-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cn.edu.zucc.news.model;

import java.sql.*;
import java.util.*;


/**
 * @author
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WebUserDAO {
    private Connection conn = null;
    private Statement st = null;
    public WebUserDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/j2ee_news?user=root&password=123456");
            st = conn.createStatement();
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

        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }

    public synchronized List loadAllUsers() throws Exception{
        List result = new ArrayList();
        try {
            ResultSet rs = st.executeQuery("select * from tb_users");
            while (rs.next()) {
                WebUser user=new WebUser();
                user.setPwd(rs.getString("pwd"));
                user.setUserid(rs.getString("userid"));
                user.setUsername(rs.getString("username"));
                user.setUsertype(rs.getString("usertype"));
                result.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ��ѯ����");
        }
        return result;
    }
    public synchronized void addUser(WebUser user) throws Exception{
        try {
            ResultSet rs = st.executeQuery("select * from tb_users where userid='"+user.getUserid()+"'");
            if (rs.next()) {
                throw new Exception("��¼�Ų����ظ�");
            }
            String sql="insert into tb_users(userid,pwd,username,usertype) values('"+user.getUserid()+"','"
            +user.getPwd()+"','"+user.getUsername()+"','"+user.getUsertype()+"')";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ����");
        }

    }
    public synchronized void saveUser(WebUser user) throws Exception{
        try {
            ResultSet rs = st.executeQuery("select * from tb_users where userid='"+user.getUserid()+"'");
            if (!rs.next()) {
                throw new Exception("�û�������");
            }
            String sql="update tb_users set pwd='"+user.getPwd()
                    +"',username='"+user.getUsername()+"',usertype='"
                    +user.getUsertype()+"' where userid='"+user.getUserid()+"'";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ����");
        }

    }
    public synchronized void delUser(String userid) throws Exception{
        try {
            ResultSet rs = st.executeQuery("select * from tb_users where userid='"+userid+"'");
            if (!rs.next()) {
                throw new Exception("�û�������");
            }
            String sql="delete from tb_users where userid='"+userid+"'";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ����");
        }
    }
    public synchronized WebUser readUser(String userid) throws Exception{
        WebUser result=null;
        try {
            ResultSet rs = st.executeQuery("select * from tb_users where userid='"+userid+"'");
            if (rs.next()) {
                result=new WebUser();
                result.setPwd(rs.getString("pwd"));
                result.setUserid(rs.getString("userid"));
                result.setUsername(rs.getString("username"));
                result.setUsertype(rs.getString("usertype"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("���ݿ��ѯ����");
        }

        return result;

    }


}
