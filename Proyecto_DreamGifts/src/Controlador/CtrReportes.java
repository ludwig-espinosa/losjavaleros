package Controlador;

import DreamsGifts.Reportes;
import Modelo.ConsultaReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrReportes implements ActionListener{
    Reportes repo;
    ConsultaReportes conRep;
    
//    metodo constructor
    public CtrReportes(){
        repo = new Reportes();
        conRep = new ConsultaReportes();
        
    }
    
    
//    Metodo para hacer visible la pestaña de reportes
    public void iniciar(){
        if (!repo.isVisible()) {
            repo.setVisible(true);
        }
    }
    
    
//  Adicion de eventos a los objetos añadidos
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
