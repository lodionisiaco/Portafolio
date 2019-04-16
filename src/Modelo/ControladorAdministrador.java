/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author camil
 */
public class ControladorAdministrador {
    Conexion conexion = new Conexion();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public boolean Registrar(Personal personal){
        try {
            
            ps = conexion.getConnection().prepareStatement("insert into personal (rut,nombre,apellido_paterno,apellido_materno,f_contacto1,f_contacto2,email,sueldo,calle,numero,depto,id_cargo,id_comuna,id_perfil,fecha_nac)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, personal.getRut());
            ps.setString(2, personal.getApellidoPaterno());
            ps.setString(3, personal.getApellidoMaterno());
            ps.setInt(4, personal.getF_contacto1());
            ps.setInt(5, personal.getF_contacto2());
            ps.setString(6, personal.getEmail());
            ps.setInt(7, personal.getSueldo());
            ps.setString(8, personal.getCalle());
            ps.setInt(9, personal.getNumero());
            ps.setInt(10, personal.getDepto());
            ps.setInt(11, personal.getId_cargo());
            ps.setInt(12, personal.getId_comuna());
            ps.setInt(13, personal.getId_perfil());
            ps.setDate(14, personal.getFecha_nac());
            
            
            int resultado = ps.executeUpdate();
            
            if(resultado>0){
                return true;
            }
            else{
                return false;
            }
            
        } catch (Exception ex) {
            System.err.println("Error, "+ex);
            return false;
        }
    }
    
    public boolean RegistrarUsuario(Usuario usuario){
        try {
           
            ps = conexion.getConnection().prepareStatement("insert into usuario (rut,clave,id_perfil) values (?,?,?)");
            ps.setString(1, usuario.getRut());
            ps.setString(2, usuario.getClave());
            ps.setInt(3, usuario.getId_perfil());
            ps.executeUpdate();
            int resultado = ps.executeUpdate();
            
            if(resultado>0){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error, "+ex);
            return false;
        }
    }
    
    public int verificarUsuario(String usuario){
        try{
            
            ps = conexion.getConnection().prepareStatement("select count(id) from usuario where rut=?");
            ps.setString(1, usuario);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1);
            }
            
            return 1;
         
        }catch(Exception ex){
            return 1;
        }
    }
    
    public boolean comprobarEmail(String correo){
        Pattern patron  =  Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
        
        Matcher matcher = patron.matcher(correo);
        
        return matcher.find();
    }
    
}
