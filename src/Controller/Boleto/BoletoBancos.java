/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Boleto;

import Controller.Boleto.Bancos.BancoBrasil;
import Controller.Boleto.Bancos.BancoGenerico;
import Controller.Boleto.Bancos.BancoReal;
import Controller.Boleto.Bancos.Bradesco;
import Controller.Boleto.Bancos.CaixaEconomica;
import Controller.Boleto.Bancos.Hsbc;
import Controller.Boleto.Bancos.Itau;
import Controller.Boleto.Bancos.NossaCaixa;
import Controller.Boleto.Bancos.Santander;
import Controller.Boleto.Bancos.Unibanco;


/**
 * Classe resposavel por setar todas as configuracoes necessarias para a geracao do titulo / boleto
 * @author Amarildo dos Santos de Lima
 */
 
 public class BoletoBancos {
    
    public static final int BANCO_DO_BRASIL = 1;
    public static final int BRADESCO = 237;
    public static final int ITAU = 341;
    public static final int BANCO_REAL = 356;
    public static final int CAIXA_ECONOMICA = 104;
    public static final int UNIBANCO = 409;
    public static final int HSBC = 399;
    public static final int NOSSACAIXA = 151;
    public static final int SANTANDER = 33;
    public static final int BANCO_GENERICO = 99999;
    
    private final GerarBoleto boleto;
    private Banco banco;

    public BoletoBancos(GerarBoleto boleto, int codBanco) {
        
        this.boleto = boleto;
        
        switch (codBanco) {
            case BoletoBancos.BANCO_DO_BRASIL:
                banco = new BancoBrasil(boleto);
                break;
            case BoletoBancos.BRADESCO:
                banco = new Bradesco(boleto);
                break;
            case BoletoBancos.ITAU:
                banco = new Itau(boleto);
                break;
            case BoletoBancos.BANCO_REAL:
                banco = new BancoReal(boleto);
                break;
            case BoletoBancos.CAIXA_ECONOMICA:
                banco = new CaixaEconomica(boleto);
                break;
            case BoletoBancos.UNIBANCO:
                banco = new Unibanco(boleto);
                break;
            case BoletoBancos.HSBC:
                banco = new Hsbc(boleto);
                break;
            case BoletoBancos.NOSSACAIXA:
                banco = new NossaCaixa(boleto);
                break;
            case BoletoBancos.SANTANDER:
                banco = new Santander(boleto);
                break;
            case BoletoBancos.BANCO_GENERICO:
                banco = new BancoGenerico(boleto);
                break;
            default:
                break;
        }
      
        boleto.setCodigoBarras(banco.getCodigoBarras());
        boleto.setLinhaDigitavel(banco.getLinhaDigitavel());
    }

    public BoletoBancos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}