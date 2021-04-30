package Paquete;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * 
 * @author Jonatan Alvarez
 */
public class PanelFondo extends JPanel{
    Color colorFondo = Color.gray;
    int tamMax, tam, can, res;

    public PanelFondo(int tamMax, int can) {
        this.tamMax = tamMax;
        this.can = can;
        this.tam = tamMax/can;
        this.res = tamMax%can;
    }
    
    @Override
    public void paint(Graphics pintor){
        //Esto sirve para ir cambiando ya que se suele repintar el panel
        super.paint(pintor);
        pintor.setColor(colorFondo);
        for (int i = 0; i < can; i++) {
            for (int j = 0; j < can; j++) {
                pintor.fillRect(res/2+i*tam, res/2+j*tam, tam-1, tam-1);
            }
        }
    }   
}
