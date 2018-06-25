package cn.edu.zucc.news.model;


public class StatusObj {
    enum statusenum {
        sucess,error //为什么sucess(1)不行？我不服！
    }
    statusenum status;
    String msg;

    public statusenum getStatus() {
        return status;
    }

    public void setStatus(statusenum status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
