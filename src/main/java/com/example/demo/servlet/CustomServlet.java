package com.example.demo.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@Slf4j
public class CustomServlet extends HttpServlet {

     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
     {
         String path = req.getServletPath();
         PrintWriter out = resp.getWriter();
         //log.info("###### PATH :"+path);
         String flag = "2";
         if(flag.equals("2"))
         {
             req.getRequestDispatcher(path+"/").forward(req, resp);
         }
         else {
             resp.setContentType("text/html");
             out.print("<html><body>");
             out.print("<h2>Error details</h2><br/>");

             out.print("The requested resource access is not authorized <h3>"+ path + "</h3><br/>");
             out.print("</body></html>");
         }

         out.close();
     }

}
