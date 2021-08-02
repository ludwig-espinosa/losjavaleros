
package Controlador;

import DreamsGifts.Login;
import DreamsGifts.Principal;
import Modelo.Conexion;
import Modelo.ConsultaReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CtrPrincipal implements ActionListener{
    private Principal princ = new Principal();
//    Creacion de controladores secundarios o hijos
    private CtrAdministracion ctAd = new CtrAdministracion();
    private CtrReportes ctRep = new CtrReportes();
    private CtrVentas ctVent = new CtrVentas();
    private CtrInventario ctInv;
    static Login lgn;
    
    
    public CtrPrincipal(Login login) throws SQLException{
        this.ctInv = new CtrInventario();
        this.ctVent = new CtrVentas();
        this.princ.btnAdmin.addActionListener(this);
        this.princ.BotonSalir.addActionListener(this);
        this.princ.btnReportes.addActionListener(this);
        this.princ.princVenta.addActionListener(this);
        this.princ.inventario.addActionListener(this);
        lgn = login;
        
    }

       public void iniciar(){
       if (!princ.isVisible()){
           princ.setVisible(true);
       }
   }  
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == princ.btnAdmin){
            this.ctAd.iniciar();
        }
                if (e.getSource() == princ.BotonSalir){
            princ.setVisible(false);
            lgn.setVisible(true);
        }
                if (e.getSource() == princ.btnReportes) {
            ctRep.iniciar();
        }
        if (e.getSource() == princ.princVenta) {
            ctVent.iniciar();
        }
        if (e.getSource() == princ.inventario) {
            ctInv.iniciar();
        }
    }
}
