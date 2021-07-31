/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import DreamsGifts.Administracion;
import DreamsGifts.*;
import Modelo.Articulo;
import Modelo.ConsultaArticulo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Modelo.Conexion;
import Modelo.ConsultaArticulo;
import Modelo.ConsultaCategoria;
import Modelo.ConsultaPack;
import Modelo.ConsultaProveedor;
import Modelo.Usuario;


/**
 *
 * @author Usuario
 */
public class CtrInventario implements ActionListener{

private static Inventario inven = new Inventario();
private static ConsultaArticulo invenArt = new ConsultaArticulo();
private static ConsultaCategoria invenCat= new ConsultaCategoria();
private static ConsultaPack invenPack = new ConsultaPack();
private static ConsultaProveedor invenProvee = new ConsultaProveedor();

  public void iniciar(){
       if (!inven.isVisible()){
           inven.setVisible(true);
       }
   }  
   
   public CtrInventario(){
       this.iniciarArticulo();
    
       
   }
  public void iniciarArticulo(){
       inven.ArticuloBtnAdd(this);
            
   }
    /*       inicio CRUD Usuarios        */

   
   public boolean agregarArticulo(){
       Articulo art = new Articulo();
       art.setNombre(inven.nomTextArticulo.getText());
       art.setCodigo(inven.codIntArticulo.getText());
       art.setProveedor(inven.proveedorTextArticulo.isSelected());
       art.setCantidad(inven.cantIntArticulo.getText());
       art.setCosto(inven.costoIntArticulo.getText());
       art.setDescripcion(inven.descTextArticulo.getText());
       art.setFecha(inven.fechaDArticulo.getText());
       
       inven.nomTextArticulo.setText("");
       inven.codIntArticulo.setText("");
       inven.descTextArticulo.setText("");       
       inven.cantIntArticulo.setText("");
       inven.costoIntArticulo.setText("");
       inven.proveedorTextArticulo.setText("");
       inven.fechaDarticulo.setTexT("");
       
       
         if (!invenArt.buscar(art)) {
             System.out.println("intentando agregar");
             invenArt.registrar(art);
            return true;
        } else{
             System.out.println("a modificar");
             return invenArt.modificar(art);
         }
        
   }
   
   public void actualizarArticulos(){
        this.borrarTabla(inven.articulosTable);
        ResultSet rs = invenArt.llamarTodos();
        Object[] row;
        row = new Object[7];
        DefaultTableModel rm = (DefaultTableModel) inven.articulosTable.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString("Nombre");
                row[1] = rs.getString("Codigo");
                row[2] = rs.getString("Cantidad");
                row[3] = rs.getString("Fecha");
                row[4] = rs.getBoolean("Costo");
                row[5] = rs.getBoolean("Proveedor");
                row[6] = rs.getBoolean("Descripcion");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
   

  
    @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inven.ArticuloBtnAdd) {
            this.agregarArticulo();
            this.actualizarArticulos();
        }
         }
   

}
