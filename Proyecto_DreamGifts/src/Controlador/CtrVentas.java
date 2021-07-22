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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author lespinosa
 */
public class CtrVentas implements ActionListener {
    private static Ventas venta = new Ventas();
    
    public void iniciar(){
        if (!venta.isVisible()){
            venta.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
