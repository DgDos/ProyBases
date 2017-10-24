/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import dao.TrabajadorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Trabajador;

/**
 *
 * @author FiJus
 */
public class Usuario extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action=request.getParameter("action");
        TrabajadorDAO t=new TrabajadorDAO();
        ArrayList<Trabajador> trabajadores=new ArrayList<>();
        try {
            trabajadores=t.getAllTrabajadores();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("usuarios", trabajadores);
        if (action.equals("create")) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuarioC.jsp");
            rd.forward(request, response);
        }
        if(action.equals("update")){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuarioU.jsp");
            rd.forward(request, response);
        }
        if(action.equals("delete")){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuarioD.jsp");
            rd.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuarioT=request.getParameter("usuario");
        String passwordT=request.getParameter("password");
        String nombre=request.getParameter("trabajador");
        String cargo=request.getParameter("cargo");
        int supervisor;
        System.out.println("val:  "+request.getParameter("supervisor")!=null);
        if(request.getParameter("supervisor")!=null){
            supervisor=Integer.parseInt(request.getParameter("supervisor"));
        }else{
            supervisor=0;
        }
        Trabajador trabajador=new Trabajador(usuarioT, passwordT, nombre, cargo, supervisor);
        TrabajadorDAO t=new TrabajadorDAO();
        try {
            t.addTrabajador(trabajador);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("menu.html");
    }


}
