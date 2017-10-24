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
import model.Mensaje;
import model.Servicio;
import util.DbUtil;

/**
 *
 * @author FiJus
 */
public class ServicioDAO {
    private Connection connection;

    public ServicioDAO() {
        connection = DbUtil.getConnection();
    }

    public void addServicio(Servicio s) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Servicio(nombreS) values (?)");
        preparedStatement.setString(1, s.getNombreS());
        preparedStatement.executeUpdate();
    }

    public void deleteServicio(int idU1,int idU2) throws SQLException {
        
        PreparedStatement preparedStatement = connection.prepareStatement("delete from Mensaje where idU1=? and idU2=?");
        preparedStatement.setInt(1, idU1);
        preparedStatement.setInt(2, idU2);
        preparedStatement.executeUpdate();
    }

    public void updateServicio(int idU1,int idU2, String texto) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update esquema set Texto=?" + " where idU1=? and idU2=?");
        preparedStatement.setInt(2, idU1);
        preparedStatement.setInt(3, idU2);
        preparedStatement.setString(1, texto);
        preparedStatement.executeUpdate();
    }

    public ArrayList<Servicio> getAllServicios() throws SQLException {
        ArrayList<Servicio> servicios = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Servicio");
        while (rs.next()) {
            Servicio s=new Servicio();
            s.setIdServicio(rs.getInt("idServicio"));
            s.setNombreS(rs.getString("nombreS"));
            servicios.add(s);
        }
        return servicios;
    }
}
