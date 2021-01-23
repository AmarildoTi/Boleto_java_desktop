
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Amarildo dos Santos de Lima
 */

public class Fac extends Utilidades{

 private ResultSet Eof;   
 private String CepB,ArqErr, Midia;
 private String Categoria,CodCep,CodTri;
 private String CodCif;
 private String NumPostal;
 private  int    sequencia = 0; 
 private  int    NumLote   = 0;
 private  int    XTotal    = 0;
 private boolean Continua  = false;
 final Configura ConfigFac    =  Configura.getInstance();    


  public boolean Modalidade() throws SQLException{  
 
   if (ConfigFac.getGera().equals("Produção")){    
        int Verifica = JOptionPane.showConfirmDialog(null,"Os Itens Abaixo estão corretos !!!!"+"\n"
            +Space(2)+"-----------------------------------------------"+Space(1)+"\n"
            +Space(4)+"1º Item Modelo  "+Space(1)+" = "+Space(1)+ConfigFac.getModelo()  +Space(1)+"\n"
            +Space(4)+"2º Item Gerar   "+Space(3)+" = "+Space(1)+ConfigFac.getGera()    +Space(1)+"\n"
            +Space(4)+"3º Item Contrato"+Space(1)+" = "+Space(1)+ConfigFac.getContrato()+Space(1)+"\n"
            +Space(4)+"4º Item Peso    "+Space(3)+" = "+Space(1)+ConfigFac.getPeso()    +Space(1)+"\n"
            +Space(4)+"5º Item Data    "+Space(4)+" = "+Space(1)+ConfigFac.getPostagem()+Space(1)+"\n"
            +Space(2)+"-----------------------------------------------"+Space(1)+"\n"
            +"Sim, Não Ou Cancela"," Confirmação das Configurações",JOptionPane.YES_NO_CANCEL_OPTION);
        if(Verifica == 0 ){Continua = true;}else if(Verifica == 2 ){System.exit(0);}
   }else{Continua = true;}  
  
   return Continua;
 
  } 
 
 
  public void Processa() throws SQLException{  
    final Banco conexao = new Banco();                                          //Estancia da Classe BancoDao Para Criar o Objeto Conexãoo
    conexao.connect();                                                          //Conexão com o Banco

    ArqErr = ConfigFac.getArquivo()+".Err";
   
  // Começo Seleciona Banco Contrato Fac ************************************   
    conexao.executeSQL_BdNavigator("Select * From App."+ConfigFac.getContrato());                // Seleciona o Banco Contrato Fac
  // Começo Seleciona Banco Contrato Fac ************************************   

   // Começo Criação de Variavel Eof ,com Resultado de um ResultSet ******
    Eof = conexao.resultset;                                                    // Eof ou End Of File  Enquando não for final de Arquivo
    Eof.next();   
  // Final Criação de Variavel Eof ,com Resultado de um ResultSet ******
     
   String Codigo_Dr  =  Eof.getString("Codigo_Dr");
   String Cod_Admin  =  Eof.getString("Cod_admin");
   String Num_Cartao =  Eof.getString("Num_Cartao");
   String Cod_Postag =  Eof.getString("Cod_Postag");
   String Cep_Origem =  Eof.getString("Cep_Origem");
   String N_Contrato =  Eof.getString("N_Contrato");
       
    NumLote = Integer.parseInt(Eof.getString("Num_Lote")) + 1;
     
    conexao.executeSQL_BdAppend("UpDate App."+ConfigFac.getContrato()+Space(01)+"set Num_Lote = "+"'"+Integer.toString(NumLote)+"'");        
   
            Midia = Cod_Admin
                  +"_" + Space(01)+Padl(Integer.toString(NumLote).trim(),05,"0")
                  +"_" + Space(01)+"UNICA"
                  +"_" + Space(01)+Codigo_Dr+".Txt";

            Write(Midia, "1"
                          +Codigo_Dr 
                          +Cod_Admin
                          +Num_Cartao
                          +Padl(Integer.toString(NumLote).trim(),05,"0")
                          +Cod_Postag 
                          +Cep_Origem 
                          +N_Contrato );
      
  // Começo Seleciona Banco de Dados ************************************   
    conexao.executeSQL_BdNavigator("Select * From App.Tipo01 Order By Cep Asc");// Seleciona o Banco de Dados Por Ordem de Cep
  // Final Seleciona Banco de Dados *** *********************************   
  
  // Começo Criação de Variavel Eof ,com Resultado de um ResultSet ******
    Eof = conexao.resultset;                                                    // Eof ou End Of File  Enquando não for final de Arquivo
  // Final Criação de Variavel Eof ,com Resultado de um ResultSet ******
    
    while (Eof.next()) {                                                        // Eof ou End Of File  Enquando não for final de Arquivo
   
       CepB =  StrTran(Eof.getString("Cep"),".-",""); 
     
        if ((Integer.parseInt(CepB) >= 1001000 && Integer.parseInt(CepB) <  2000000)
         || (Integer.parseInt(CepB) >= 3001000 && Integer.parseInt(CepB) <  7000000)
         || (Integer.parseInt(CepB) >= 9001000 && Integer.parseInt(CepB) < 10000000)){
            Categoria =   "82015";
            CodCep    =   "1";
            CodTri    =   "1";
        }else if((Integer.parseInt(CepB) >= 2001000 && Integer.parseInt(CepB) <  3000000)
         || (Integer.parseInt(CepB) >= 7001000 && Integer.parseInt(CepB) <  9000000)){
            Categoria =   "82015";
            CodCep    =   "1";
            CodTri    =   "2";
        }else if(Integer.parseInt(CepB) >= 11000000 && Integer.parseInt(CepB) <  14000000){
            Categoria  =   "82023";
            CodCep     =   "2";
            CodTri     =   "3";
        }else if(Integer.parseInt(CepB) >= 14000000 && Integer.parseInt(CepB) <  20000000){
            Categoria  =   "82023";
            CodCep     =   "2";
            CodTri     =   "4";
        }else if(Integer.parseInt(CepB) >= 20000000 && Integer.parseInt(CepB) <  60000000){
           Categoria  =   "82031";
           CodCep     =   "3";
           CodTri     =   "5";  
        }else if(Integer.parseInt(CepB) >= 60000000 && Integer.parseInt(CepB) < 100000000){
           Categoria  =   "82031";
           CodCep     =   "3";
           CodTri     =   "6";
        }else if((Integer.parseInt(Eof.getString("Cep")) == 0) 
              || (Integer.parseInt(Eof.getString("Cep")) >= 11111111)
              || (Integer.parseInt(Eof.getString("Cep")) >= 22222222)
              || (Integer.parseInt(Eof.getString("Cep")) >= 33333333)
              || (Integer.parseInt(Eof.getString("Cep")) >= 44444444)
              || (Integer.parseInt(Eof.getString("Cep")) >= 55555555)
              || (Integer.parseInt(Eof.getString("Cep")) >= 66666666)
              || (Integer.parseInt(Eof.getString("Cep")) >= 77777777)
              || (Integer.parseInt(Eof.getString("Cep")) >= 88888888)
              || (Integer.parseInt(Eof.getString("Cep")) >= 99999999)){
           CodTri     =   " ";
        }
     
        CodTri = ValidaCep(Integer.parseInt(CepB),Eof.getString("Estado"),CodTri);
        
        if (CodTri.equals(" ")){
            Write(ArqErr,Eof.getString("Cpf")
                   +";"+ Eof.getString("Nome")
                   +";"+ Eof.getString("Endereco")
                   +";"+ Eof.getString("Cidade")
                   +";"+ Eof.getString("Estado")
                   +";"+ Eof.getString("Cep"));
            Eof.deleteRow();
        }else{
            
            sequencia++; 
            
            CodCif = Codigo_Dr
                   + Cod_Admin
                   + Padl(Integer.toString(NumLote).trim(),5,"0")
                   + Padl(Integer.toString(sequencia).trim(),11,"0")
                   + CodCep
                   + "0" 
                   + StrTran(ConfigFac.getPostagem(),"/","").substring(0, 2)
                   + StrTran(ConfigFac.getPostagem(),"/","").substring(2, 4)
                   + StrTran(ConfigFac.getPostagem(),"/","").substring(6, 8);
            
            NumPostal = Padl(StrTran(ConfigFac.getPostagem(),"/","")+Padl(Integer.toString(sequencia).trim(),11,"0"),20,"0");

            conexao.executeSQL_BdAppend("UpDate App.Tipo01 set NumPostal = "+"'"+NumPostal +"'"+
                                                          ","+"CodCep    = "+"'"+CodCep    +"'"+
                                                          ","+"CodTri    = "+"'"+CodTri    +"'"+
                                                          ","+"CodCif    = "+"'"+CodCif    +"'"+
                                                          ","+"Categoria = "+"'"+Categoria +"'"+Space(01)+
                                                              "where Id  = "+"'"+Eof.getString("Id")+"'");        
            Write(Midia, "2"
                          +Padl(Integer.toString(sequencia).trim(),11,"0")
                          +Padl(StrTran(ConfigFac.getPeso().trim(),",",""),6,"0")
                          +CepB
                          +Categoria);
            
        }
        
    } // Fim do While

            XTotal = sequencia * Integer.parseInt(Padl(StrTran(ConfigFac.getPeso().trim(),",",""),6,"0"));

            Write(Midia, "4"
                          +Padl(Integer.toString(sequencia).trim(),7,"0")
                          +Padl(Integer.toString(XTotal).trim(),10,"0"));
    
       Eof.close();
      conexao.disconnect();
  }
   
   /** Metodo Para Comparar Cep com Estado e Validar*/
   private String ValidaCep(int XCep, String XEstado,String XCodTri){
    
       String CodTri = XCodTri;
       
   //************************ ACRE
   if ((XCep >= 69900000) && (XCep <= 69999999)){
    if (!XEstado.equals("AC")){
         CodTri = " ";
    }
   }           
   //************************ ALAGOAS
   if ((XCep  >= 57000000) && (XCep <= 57999999)){
    if (!XEstado.equals("AL")){
         CodTri = " ";
    }
   }         
   //************************ AMAZONAS
   if ((XCep >= 69000000) && (XCep <= 69299999)  
    || (XCep >= 69400000) && (XCep <= 69899999)){
    if (!XEstado.equals("AM")){
         CodTri = " ";
    }
   }    
  //***************************PARA
   if ((XCep >= 66000000 ) && (XCep <= 68899999)){
    if (!XEstado.equals("PA")){
         CodTri = " ";
    }
   }
  //************************ SAO PAULO
   if ((XCep >=  1000000) && ( XCep <= 19999999)){
    if(!XEstado.equals("SP")){
         CodTri = " ";
    }
   }
  //************************ PARAIBA
   if ((XCep >= 58000000) && (XCep <= 58999999)){
    if(!XEstado.equals("PB")){
         CodTri = " ";
    }
   }
  //************************ PARANA
   if ((XCep >= 80000000) && (XCep <= 87999999)){
    if (!XEstado.equals("PR")){
         CodTri = " ";
    }
   }
  //************************ AMAPA
   if((XCep >= 68900000) &&(XCep <= 68999999)){
    if(!XEstado.equals("AP")){
         CodTri = " ";
    }
  }
  //************************ BAHIA
   if ((XCep >= 40000000 ) &&(XCep <= 48999999)){
    if (!XEstado.equals("BA")){
         CodTri = " ";
    } 
   }
  //************************ CEARA
   if ((XCep >= 60000000 )&&(XCep <= 63999999)){
    if (!XEstado.equals("CE")){
         CodTri = " ";
    } 
   }
  //************************ DISTRITO FEDERAL
   if ((XCep >= 70000000) && (XCep <= 72799999) 
    || (XCep >= 73000000) && (XCep <= 73699999)){
    if(!XEstado.equals("DF")){
         CodTri = " ";
    }
   }
  //************************ ESPIRITO SANTO 
   if((XCep  >= 29000000 ) && (XCep <= 29999999)){
    if(!XEstado.equals("ES")){
         CodTri = " ";
   }
  }
  //************************ GOIAS
   if((XCep >= 72800000) && (XCep <= 72999999) 
   || (XCep >= 73700000) && (XCep <= 76799999)){
    if(!XEstado.equals("GO")){
         CodTri = " ";
    }
   }
  //************************ MARANHAO
   if((XCep >= 65000000 )&&(XCep <= 65999999)){
    if (!XEstado.equals("MA")){
         CodTri = " ";
    }
   }
  //************************ MINAS GERAIS
   if((XCep >= 30000000) &&(XCep <= 39999999)){
    if(!XEstado.equals("MG")){
         CodTri = " ";
    }
   }
  //************************ MATO GROSSO DO SUL
   if((XCep >= 79000000) &&(XCep <= 79999999)){
    if(!XEstado.equals("MS")){
         CodTri = " ";
    }
   }
  //************************ MATO GROSSO
   if((XCep >= 78000000) &&(XCep <= 78899999)){
    if(!XEstado.equals("MT")){
         CodTri = " ";
    }
   }
  //************************ PERNAMBUCO
   if ((XCep >= 50000000) && (XCep <= 56999999)){
    if(!XEstado.equals("PE")){
         CodTri = " ";
    }
   }
  //************************ PIAUI
   if((XCep >= 64000000) && (XCep <= 64999999)){
    if(!XEstado.equals("PI")){
         CodTri = " ";
    }
   }
  //************************ RIO DE JANEIRO
   if((XCep >= 20000000) && (XCep <= 28999999)){
    if(!XEstado.equals("RJ")){
         CodTri = " ";
    }
   }
  //************************ RIO GRANDE DO NORTE
   if((XCep >= 59000000) && (XCep <= 59999999)){
    if(!XEstado.equals("RN")){
         CodTri = " ";
    }
   }
  //************************ RONDONIA
   if ((XCep >= 78900000 ) && (XCep <= 78999999)) {
    if(!XEstado.equals("RO")){
         CodTri = " ";
    }
   }
  //************************ RORAIMA
   if((XCep >= 69300000) && (XCep <= 69399999)){
    if (!XEstado.equals("RR")){
         CodTri = " ";
    }
   }
  //************************ RIO GRANDE DO SUL
   if((XCep >= 90000000) && (XCep <= 99999999)){
    if (!XEstado.equals("RS")){
         CodTri = " ";
    }
   }
  //************************ SANTA CATARINA
   if((XCep >= 88000000) && (XCep <= 88999999)){
    if(!XEstado.equals("SC")){
         CodTri = " ";
    }
   }
  //************************ SERGIPE
   if ((XCep >= 49000000) && (XCep <= 49999999)){
    if(!XEstado.equals("SE")){
         CodTri = " ";
    }
   }
  //************************ TOCANTINS
   if ((XCep >= 77000000) && (XCep <= 77999999)){
    if(!XEstado.equals("TO")){
         CodTri = " ";
    }
   }
   return CodTri;
  }

   
}

