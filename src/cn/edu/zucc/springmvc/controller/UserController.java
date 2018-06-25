package cn.edu.zucc.springmvc.controller;

import cn.edu.zucc.news.model.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class UserController{
    UserDao userDao=new UserDao();
    @RequestMapping(value="/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
//        System.out.println(httpSession.getAttribute("username"));
        return "/login.jsp";
    }
    @RequestMapping(value="/mainpage")
    public String mainpage(HttpSession httpSession){
//        httpSession.invalidate();
        if(httpSession.getAttribute("usertype").equals("teacher"))
            return "/teacher.jsp";
        return "/editor.jsp";
    }
    @RequestMapping(value="/register")
    public String handleRequest2(HttpSession httpSession, HttpServletRequest request) throws Exception {
        User user=this.getUserFromRequest(request);
        HibernateUtil.save(user);
        user.setUserid(userDao.checkUser(user));
        UserController.setUserToSession(user,httpSession);
        if(user.getUsertype().equals("teacher"))
            return "/teacher.jsp";
        else{
            return "/editor.jsp";
        }
    }
    public User getUserFromRequest(HttpServletRequest request){
        User user=new User();
        user.setUsertype(request.getParameter("usertype"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        return user;
    }
    public static void setUserToSession(User user,HttpSession httpSession){
        httpSession.setAttribute("userid",user.getUserid());
        httpSession.setAttribute("usertype",user.getUsertype());
        httpSession.setAttribute("username",user.getUsername());
    }
//em....要返回试图必须要get方法才行吗？
    @RequestMapping(value="/login2")
    public String handleRequest(HttpSession httpSession, HttpServletRequest request) throws Exception {
//        HibernateUtil.getSession2();
//        User user=new User();
        System.out.println("username"+request.getParameter("username"));
        System.out.println("usertype"+request.getParameter("usertype"));
//        return user;
//        return "/WEB-INF/Jsp/first.jsp";
        System.out.println("login2");
        String s=checklogin(request);
//        response.sendRedirect("teacher.jsp");
        System.out.println("将要返回的页面是"+s);
        return s;
    }
    private String checklogin(HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String usertype=request.getParameter("usertype");
//        String test=request.getParameter("test");
        System.out.println("username"+username);
        System.out.println("pas"+password);
        System.out.println("usertype"+usertype);
//        System.out.println("test"+test);
        HttpSession session = request.getSession();
        UserDao userDao=new UserDao();
        User user=new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setUsertype(usertype);
        try {
            int result = userDao.checkUser(user);
            System.out.println("result"+result);
            if (result > 0) {
                user.setUserid(result);
                UserController.setUserToSession(user,session);
                if(user.getUsertype().equals("teacher"))
                    return "/teacher.jsp";
                else{
                    return "/editor.jsp";
                }
            } else if (result == -1) {
                session.setAttribute("errormsg","没有这个用户");
                return "/login.jsp";
            }else if(result==-2){
                session.setAttribute("errormsg","密码错误");
                return "/login.jsp";
            }else if(result==-3){
                session.setAttribute("errormsg","不是"+usertype+"用户");
                return "/login.jsp";
            }
        }
        catch (Exception e ){
            e.printStackTrace();
        }

        return "";
    }
    @RequestMapping(value="/user/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User handleRequest(@PathVariable("id") String id) throws Exception {
//        HibernateUtil.getSession2();
        User user=new User();
        return user;
//        return "/WEB-INF/Jsp/first.jsp";
    }
//    @RequestMapping("/testJson")

}
