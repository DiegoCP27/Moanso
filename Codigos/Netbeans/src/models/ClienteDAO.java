package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    Conexion conectar=new Conexion();
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
            
        public List<Cliente> listar(){
            List<Cliente> listarClientes = new ArrayList<>();
            String sql="SELECT * FROM clientes";
            try{
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                rs= ps.executeQuery();
                while(rs.next()){
                    Cliente cli=new Cliente();
                    cli.setId(rs.getInt(1));
                    cli.setNombres(rs.getString(2));
                    cli.setApellidos(rs.getString(3));
                    cli.setTelefono(rs.getString(4));
                    cli.setDireccion(rs.getString(5));
                    cli.setCorreo(rs.getString(6));
                    listarClientes.add(cli);
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            return listarClientes;
            }    
        public int agregar(Cliente c){
            String sql="INSERT INTO clientes VALUES(?,?,?,?,?,?)";
            try{
                con=conectar.getConnection  ();
                ps=con.prepareStatement(sql);
                ps.setInt(1,c.getId());
                ps.setString(2,c.getNombres());
                ps.setString(3,c.getApellidos());
                ps.setString(4,c.getTelefono());
                ps.setString(5,c.getDireccion());
                ps.setString(6,c.getCorreo());
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println("Error al agregar: "+e.getMessage());
            }
              return 1;
        }  
        
        public int Actualizar(Cliente c){
            int r = 0;
                String sql = "UPDATE clientes SET idClientes = ?, Nombres = ?, Apellidos = ?, Telefono = ?, Direccion = ?, Correo = ?";
                try{
                    con = conectar.getConnection();
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, c.getId());
                    ps.setString(2, c.getNombres());
                    ps.setString(3, c.getApellidos());
                    ps.setString(4,c.getTelefono());
                    ps.setString(5,c.getDireccion());
                    ps.setString(6,c.getCorreo());
                    r = ps.executeUpdate();
                    if(r==1){
                        return 1;
                    }else{
                        return 0;
                    }
                }catch(Exception e){
                    System.out.println("Error al actualizar"+e.getMessage()); 
                }
            return r;
        }
        
        public void eliminar(int id){
            String sql = "DELETE FROM clientes WHERE idClientes = "+id;
            try{
                con = conectar.getConnection();
                ps = con.prepareStatement(sql);
                ps.executeUpdate();
            } catch(Exception e){
                System.out.println("Error al eliminar: "+e.getMessage());
            }
        }
}
