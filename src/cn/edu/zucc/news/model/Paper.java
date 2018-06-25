package cn.edu.zucc.news.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class Paper implements Serializable {

    int paperid;
    int uploaderid;
    String state;
    String title;
    int editorid;
    String authorlist;
    String papertype;
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPapertype() {
        return papertype;
    }

    public void setPapertype(String papertype) {
        this.papertype = papertype;
    }

    public int getPaperid() {
        return paperid;
    }

    public void setPaperid(int paperid) {
        this.paperid = paperid;
    }

    public int getUploaderid() {
        return uploaderid;
    }

    public void setUploaderid(int uploaderid) {
        this.uploaderid = uploaderid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEditorid() {
        return editorid;
    }

    public void setEditorid(int editorid) {
        this.editorid = editorid;
    }

    public String getAuthorlist() {
        return authorlist;
    }

    public void setAuthorlist(String authorlist) {
        this.authorlist = authorlist;
    }
}
