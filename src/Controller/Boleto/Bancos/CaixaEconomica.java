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
public class CaixaEconomica implements Banco {

    GerarBoleto boleto;

    /**
     * Metdodo responsavel por resgatar o numero do banco, coloque no return o codigo do seu banco
     */
    @Override
    public String getNumero() {
        return "104";
    }

    /**
     * Retorna o Campo livre
     */
    private String getCampoLivre() {
        return boleto.getCarteira() + boleto.getNossoNumero() + boleto.getAgencia() + boleto.getCodigoOperacao() + boleto.getCodigoFornecidoAgencia();
    }

    /**
     * Classe construtura, recebe como parametro a classe jboletobean
     * @param boleto
     */
    public CaixaEconomica(GerarBoleto boleto) {
        this.boleto = boleto;
    }

    /**
     * Metodo que monta o primeiro campo do codigo de barras
     * Este campo como os demais e feito a partir do da documentacao do banco
     * A documentacao do banco tem a estrutura de como monta cada campo, depois disso
     * concatenar os campos como no exemplo abaixo.
     */
    private String getCampo1() {
        String campo = getNumero() + String.valueOf(boleto.getMoeda()) + getCampoLivre().substring(0, 5);

        return boleto.getDigitoCampo(campo, 2);
    }

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo2() {
        String campo = getCampoLivre().substring(5, 15);

        return boleto.getDigitoCampo(campo, 1);
    }

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo3() {
        String campo = getCampoLivre().substring(15);

        return boleto.getDigitoCampo(campo, 1);
    }

    /**
     * ver documentacao do banco para saber qual a ordem deste campo
     */
    private String getCampo4() {
        String campo = getNumero() + String.valueOf(boleto.getMoeda()) + boleto.getFatorVencimento() + boleto.getValorTitulo() + getCampoLivre();

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
    @Override
    public String getCodigoBarras() {
        return getNumero() + String.valueOf(boleto.getMoeda()) + String.valueOf(getCampo4()) + String.valueOf(getCampo5()) + getCampoLivre();
    }

    /**
     * Metodo que concatena os campo para formar a linha digitavel
     * E necessario tambem olhar a documentacao do banco para saber a ordem correta a linha digitavel
     */
    @Override
    public String getLinhaDigitavel() {
        return getCampo1().substring(0, 5) + "." + getCampo1().substring(5) + "  " + getCampo2().substring(0, 5) + "." + getCampo2().substring(5) + "  " + getCampo3().substring(0, 5) + "." + getCampo3().substring(5) + "  " + getCampo4() + "  " + getCampo5();
    }

    /**
     * Recupera a carteira no padrao especificado pelo banco
     * @author Amarildo dos Santos de Lima
     */
    @Override
    public String getCarteiraFormatted() {
        return "SR";
    }

    /**
     * Recupera a carteira no padrao especificado pelo banco
     * @author Amarildo dos Santos de Lima
     */
    @Override
    public String getAgenciaCodCedenteFormatted() {

        Integer f1 = Integer.parseInt(boleto.getAgencia().substring(0, 4));
        Integer f2 = Integer.parseInt(boleto.getCodigoOperacao().substring(0, 3));
        Integer f3 = Integer.parseInt(boleto.getCodigoFornecidoAgencia());
        Integer f4 = Integer.parseInt(boleto.getDvCodigoFornecidoAgencia());

        return String.format("%04d.%03d.%08d-%01d", f1, f2, f3, f4);
    }

    @Override
    public String getNossoNumeroFormatted() {
        return boleto.getCarteira() + "." + boleto.getNossoNumero() + "-" + boleto.getDvNossoNumero();
    }
}