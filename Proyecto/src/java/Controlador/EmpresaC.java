/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import dao.EmpresaDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Empresa;

/**
 *
 * @author FiJus
 */
public class EmpresaC extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action=request.getParameter("action");
        EmpresaDAO e=new EmpresaDAO();
        ArrayList<Empresa> empresas=new ArrayList<>();
        try {
            empresas=e.getAllEmpresas();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("empresas", empresas);
        if (action.equals("create")) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/EmpresasC.jsp");
            rd.forward(request, response);
        }
        if(action.equals("update")){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/EmpresasU.jsp");
            rd.forward(request, response);
        }
        if(action.equals("delete")){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/EmrpesasD.jsp");
            rd.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuarioE=request.getParameter("usuario");
        String passwordE=request.getParameter("password");
        String nombre=request.getParameter("nombreE");
        int NIT=Integer.parseInt(request.getParameter("NIT"));
        String direccion=request.getParameter("direccion");
        Empresa empresa=new Empresa(NIT, nombre, usuarioE, passwordE, direccion);
        
        EmpresaDAO e=new EmpresaDAO();
        try {
            e.addEmpresa(empresa);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("menu.html");
    }

}
