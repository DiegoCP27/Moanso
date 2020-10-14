package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Conexion conectar=new Conexion();
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
            
        public List<Producto> listar(){
            List<Producto> listarProductos = new ArrayList<>();
            String sql="SELECT * FROM productos";
            try{
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                rs= ps.executeQuery();
                while(rs.next()){
                    Producto pro=new Producto();
                    pro.setId(rs.getInt(1));
                    pro.setNombres(rs.getString(2));
                    pro.setCategoria(rs.getString(3));
                    pro.setStock(rs.getString(4));
                    pro.setPrecio(rs.getString(5));
                    listarProductos.add(pro);
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            return listarProductos;
            }    
        public int agregar(Producto p){
            String sql="INSERT INTO productos VALUES(?,?,?,?,?)";
            try{
                con=conectar.getConnection  ();
                ps=con.prepareStatement(sql);
                ps.setInt(1,p.getId());
                ps.setString(2,p.getNombres());
                ps.setString(3,p.getCategoria());
                ps.setString(4,p.getStock());
                ps.setString(5,p.getPrecio());
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println("Error al agregar: "+e.getMessage());
            }
              return 1;
        }  
        
        public int Actualizar(Producto p){
            int r = 0;
                String sql = "UPDATE productos SET idProductos = ?, Nombre = ?, Categoía = ?, Stock = ?, Precio = ?";
                try{
                    con = conectar.getConnection();
                    ps.setInt(1,p.getId());
                    ps.setString(2,p.getNombres());
                    ps.setString(3,p.getCategoria());
                    ps.setString(4,p.getStock());
                    ps.setString(5,p.getPrecio());
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
            String sql = "DELETE FROM productos WHERE idProductos = "+id;
            try{
                con = conectar.getConnection();
                ps = con.prepareStatement(sql);
                ps.executeUpdate();
            } catch(Exception e){
                System.out.println("Error al eliminar: "+e.getMessage());
            }
        }
}
