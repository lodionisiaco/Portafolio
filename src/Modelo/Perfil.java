/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author camil
 */
public class Perfil {
    
    private int id_perfil;
    private String descripcion;

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }
    
    public Vector<Perfil> mostrarPerfil(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Vector<Perfil> vectorPerfil = new Vector<Perfil>();
        Perfil perfil = null;
        try {
            Conexion conexion = new Conexion();
            
            ps = conexion.getConnection().prepareStatement("select * from perfil");
            rs = ps.executeQuery();
            
            perfil = new Perfil();
            perfil.setId_perfil(0);
            perfil.setDescripcion("Seleccione Perfil");
            vectorPerfil.add(perfil);
            
            while(rs.next()){
                perfil = new Perfil();
                perfil.setId_perfil(rs.getInt("id_perfil"));
                perfil.setDescripcion(rs.getString("descripcion"));
                vectorPerfil.add(perfil);
            }
            rs.close();
            
        } catch (Exception ex) {
            System.err.println("Error, "+ex);
        }
        return vectorPerfil;
    }
    
    
}
