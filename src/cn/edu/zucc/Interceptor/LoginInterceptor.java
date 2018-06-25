package cn.edu.zucc.Interceptor;

import cn.edu.zucc.news.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("这是拦截器");
        String url=httpServletRequest.getRequestURI();
        if(url.indexOf("/login2")>=0 || url.indexOf("/register")>=0){
            return true;
        }
        HttpSession session=httpServletRequest.getSession();
        String username=(String) session.getAttribute("username");
        System.out.println(username);
        if(username != null){
            return true;
        }
        System.out.println("it should return false");

        httpServletRequest.setAttribute("errormsg","您还没有登录请等先登录");//和session的不同应该在与级别，获取方式的不同也只是一个用session.get一个用request.get
//        httpServletRequest.getRequestDispatcher("/editor.jsp").forward(httpServletRequest,httpServletResponse); //这句话没用？
        httpServletResponse.sendRedirect("/login.jsp");

        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
