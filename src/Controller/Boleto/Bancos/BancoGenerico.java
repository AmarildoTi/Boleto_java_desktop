/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Boleto.Bancos;

import Controller.Boleto.Banco;
import Controller.Boleto.GerarBoleto;


/**
 * Classe modelo para a criacao de outros
 * Copie este arquivo para o nome do banco q vc pretende criar, seguindo a mesma nomeclatura de nomes das outras classes
 * Caso o banco tenha algum metodo diferente de calcular os seus campos, vc pode criar os seu metodos particulares dentro
 * desta classe, pois os metodos que tem nesta classe sao padroes
 * @author Amarildo dos Santos de Lima
 */
public class BancoGenerico implements Banco {

  GerarBoleto boleto;

  /**
   * Metdodo responsavel por resgatar o numero do banco, coloque no return o codigo do seu banco
   */
  @Override
  public String getNumero() {
    return "99999";
  }

  /**
   * Classe construtura, recebe como parametro a classe jboletobean
     * @param boleto
   */
  public BancoGenerico(GerarBoleto boleto) {
    this.boleto = boleto;
  }

  /**
   * Metodo que monta o primeiro campo do codigo de barras
   * Este campo como os demais e feito a partir do da documentacao do banco
   * A documentacao do banco tem a estrutura de como monta cada campo, depois disso
   * é só concatenar os campos como no exemplo abaixo.
   */
  private String getCampo1() {
    return "";
  }

  /**
   * ver documentacao do banco para saber qual a ordem deste campo
   */
  private String getCampo2() {
    return "";
  }

  /**
   * ver documentacao do banco para saber qual a ordem deste campo
   */
  private String getCampo3() {
    return "";
  }

  /**
   * ver documentacao do banco para saber qual a ordem deste campo
   */
  private String getCampo4() {
    return "";
  }

  /**
   * ver documentacao do banco para saber qual a ordem deste campo
   */
  private String getCampo5() {
    return "1234";
  }

  /**
   * Metodo para monta o desenho do codigo de barras
   * A ordem destes campos tambem variam de banco para banco, entao e so olhar na documentacao do seu banco
   * qual a ordem correta
   */
  @Override
  public String getCodigoBarras() {
      return "0000000000000000000000000000000000000000";
  }

  /**
   * Metodo que concatena os campo para formar a linha digitavel
   * E necessario tambem olhar a documentacao do banco para saber a ordem correta a linha digitavel
   */
  @Override
  public String getLinhaDigitavel() {
      return "          >>>>>>>>> COBRANCA EM CARTEIRA <<<<<<<<<";
  }

  /**
   * Recupera a carteira no padrao especificado pelo banco
   * @author Amarildo dos Santos de Lima
   */
  @Override
  public String getCarteiraFormatted() {
    return ""; //boleto.getCarteira();
  }

  /**
   * Recupera a agencia / codigo cedente no padrao especificado pelo banco
   * @author Amarildo dos Santos de Lima
   */
  @Override
  public String getAgenciaCodCedenteFormatted() {
    return ""; //return boleto.getAgencia() + " / " + boleto.getContaCorrente() + "-" + boleto.getDvContaCorrente();
  }

  /**
   * Recupera o nossoNumero no padrao especificado pelo banco
   * @author Amarildo dos Santos de Lima
   */
  @Override
  public String getNossoNumeroFormatted() {
    return ""; //String.valueOf(Integer.parseInt(boleto.getNossoNumero()));
  }
}
