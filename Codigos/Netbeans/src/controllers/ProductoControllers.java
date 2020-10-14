/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Producto;
import models.ProductoDAO;
import views.Principal2;

/**
 *
 * @author Usuario
 */
public class ProductoControllers implements ActionListener{
    DefaultTableModel modeloTabla=new DefaultTableModel();
    Principal2 vista2 = new Principal2();
    ProductoDAO dao=new ProductoDAO();
    
    public ProductoControllers(Principal2 principal){
        this.vista2=principal;
        this.vista2.btnListar.addActionListener(this);
        this.vista2.btnAgregar.addActionListener(this);
        this.vista2.btnEditar.addActionListener(this);
        this.vista2.btnActualizar.addActionListener(this);
        this.vista2.btnEliminar.addActionListener(this);
        this.vista2.btnNuevo.addActionListener(this);
    }
    
    public ProductoControllers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista2.btnListar){
            limpiarTabla();
            listar(vista2.tbDatos2);
        }
        if(e.getSource()==vista2.btnAgregar){
            agregar();
            limpiarCajas();
            limpiarTabla();
            listar(vista2.tbDatos2);
        }
        if(e.getSource() == vista2.btnEditar){
            int fila = vista2.tbDatos2.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista2, "Debe seleccionar una fila");
            }else{
                vista2.btnAgregar.setEnabled(false);
                vista2.btnActualizar.setEnabled(true);
                //capturar los datos de la tabla
                int id = Integer.parseInt((String)vista2.tbDatos2.getValueAt(fila, 0).toString());
                String nombre = (String)vista2.tbDatos2.getValueAt(fila, 1);
                String categoria = (String)vista2.tbDatos2.getValueAt(fila, 2);
                String stock = (String)vista2.tbDatos2.getValueAt(fila, 3);
                String precio = (String)vista2.tbDatos2.getValueAt(fila, 4);
                //colocar los datos capturados en las cajas de texto
                vista2.txtID2.setText(id+"");
                vista2.txtNombre.setText(nombre);
                vista2.txtCategoria.setText(categoria);
                vista2.txtStock.setText(stock);
                vista2.txtPrecio.setText(precio);
            }
        }
        if(e.getSource() == vista2.btnActualizar){
            actualizar();
            limpiarTabla();
            listar(vista2.tbDatos2);
            limpiarCajas();
        }
        if(e.getSource() == vista2.btnEliminar){
            eliminar();
            limpiarTabla();
            listar(vista2.tbDatos2);
        }
        if(e.getSource() == vista2.btnNuevo){
            limpiarCajas();
            vista2.btnAgregar.setEnabled(true);
            vista2.btnActualizar.setEnabled(false);
        }
    }
    
    private void eliminar(){
        int fila = vista2.tbDatos2.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista2, "Debe seleccionar una fila");
        }else{
            int id = Integer.parseInt((String)vista2.tbDatos2.getValueAt(fila, 0).toString());
            this.dao.eliminar(id);
            JOptionPane.showMessageDialog(vista2, "Producto eliminado");
        }
    }
    
    private void actualizar(){
        //capturar valores de las cajas de texto
        int id = Integer.parseInt((String)vista2.txtID2.getText());
        String nombre = vista2.txtNombre.getText();
        String categoria = vista2.txtCategoria.getText();
        String stock = vista2.txtStock.getText();
        String precio = vista2.txtPrecio.getText();
        //Setear los valores capturados al objeto persona
        if(nombre.equals(vista2.txtNombre) && categoria.equals(vista2.txtCategoria) && stock.equals(vista2.txtStock)
                && precio.equals(vista2.txtPrecio)){
            JOptionPane.showMessageDialog(vista2, "Los datos del producto son los mismos");
            }else{
            Producto producto = new Producto(id, nombre, categoria, stock, precio);
            //Metodo del PersonaDAO
            int r = this.dao.Actualizar(producto);
            if(r==1){
                JOptionPane.showMessageDialog(vista2, "Producto actualizado");
            }else{
                JOptionPane.showMessageDialog(vista2, "Error al actualizar");
            }
        }
    }
    private void agregar(){
        int id = Integer.parseInt((String)vista2.txtID2.getText());
        String nombre=vista2.txtNombre.getText();
        String categoria=vista2.txtCategoria.getText();
        String stock=vista2.txtStock.getText();
        String precio=vista2.txtPrecio.getText();
            if(nombre.equals("") || categoria.equals("") || stock.equals("") || precio.equals("")){
                JOptionPane.showMessageDialog(vista2, "Los datos del producto no pueden estar vacios");
            }else{
                Producto producto = new Producto(id, nombre, categoria, stock, precio);
                int rpta=this.dao.agregar(producto);
                if(rpta==1){
                    JOptionPane.showMessageDialog(vista2, "Producto registrado con exito");
                } else {
                    JOptionPane.showMessageDialog(vista2, "Error al agregar producto");
                }
            }
    }
    private void listar (JTable tabla){
        this.modeloTabla=(DefaultTableModel)tabla.getModel();
        List<Producto> listaProductos= this.dao.listar();
        Object[]objeto=new Object[6];
        for(int i=0;i<listaProductos.size();i++){
            objeto[0]=listaProductos.get(i).getId();
            objeto[1]=listaProductos.get(i).getNombres();
            objeto[2]=listaProductos.get(i).getCategoria();
            objeto[3]=listaProductos.get(i).getStock();
            objeto[4]=listaProductos.get(i).getPrecio();
            modeloTabla.addRow(objeto);
        }
        vista2.tbDatos2.setModel(modeloTabla);
    }
    private void limpiarTabla(){
        for (int i=0;i<vista2.tbDatos2.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }
    private void limpiarCajas(){
        vista2.txtID2.setText("");
        vista2.txtNombre.setText("");
        vista2.txtCategoria.setText("");
        vista2.txtStock.setText("");
        vista2.txtPrecio.setText("");
    }
}