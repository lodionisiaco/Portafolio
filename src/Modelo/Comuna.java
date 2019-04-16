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
public class Comuna {
    
    private int id_comuna;
    private String descripcion;

    public int getId_comuna() {
        return id_comuna;
    }

    public void setId_comuna(int id_comuna) {
        this.id_comuna = id_comuna;
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
    
    public Vector<Comuna> mostrarComuna(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Vector<Comuna> vectorComuna = new Vector<Comuna>();
        Comuna comuna = null;
        try {
            Conexion conexion = new Conexion();
            
            ps = conexion.getConnection().prepareStatement("select * from comuna");
            rs = ps.executeQuery();
            
            comuna = new Comuna();
            comuna.setId_comuna(0);
            comuna.setDescripcion("Seleccione comuna");
            vectorComuna.add(comuna);
            
            while(rs.next()){
                comuna = new Comuna();
                comuna.setId_comuna(rs.getInt("id_comuna"));
                comuna.setDescripcion(rs.getString("descripcion"));
                vectorComuna.add(comuna);
            }
            rs.close();
            
        } catch (Exception ex) {
            System.err.println("Error, "+ex);
        }
        return vectorComuna;
    }
    
}
