/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Boleto;

/**
 * Classe Seta as variaveis de calculo 
 * @author Amarildo dos Santos de Lima
 *
 */

public interface Banco {

    /**
     * Recupera o codigo do banco
     * @return o codigo do banco
     */
    public String getNumero();

    /**
     * Recupera o numero necessario para a geracao do codigo de barras
     * @return 
     */
    public String getCodigoBarras();

    /**
     * Recupera a numeracao para a geracao da linha digitavel do boleto
     * @return 
     */
    public String getLinhaDigitavel();

    /**
     * @return 
     * @Return Recupera a carteira no padrao especificado pelo banco
     */
    public String getCarteiraFormatted();

    /**
     * @return 
     * @Return Recupera a agencia/codigo cedente no padrao especificado pelo banco
     */
    public String getAgenciaCodCedenteFormatted();

    /**
     * @return 
     * @Return Recupera o nosso numero no padrao especificado pelo banco
     */
    public String getNossoNumeroFormatted();
}