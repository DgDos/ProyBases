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
import model.Trabajador;
import util.DbUtil;

/**
 *
 * @author FiJus
 */
public class TrabajadorDAO {
    private Connection connection;

    public TrabajadorDAO() {
        connection = DbUtil.getConnection();
    }

    public void addTrabajador(Trabajador t) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Trabajador(usuarioT,passwordT,nombre,cargo,supervisor) values (?,?,?,?,?)");
        preparedStatement.setString(1, t.getUsuarioT());
        preparedStatement.setString(2, t.getPasswordT());
        preparedStatement.setString(3, t.getNombre());
        preparedStatement.setString(4, t.getCargo());
        preparedStatement.setInt(5, t.getSupervisor());
        preparedStatement.executeUpdate();
    }

    public void deleteTrabajador(int idT) throws SQLException {
        
        PreparedStatement preparedStatement = connection.prepareStatement("delete from Trabajador where idUsuario=?");
        preparedStatement.setInt(1, idT);
        preparedStatement.executeUpdate();
    }

    public void updateEsquema(String esquema,int idE) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update esquema set nombre_esquema=?" + " where id_esquema=?");
        preparedStatement.setString(1, esquema);
        preparedStatement.setInt(2, idE);
        
        preparedStatement.executeUpdate();
    }

    public ArrayList<Trabajador> getAllTrabajadores() throws SQLException {
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Trabajador");
        while (rs.next()) {
            Trabajador t = new Trabajador();
            t.setIdUsuario(rs.getInt("idUsuario"));
            t.setNombre(rs.getString("usuarioT"));
            t.setPasswordT(rs.getString("passwordT"));
            t.setNombre(rs.getString("nombre"));
            t.setCargo(rs.getString("cargo"));
            t.setSupervisor(rs.getInt("supervisor"));
            trabajadores.add(t);
        }
        return trabajadores;
    }
}
