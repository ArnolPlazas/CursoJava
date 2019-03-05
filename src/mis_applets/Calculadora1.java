package mis_applets;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author afplazasac
 */
public class Calculadora1 extends JApplet {
    public void init() {
        JFrame mimarco=new JFrame();
        mimarco.setSize(500,300);
        LaminaCalculadora1 milamina=new LaminaCalculadora1();
        mimarco.add(milamina);
        
        JButton boton_calculdadora=new JButton ("Calculadora");
        add(boton_calculdadora);
        boton_calculdadora.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mimarco.setVisible(!mimarco.isVisible()); // hacerlo visible en cvaso de que no lo este (!): operacion de negacion
            }
            
        });
    }
    
}

class LaminaCalculadora1 extends JPanel{
    public LaminaCalculadora1(){
        principio=true;
        setLayout(new BorderLayout());
        pantalla=new JButton("0");
        pantalla.setEnabled(false);//desahabilitar el boton
        add(pantalla,BorderLayout.NORTH);
        
        milamina2=new JPanel();
        milamina2.setLayout(new GridLayout(4,4)); // grid de 4 columnas y cuatro filas     
    
        ActionListener insertar=new InsertaNumero();
        ActionListener orden=new AccionOrden();
        ponerBoton("7",insertar);
        ponerBoton("8",insertar);
        ponerBoton("9",insertar);
        ponerBoton("/",orden);
        
        ponerBoton("4",insertar);
        ponerBoton("5",insertar);
        ponerBoton("6",insertar);
        ponerBoton("*",orden);
        
        ponerBoton("1",insertar);
        ponerBoton("2",insertar);
        ponerBoton("3",insertar);
        ponerBoton("-",orden);
        
        ponerBoton("0",insertar);
        ponerBoton(".",insertar);
        ponerBoton("=",orden);
        ponerBoton("+",orden);
        
        add(milamina2,BorderLayout.CENTER);
        ultimaOperacion="=";
        
    
    }
    
    private void ponerBoton(String rotulo,ActionListener oyente){
        JButton boton=new JButton (rotulo);
        boton.addActionListener(oyente);// Poner todos los botones a la escucha
        milamina2.add(boton);
    }
    
    private class InsertaNumero implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String entrada=e.getActionCommand();
            if (principio){
                pantalla.setText("");
                principio=false;
                        
            }
            pantalla.setText(pantalla.getText()+entrada);
        }
        
    }
    
    private class AccionOrden implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String operacion=e.getActionCommand();
            calcular(Double.parseDouble(pantalla.getText()));
            ultimaOperacion=operacion;
            principio=true;
            
        }
        
        public void calcular(double x){
            if(ultimaOperacion.equals("+")){
                resultado+=x;            
            }
            else if(ultimaOperacion.equals("-")){
                resultado-=x;            
            }
            else if(ultimaOperacion.equals("*")){
                resultado*=x;            
            }
            else if(ultimaOperacion.equals("/")){
                resultado/=x;            
            }
            else if(ultimaOperacion.equals("=")){
                resultado=x;            
            }
            
            pantalla.setText(""+resultado);
        
        }
        
    }
    
    private JPanel milamina2;
    private JButton pantalla;
    private boolean principio;
    private String ultimaOperacion;
    private Double resultado;
    
}
