/*
 * Created on 2007-4-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cn.edu.zucc.news.model;

import java.io.Serializable;

/**
 * @author 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WebUser implements Serializable {
    private String userid="";
    private String pwd="";
    private String username="";
    private String usertype="editor";
    private String method="adduserresult"; //���ڱ�ʾ��һ���Ĵ�����
    /**
     * @return Returns the method.
     */
    public String getMethod() {
        return method;
    }
    /**
     * @param method The method to set.
     */
    public void setMethod(String method) {
        this.method = method;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return Returns the usertype.
     */
    public String getUsertype() {
        return usertype;
    }
    /**
     * @param usertype The usertype to set.
     */
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
