/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Boleto.Bancos;

import Controller.Boleto.Banco;
import Controller.Boleto.GerarBoleto;


/**
 * Classe responsavel em criar os campos do Banco Bradesco.
 * @author Amarildo dos Santos de Lima
 */
public class Bradesco implements Banco {
    
    GerarBoleto boleto;
    
    @Override
    public String getNumero() {
        return "237";
    }
    
    public Bradesco(GerarBoleto boleto) {
        this.boleto = boleto;
    }
    
    private String getCampoLivre() {
        
        String campo = boleto.getAgencia() + boleto.getCarteira() + boleto.getNossoNumero() + boleto.getContaCorrente() + "0";
        
        return campo;
    }
    
    private String getCampo1() {
        String campo = getNumero() + boleto.getMoeda() + getCampoLivre().substring(0,5);
        
        return boleto.getDigitoCampo(campo,2);
    }
    
    private String getCampo2() {
        String campo = getCampoLivre().substring(5,15);
        
        return boleto.getDigitoCampo(campo,1);
    }
    
    private String getCampo3() {
        String campo = getCampoLivre().substring(15,25);
        
        return boleto.getDigitoCampo(campo,1);
    }
    
    private String getCampo4() {
        String campo = 	getNumero() +
                boleto.getMoeda() +
                boleto.getFatorVencimento() +
                boleto.getValorTitulo() +
                boleto.getAgencia() +
                boleto.getCarteira() +
                boleto.getNossoNumero() +
                boleto.getContaCorrente() + "0";
        
        return boleto.getDigitoCodigoBarras(campo);
    }
    
    private String getCampo5() {
        String campo = boleto.getFatorVencimento() + boleto.getValorTitulo();
        return campo;
    }
    
    @Override
    public String getCodigoBarras() {
        String campo = 	getNumero() + String.valueOf(boleto.getMoeda()) + getCampo4() +
                boleto.getFatorVencimento() + boleto.getValorTitulo() + boleto.getAgencia() +
                boleto.getCarteira() + boleto.getNossoNumero() + boleto.getContaCorrente() + "0";
        
        return campo;
    }
    
    @Override
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
    @Override
    public String getCarteiraFormatted() {
        return boleto.getCarteira();
    }
    
    /**
     * Recupera a agencia / codigo cedente no padrao especificado pelo banco
     * @author Amarildo dos Santos de Lima
     */
    @Override
    public String getAgenciaCodCedenteFormatted() {
        return boleto.getAgencia() + " / " + boleto.getContaCorrente() + "-" + boleto.getDvContaCorrente();
    }
    
    /**
     * Recupera o nossoNumero no padrao especificado pelo banco
     * @author Amarildo dos Santos de Lima
     */
    @Override
    public String getNossoNumeroFormatted() {
        return getCarteiraFormatted().concat( " / " ).concat( boleto.getNossoNumero());
    }
}