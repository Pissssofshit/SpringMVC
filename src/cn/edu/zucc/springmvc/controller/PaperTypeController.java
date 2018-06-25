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
public class PaperTypeController {
    PaperTypeDao paperType=new PaperTypeDao();
    @RequestMapping(value = "/changetypename")
    public void handleRequest2(HttpSession httpSession, HttpServletRequest request) throws Exception {
        paperType.updateTypename(Integer.parseInt(request.getParameter("typeid")),request.getParameter("typename"));
    }
}
