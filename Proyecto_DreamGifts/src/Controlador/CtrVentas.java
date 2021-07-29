/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DreamsGifts.Ventas;
import Modelo.Venta;
import Modelo.ConsultaVentas;
import Modelo.Cliente;
import Modelo.ConsultaCliente;
import Modelo.ConsultaComuna;
import Modelo.ConsultaRedSocial;
import Modelo.ConsultasBanco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lespinosa
 */
public class CtrVentas implements ActionListener {
    private static Ventas venta = new Ventas();
    private static ConsultaCliente conCliente = new ConsultaCliente();
    private static ConsultaVentas conventas = new ConsultaVentas();
    private static ConsultaRedSocial conrrss = new ConsultaRedSocial();
    private static ConsultaComuna concomu = new ConsultaComuna();
    private static ConsultasBanco conban = new ConsultasBanco();
   // privata static ConsultaPack conpack = new ConsultaPack();
    
    public void iniciar(){
        if (!venta.isVisible()){
            venta.setVisible(true);
        }
    }
/*       inicio CRUD Ventas        */

   public void iniciarVentas(){
       venta.SaveVenta.addActionListener(this);
       venta.CancelVenta.addActionListener(this);
       venta.SearchClient.addActionListener(this);
       this.actualizarTablaVentas();
   }
   
    public CtrVentas(){
      this.iniciarVentas();
      
   }
    
    public String ObtenerRUT () {
      String rutobtn;
        rutobtn = (venta.rutvent.getText()+venta.dvclient.getText());
      String rutclient = rutobtn;
      System.out.println(rutclient);
      return rutclient;
  }
    
    public void ObtenName (){
        String NameClient;
        NameClient = conCliente.buscarNamePorRut(this.ObtenerRUT());
        venta.VNameClient.setText(NameClient);            
    }
    
        
    public void borrarTabla(JTable tabla){
       DefaultTableModel rm = (DefaultTableModel) tabla.getModel();
       while (rm.getRowCount() > 0){
           rm.removeRow(0);
       }
   }
    
   public boolean agregarVentas(){
       Venta vent = new Venta();
       DefaultComboBoxModel CbRrss = (DefaultComboBoxModel) venta.VRedSocialClient.getModel();
       DefaultComboBoxModel CbEstadoDePago = (DefaultComboBoxModel) venta.EstadoDePago.getModel();
       //DefaultComboBoxModel CbPack = (DefaultComboBoxModel) venta.PackVenta.getModel();
       DefaultComboBoxModel CbEstadoDeOrden = (DefaultComboBoxModel) venta.EstadoDeOrden.getModel();
       vent.setDireccion(venta.VAddrClient.getText());
       vent.setIdCliente(conCliente.buscarIdPorRut(this.ObtenerRUT()));
       vent.setReceptor(venta.VNameRecp.getText());
       vent.setContactoReceptor(venta.VNumberContact.getText());
       vent.setCodigoTransaccion(venta.codigoTransaccion.getText());
       vent.setRrss((int) CbRrss.getSelectedItem());
       vent.setIdEstadoPago((int) CbEstadoDePago.getSelectedItem());
       vent.setEstadoDeOrden((String) CbEstadoDeOrden.getSelectedItem());
       venta.rutvent.setText("");
       venta.dvclient.setText("");
       venta.VAddrClient.setText("");
       venta.VNameClient.setText("");
       venta.VNameRecp.setText("");
       venta.VNumberContact.setText("");
       venta.codigoTransaccion.setText("");

         if (!conventas.buscar(vent)) {
             System.out.println("intentando agregar");
             conventas.registrar(vent);
            return true;
        } else{
             System.out.println("a modificar");
             return conventas.modificar(vent);
         }
        
   }
   
   public void actualizarTablaVentas(){
        this.borrarTabla(venta.TablaVentas);
        ResultSet rs = conventas.llamarTodos();
        Object[] row;
        row = new Object[6];
        DefaultTableModel rm = (DefaultTableModel) venta.TablaVentas.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString("Orden de Venta");
                row[1] = rs.getString("Nombre de Cliente");
                row[2] = rs.getString("Fecha de Entrega");
                row[3] = rs.getString("Bloque Horario");
                row[4] = rs.getString("Direccion de Entrega");
                row[5] = rs.getString("Nro de Contacto");
                row[5] = rs.getBoolean("Estado");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
   /* Fin CRUD Ventas*/   

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == venta.SaveVenta){
            this.agregarVentas();
            this.actualizarTablaVentas();
        }   
        if (e.getSource() == venta.SearchClient) {
            System.out.println("buscando rut");
            this.ObtenName();
        }
       
    }
    
}
