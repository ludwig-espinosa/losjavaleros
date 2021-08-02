package Controlador;
import DreamsGifts.*;
import Modelo.Articulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Modelo.ConsultaArticulo;

import Modelo.ConsultaPack;
import Modelo.DetallePack;
import Modelo.Pack;

public class CtrInventario implements ActionListener{
    private static Inventario inven = new Inventario();
    private static ConsultaArticulo invenArt = new ConsultaArticulo();
    //private static ConsultaCategoria invenCat= new ConsultaCategoria();
    private static ConsultaPack invenPack = new ConsultaPack();
    //private static ConsultaProveedor invenProvee = new ConsultaProveedor();
    private static ConsultaPack conPack = new ConsultaPack();

    public void iniciar(){
         if (!inven.isVisible()){
             inven.setVisible(true);
         }
     }

     public CtrInventario(){
         this.iniciarPackInicio();


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
    inven.packCrearAdd.addActionListener(this);
}
   public void agregarPack(){
       Pack pack = new Pack();
       pack.setDescripcion(inven.packCrearDesc.getText());
       pack.setEstado(true);
       pack.setNombre(inven.packCrearNombre.getText());
       pack.setPrecio(Integer.parseInt(inven.packCrearValor.getText()));
       if (conPack.registrar(pack)){
           DefaultTableModel table = (DefaultTableModel) inven.packCrearTabla.getModel();
           DetallePack detPack = new DetallePack();
           detPack.setIdPack(conPack.PackIdPorNombre(pack.getNombre()));
           for (int i = 0; i < table.getRowCount(); i++) {
               detPack.setIdArticulo(0);
               detPack.setCantidad(1);
               conPack.agregarArticulo(detPack, pack);
           }
       }
       
   }
   
   
   
   
    @Override
     public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == inven.ArticuloBtnAdd) {
//            this.agregarArticulo();
//            this.actualizarArticulos();
//        }
        if (e.getSource() == inven.packCrearAdd) {
            System.out.println("intentando registrar pack");
            this.agregarPack();
        }
    }
   

}
