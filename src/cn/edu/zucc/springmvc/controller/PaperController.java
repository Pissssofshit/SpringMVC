package cn.edu.zucc.springmvc.controller;

import cn.edu.zucc.news.model.*;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.LobHelper;
import org.hibernate.internal.SessionImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import sun.nio.ch.IOUtil;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class PaperController {
    PaperDao paperDao=new PaperDao();
    @RequestMapping("/firstController")
    public String handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Model model,Integer ss) throws Exception {
//        HibernateUtil hibernateUtil=new HibernateUtil();
//        HibernateUtil.test();
//        System.out.println("hello im firstcontroller");
//        System.out.println("ss"+httpServletRequest.getParameter("id"));
//        System.out.println(ss);
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("msg","hello");
//        mv.setViewName("/WEB-INF/Jsp/first.jsp");
//        model.addAttribute("msg",""+ss);

        Paper paper=new Paper();
//        paper.setPaperid("123555");
//        paperDao.uploadPaper(paper);
//        paperDao.changePaperState(paper);
        return "/WEB-INF/Jsp/first.jsp";
    }
    @RequestMapping(value = "/uploadpaper1", method = RequestMethod.POST)
    public void saveHeaderPic(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException ,Exception {

        String resMsg = "";
        try {

            long  startTime=System.currentTimeMillis();
//            System.out.println(request.getParameter("testusername"));
//            request.getParameter("");
//            request.getParameter("");
            System.out.println("fileName："+file.getOriginalFilename());
//            String path="/Users/loukai/easylife/files/"+file.getOriginalFilename()+new Date().getTime();
//            String path=file.getInputStream()
//            System.out.println("path:" + path);


            InputStream newFile=file.getInputStream();
            byte[] content=null;
            content=new byte[newFile.available()];
            newFile.read(content);
            //通过CommonsMultipartFile的方法直接写文件
            Paper paper=new Paper();
            Blob blobcontent=HibernateUtil.getSession().getLobHelper().createBlob(content);
            paper.setFile(blobcontent);
            paperDao.uploadPaper(paper);
//            paper.setFile(IOUtils.toByteArray(newFile));
//            file.transferTo(newFile);
//            long  endTime=System.currentTimeMillis();
//            System.out.println("运行时间："+String.valueOf(endTime-startTime)+"ms");
//            resMsg = "1";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resMsg = "0";
        }
        response.getWriter().write(resMsg);

    }
    @RequestMapping("/uploadfile")
    @ResponseBody
    public void uploadfile(MultipartFile uploadFile, HttpSession session) throws Exception{
        String filename= uploadFile.getOriginalFilename();
        String leftPath = session.getServletContext().getRealPath("/images");
        File file=new File(leftPath,filename);
        uploadFile.transferTo(file);
    }
    @RequestMapping("/testJson")
    @ResponseBody
    public User testjson(@RequestBody  User paper){
        System.out.println(paper);
        return paper;
    }
    @RequestMapping(value="/showpage/{authorid}",method = RequestMethod.GET)
    @ResponseBody
    public List<Paper> handleRequest6(@PathVariable("authorid") String authorid) throws Exception {
        return paperDao.showPaper(authorid);
    }
    @RequestMapping(value="/checkpaper",method = RequestMethod.GET)
    @ResponseBody
    public void handleRequest5(@RequestBody CheckPaper checkPaper) throws Exception {
        paperDao.changePaperState(checkPaper);
    }
    @RequestMapping(value="/uppaper",method = RequestMethod.GET)
    @ResponseBody
    public void handleRequest4(@RequestBody Paper paper) throws Exception {
       paperDao.uploadPaper(paper);
    }
    @RequestMapping(value="/delpaper/{paperid}",method = RequestMethod.GET)
    @ResponseBody
    public void handleRequest2(@PathVariable("paperid") int id) throws Exception {
        Paper paper=new Paper();
        paper.setPaperid(id);
        paperDao.deletePaper(paper);
    }
    @RequestMapping(value="/delAuPaper/{authorid}",method = RequestMethod.GET)
    @ResponseBody
    public void handleRequest3(@PathVariable("authorid") String id) throws Exception {

        paperDao.delAupaper(id);
    }
//
    @RequestMapping(value="/paper/{authorid}",method = RequestMethod.GET)
    @ResponseBody
    public List<Paper> handleRequest(@PathVariable("authorid") String id) throws Exception {
//        HibernateUtil.getSession2();
//        User user=new User();
        List<Paper> papers=new ArrayList<Paper>();
        papers=paperDao.showPaper(id);
                for(Paper paper : papers){
            System.out.println(paper.getPaperid());
        }
        return papers;
//        return "/WEB-INF/Jsp/first.jsp";
    }
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
