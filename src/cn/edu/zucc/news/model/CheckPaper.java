package cn.edu.zucc.news.model;

public class CheckPaper {


    int checkid; //主键
    int editorid; //编辑者id
    String  decide;//决定　，过或拒绝
    int  paperid;//论文id
    String advide;//文字的修改意见

    public int getCheckid() {
        return checkid;
    }

    public void setCheckid(int checkid) {
        this.checkid = checkid;
    }

    public int getEditorid() {
        return editorid;
    }

    public void setEditorid(int editorid) {
        this.editorid = editorid;
    }

    public String getDecide() {
        return decide;
    }

    public void setDecide(String decide) {
        this.decide = decide;
    }

    public int getPaperid() {
        return paperid;
    }

    public void setPaperid(int paperid) {
        this.paperid = paperid;
    }

    public String getAdvide() {
        return advide;
    }

    public void setAdvide(String advide) {
        this.advide = advide;
    }
}
