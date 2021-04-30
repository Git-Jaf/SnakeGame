
package Paquete;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Jonatan Alvarez
 */
public class PanelSnake extends JPanel {

    Color colorSnake = Color.blue;
    Color colorComida = Color.red;
    int tamMax, tam, can, res;
    //Coordenadas del snake
    List<int[]> snake = new ArrayList<>();
    int[] comida=new int[2];
    String direccion="right";

    public PanelSnake(int tamMax, int can) {
        this.tamMax = tamMax;
        this.can = can;
        this.tam = tamMax / can;
        this.res = tamMax % can;
        int[] a = {can/2-1,can/2-1};
        int[] b = {can/2,can/2-1};
        snake.add(a);
        snake.add(b);
    }

    @Override
    public void paint(Graphics pintor) {
        //Esto sirve para ir cambiando ya que se suele repintar el panel
        super.paint(pintor);
        pintor.setColor(colorSnake);
        
        for (int[] par : snake) {
            pintor.fillRect(res/2+par[0]*tam,res/2+par[1]*tam, tam-1, tam-1);
        }
        
        pintor.setColor(colorComida);
        pintor.fillRect(res/2+comida[0]*tam,res/2+comida[1]*tam, tam-1, tam-1);
        
        /*for (int i = 0; i < 10; i++) {
            pintor.fillRect(res/2+snake.get(i)[0]*tam, res/2+snake.get(i)[1]*tam,tam-1, tam-1);
        }*/
    }
    
    public void avanzar(){
        int[] ultimo = snake.get(snake.size()-1);
        int agregarx=0, agregary=0;
        
        switch(direccion){
            case "right":agregarx=1;break;
            case "left":agregarx=-1;break;
            case "up":agregary=-1;break;
            case "down":agregary=1;break;
        }
        
        int[] nuevo = {Math.floorMod(ultimo[0]+agregarx, can), Math.floorMod(ultimo[1]+agregary, can)};
        
        snake.add(nuevo);
        snake.remove(0);
    }
    
    public void generaComida(){
        boolean existe=false;
        int a = (int) Math.random()*can;
        int b = (int) Math.random()*can;
        
        for (int[] par : snake) {
            if (par[0]==a&&par[1]==b) {
                existe=true;
                generaComida();
                break;
            }
        }
        
        if (!existe) {
            this.comida[0]=a;
            this.comida[1]=b;
        }
    }
}
