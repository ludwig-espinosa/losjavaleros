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
      //this.actualizarComboBoxProveedor();    
      
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
       
    public void ObtenName (){
        String NameClient;
        NameClient = conprov.buscarNamePorRut(this.ObtenerRUT());
        compra.ProveedorComp.setText(NameClient);            
    }
    
    public void ObtenName2 (){
        String NameClient2;
        NameClient2 = conprov.buscarNamePorRut(this.ObtenerRUTRev());
        compra.RutProveeRevComp.setText(NameClient2);            
    }
    
    public void borrarTabla(JTable tabla){
       DefaultTableModel rm = (DefaultTableModel) tabla.getModel();
       while (rm.getRowCount() > 0){
           rm.removeRow(0);
       }
   }
    
   public boolean agregarCompras(){
       Compras compra = new Compras();
       DefaultComboBoxModel CbRrss = (DefaultComboBoxModel) venta.VRedSocialClient.getModel();
       DefaultComboBoxModel CbEstadoDePago = (DefaultComboBoxModel) venta.EstadoDePago.getModel();
       DefaultComboBoxModel CbPack = (DefaultComboBoxModel) venta.PackVenta.getModel();
       DefaultComboBoxModel CbEstadoDeOrden = (DefaultComboBoxModel) venta.EstadoDeOrden.getModel();
       DefaultComboBoxModel CbComuna = (DefaultComboBoxModel) venta.ComunaVent.getModel();
       DefaultComboBoxModel CbBanco = (DefaultComboBoxModel) venta.BancoBox.getModel();
       DefaultComboBoxModel CbBloqHor = (DefaultComboBoxModel) venta.BloqueHorarioVent.getModel();
       compra.setDireccion(venta.VAddrClient.getText());
       compra.setIdCliente(conCliente.buscarIdPorRut(this.ObtenerRUT()));
       compra.setReceptor(venta.VNameRecp.getText());
       compra.setContactoReceptor(venta.VNumberContact.getText());
       compra.setCodigoTransaccion(venta.codigoTransaccion.getText());
       compra.setRrss((int) conrrss.RRSSIdPorNombre((String) CbRrss.getSelectedItem()));
       compra.setIdPack((int) conpack.PackIdPorNombre((String) CbPack.getSelectedItem()));
       compra.setIdBanco((int) conban.BancoIdPorNombre((String) CbBanco.getSelectedItem()));
       compra.setEstadoPago((String) CbEstadoDePago.getSelectedItem());
       compra.setEstadoDeOrden((String) CbEstadoDeOrden.getSelectedItem());
       compra.setIdcomuna((int) concom.ComunaIdPorNombre((String) CbComuna.getSelectedItem()));
       compra.setMonto(Integer.parseInt(venta.ValorVenta.getText()));
       compra.setFechaCompra((Date) venta.FechaCompraVent.getDate());
       compra.setFechaEntrega((Date) venta.FechanEntregaVent.getDate());
       compra.setBloqueHorario((String) CbBloqHor.getSelectedItem().toString());
       compra.rutvent.setText("");
       compra.dvclient.setText("");
       compra.VAddrClient.setText("");
       compra.VNameClient.setText("");
       compra.VNameRecp.setText("");
       compra.VNumberContact.setText("");
       compra.codigoTransaccion.setText("");
       compra.ValorVenta.setText("");
       compra.ValorVenta.setText("");
            System.out.println(compra.getIdBanco());
            System.out.println(CbBanco.getSelectedItem());
         if (!conventas.buscar(compra)) {
             System.out.println("intentando agregar");
             conventas.registrar(vent);
            return true;
        } else{
             System.out.println("a modificar");
             return conventas.modificar(vent);
         }
        
   }
   
   public void actualizarTablaCompras(){
        this.borrarTabla(compra.TablaPedidosComp);
        this.borrarTabla(compra.TablaDetailFact);
        this.borrarTabla(compra.TablaFactRev);
        this.borrarTabla(compra.TablaRevFact);
        ResultSet rs = condetf.llamarActualizacionTabla();
        Object[] row;
        row = new Object[10];
        DefaultTableModel rm = (DefaultTableModel) venta.TablaVentas.getModel();
        //String namclient;
        //String comuna;
       // String banco;
        //String codtrx;
        try {
            while (rs.next()){
                //namclient = conCliente.buscarNamePorId(rs.getInt("ID_Cliente"));
                //comuna = concom.buscarNamePorId(rs.getInt("comuna_id"));
                row[0] = rs.getString("Orden de Venta");
                row[1] = rs.getString("Nombre de Cliente");//namclient;
                row[2] = rs.getString("Fecha de Entrega");
                row[3] = rs.getString("Bloque Horario");
                row[4] = rs.getString("Comuna");//comuna;
                row[5] = rs.getString("Direccion de Entrega");
                row[6] = rs.getString("Nro de Contacto");
                row[7] = rs.getString("Banco");//banco;
                row[8] = rs.getString("Codigo_TRX");//codtrx;
                row[9] = rs.getString("Estado");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
