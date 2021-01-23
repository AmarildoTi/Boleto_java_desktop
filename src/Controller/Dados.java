/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Banco;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Amarildo dos Santos de Lima
 */
public class Dados extends Utilidades{
    private String Input;
    private String tabela;
    private String header;
    private String campos;
    private String dados;
    private String Id;
    private int iniEntrada;
    private int Contador;
    private int Sequencia;
   
//Começo Estrutura dos Dados nas Tabelas Começo ********************************
 private int Estrutura[] = {};                                                  // Estrutura Recebe a Estrutura da Demais Tabelas Campo a Campo

 private final int EstruturaTipo00[] = {  2,  5,  5,  8,  5,  9,  2, 25};       // Estrutura da Tabela Tipo00 Campo a Campo

 private final int EstruturaTipo01[] = {  2,  8,  8, 14,  1, 40, 14, 55, 20, 2  // Estrutura da Tabela Tipo01 Campo a Campo
                                       , 10, 10, 12,  8,  4,  6, 52, 70, 70, 70 // Estrutura da Tabela Tipo01 Campo a Campo
                                       , 70, 70, 70, 70, 50, 50, 20,  3, 10, 10 // Estrutura da Tabela Tipo01 Campo a Campo
                                       , 10, 16, 9, 25 };                       // Estrutura da Tabela Tipo01 Campo a Campo
 
 private final int EstruturaTipo02[] = {  2, 10, 10, 10, 10, 20, 10, 10, 25};   // Estrutura da Tabela Tipo02 Campo a Campo
 
 private final int EstruturaTipo03[] = {  2,  2, 10, 10, 10, 10, 10, 10, 10, 25};// Estrutura da Tabela Tipo03 Campo a Campo
//Final  Estrutura dos Dados nas Tabelas Começo ********************************

/** 
Metodo Zap Para Limpar as Tabelas                                            <p>
void                                                                         <p>
Exemplo de Uso =  Estrutura.Zap();                                           <P>
 */
  public void Zap(){    
  // Começo Conecta Banco de Dados em Run Time , Em tempo de Execução
     final Banco conexao = new Banco();                                   //Estancia da Classe BancoDao Para Criar o Objeto Conexãoo
     conexao.connect();                                                         //Conexão com o Banco
  // Final Conecta Banco de Dados em Run Time , Em tempo de Execução
   
   // Começo Limpa a Tabela **********************************************
     conexao.executeSQL_BdClear("Delete From App.Tipo00");                      // Limpa o Banco de Dados  
     conexao.executeSQL_BdClear("Delete From App.Tipo01");                      // Limpa o Banco de Dados  
     conexao.executeSQL_BdClear("Delete From App.Tipo02");                      // Limpa o Banco de Dados  
     conexao.executeSQL_BdClear("Delete From App.Tipo03");                      // Limpa o Banco de Dados  
   // Final  Limpa a Tabela **********************************************
  }

/** 
  Metodo Append Para Carregar As Tabelas Com os Dados                          <p>
  1 Argumento                                                                  <p>
  1º Tipo String                                                               <p>
  Exemplo de Uso =  Append(Arquivo_Entrada);                                   <p>
  * @param Arquivo_Entrada
  * @return 
*/
 public String Append(File Arquivo_Entrada){    
  // Começo Conecta Banco de Dados em Run Time , Em tempo de Execução
     final Banco conexao = new Banco();                                         //Estancia da Classe BancoDao Para Criar o Objeto Conexãoo
     conexao.connect();                                                         //Conexão com o Banco
  // Final Conecta Banco de Dados em Run Time , Em tempo de Execução
   
  try{
         try (FileReader reader = new FileReader(Arquivo_Entrada); 
                 BufferedReader bufReader = new BufferedReader(reader)) {
             
             while ((Input = bufReader.readLine()) != null) {
                 tabela     =  "";
                 header     =  "";
                 campos     =  "";
                 dados      =  "";
                 iniEntrada =   0;
                 Contador   =   0;
                 Id         =   "";
                 
                 header = Input.substring(0,2);
                 
                 switch (header) {
                     case "00":
                         tabela    = "Tipo00"+Space(1);
                         Estrutura =  EstruturaTipo00.clone();
                         campos    = "TipoReg,Lixo1,Agencia,Conta,Carteira,CodCedente,Especie,Id";
                         break;
                     case "01":
                         Sequencia++;
                         tabela    = "Tipo01"+Space(1);
                         Estrutura =  EstruturaTipo01.clone();
                         campos    = "TipoReg,Cep,Processo,Nossonum,DigNosso,Nome,Cpf,"
                                 + "Endereco,Cidade,Estado,Vencimento,Limite,telefone,"
                                 + "CodDevedor,CodCredor,Remessa,Brancos,Filial01,"
                                 + "Filial02,Filial03,Filial04,Filial05,Filial06, "
                                 + "Filial07,Titulo1,Titulo2,Contrato,Desconto,ValorTot,"
                                 + "CodDivida,Lixo_1_To1,NossoNum2,Brancos01,Id";
                         break;
                     case "02":
                         tabela    = "Tipo02"+Space(1);
                         Estrutura =  EstruturaTipo02.clone();
                         campos    = "TipoReg,Vencimento,ValorAtual,Desconto,ValorDesc,"
                                 + "Contrato,CodSegu2,PercDesc2,Id";
                         break;
                     case "03":
                         tabela    = "Tipo03"+Space(1);
                         Estrutura =  EstruturaTipo03.clone();
                         campos    = "TipoReg,Parcelas,ValorEntr,ValorParc,ValorAvist,"
                                 + "CodSeg,PercDesc3,Cet_Mes,Cet_Ano,Id";
                         break;
                     default:
                         break;
                 }
                 
                 Id  = Padl(Integer.toString(Sequencia).trim()+StrTran(DataSystema().trim()," #,./-:",""),25,"0");
                 
                 Input = Input+Id;
                 
                 while (Contador < Estrutura.length){
                     if (Contador == Estrutura.length-1)
                         dados = dados + "'" + Input.replace("'", " ").substring(iniEntrada, iniEntrada + Estrutura[Contador])+"'";
                     else
                         dados = dados + "'" + Input.replace("'", " ").substring(iniEntrada, iniEntrada + Estrutura[Contador]) + "', ";
                     iniEntrada = iniEntrada + Estrutura[Contador];
                     Contador++;
                 }
                 
                 conexao.executeSQL_BdAppend(
                         "Insert Into "                                                         // Comando Sql
                         +"App."                                                                // Squema Da Tabela
                         +""+ tabela +""                                                        // Tipo de Tabela
                         +"("+ campos +")"                                                      // Nomes dos Campos Da tabela
                         +" values (" + dados + ")");                                           // Dados a ser Inseridos Nos Campos Da tabela
             }
             
         }
   }
   catch (IOException erro){
    erro.getMessage();
   }
  return Input; 
  }
}
