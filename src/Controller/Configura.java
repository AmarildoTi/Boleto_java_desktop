/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JavaX
 */
public class Configura extends Observable implements Runnable {

    
   private static Configura instance = new Configura();

   private Configura() {

   }

   public static Configura getInstance() {
      return instance;
   }
    
    private String Modelo   ; 
    private String Gera     ;
    private String Contrato ;
    private String Peso     ;
    private String Arquivo  ;
    private String Contador ;
    private String Local    ;
    private String Estadual ;
    private String Nacional ;
    private JFormattedTextField Data;

//***** Começo Metodos Get(s) e  Set(s)    
 
public String getModelo() {
    return this.Modelo;
  }    

public void setModelo(String modelo) {
    this.Modelo = modelo;
}

public String getGera() {
    return this.Gera;
  }
public void setGera(String gera) {
    this.Gera = gera;
  }

public String getContrato() {
    return this.Contrato;
}

public void setContrato(String contrato) {
     this.Contrato = contrato;
}

public String getPeso() {
    return this.Peso;
  }

public void setPeso(String peso) {
    this.Peso = peso;
  }

public String getPostagem() {
     return Data.getText();
  }

public void setPostagem(MaskFormatter postagem) {
       try {                                                                            //----- \
         postagem = new MaskFormatter("##/##/####");                                    //-------\   Funções Para Formatar Texto
         postagem.setPlaceholderCharacter('_');                                         //-------/ 
       }                                                                                //------/
        catch (ParseException Erro) 
       {
       Erro.getMessage();
       }        
   this.Data = new JFormattedTextField(postagem);
  }

public String getArquivo() {
    return this.Arquivo;
  }

public void setArquivo(String arquivo) {
    this.Arquivo = arquivo;
  }

public String getContador() {
   return this.Contador;
  }

public void setContador(String contador) {
    this.Contador = contador;
  }

public String getLocal() {
    return this.Local;
  }

public void setLocal(String local) {
    this.Local = local;
  }

public String getEstadual() {
   return this.Estadual;
  }

public void setEstadual(String estadual) {
    this.Estadual = estadual;
  }

public String getNacional() {
   return this.Nacional;
  }

public void setNacional(String nacional) {
    this.Nacional = nacional;
  }
//***** Final Metodos Get(s) e  Set(s)


    /** 
     * Construtor que recebe um objeto que irá observa-lo 
     * @param observador Objeto que irá acompanhar as mudanças deste objeto 
     */  
    public Configura(Observer observador) {  
        //Adiciona o objeto observador a lista de observadores  
        addObserver(observador);  
        //...outras inicializações  
    }  
      
    /** 
     * Ponto de entrada da Thread. 
     * @see java.lang.Runnable#run() 
     */  
    public void run() {  
        Configura Config =  Configura.getInstance();
        int i;  
 
        System.out.println("Verifica = "+Config.getContador());
        for(i=0; i<= Integer.parseInt(Config.getContador()); i++) {  
                notifyObservers(i);  
                setChanged();  
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Configura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        notifyObservers(new Boolean(true));  
        setChanged();   
    }


}
