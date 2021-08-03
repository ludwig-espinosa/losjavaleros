package Controlador;
import static Controlador.CtrLogin.lgn;
import DreamsGifts.*;
import Modelo.Articulo;
import Modelo.Categoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Modelo.ConsultaArticulo;
import Modelo.ConsultaCategoria;

import Modelo.ConsultaPack;
import Modelo.ConsultaProveedor;
import Modelo.DetallePack;
import Modelo.Pack;
import Modelo.Proveedor;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class CtrInventario implements ActionListener{
    private static Inventario inven = new Inventario();
    private static ConsultaArticulo invenArt = new ConsultaArticulo();
    //private static ConsultaCategoria invenCat= new ConsultaCategoria();
    private static ConsultaPack invenPack = new ConsultaPack();
    //private static ConsultaProveedor invenProvee = new ConsultaProveedor();
    private static ConsultaPack conPack = new ConsultaPack();
    private static ConsultaCategoria conCat = new ConsultaCategoria();
    private static ConsultaProveedor conProv = new ConsultaProveedor();

    public void iniciar(){
         if (!inven.isVisible()){
             inven.setVisible(true);
         }
     }

     public CtrInventario(){
         this.iniciarPackInicio();
         this.iniciarcategoria();

     }
     
     
    public void borrarTabla(JTable tabla){
       DefaultTableModel rm = (DefaultTableModel) tabla.getModel();
       while (rm.getRowCount() > 0){
           rm.removeRow(0);
       }
   }
//      public void iniciarArticulo(){
//           inven.ArticuloBtnAdd(this);
//
//       }
        /*       inicio CRUD Usuarios        */

//
//   public boolean agregarArticulo(){
//       Articulo art = new Articulo();
//       art.setNombre(inven.nomTextArticulo.getText());
//       art.setCodigo(inven.codIntArticulo.getText());
//       art.setProveedor(inven.proveedorTextArticulo.isSelected());
//       art.setCantidad(inven.cantIntArticulo.getText());
//       art.setCosto(inven.costoIntArticulo.getText());
//       art.setDescripcion(inven.descTextArticulo.getText());
//       art.setFecha(inven.fechaDArticulo.getText());
//       
//       inven.nomTextArticulo.setText("");
//       inven.codIntArticulo.setText("");
//       inven.descTextArticulo.setText("");       
//       inven.cantIntArticulo.setText("");
//       inven.costoIntArticulo.setText("");
//       inven.proveedorTextArticulo.setText("");
//       inven.fechaDarticulo.setTexT("");
//       
//       
//         if (!invenArt.buscar(art)) {
//             System.out.println("intentando agregar");
//             invenArt.registrar(art);
//            return true;
//        } else{
//             System.out.println("a modificar");
//             return invenArt.modificar(art);
//         }
//        
//   }
//   
//   public void actualizarArticulos(){
//        this.borrarTabla(inven.articulosTable);
//        ResultSet rs = invenArt.llamarTodos();
//        Object[] row;
//        row = new Object[7];
//        DefaultTableModel rm = (DefaultTableModel) inven.articulosTable.getModel();
//        try {
//            while (rs.next()){
//                row[0] = rs.getString("Nombre");
//                row[1] = rs.getString("Codigo");
//                row[2] = rs.getString("Cantidad");
//                row[3] = rs.getString("Fecha");
//                row[4] = rs.getBoolean("Costo");
//                row[5] = rs.getBoolean("Proveedor");
//                row[6] = rs.getBoolean("Descripcion");
//
//                rm.addRow(row);  
//            }
//            } catch (SQLException ex) {
//                System.out.println(ex);
//        }
//    }
//   

/*         INICIO CRUD PACK          */
   
   public void iniciarPackInicio(){
    inven.packSave.addActionListener(this);
    this.actualizarTablaCategoria();
}
   
   public void actualizarTablaCategoria(){
        this.borrarTabla(inven.categoriaTable);
        ResultSet rs = conCat.llamarTodos();
        Object[] row;
        row = new Object[3];
        DefaultTableModel rm = (DefaultTableModel) inven.categoriaTable.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString(3);
                row[1] = rs.getString(2);
                row[2] = rs.getBoolean(4);

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
   }
   
   
   public void agregarPack(){
       Pack pack = new Pack();
       pack.setDescripcion(inven.packDesc.getText());
       pack.setEstado(true);
       pack.setNombre(inven.packNombre.getText());
       pack.setPrecio(Integer.parseInt(inven.packValor.getText()));
       if (conPack.registrar(pack)){
           DefaultTableModel table = (DefaultTableModel) inven.packTabla.getModel();
           DetallePack detPack = new DetallePack();
           detPack.setIdPack(conPack.PackIdPorNombre(pack.getNombre()));
           for (int i = 0; i < table.getRowCount(); i++) {
               detPack.setIdArticulo(0);
               detPack.setCantidad(1);
               conPack.agregarArticulo(detPack, pack);
           }
       }
       
   }
   
   /*         INICIO CRUD PACK          */
   public void iniciarcategoria(){
       inven.categoriaSave.addActionListener(this);
       this.actualizarTablaProveedor();
   }
   
   public void agregarCategoria(){
       Categoria cat = new Categoria();
       cat.setCodigo(inven.categoriaCodigo.getText());
       cat.setDescripcion(inven.categoriaDesc.getText());
       cat.setNombre(inven.categoriaNombre.getText());
       cat.setEstado(inven.categoriaActive.isSelected());
       if (!conCat.buscar(cat)) {
            if (conCat.registrar(cat)) {
                JOptionPane.showMessageDialog(lgn, "categoria agregada con exito");   
            } else {
                JOptionPane.showMessageDialog(lgn, "hubo un error al agregar la categoria");
            }   
       } else{
           conCat.modificar(cat);
       }
   }
   
   
   /*         INICIO CRUD Proveedores         */
   
   public void actualizarTablaProveedor(){
        this.borrarTabla(inven.proveedoresTable);
        ResultSet rs = conProv.llamarTodos();
        Object[] row;
        row = new Object[6];
        DefaultTableModel rm = (DefaultTableModel) inven.proveedoresTable.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString("nombre");
                row[1] = rs.getString("RUT");
                row[2] = rs.getString("correo");
                row[3] = rs.getString("fono");
                row[4] = rs.getString("ciclo");
                row[5] = rs.getBoolean("estado");


                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
   }
   public void agregarProveedor() {
    Proveedor prov = new Proveedor();
    prov.setDireccion(inven.proveedoresDireccion.getText());
    prov.setCicloFac(inven.proveedoresCiclo.getText());
    prov.setCorreo(inven.proveedoresCorreo.getText());
    prov.setEstado(inven.proveedoresActive.isSelected());
    prov.setFono(inven.proveedoresTel.getText());
    prov.setNombre(inven.proveedoresNombre.getText());
    prov.setRut(inven.proveedoresRut.getText() + inven.proveedoresRutDv.getText());
       if (!conProv.buscar(prov)) {
           if (conProv.registrar(prov)) {
            JOptionPane.showMessageDialog(lgn, "Proveedor registrado con exito");
            }
            else{
                 JOptionPane.showMessageDialog(lgn, "Ocurrio un error al intentar registrar al proveedor");
        }
       }else {
           conProv.modificar(prov);
       }
        
    }
   
   
    @Override
     public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == inven.ArticuloBtnAdd) {
//            this.agregarArticulo();
//            this.actualizarArticulos();
//        }
        if (e.getSource() == inven.packSave) {
            System.out.println("intentando registrar pack");
            this.agregarPack();
        }
        if (e.getSource() == inven.categoriaSave) {
            System.out.println("intentando agregar");
            this.agregarCategoria();
            this.actualizarTablaCategoria();
        }
    }
   

}
