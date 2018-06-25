package cn.edu.zucc.news.model;

//import com.mysql.jdbc.Blob;

import java.sql.Blob;

public class File {
    Blob file;

    int paperid;

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }

    public int getPaperid() {
        return paperid;
    }

    public void setPaperid(int paperid) {
        this.paperid = paperid;
    }
}
