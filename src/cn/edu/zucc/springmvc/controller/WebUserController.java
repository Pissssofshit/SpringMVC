package cn.edu.zucc.springmvc.controller;

/*
 * Created on 2007-4-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.news.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
@Controller
public class WebUserController  {
    private WebUserDAO dao = new WebUserDAO();



    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //TODO Method stub generated by Lomboz
        this.doPost(request, response);
    }

    private String logincheck(HttpServletRequest request) {
        try {
            String userid=request.getParameter("userid");
            if(userid==null ||userid.equals(""))
                throw new Exception("�������û���");
            String pwd=request.getParameter("pwd");
            if(pwd==null)pwd="";
            WebUser user=dao.readUser(userid);
            if(user==null)
                throw new Exception("�û�������");
            if(!pwd.equals(user.getPwd()))
                throw new Exception("�������");
            request.getSession().setAttribute("username",user.getUsername());
            if("admin".equals(user.getUsertype()))
                return "/manager.jsp";
            else
                return "/editor.jsp";
        } catch (Exception ex) {
            request.setAttribute("errormsg", ex.getMessage());
            return "/login.jsp";
        }
    }

    private String list(HttpServletRequest request) {
        try {
            request.setAttribute("objlist", dao.loadAllUsers());

            return "/user_list.jsp";
        } catch (Exception ex) {
            request.setAttribute("errormsg", ex.getMessage());
            return "/error.jsp";
        }
    }

    private String adduser(HttpServletRequest request) {
        request.setAttribute("obj", new WebUser());
        return "/user_edit.jsp";
    }

    private String adduserresult(HttpServletRequest request) {
        WebUser user = new WebUser();
        user.setUsername(request.getParameter("username"));
        user.setPwd(request.getParameter("pwd"));
        user.setUsertype(request.getParameter("usertype"));
        user.setUserid(request.getParameter("userid"));
        try {
            this.dao.addUser(user);
            return this.list(request);
        } catch (Exception ex) {
            request.setAttribute("errormsg", ex.getMessage());
            return "/error.jsp";
        }

    }

    private String modifyuser(HttpServletRequest request) {
        try {
            WebUser user = this.dao.readUser(request.getParameter("userid"));
            user.setMethod("modifyuserresult");
            request.setAttribute("obj", user);
            return "/user_edit.jsp";
        } catch (Exception ex) {
            request.setAttribute("errormsg", ex.getMessage());
            return "/error.jsp";
        }
    }

    private String modifyuserresult(HttpServletRequest request) {
        WebUser user = new WebUser();
        user.setUsername(request.getParameter("username"));
        user.setPwd(request.getParameter("pwd"));
        user.setUsertype(request.getParameter("usertype"));
        user.setUserid(request.getParameter("userid"));
        try {
            this.dao.saveUser(user);
            return this.list(request);
        } catch (Exception ex) {
            request.setAttribute("errormsg", ex.getMessage());
            return "/error.jsp";
        }
    }

    private String deleteuser(HttpServletRequest request) {
        try {
            this.dao.delUser(request.getParameter("userid"));
            return this.list(request);
        } catch (Exception ex) {
            request.setAttribute("errormsg", ex.getMessage());
            return "/error.jsp";
        }
    }

    @RequestMapping("/user")
    protected String doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("gbk");
        String method = request.getParameter("method");
        if (method == null)  method = "list";
        String result = "";
        if (method.equals("adduser"))
            result = adduser(request);
        else if (method.equals("adduserresult"))
            result = adduserresult(request);
        else if (method.equals("modifyuser"))
            result = modifyuser(request);
        else if (method.equals("modifyuserresult"))
            result = modifyuserresult(request);
        else if (method.equals("deleteuser"))
            result = deleteuser(request);
        else if (method.equals("logincheck"))
            result = this.logincheck(request);
        else
            result = list(request);

        return result;
//        RequestDispatcher dispatcher = request.getSession().getServletContext()
//                .getRequestDispatcher(result);
//        if (dispatcher != null)
//            dispatcher.forward(request, response);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Servlet#destroy()
     */
//    public void destroy() {
//        // TODO Auto-generated method stub
//        dao.release();
//        super.destroy();
//    }

}
