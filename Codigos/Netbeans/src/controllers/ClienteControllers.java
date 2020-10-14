
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Cliente;
import models.ClienteDAO;
import views.Principal;

public class ClienteControllers implements ActionListener{
    DefaultTableModel modeloTabla=new DefaultTableModel();
    Principal vista = new Principal();
    ClienteDAO dao=new ClienteDAO();
    
    public ClienteControllers(Principal principal){
        this.vista=principal;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);
    }
    
    public ClienteControllers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnListar){
            limpiarTabla();
            listar(vista.tbDatos);
        }
        if(e.getSource()==vista.btnAgregar){
            agregar();
            limpiarCajas();
            limpiarTabla();
            listar(vista.tbDatos);
        }
        if(e.getSource() == vista.btnEditar){
            int fila = vista.tbDatos.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                vista.btnAgregar.setEnabled(false);
                vista.btnActualizar.setEnabled(true);
                //capturar los datos de la tabla
                int id = Integer.parseInt((String)vista.tbDatos.getValueAt(fila, 0).toString());
                String nombres = (String)vista.tbDatos.getValueAt(fila, 1);
                String apellidos = (String)vista.tbDatos.getValueAt(fila, 2);
                String telefono = (String)vista.tbDatos.getValueAt(fila, 3);
                String direccion = (String)vista.tbDatos.getValueAt(fila, 4);
                String correo = (String)vista.tbDatos.getValueAt(fila, 5);
                //colocar los datos capturados en las cajas de texto
                vista.txtID.setText(id+"");
                vista.txtNombres.setText(nombres);
                vista.txtApellidos.setText(apellidos);
                vista.txtTelefono.setText(telefono);
                vista.txtDireccion.setText(direccion);
                vista.txtCorreo.setText(correo);
            }
        }
        if(e.getSource() == vista.btnActualizar){
            actualizar();
            limpiarTabla();
            listar(vista.tbDatos);
            limpiarCajas();
        }
        if(e.getSource() == vista.btnEliminar){
            eliminar();
            limpiarTabla();
            listar(vista.tbDatos);
        }
        if(e.getSource() == vista.btnNuevo){
            limpiarCajas();
            vista.btnAgregar.setEnabled(true);
            vista.btnActualizar.setEnabled(false);
        }
    }
    
    private void eliminar(){
        int fila = vista.tbDatos.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }else{
            int id = Integer.parseInt((String)vista.tbDatos.getValueAt(fila, 0).toString());
            this.dao.eliminar(id);
            JOptionPane.showMessageDialog(vista, "Cliente eliminado");
        }
    }
    
    private void actualizar(){
        //capturar valores de las cajas de texto
        int id = Integer.parseInt((String)vista.txtID.getText());
        String nombres = vista.txtNombres.getText();
        String apellidos = vista.txtApellidos.getText();
        String telefono = vista.txtTelefono.getText();
        String direccion = vista.txtDireccion.getText();
        String correo = vista.txtCorreo.getText();
        //Setear los valores capturados al objeto persona
        if(nombres.equals(vista.txtNombres) && apellidos.equals(vista.txtApellidos) && telefono.equals(vista.txtTelefono)
                && direccion.equals(vista.txtDireccion) && correo.equals(vista.txtCorreo)){
            JOptionPane.showMessageDialog(vista, "Los datos de la persona son los mismos");
            }else{
            Cliente cliente = new Cliente(id, nombres, apellidos, telefono, direccion, correo);
            //Metodo del PersonaDAO
            int r = this.dao.Actualizar(cliente);
            if(r==1){
                JOptionPane.showMessageDialog(vista, "Cliente actualizado");
            }else{
                JOptionPane.showMessageDialog(vista, "Error al actualizar");
            }
        }
    }
    private void agregar(){
        int id = Integer.parseInt((String)vista.txtID.getText());
        String nombres=vista.txtNombres.getText();
        String apellidos=vista.txtApellidos.getText();
        String telefono=vista.txtTelefono.getText();
        String direccion=vista.txtDireccion.getText();
        String correo=vista.txtCorreo.getText();
            if(nombres.equals("") || apellidos.equals("") || telefono.equals("") || direccion.equals("") || correo.equals("")){
                JOptionPane.showMessageDialog(vista, "Los datos de la persona no pueden estar vacios");
            }else{
                Cliente cliente=new Cliente(id,nombres, apellidos, telefono, direccion, correo);
                int rpta=this.dao.agregar(cliente);
                if(rpta==1){
                    JOptionPane.showMessageDialog(vista, "Cliente registrado con exito");
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al agregar persona");
                }
            }
    }
    private void listar (JTable tabla){
        this.modeloTabla=(DefaultTableModel)tabla.getModel();
        List<Cliente> listaClientes= this.dao.listar();
        Object[]objeto=new Object[6];
        for(int i=0;i<listaClientes.size();i++){
            objeto[0]=listaClientes.get(i).getId();
            objeto[1]=listaClientes.get(i).getNombres();
            objeto[2]=listaClientes.get(i).getApellidos();
            objeto[3]=listaClientes.get(i).getTelefono();
            objeto[4]=listaClientes.get(i).getDireccion();
            objeto[5]=listaClientes.get(i).getCorreo();
            modeloTabla.addRow(objeto);
        }
        vista.tbDatos.setModel(modeloTabla);
    }
    private void limpiarTabla(){
        for (int i=0;i<vista.tbDatos.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }
    private void limpiarCajas(){
        vista.txtID.setText("");
        vista.txtNombres.setText("");
        vista.txtApellidos.setText("");
        vista.txtTelefono.setText("");
        vista.txtDireccion.setText("");
        vista.txtCorreo.setText("");
    }
}