/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.EmpresaCliente;
import util.DbUtil;

/**
 *
 * @author FiJus
 */
public class EmpresaClienteDAO {
    private Connection connection;

    public EmpresaClienteDAO() {
        connection = DbUtil.getConnection();
    }

    public void addEmpresa(EmpresaCliente e) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Empresa(NIT,nombreEmpresa,usuarioE,passwordE,direccion) values (?,?,?,?,?)");
        preparedStatement.setInt(1, e.getNIT());
        preparedStatement.setString(2, e.getNombreEmpresa());
        preparedStatement.setString(3, e.getUsuarioE());
        preparedStatement.setString(4, e.getPasswordE());
        preparedStatement.setString(5, e.getDireccion());
        preparedStatement.executeUpdate();
    }

    public void deleteEmpresa(int idE) throws SQLException {
        
        PreparedStatement preparedStatement = connection.prepareStatement("delete from Empresa where NIT=?");
        preparedStatement.setInt(1, idE);
        preparedStatement.executeUpdate();
    }

    public void updateEmpresa(String esquema,int idE) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update esquema set nombre_esquema=?" + " where id_esquema=?");
        preparedStatement.setString(1, esquema);
        preparedStatement.setInt(2, idE);
        
        preparedStatement.executeUpdate();
    }

    public ArrayList<EmpresaCliente> getAllEmpresas() throws SQLException {
        ArrayList<EmpresaCliente> empresas = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Empresa");
        while (rs.next()) {
            EmpresaCliente e = new EmpresaCliente();
            e.setNIT(rs.getInt("NIT"));
            e.setUsuarioE(rs.getString("usuarioE"));
            e.setPasswordE(rs.getString("passwordE"));
            e.setNombreEmpresa(rs.getString("nombreEmpresa"));
            e.setDireccion(rs.getString("direccion"));
            empresas.add(e);
        }
        return empresas;
    }
}
