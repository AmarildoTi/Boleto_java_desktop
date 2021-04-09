/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Controller.Boleto.BoletoBancos;
import Controller.Boleto.GerarBoleto;
import Model.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amarildo dos Santos de Lima
 */
public class Spool {

   private ResultSet Eof;
   private int QtdeArqs , sequencia, SeqX, SeqExt = 1, Ind1 = 1;
   private int CepLocal, ImpCepLocal;
   private int CepEstadual, ImpCepEstadual;
   private int CepNacional, ImpCepNacional;
   private final int Quebra = 500;
   private String Spool, ArqOs, CodCif;
   private final String[]   TipoX  = new String[3];
   private final String[][] LinhaF = new String[3][31];
   private final String[][] LinhaV = new String[3][9];
   private boolean Fim;
   private static final int BRADESCO = 237;

    Configura Config =  Configura.getInstance();   
    Utilidades Util = new Utilidades();
 

/**
  Metodo para, Criar Spool de Impressão para Posicionamento de Variaveis     <p>
  1 Argumentos                                                               <p>
  1º do tipo String                                                          <p>
  Exemplo de Uso = Spool(String Arquivo);                                    <p>
  Retorno void                                                               <p>
  * @param Arquivo
  * @throws java.sql.SQLException
 */
  public void Spool(String Arquivo) throws SQLException {  

  // Começo Conecta Banco de Dados em Run Time , Em tempo de Execução
    final Banco conexao = new Banco();                                          //Estancia da Classe BancoDao Para Criar o Objeto Conexãoo
    conexao.connect();                                                          //Conexão com o Banco

  // Começo Objetos Boleto
    final GerarBoleto Bol  =  new  GerarBoleto();                               // Classe Opção Bol
  // Final Objetos  Boleto
    
  // Começo Seleciona Banco de Dados ************************************   
    conexao.executeSQL_BdNavigator("Select * From App.Tipo01 Order By Cep Asc");// Seleciona o Banco de Dados Por Ordem de Cep
  // Final Seleciona Banco de Dados *** *********************************   
  
  // Começo Criação de Variavel Eof ,com Resultado de um ResultSet ******
    Eof = conexao.resultset;                                                    // Eof ou End Of File  Enquando não for final de Arquivo
  // Final Criação de Variavel Eof ,com Resultado de um ResultSet ******
    
    Eof.last();                                                                 //-----\  Percorre Banco Até o Fim
    int count = Eof.getRow();                                                   //----- > count agora tem o numero de linhas do ResultSet
    Eof.beforeFirst();                                                          //-----/  volta o cursor la pra cima pra vc realizar suas tarefas. Como Um "Goto Top" No Banco

    QtdeArqs = count/Quebra + 1;                                                //----- > Retorna a Quantidade Total De Arquivos Que Serão Gerados
    
   // Começo Deletar Aquivos Para Gerar Novos ********************************   
    for (int X = 1; X <= QtdeArqs; X++) {                                       //-----\  Faz Até Atingir a Quantidade de Arquivos 
      Util.DeleteFile(Arquivo+"."+Util.Padl(Integer.toString(X),03,"0"));                 //----- > Deleta Os Arquivo para Gerar Novos Arquivos
      Util.DeleteFile(Arquivo+Util.Padl(Integer.toString(X),03,"0")+".Pdf");              //----- > Deleta Os Arquivo para Gerar Novos Arquivos
    }                                                                           //-----/  Apenas se Os Arquivos já Existirem na Pasta
   
    Util.DeleteFile(Arquivo+".OS");                                                  //----- > Deleta Arquivo .Os Para Gera Novo Arquivo
   // Final Deletar Aquivos Para Gerar Novos  ********************************

    while (Eof.next()) {                                                        // Eof ou End Of File  Enquando não for final de Arquivo
     sequencia++;
     SeqX++;
     
     //Começo Totais Local Estadual  Nacional e Cep erro
      //**** Local  
        if (Integer.parseInt(Eof.getString("Cep")) < 10000000) {
         CepLocal++;
         ImpCepLocal++;
        } 
      //**** Estadual  
        if ((Integer.parseInt(Eof.getString("Cep")) >= 11000000) 
        && (Integer.parseInt(Eof.getString("Cep")) < 20000000)){
         CepEstadual++;
         ImpCepEstadual++;
        }
      //**** Nacional  
        if (Integer.parseInt(Eof.getString("Cep")) >= 20000000){ 
         CepNacional++;
         ImpCepNacional++;
        }
     //Final Totais Local Estadual  Nacional e Cep erro

      ArqOs   =  Arquivo+".OS";
      Spool   =  Arquivo+"."+Util.Padl(Integer.toString(SeqExt),03,"0");
     
      if (sequencia%Quebra == 1) {
       Util.Write(Spool,"%!");
       Util.Write(Spool,"XGF");
       Util.Write(Spool,"500 SETBUFSIZE");
       Util.Write(Spool,"(PRINCIPAL.JDT) STARTLM");
      }

       if (sequencia%Quebra == 0 || sequencia == count){
        Fim = true; 
        SeqExt++ ;
        
         Util.Write(ArqOs,"A;"+Spool+";"
        +Util.Space(01)+Util.Padl(Integer.toString(SeqX).trim(),05,"0")+";"
        +Util.Space(01)+Util.Padl(Integer.toString(CepLocal).trim(),05,"0")+";"
        +Util.Space(01)+Util.Padl(Integer.toString(CepEstadual).trim(),05,"0")+";"
        +Util.Space(01)+Util.Padl(Integer.toString(CepNacional).trim(),05,"0"));
        
          if (sequencia == count){
           Util.Write(ArqOs,"J;"+"PRINCIPAL.JDT"); 
           Util.Write(ArqOs,"J;"+Config.getModelo()+"_F.JDT"); 
           Util.Write(ArqOs,"J;"+Config.getModelo()+"_V.JDT"); 
           Util.Write(ArqOs,"P;"+Config.getModelo()+"_F.ps".toLowerCase()); 
           Util.Write(ArqOs,"P;"+Config.getModelo()+"_V.ps".toLowerCase()); 
          }

         SeqX         =  0;
         CepLocal     =  0;
         CepEstadual  =  0;
         CepNacional  =  0;

       }else{
        Fim = false; 
       }

  //Começo Exibe Nome do Arquivo de Saida de Contadores      
       Config.setArquivo(Spool);
       Config.setContador(Util.Padl(Integer.toString(sequencia).trim(),05,"0"));
       Config.setLocal(Util.Padl(Integer.toString(ImpCepLocal).trim(),05,"0"));
       Config.setEstadual(Util.Padl(Integer.toString(ImpCepEstadual).trim(),05,"0"));
       Config.setNacional(Util.Padl(Integer.toString(ImpCepNacional).trim(),05,"0"));
 //Começo Exibe Nome do Arquivo de Saida de Contadores      
   
//Começo Captura dados Para o Boleto Bancario Sets
  Bol.setLocalPagamento("Pagável em qualquer agência até o Vencimento");
  Bol.setLocalPagamento2("Após Vencimento somente no Bradesco");
  Bol.setCedente("Hoepers S/A");
  Bol.setAgencia("0324");
  Bol.setDvAgencia("7");
  Bol.setContaCorrente("0169983");
  Bol.setDvContaCorrente("0");
  Bol.setCarteira("06");                      
  Bol.setEspecieDocumento("RC");
  Bol.setAceite("N");
  Bol.setDataDocumento(Util.DataSystema().substring(0, 10));
  Bol.setDataProcessamento(Util.DataSystema().substring(0, 10));
  Bol.setDataVencimento(Eof.getString("Vencimento"));
  Bol.setNossoNumero(Util.Padl(Util.RetZeros(Eof.getString("NossoNum")).trim(),11,"0"));
  Bol.setDvNossoNumero(Util.Padl(Util.RetZeros(Eof.getString("NossoNum")).trim(),11,"0"));
  Bol.setNoDocumento(Util.Padl(Util.RetZeros(Eof.getString("NossoNum")).trim(),11,"0"));
  Bol.setValorBoleto(Eof.getString("ValorTot"));
  Bol.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
  Bol.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA");
  Bol.setInstrucao3("");
  Bol.setInstrucao4("");
  Bol.setInstrucao5("");
  BoletoBancos boleto = new BoletoBancos(Bol,BRADESCO); // Obs: Caso seja Necessario Mudar o Bando Declarar a Variavel conforme Exemplo = public static final int BRADESCO = 237;
//Final Captura dados Para o Boleto Bancario Sets

     CodCif = (Config.getGera().equals("Produção") ? Eof.getString("CodCif") : Util.Padl(Eof.getString("id"),34,"0"));

     //********************************* Começo Canal do Spool
       TipoX[Ind1]="01"+Util.Space(1)+CodCif;
     //********************************* Final Canal do Spool
    
    //********************************** Começo Parte Da Frente Boleto bradesco
      LinhaF[Ind1][1]=Util.Space(1)+Eof.getString("Cpf");
      LinhaF[Ind1][2]=Util.Space(1)+Eof.getString("Contrato");
      LinhaF[Ind1][3]=Util.Space(1)+Eof.getString("Nome");
      LinhaF[Ind1][4]=Util.Space(1)+"R$"+Util.Space(01)+Util.FormataMoeda(Eof.getString("ValorTot"));
      LinhaF[Ind1][5]=Util.Space(1)+Bol.getLinhaDigitavel();
      LinhaF[Ind1][6]=Util.Space(1)+Bol.getLocalPagamento();
      LinhaF[Ind1][7]=Util.Space(1)+Bol.getLocalPagamento2();
      LinhaF[Ind1][8]=Util.Space(1)+Bol.getCedente();
      LinhaF[Ind1][9]=Util.Space(1)+Bol.getDataVencimento();
      LinhaF[Ind1][10]=Util.Space(1)+Bol.getAgencia()+"-"+Bol.getDvAgencia()+"/"+Bol.getContaCorrente()+"-"+Bol.getDvContaCorrente();
      LinhaF[Ind1][11]=Util.Space(1)+Bol.getDataDocumento();
      LinhaF[Ind1][12]=Util.Space(1)+Bol.getNoDocumento();
      LinhaF[Ind1][13]=Util.Space(1)+Bol.getEspecieDocumento();
      LinhaF[Ind1][14]=Util.Space(1)+Bol.getAceite();
      LinhaF[Ind1][15]=Util.Space(1)+Bol.getDataProcessamento();
      LinhaF[Ind1][16]=Util.Space(1)+Bol.getCarteira()+"/"+Bol.getNossoNumero()+"-"+Bol.getDvNossoNumero();
      LinhaF[Ind1][17]=Util.Space(1)+Bol.getCarteira();
      LinhaF[Ind1][18]=Util.Space(1)+"R$";
      LinhaF[Ind1][19]=Util.Space(1)+Util.FormataMoeda(Eof.getString("ValorTot"));
      LinhaF[Ind1][20]=Util.Space(1);
      LinhaF[Ind1][21]=Util.Space(1)+Bol.getInstrucao1();
      LinhaF[Ind1][22]=Util.Space(1)+Bol.getInstrucao2();
      LinhaF[Ind1][23]=Util.Space(1)+Bol.getInstrucao3();
      LinhaF[Ind1][24]=Util.Space(1)+Bol.getInstrucao4();
      LinhaF[Ind1][25]=Util.Space(1)+Bol.getInstrucao5();
      LinhaF[Ind1][26]=Util.Space(1);
      LinhaF[Ind1][27]=Util.Space(1)+Eof.getString("Nome");
      LinhaF[Ind1][28]=Util.Space(1)+Eof.getString("Endereco");
      LinhaF[Ind1][29]=Util.Space(1)+Util.Stuff(Eof.getString("Cep"),6,"-")+" - "+Eof.getString("Cidade").trim()+" - "+Eof.getString("Estado");
      LinhaF[Ind1][30]=Util.Space(1)+Bol.getCodigoBarras();
    //********************************** Final Parte Da Frente Boleto bradesco
    
   
    //********************************** Começo Verso Do Boleto Endereçamento
      LinhaV[Ind1][1]=Util.Space(1)+Util.StrTran(Util.Postnet(Eof.getString("Cep")),"*","");
      LinhaV[Ind1][2]=Util.Space(1)+Eof.getString("Nome");
      LinhaV[Ind1][3]=Util.Space(1)+Eof.getString("Endereco");
      LinhaV[Ind1][4]=Util.Space(1)+Eof.getString("Cidade").trim()+" - "+Eof.getString("Estado");
      LinhaV[Ind1][5]=Util.Space(1)+Util.Stuff(Eof.getString("Cep"),6,"-");
      LinhaV[Ind1][6]=Util.Space(1)+Util.Padl(Integer.toString(sequencia),7,"0")+Util.Space(01)+"Arq:"+Util.Space(01)+Spool;
      LinhaV[Ind1][7]=Util.Space(1)+CodCif;
      LinhaV[Ind1][8]=Util.Space(1); 
    //********************************** Final Verso Do Boleto Endereçamento

   // Final Carrega As Variaveis Para Impressao *************************

//********************* Começo Imprime e Limpa Variaveis
      Util.Write(Spool,"+$DJDE$ FORMS="+Config.getModelo()+"_F"+",FEED=BAN3,END;");
      for (int Ind1 = 1; Ind1 <= 1; Ind1++){
        for (int Ind2 = 1; Ind2 <= 30; Ind2++){
         Util.Write(Spool,TipoX[Ind1]+LinhaF[Ind1][Ind2]);                           //Para Imprimir as Variaveis Em arquivo Texto
         LinhaF[Ind1][Ind2]=Util.Space(01)+"*****";                                  //Para Limpar as Variaveis
        }     
      }
       
     Util.Write(Spool,"+$DJDE$ FORMS="+Config.getModelo()+"_V"+",FEED=BAN3,END;");
      for (int Ind1 = 1; Ind1 <= 1; Ind1++){
        for (int Ind2 = 1; Ind2 <= 8; Ind2++){
         Util.Write(Spool,TipoX[Ind1]+LinhaV[Ind1][Ind2]);                           //Para Imprimir as Variaveis Em arquivo Texto    
         LinhaV[Ind1][Ind2]=Util.Space(01)+"*****";                                  //Para Limpar as Variaveis
        }     
      }
//********************* Final Imprime e Limpa Variaveis
      
   //********** Começo = Fecha, Finaliza Quebra de arquivo e gera o PDF
     if (Fim){
      Util.Write(Spool);                                                             //Gerar Pdf
     }
   //********** Final  = Fecha, Finaliza Quebra de arquivo e gera o PDF
   
    } // Fim do While
      Eof.close();
      conexao.disconnect();
  }

}
    

