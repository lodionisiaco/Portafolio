/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author camil
 */
public class Cargo {
    
    private int id_cargo;
    private String descripcion;

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
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
    
    public Vector<Cargo> mostrarCargo(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Vector<Cargo> vectorCargo = new Vector<Cargo>();
        Cargo cargo = null;
        try {
            Conexion conexion = new Conexion();
            
            ps = conexion.getConnection().prepareStatement("select * from cargo");
            rs = ps.executeQuery();
            
            cargo = new Cargo();
            cargo.setId_cargo(0);
            cargo.setDescripcion("Seleccione Cargo");
            vectorCargo.add(cargo);
            
            while(rs.next()){
                cargo = new Cargo();
                cargo.setId_cargo(rs.getInt("id_cargo"));
                cargo.setDescripcion(rs.getString("descripcion"));
                vectorCargo.add(cargo);
            }
            rs.close();
            
        } catch (Exception ex) {
            System.err.println("Error, "+ex);
        }
        return vectorCargo;
    }
    
    
    
}
