package com.Controller;

import com.Entity.Grade;
import com.Entity.User;
import com.Service.GradeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GradeController",urlPatterns = "/gradeServlet")
public class GradeController extends HttpServlet {
    private GradeService gradeService=new GradeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if(method.equals("getGradeList"))
            getGradeList(request,response);

    }
    //显示总成绩
    public void getGradeList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //获取session
        User user = (User)request.getSession().getAttribute("user");
        List<Grade> gradeList=gradeService.queryGradeList(user.getUser_id());
        request.setAttribute("gradeList",gradeList);
        //转发request.getRequestDispatcher(jsp目录)
        request.getRequestDispatcher("WEB-INF/exam/resultTable.jsp").forward(request,response);
    }


}
