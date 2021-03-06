package Comercial.datos;


import Comercial.dominio.Cliente;
import Comercial.dominio.Deudores;
import Comercial.dominio.ProcesoProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PERSONAL
 */
public class ClienteDao {
    
    private static final String SQL_SELECT = "SELECT * FROM tbl_cliente";
    private static final String SQL_INSERT = "INSERT INTO tbl_cliente(ID, cliente, Nit, Estatus_Cliente, telefono) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE tbl_cliente SET ID=?, cliente=?, Nit=?, Estatus_Cliente=?, telefono=? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM tbl_cliente WHERE ID=?";
    private static final String SQL_QUERY = "SELECT ID, cliente, Nit, Estatus_Cliente, telefono FROM tbl_cliente WHERE ID = ?";
     public List<Cliente> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente venta = null;
        List<Cliente> ventas = new ArrayList<Cliente>();
        try {
            /**
             *
             * conecion con sql de selecccion
             */
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                /**
                 *
                 * busqueda de datos de la bitacocora en la de usuarios
                 */
                String id_cliente  = rs.getString("Id_cliente");
                String cliente  = rs.getString("cliente");
                String nit =      rs.getString("Nit");
                String telefono = rs.getString("telefono");
                
                 
                    String estatus_cliente  = rs.getString("Estatus_Cliente");
                      ;

                /**
                 *
                 * concatenacionde de variables de de busqueda
                 */
              

                  venta = new Cliente();
                  venta.setCliente(cliente);
                    venta.setId_cliente(id_cliente);
                 venta.setNit(nit);
                 venta.setTelefono(telefono);
                 
               venta.setEstatus_Cliente(estatus_cliente);
                 
                    ventas.add(venta);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
          
    Conexion.close(stmt);
            Conexion.close(conn);
        }
  return ventas;

       
    }
 public   Cliente query(Cliente venta){
        /**
         *
         * conexion de base de datos
         */
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> ventas = new ArrayList<Cliente>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
             stmt.setString(1, venta.getId_cliente());
             
          
            rs = stmt.executeQuery();
            while (rs.next()) {
              
                String cliente  = rs.getString("Cliente");
                String nit =      rs.getString("Nit");
                String telefono = rs.getString("telefono");
              
                    String estatus_cliente  = rs.getString("Estatus_Cliente");
                  String id = rs.getString("Id_cliente");
               
                /**
                 *
                 * concatenacionde de variables de de busqueda
                 */
              

                  venta = new Cliente();
                  venta.setCliente(cliente);
              venta.setId_cliente(id);
                 venta.setNit(nit);
                 venta.setTelefono(telefono);
               
               venta.setEstatus_Cliente(estatus_cliente);
                
                    ventas.add(venta);
                /**
                 *
                 * busqueda de datos de la bitacocora en la de usuarios
                 */
                
               
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
           
            Conexion.close(stmt);
            Conexion.close(conn);
        }
       return venta;
 
     }
     public int insert(Cliente insertar)  {
         ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stmt = null;
      
        int rows = 0;
        try {
             
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,  insertar.getId_cliente());
            stmt.setString(2,  insertar.getCliente());       
            stmt.setString(3,   insertar.getNit());
            stmt.setString(4,  insertar.getEstatus_Cliente());
            stmt.setString(5,  insertar.getTelefono());
          
                
       
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
          
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
        public int update(Cliente mod) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,  mod.getId_cliente());
            stmt.setString(2,  mod.getCliente());       
            stmt.setString(3,  mod.getNit());
            stmt.setString(4,  mod.getEstatus_Cliente());
            stmt.setString(5,  mod.getTelefono());
            stmt.setString(6,  mod.getId_cliente());
           
   rows = stmt.executeUpdate();
         
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

       public int delete(Cliente eliminar) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
             
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
           
           System.out.println("Ejecutando query:" + SQL_DELETE);
               stmt.setString(1,  eliminar.getId_cliente());
         
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
     
    
}
