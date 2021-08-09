/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DreamsGifts.Compras;
import Modelo.ConsultaArticulo;
import Modelo.ConsultaDetalleFact;
import Modelo.ConsultaFactura;
import Modelo.ConsultaProveedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ludwig
 */
public class CtrCompras implements ActionListener {
    private static Compras compra;
    private static ConsultaProveedor conprov = new ConsultaProveedor();
    private static ConsultaArticulo conart = new ConsultaArticulo();
    private static ConsultaDetalleFact condetf = new ConsultaDetalleFact();
    private static ConsultaFactura confact = new ConsultaFactura();
    

     public void iniciar(){
        if (!compra.isVisible()){
            compra.setVisible(true);
        }
    }
    
     public void iniciarCompras(){
       compra.SavePedid.addActionListener(this);
       compra.CancelPedid.addActionListener(this);
       compra.AddPedid.addActionListener(this);
       compra.RemovPedid.addActionListener(this);
       compra.SearchRegFact.addActionListener(this);
       compra.CancelRegComp.addActionListener(this);
       compra.SaveRegComp.addActionListener(this);
       compra.CancelDetailComp.addActionListener(this);
       compra.SaveDeailtComp.addActionListener(this);
       compra.SearchRevFact.addActionListener(this);
       compra.CancelRevFact.addActionListener(this);
       compra.SaveRevFact.addActionListener(this);
       compra.EditRevFact.addActionListener(this);
       compra.SaveOC.addActionListener(this);
       //compra.VendorSoliComp.addItemListener(this::comboBoxitemStateChanged);
       //compra.ProveedorComp.addItemListener(this::comboBoxitemStateChanged);
       //compra.RutProveeRevComp.addItemListener(this::comboBoxitemStateChanged);
       this.actualizarTablaCompras();
   }
     
      public CtrCompras() throws SQLException {
      compra = new Compras();
      this.iniciarCompras();
      this.actualizarComboBoxProveedor();    
      
   }
       public String ObtenerRUT () {
      String rutobtn;
        rutobtn = (compra.RutProveeComp.getText()+compra.DVProveComp.getText());
      String rutprov = rutobtn;
      System.out.println(rutprov);
      return rutprov;
  }
       public String ObtenerRUTRev () {
      String rutobtn;
        rutobtn = (compra.RutProveeComp1.getText()+compra.DVProveComp1.getText());
      String rutprov2 = rutobtn;
      System.out.println(rutprov2);
      return rutprov2;
  }
       
    
    public void borrarTabla(JTable tabla){
       DefaultTableModel rm = (DefaultTableModel) tabla.getModel();
       while (rm.getRowCount() > 0){
           rm.removeRow(0);
       }
   }
    
   public boolean agregarCompras(){
       Compras compra = new Compras();
       DefaultComboBoxModel CbProveedor = (DefaultComboBoxModel) compra.VendorSoliComp.getModel();
       compra.setID_Proveedor((int) conprov.buscarIdPorName((int) CbProveedor.getSelectedItem()));
       compra.rutvent.setText("");
       compra.dvclient.setText("");
       compra.VAddrClient.setText("");
       compra.VNameClient.setText("");
       compra.VNameRecp.setText("");
       compra.VNumberContact.setText("");
       compra.codigoTransaccion.setText("");
       compra.ValorVenta.setText("");
       compra.ValorVenta.setText("");
            System.out.println(confact.getID_Proveedor());
            System.out.println(CbProveedor.getSelectedItem());
         if (!condetf.buscar(compra)) {
             System.out.println("intentando agregar");
             condetf.registrar(compra);
            return true;
        } else{
             System.out.println("a modificar");
             return confact.modificar(compras);
         }
        
   }
   
   
   
   public void actualizarTablaPedidosComp(){
        this.borrarTabla(compra.TablaPedidosComp);
        ResultSet rs = condetf.listadoFact();
        Object[] row;
        row = new Object[10];
        DefaultTableModel rm = (DefaultTableModel) compra.TablaPedidosComp.getModel();
        try {
            while (rs.next()){
                
                row[0] = rs.getString("Orden de Venta");
                row[1] = rs.getString("Nombre de Cliente");
                row[2] = rs.getString("Fecha de Entrega");
                row[3] = rs.getString("Bloque Horario");
                row[4] = rs.getString("Comuna");
                row[5] = rs.getString("Direccion de Entrega");
                row[6] = rs.getString("Nro de Contacto");
                row[7] = rs.getString("Banco");
                row[8] = rs.getString("Codigo_TRX");
                row[9] = rs.getString("Estado");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
   
      public void actualizarTablaDetailFact(){
        this.borrarTabla(compra.TablaDetailFact);
        ResultSet rs = condetf.listadoFact();
        Object[] row;
        row = new Object[10];
        DefaultTableModel rm = (DefaultTableModel) compra.TablaDetailFact.getModel();
        try {
            while (rs.next()){
                
                row[0] = rs.getString("Orden de Venta");
                row[1] = rs.getString("Nombre de Cliente");
                row[2] = rs.getString("Fecha de Entrega");
                row[3] = rs.getString("Bloque Horario");
                row[4] = rs.getString("Comuna");
                row[5] = rs.getString("Direccion de Entrega");
                row[6] = rs.getString("Nro de Contacto");
                row[7] = rs.getString("Banco");
                row[8] = rs.getString("Codigo_TRX");
                row[9] = rs.getString("Estado");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
      
       public void actualizarTablaFactRev(){
        this.borrarTabla(compra.TablaFactRev);
        ResultSet rs = condetf.listadoFact();
        Object[] row;
        row = new Object[10];
        DefaultTableModel rm = (DefaultTableModel) compra.TablaFactRev.getModel();
        try {
            while (rs.next()){
                
                row[0] = rs.getString("Orden de Venta");
                row[1] = rs.getString("Nombre de Cliente");
                row[2] = rs.getString("Fecha de Entrega");
                row[3] = rs.getString("Bloque Horario");
                row[4] = rs.getString("Comuna");
                row[5] = rs.getString("Direccion de Entrega");
                row[6] = rs.getString("Nro de Contacto");
                row[7] = rs.getString("Banco");
                row[8] = rs.getString("Codigo_TRX");
                row[9] = rs.getString("Estado");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
       public void actualizarTablaRevFact(){
        this.borrarTabla(compra.TablaRevFact);
        ResultSet rs = condetf.listadoFact();
        Object[] row;
        row = new Object[10];
        DefaultTableModel rm = (DefaultTableModel) compra.TablaRevFact.getModel();
        try {
            while (rs.next()){
                
                row[0] = rs.getString("Orden de Venta");
                row[1] = rs.getString("Nombre de Cliente");
                row[2] = rs.getString("Fecha de Entrega");
                row[3] = rs.getString("Bloque Horario");
                row[4] = rs.getString("Comuna");
                row[5] = rs.getString("Direccion de Entrega");
                row[6] = rs.getString("Nro de Contacto");
                row[7] = rs.getString("Banco");
                row[8] = rs.getString("Codigo_TRX");
                row[9] = rs.getString("Estado");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
   
   public void actualizarComboBoxProveedor() throws SQLException{
       DefaultComboBoxModel cbModel = (DefaultComboBoxModel) compra.VendorSoliComp.getModel();
       cbModel.removeAllElements();
       ResultSet rs = conprov.llamarActivos();
       while (rs.next()){
           cbModel.addElement(rs.getString(2));
       }       
   }
   
      
   public void ListarArticulos() {
        DefaultListModel li = new DefaultListModel();
        ResultSet rs = conart.llamarActivos();
        try {
            while(rs.next()) {
                li.addElement(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        compra.DetailArtic1.setModel(li);
        
   }
   
   public int existeTabla(JTable tabla,Object valor , int columna){
       DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
       if (valor != null) {
            for(int i=0; i < tb.getRowCount(); i++) {
                 if (valor.equals(tb.getValueAt(i, columna).toString())) {
                     return i;
                }
            }
       }    
       return -1;
   } 
   
   public void packQuitarArticulo() {
       DefaultTableModel tb = (DefaultTableModel) compra.DetailArtic1.getModel();
       String nombreAr = compra.DetailArtic1.getSelectedValue();
       int cantidad = Integer.parseInt(compra.CantArtic.getText());
       int pos = this.existeTabla(compra.TablaArticPed, nombreAr, 1);
       if (pos != -1) {
            int cantidadAct = (int) tb.getValueAt(pos, 2);
            if (cantidadAct - cantidad > 0) {
                tb.setValueAt(cantidadAct - cantidad, pos, 2);
           } else {
                tb.removeRow(pos);
            }
       }
   }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compra.SavePedid) {
            System.out.println("intentando registrar Solicitud de Compra");
            this.agregarCompras();
            this.actualizarTablaPedidosComp();
        }
        if (e.getSource() == inven.categoriaSave) {
            System.out.println("intentando agregar");
            this.agregarCategoria();
            this.actualizarTablaCategoria();
        }
        if(e.getSource() == inven.proveedoresSave){
            this.agregarProveedor();
            this.actualizarTablaProveedor();
        }
        if(e.getSource() == compra.AddPedid) {
            this.agregarCompras();
        }
        if (e.getSource() == compra.RemovPedid) {
            this.packQuitarArticulo();
        }
        if (e.getSource() == compra.SearchRegFact) {
            this.packBuscar();
        }
    }
    
}
