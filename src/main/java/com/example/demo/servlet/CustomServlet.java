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

@Slf4j
public class CustomServlet extends HttpServlet {

     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
     {
         String path = req.getServletPath();
         PrintWriter out = resp.getWriter();
         out.println("Some information on the browser..."+path);
         log.info("###### PATH :"+path);
         out.close();
     }

}
