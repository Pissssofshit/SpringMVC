package cn.edu.zucc.springmvc.controller;

import cn.edu.zucc.news.model.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@org.springframework.stereotype.Controller
public class UserController{

    @RequestMapping(value="/user/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User handleRequest(@PathVariable("id") String id) throws Exception {
//        HibernateUtil.getSession2();
        User user=new User();
        return user;
//        return "/WEB-INF/Jsp/first.jsp";
    }
//    @RequestMapping("/testJson")
//    @ResponseBody
//    public User testjson(@RequestBody  User paper){
//        System.out.println(paper);
//        return paper;
//    }
//    @RequestMapping("/firstController")
//    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        System.out.println("hello  im firstcontroller");
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("msg","hello");
//        mv.setViewName("/WEB-INF/Jsp/first.jsp");
//        return mv;
//    }
//    @RequestMapping("/secondController")
//    public ModelAndView handleRequest2(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        System.out.println("hello im firstcontroller");
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("msg","hello");
//        mv.setViewName("/WEB-INF/Jsp/first.jsp");
//        return mv;
//    }
}
