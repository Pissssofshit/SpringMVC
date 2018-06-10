/*
 * Created on 2007-4-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cn.edu.zucc.news.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NewsType implements Serializable {
    private String newsTypeName="";
    private String newsTypeId="";
    private int listOrd=0;
    private List newsList=new ArrayList();
    private String method="addNewsTypeResult"; //用于表示该对象下一步的处理方法

    /**
     * @return Returns the newsList.
     */
    public List getNewsList() {
        return newsList;
    }
    /**
     * @param newsList The newsList to set.
     */
    public void setNewsList(List newsList) {
        this.newsList = newsList;
    }
    /**
     * @return Returns the listOrd.
     */
    public int getListOrd() {
        return listOrd;
    }
    /**
     * @param listOrd The listOrd to set.
     */
    public void setListOrd(int listOrd) {
        this.listOrd = listOrd;
    }
    /**
     * @return Returns the newsTypeId.
     */
    public String getNewsTypeId() {
        return newsTypeId;
    }
    /**
     * @param newsTypeId The newsTypeId to set.
     */
    public void setNewsTypeId(String newsTypeId) {
        this.newsTypeId = newsTypeId;
    }
    /**
     * @return Returns the newsTypeName.
     */
    public String getNewsTypeName() {
        return newsTypeName;
    }
    /**
     * @param newsTypeName The newsTypeName to set.
     */
    public void setNewsTypeName(String newsTypeName) {
        this.newsTypeName = newsTypeName;
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
