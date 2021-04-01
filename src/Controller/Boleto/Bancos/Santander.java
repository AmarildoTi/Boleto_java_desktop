/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Boleto.Bancos;

import Controller.Boleto.Banco;
import Controller.Boleto.GerarBoleto;


/**
 * Classe para gerar o boleto do santander
 * @author Amarildo dos Santos de Lima
 */
public class Santander implements Banco {
	
	GerarBoleto boleto;
	
	public String D1 = "0";
	public String D2 = "0";
		
    /**
     * Metdodo responsavel por resgatar o numero do banco, coloque no return o codigo do seu banco
     */
        @Override
	public String getNumero() {
        return "033";
    }
    
    /**
     * Método paticular do santander
     * @return 
     */    
    public String getZero() {
        return "00";
    }
        
    private String getCampoLivre() {        

        // alteração realizada por Amarildo dos Santos de Lima
        // com base na versao 20/2010 do layout do banco
        String campo = "9" + boleto.getCodCliente() + boleto.getNossoNumero() + boleto.getIOS() + boleto.getCarteira() ;
        
        return campo;
    }
    
    /**
     * Classe construtura, recebe como parametro a classe jboletobean
     * @param boleto
     */
	public Santander(GerarBoleto boleto) {
		this.boleto = boleto;		
	}
	
    /**
     * Metodo que monta o primeiro campo do codigo de barras 
     * Este campo como os demais e feito a partir do da documentacao do banco
     * A documentacao do banco tem a estrutura de como monta cada campo, depois disso
     * Ã© sÃ³ concatenar os campos como no exemplo abaixo.
     */
	private String getCampo1() {
		String campo = getNumero() + String.valueOf(boleto.getMoeda()) + getCampoLivre().substring(0, 5);

		return campo + boleto.getModulo10(campo);		
	}
	
    /**
     * ver documentacao do banco para saber qual a ordem deste campo 
     */
	private String getCampo2() {
		String campo = getCampoLivre().substring(5, 15);		
		
		return campo + boleto.getModulo10(campo);
	}

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
	private String getCampo3() {
		String campo = getCampoLivre().substring(15, 25);		
		
		return campo + boleto.getModulo10(campo);		
	}
	
    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
	private String getCampo4() {
		String campo = 	getNumero() + String.valueOf(boleto.getMoeda()) +
						boleto.getFatorVencimento() + boleto.getValorTitulo() + getCampoLivre() ;

		return boleto.getModulo11(campo,9);
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
        @Override
	public String getCodigoBarras() {
		
		return getNumero() + String.valueOf(boleto.getMoeda()) + getCampo4() + getCampo5() + getCampoLivre();
		
	}

    /**
     * Metodo que concatena os campo para formar a linha digitavel
     * E necessario tambem olhar a documentacao do banco para saber a ordem correta a linha digitavel
     */    
    
        @Override
	public String getLinhaDigitavel() {
		return 	getCampo1().substring(0,5) + "." + getCampo1().substring(5) + "  " + 
				getCampo2().substring(0,5) + "." + getCampo2().substring(5) + "  " +
				getCampo3().substring(0,5) + "." + getCampo3().substring(5) + "  " +
				getCampo4() + "  " + getCampo5(); 
	}

    @Override
    public String getCarteiraFormatted() {
        return boleto.getCarteira();
    }

    @Override
    public String getAgenciaCodCedenteFormatted() {
        return boleto.getAgencia();
    }

    @Override
    public String getNossoNumeroFormatted() {
        return boleto.getNossoNumero();
    }
	
}