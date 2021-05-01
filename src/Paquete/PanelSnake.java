
package Paquete;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * @author Jonatan Alvarez
 */
public class PanelSnake extends JPanel {

    Color colorSnake = Color.BLACK;
    Color colorComida = Color.red;
    int tamMax, tam, can, res;
    //Coordenadas del snake
    List<int[]> snake = new ArrayList<>();
    int[] comida=new int[2];
    String direccion="RIGHT";
    String direccionProxima = "RIGHT";
    //Creacion de nuevo hilo
    Thread hilo;
    Caminante camino;

    public PanelSnake(int tamMax, int can) {
        this.tamMax = tamMax;
        this.can = can;
        this.tam = tamMax / can;
        this.res = tamMax % can;
        int[] a = {can/2-1,can/2-1};
        int[] b = {can/2,can/2-1};
        snake.add(a);
        snake.add(b);
        generaComida();
        
        camino = new Caminante(this);
        hilo = new Thread(camino);
        hilo.start();
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
        igualarDireccion();
        int[] ultimo = snake.get(snake.size()-1);
        int agregarx=0, agregary=0;
        
        switch(direccion){
            case "RIGHT":agregarx=1;break;
            case "LEFT":agregarx=-1;break;
            case "UP":agregary=-1;break;
            case "DOWN":agregary=1;break;
        }
        
        int[] nuevo = {Math.floorMod(ultimo[0]+agregarx, can), Math.floorMod(ultimo[1]+agregary, can)};
        boolean existe=false;
        for (int i = 0; i < snake.size(); i++) {
            if (nuevo[0]==snake.get(i)[0] && nuevo[1]==snake.get(i)[1]) {
                existe=true;
                break;
            }
        }
        
        if (existe) {
            JOptionPane.showMessageDialog(this, "GAME OVER");
            hilo.stop();
        } else {
            if (nuevo[0]==comida[0]&&nuevo[1]==comida[1]) {
                snake.add(nuevo);
                generaComida();
            }else{
                snake.add(nuevo);
                snake.remove(0);
            }
        }
    }
    
    public void generaComida(){
        boolean existe=false;
        int a = (int) (Math.random() * can);
        int b = (int) (Math.random() * can);
        
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
    
    public void cambiarDireccion(String dir){
        if ((this.direccion.equals("RIGHT")||this.direccion.equals("LEFT"))&&(dir.equals("UP")||dir.equals("DOWN"))) {
            this.direccionProxima = dir;
        }
        
        if ((this.direccion.equals("UP")||this.direccion.equals("DOWN"))&&(dir.equals("LEFT")||dir.equals("RIGHT"))) {
            this.direccionProxima = dir;
        }
        
    }
    
    public void igualarDireccion(){
        this.direccion = this.direccionProxima;
    }
}
