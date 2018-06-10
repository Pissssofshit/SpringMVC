/*
 * Created on 2007-4-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cn.edu.zucc.news.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class News implements Serializable {
    private String newsid="";
    private String newsTitle="";
    private String newsContent="";
    private String newsAutor="";
    private String isurl="n";
    private String newsTypeId="";
    private String newsTypaName="";
    private Date createDate=null;
    private int readcount = 0;
    private Date recentReadDate = null;
    private String method="addNewsResult"; //用于表示该对象下一步的处理方法
    /**
     * @return Returns the createDate.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     *            The createDate to set.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return Returns the isurl.
     */
    public String getIsurl() {
        return isurl;
    }

    /**
     * @param isurl
     *            The isurl to set.
     */
    public void setIsurl(String isurl) {
        this.isurl = isurl;
    }

    /**
     * @return Returns the newsAutor.
     */
    public String getNewsAutor() {
        return newsAutor;
    }

    /**
     * @param newsAutor
     *            The newsAutor to set.
     */
    public void setNewsAutor(String newsAutor) {
        this.newsAutor = newsAutor;
    }

    /**
     * @return Returns the newsContent.
     */
    public String getNewsContent() {
        return newsContent;
    }

    /**
     * @param newsContent
     *            The newsContent to set.
     */
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    /**
     * @return Returns the newsid.
     */
    public String getNewsid() {
        return newsid;
    }

    /**
     * @param newsid
     *            The newsid to set.
     */
    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    /**
     * @return Returns the newsTitle.
     */
    public String getNewsTitle() {
        return newsTitle;
    }

    /**
     * @param newsTitle
     *            The newsTitle to set.
     */
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    /**
     * @return Returns the newsTypaName.
     */
    public String getNewsTypaName() {
        return newsTypaName;
    }

    /**
     * @param newsTypaName
     *            The newsTypaName to set.
     */
    public void setNewsTypaName(String newsTypaName) {
        this.newsTypaName = newsTypaName;
    }

    /**
     * @return Returns the newsTypeId.
     */
    public String getNewsTypeId() {
        return newsTypeId;
    }

    /**
     * @param newsTypeId
     *            The newsTypeId to set.
     */
    public void setNewsTypeId(String newsTypeId) {
        this.newsTypeId = newsTypeId;
    }

    /**
     * @return Returns the readcount.
     */
    public int getReadcount() {
        return readcount;
    }

    /**
     * @param readcount
     *            The readcount to set.
     */
    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    /**
     * @return Returns the recentReadDate.
     */
    public Date getRecentReadDate() {
        return recentReadDate;
    }

    /**
     * @param recentReadDate
     *            The recentReadDate to set.
     */
    public void setRecentReadDate(Date recentReadDate) {
        this.recentReadDate = recentReadDate;
    }

    
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
}
