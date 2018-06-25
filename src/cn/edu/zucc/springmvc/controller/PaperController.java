package cn.edu.zucc.springmvc.controller;

import cn.edu.zucc.news.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.LobHelper;
import org.hibernate.internal.SessionImpl;
import org.springframework.http.*;
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
import java.io.*;
import java.io.File;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class PaperController {
    PaperDao paperDao=new PaperDao();

//    @RequestMapping(value="/getpapertype")
//    @ResponseBody
//    public List<PaperType> handleRequest4(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        PaperTypeDao paperTypeDao=new PaperTypeDao();
//        return paperTypeDao.getAllpapertye();
//    }

    @RequestMapping(value="/timesearch",method = RequestMethod.POST)
    @ResponseBody
    public List<Paper> handleRequest4(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("timesearch");
        System.out.println(httpServletRequest.getParameter("time"));
        httpServletResponse.setContentType("text/text;charset=utf-8");//设置之后，xml解析错误消失　因为页面和后台的字符集可能不同
        httpServletResponse.setCharacterEncoding("UTF-8");
        String time=httpServletRequest.getParameter("time");
        String keyword=httpServletRequest.getParameter("keyword");
        List<Paper> papers=paperDao.searchPaper(time,keyword);
//        List<Paper> papers=paperDao.searchPaperbytime(httpServletRequest.getParameter("time"));
        return  papers;
    }
    //怎么给前台发失败消息？
    @RequestMapping(value="/addpapertype")
//    @ResponseBody
    public StatusObj handleRequest3(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        System.out.println("ssdasdas");
//        return paperDao.showPaperBystate("uncheck");
        PaperTypeDao paperTypeDao=new PaperTypeDao();
        PaperType paperType=new PaperType();
        System.out.println("sss"+httpServletRequest.getParameter("type"));
        paperType.setType(httpServletRequest.getParameter("type"));
        return paperTypeDao.addPapertype(paperType);
    }
    @RequestMapping(value="/getcheckedpaper",method= RequestMethod.GET)
    @ResponseBody
    public List<Paper> handleRequestss(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return paperDao.showPaperBystate("notuncheck");
    }
    @RequestMapping(value="/getUncheckpaper",method= RequestMethod.GET)
    @ResponseBody
    public List<Paper> handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return paperDao.showPaperBystate("uncheck");
    }
    //疑问　这个请求返回的机制到底是怎么样的，设置response和produces都无效
    @RequestMapping(value="/checkPaper",method= RequestMethod.POST,produces={"application/json; charset=UTF-8"})
    public void handleRequest2(HttpServletRequest httpServletRequest,HttpServletResponse response) throws Exception {
        System.out.println("ssdasdas");
        CheckPaper checkPaper=new CheckPaperDao().CreateByRequest(httpServletRequest);
        new CheckPaperDao().save(checkPaper);
        paperDao.changePaperState(checkPaper);

//        response.setContentType("application/json;charset=utf-8"); //ajax 200 据说是返回的不是json?为什么这样设置还是不行？
        response.setContentType("text/text;charset=utf-8");//设置之后，xml解析错误消失　因为页面和后台的字符集可能不同
        response.setCharacterEncoding("UTF-8");

//        return paperDao.showPaperBystate("uncheck");
    }
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

            System.out.println("fileName："+file.getOriginalFilename());
            InputStream newFile=file.getInputStream();
            byte[] content=null;
            content=new byte[newFile.available()];
            newFile.read(content);
            //通过CommonsMultipartFile的方法直接写文件
            Paper paper=new Paper();
            paper.setUploaderid((Integer) request.getSession().getAttribute("userid"));
            paper.setTitle(request.getParameter("title"));
            paper.setState("uncheck");
            paper.setPapertype(request.getParameter("papertype"));
            paper.setAuthorlist(request.getParameter("authorlist"));
//            System.out.println("author="+request.getParameter("author"));
//            paper.setPaperid(888);
            Blob blobcontent=HibernateUtil.getSession().getLobHelper().createBlob(content);
//            paper.setFile(blobcontent);
            paperDao.uploadPaper(paper,blobcontent);
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
    //下载论文的功能，后期需要提供指定位置存放
//    @RequestMapping("/dwnpaper/{paperid}")
//    @ResponseBody
//    public void dwnfile(@PathVariable("paperid") int paperid,HttpSession httpSession) throws Exception{
////todo
////        Paper paper=paperDao.showPaperBypid(paperid);
////        InputStream in=paper.getFile().getBinaryStream();
//        FileDAO filedao=new FileDAO();
//        InputStream in=filedao.getFlieByPaperid(paperid).getFile().getBinaryStream();
//        //todo 到时候把这个下载的名字该成存在数据库里的标题
//        PaperDao paperDao=new PaperDao();
//        FileOutputStream fos=new FileOutputStream("/User/"+paperDao.showPaperBypid(paperid).getTitle()+".doc");
//        byte[] buffer=new byte[1024];
//        int len;
//        while((len=in.read(buffer))!=-1){
//            fos.write(buffer,0,len);
//        }
//    }
    @RequestMapping("/dwnpaper/{paperid}")
//    @ResponseBody
    public ResponseEntity<byte[]> dwnfile(@PathVariable("paperid") int paperid, HttpSession httpSession) throws Exception{
//todo
//        Paper paper=paperDao.showPaperBypid(paperid);
//        InputStream in=paper.getFile().getBinaryStream();
        FileDAO filedao=new FileDAO();
//        InputStream in=filedao.getFlieByPaperid(paperid).getFile().getBinaryStream();
//        File file=(File) filedao.getFlieByPaperid(paperid).getFile();

        HttpHeaders headers=new HttpHeaders();
//        InputStream input = new FileInputStream(filedao.getFlieByPaperid(paperid).getFile());

        byte[] byt =this.getBytes(filedao.getFlieByPaperid(paperid).getFile());
//        byte[] bytes = new byte[(int) filedao.getFlieByPaperid(paperid).getFile().length()];
        MediaType mediaType = new MediaType("text","html", Charset.forName("utf-8"));
        headers.setContentType(mediaType);
        headers.setContentDispositionFormData("attachment","test.doc");
//        new ResponseEntity<byte>()
//        FileUtils.readFileToByteArray()
        return new ResponseEntity<byte[]>(byt,headers, HttpStatus.OK);

        //todo 到时候把这个下载的名字该成存在数据库里的标题
//        PaperDao paperDao=new PaperDao();
//        FileOutputStream fos=new FileOutputStream("/User/"+paperDao.showPaperBypid(paperid).getTitle()+".doc");
//        byte[] buffer=new byte[1024];
//        int len;
//        while((len=in.read(buffer))!=-1){
//            fos.write(buffer,0,len);
//        }
    }
    private byte[] getBytes(Blob blob) {
        try {
            InputStream ins = blob.getBinaryStream();
            byte[] b = new byte[1024];
            int num = 0;
            String res = "";
            while ((num = ins.read(b)) != -1) {
                res += new String(b, 0, num);
            }
            return res.getBytes();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/uploadpaper")
    public String uploadfiles(MultipartFile uploadFile, HttpSession session) throws Exception{
        return "/testupload3.jsp";
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
    public void testjson(HttpSession httpSession){
        System.out.println(httpSession.getAttribute("username"));
//        System.out.println(paper);
//        return paper;
    }
    @RequestMapping(value="/getpapertype",method = RequestMethod.GET)
    @ResponseBody
    public List<PaperType> getAllpaperType() throws Exception {
        return new PaperTypeDao().getAllpapertye();
    }
    @RequestMapping(value="/showpage/{uploaderid}",method = RequestMethod.GET)
    @ResponseBody
    public List<Paper> handleRequest6(@PathVariable("uploaderid") int uploaderid) throws Exception {
        return paperDao.showPaper(uploaderid);
    }
    @RequestMapping(value="/checkpaper",method = RequestMethod.GET)
    @ResponseBody
    public void handleRequest5(@RequestBody CheckPaper checkPaper) throws Exception {
        paperDao.changePaperState(checkPaper);
    }
    @RequestMapping(value="/uppaper",method = RequestMethod.GET)
    @ResponseBody
    public void handleRequest4(@RequestBody Paper paper) throws Exception {
        //todo
//       paperDao.uploadPaper(paper);
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
    public void handleRequest3(@PathVariable("authorid") int id) throws Exception {

        paperDao.delUppaper(id);
    }
//
    @RequestMapping(value="/paper/{authorid}",method = RequestMethod.GET)
    @ResponseBody
    public List<Paper> handleRequest(@PathVariable("authorid") int id) throws Exception {
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
