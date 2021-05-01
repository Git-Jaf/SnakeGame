package Paquete;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jonatan Alvarez
 */
public class Caminante implements Runnable{
    //conbtrol de hilos
    PanelSnake panel;
    boolean estado=true;
    
    public Caminante(PanelSnake panel){
        this.panel = panel;
    }
    
    @Override
    public void run() {
        while (estado) {
            panel.avanzar();
            panel.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Caminante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void parar(){
        this.estado = false;
    }
}
