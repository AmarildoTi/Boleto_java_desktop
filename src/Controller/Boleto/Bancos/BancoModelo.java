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
public class BancoModelo implements Banco {
    
    GerarBoleto boleto;
    
    /**
     * Metdodo responsavel por resgatar o numero do banco, coloque no return o codigo do seu banco
     */
    public String getNumero() {
        return "000";
    }
    
    /**
     * Classe construtura, recebe como parametro a classe jboletobean
     */
    public BancoModelo(GerarBoleto boleto) {
        this.boleto = boleto;
    }
    
    /**
     * Metodo que monta o primeiro campo do codigo de barras
     * Este campo como os demais e feito a partir do da documentacao do banco
     * A documentacao do banco tem a estrutura de como monta cada campo, depois disso
     * é só concatenar os campos como no exemplo abaixo.
     */
    private String getCampo1() {
        String campo = getNumero() + String.valueOf(boleto.getMoeda()) + boleto.getCodCliente().substring(0,4);
        
        return boleto.getDigitoCampo(campo,2);
    }
    
    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo2() {
        String campo = boleto.getCodCliente().substring(4) + boleto.getNossoNumero().substring(0,7);
        
        return boleto.getDigitoCampo(campo,1);
    }
    
    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo3() {
        String campo = boleto.getNossoNumero().substring(7) + boleto.getIOS() + boleto.getCarteira();
        
        return boleto.getDigitoCampo(campo,1);
    }
    
    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo4() {
        String campo = 	getNumero() + String.valueOf(boleto.getMoeda()) +
                boleto.getFatorVencimento() + boleto.getValorTitulo() + "9" + boleto.getCodCliente() +
                String.valueOf(boleto.getNossoNumero()) + boleto.getIOS() + boleto.getCarteira();
        
        return boleto.getDigitoCodigoBarras(campo);
    }
    
    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo5() {
        String campo = boleto.getFatorVencimento() + boleto.getValorTitulo();
        return campo;
    }
    
    /**
     * Metodo para monta o desenho do codigo de barras
     * A ordem destes campos tambem variam de banco para banco, entao e so olhar na documentacao do seu banco
     * qual a ordem correta
     */
    public String getCodigoBarras() {
        return getNumero() + String.valueOf(boleto.getMoeda()) + String.valueOf(getCampo4()) + String.valueOf(getCampo5()) + "9" + boleto.getCodCliente() + "00000" + boleto.getNossoNumero() + boleto.getIOS() + boleto.getCarteira();
    }
    
    /**
     * Metodo que concatena os campo para formar a linha digitavel
     * E necessario tambem olhar a documentacao do banco para saber a ordem correta a linha digitavel
     */
    public String getLinhaDigitavel() {
        return 	getCampo1().substring(0,5) + "." + getCampo1().substring(5) + "  " +
                getCampo2().substring(0,5) + "." + getCampo2().substring(5) + "  " +
                getCampo3().substring(0,5) + "." + getCampo3().substring(5) + "  " +
                getCampo4() + "  " + getCampo5();
    }
    
    /**
     * Recupera a carteira no padrao especificado pelo banco
     * @author Amarildo dos Santos de Lima
     */
    public String getCarteiraFormatted() {
        return boleto.getCarteira();
    }
    
    /**
     * Recupera a agencia / codigo cedente no padrao especificado pelo banco
     * @author Amarildo dos Santos de Lima
     */
    public String getAgenciaCodCedenteFormatted() {
        return boleto.getAgencia() + " / " + boleto.getContaCorrente() + "-" + boleto.getDvContaCorrente();
    }
    
    /**
     * Recupera o nossoNumero no padrao especificado pelo banco
     * @author Amarildo dos Santos de Lima
     */
    public String getNossoNumeroFormatted() {
        return String.valueOf(Long.parseLong(boleto.getNossoNumero()));
    }
}