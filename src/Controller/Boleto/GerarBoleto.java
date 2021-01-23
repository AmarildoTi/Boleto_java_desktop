/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Boleto;

import Controller.Utilidades;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Amarildo dos Santos de lima
 */
public class GerarBoleto extends Utilidades {
    
    private String agencia;
    private String dvAgencia;
    private String contaCorrente;
    private String dvContaCorrente;
    private String moeda = "9";
    private String carteira;
    private String numConvenio;
    private String nossoNumero;
    private String dvNossoNumero;
    private String dataVencimento;
    private String dataDocumento;
    private String dataProcessamento;
    private String valorBoleto;
    private String caminho;
    private String tipoSaida;
    
    private String localPagamento;
    private String localPagamento2;
    private String cedente;
    private String qtdMoeda;
    private String valorMoeda;
    private String acrescimo;
    
    private String instrucao1;
    private String instrucao2;
    private String instrucao3;
    private String instrucao4;
    private String instrucao5;
    
    private String especieDocumento;
    private String aceite;
    
    private String linhaDigitavel;
    private String codigoBarras;
    
    private String codCliente;
    private String ios;
    
    private String noDocumento;
    private String codigoOperacao;
    private String codigoFornecidoAgencia;
    private String dvCodigoFornecidoAgencia;
    
    private String imagemMarketing;
    
    private String enderecoCodBar;
    
    public GerarBoleto() {
    }

    /**
	 * @return the enderecoCodBar
	 */
	public String getEnderecoCodBar() {
        
		return enderecoCodBar;
	}

	/**
	 * @param enderecoCodBar the enderecoCodBar to set
	 */
	public void setEnderecoCodBar(String enderecoCodBar) {
        
		this.enderecoCodBar = enderecoCodBar;
	}    

    /**
     * Método para gerar o fator de vencimento do boleto
     * 
     * @return long
     */
    public long getFatorVencimento() {
        String[] data = getDataVencimento().split("/");
        String dia = data[0];
        String mes = data[1];
        String ano = data[2];
        
        Calendar dataBase = new GregorianCalendar(1997, Calendar.OCTOBER, 7);
        Calendar vencimento = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes)-1, Integer.parseInt(dia));
        long diferenca = vencimento.getTimeInMillis() - dataBase.getTimeInMillis();
        
        long diferencaDias = diferenca/(24*60*60*1000);
        
        return diferencaDias;
    }
    
    /**
     * Retorna o numero da agencia.
     * Completar com zeros a esquerda quando necessario
     * @return Retorna o numero da agencia.
     */
    public String getAgencia() {
        return agencia;
    }
    
    /**
     * Seta o numero da agencia (ex. 2971).
     * @param agencia
     * Seta o numero da agencia (ex. 2971).
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    
    /**
     * Retorna o numero da carteira.
     * Itau = 3 digitos
     * Bradesco = 2 digitos
     * @return Retorna o numero da carteira.
     */
    public String getCarteira() {
        return carteira;
    }
    
    /**
     * Seta a carteira para o titulo (ex. 175. Para outros tipos veja com seu banco).
     * @param carteira
     * Seta a carteira para o titulo (ex. 175. Para outros tipos veja com seu banco).
     */
    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }
    
    /**
     * Retorna o numero da conta corrente.
     * @return Retorna o numero da conta corrente.
     */
    public String getContaCorrente() {
        return contaCorrente;
    }
    
    /**
     * Seta o numero da conta corrente - Coloque zeros a esquerda quando necessario
     * @param contaCorrente
     * Seta o numero da conta corrente - Coloque zeros a esquerda quando necessario
     */
    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }
    
    /**
     * Retorna o nosso numero.
     * @return Retorna o nosso numero.
     */
    public String getNossoNumero() {
        return nossoNumero;
    }

    /**
     * Retorna a data do vencimento do titulo.
     * @return Retorna a data do vencimento do titulo.
     */
    public String getDataVencimento() {
        return dataVencimento;
    }
    
    /**
     * Seta a data de vencimento do titulo (ex. 21/06/2005).
     * @param dataVencimento
     * Seta a data de vencimento do titulo (ex. 21/06/2005).
     */
    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    
    /**
     * Retorna o valor do titulo.
     * @return Retorna o valor do titulo.
     */
    public String getValorBoleto() {
        return valorBoleto;
    }
    
    /**
     * Seta o valor do titulo (ex. 23.45 ou 1234.45).
     * @param valorBoleto
     * Seta o valor do titulo (ex. 23.45 ou 1234.45).
     */
    public void setValorBoleto(String valorBoleto) {
        this.valorBoleto = valorBoleto;
    }
    
    /**
     * Retorna o caminho onde o PDF foi salvo.
     * @return Retorna o caminho onde o PDF foi salvo.
     */
    public String getCaminho() {
        return caminho;
    }
    
    /**
     * Seta o caminho onde o arquivo devera ser salvo. (ex.: /home/Amarildo/boleto-05-10-2005.pdf
     * @param caminho
     * Seta o caminho onde o arquivo devera ser salvo. (ex.: /home/Amarildo/boleto-05-10-2005.pdf
     */
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    
    /**
     * Retorna o tipo da moeda.
     * @return Retorna o tipo da moeda.
     */
    public String getMoeda() {
        return moeda;
    }
    
    /**
     * Retorna o acrescimo fornecido ao boleto.
     * @return Retorna o acrescimo fornecido ao boleto.
     */
    public String getAcrescimo() {
        return acrescimo;
    }
    
    /**
     * Seta o acrescimo ao boleto.
     * @param acrescimo
     * Seta o acrescimo ao boleto.
     */
    public void setAcrescimo(String acrescimo) {
        this.acrescimo = acrescimo;
    }
    
    /**
     * Retorna o nome do cedente.
     * @return Retorna o nome do cedente.
     */
    public String getCedente() {
        return cedente;
    }
    
    /**
     * Seta o nome do cedente.
     * @param cedente
     * Seta o nome do cedente.
     */
    public void setCedente(String cedente) {
        this.cedente = cedente;
    }
    
    
    /**
     * Retorna o campo para a 1 linha da instrucao.
     * @return Retorna o campo para a 1 linha da instrucao.
     */
    public String getInstrucao1() {
        return instrucao1;
    }
    
    /**
     * Seta o campo para a 1 linha da instrucao.
     * @param instrucao1
     * Seta o campo para a 1 linha da instrucao.
     */
    public void setInstrucao1(String instrucao1) {
        this.instrucao1 = instrucao1;
    }
    
    /**
     * Retorna o campo para a 2 linha da instrucao.
     * @return Retorna o campo para a 2 linha da instrucao.
     */
    public String getInstrucao2() {
        return instrucao2;
    }
    
    /**
     * Seta o campo para a 2 linha da instrucao.
     * @param instrucao2
     * Seta o campo para a 2 linha da instrucao.
     */
    public void setInstrucao2(String instrucao2) {
        this.instrucao2 = instrucao2;
    }
    
    /**
     * Retorna o campo para a 3 linha da instrucao.
     * @return Retorna o campo para a 3 linha da instrucao.
     */
    public String getInstrucao3() {
        return instrucao3;
    }
    
    /**
     * Seta o campo para a 3 linha da instrucao.
     * @param instrucao3
     * Seta o campo para a 3 linha da instrucao.
     */
    public void setInstrucao3(String instrucao3) {
        this.instrucao3 = instrucao3;
    }
    
    /**
     * Retorna o campo para a 4 linha da instrucao.
     * @return Retorna o campo para a 4 linha da instrucao.
     */
    public String getInstrucao4() {
        return instrucao4;
    }
    
    /**
     * seta o campo para a 4 linha da instrucao.
     * @param instrucao4
     * seta o campo para a 4 linha da instrucao.
     */
    public void setInstrucao4(String instrucao4) {
        this.instrucao4 = instrucao4;
    }
    
    /**
     * Retorna o campo para a 5 linha da instrucao.
     * @return Retorna o campo para a 5 linha da instrucao.
     */
    public String getInstrucao5() {
        return instrucao5;
    }
    
    /**
     * Seta o campo para a 5 linha da instrucao.
     * @param instrucao5
     * Seta o campo para a 5 linha da instrucao.
     */
    public void setInstrucao5(String instrucao5) {
        this.instrucao5 = instrucao5;
    }
    
    /**
     * Retorna o local de pagamento.
     * @return Retorna o local de pagamento.
     */
    public String getLocalPagamento() {
        return localPagamento;
    }
    
    /**
     * Seta o local de pagamento.
     * @param localPagamento
     * Seta o local de pagamento.
     */
    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }
    
    /**
     * Retorna a quantidade da moeda.
     * @return Retorna a quantidade da moeda.
     */
    public String getQtdMoeda() {
        return qtdMoeda;
    }
    
    /**
     * Seta a quantidade da moeda.
     * @param qtdMoeda
     * Seta a quantidade da moeda.
     */
    public void setQtdMoeda(String qtdMoeda) {
        this.qtdMoeda = qtdMoeda;
    }
    
    /**
     * Retorna o valor da moeda
     * @return Retorna o valor da moeda
     */
    public String getValorMoeda() {
        return valorMoeda;
    }
    
    /**
     * Seta o valor da moeda.
     * @param valorMoeda
     * Seta o valor da moeda.
     */
    public void setValorMoeda(String valorMoeda) {
        this.valorMoeda = valorMoeda;
    }
    
    /**
     * Retorna a segunda linha do local de pagamento
     * @return Retorna a segunda linha do local de pagamento
     */
    public String getLocalPagamento2() {
        return localPagamento2;
    }
    
    /**
     * Seta a segunda linha do local de pagamento.
     * @param localPagamento2
     * Seta a segunda linha do local de pagamento.
     */
    public void setLocalPagamento2(String localPagamento2) {
        this.localPagamento2 = localPagamento2;
    }
    
    /**
     * Retorna o campo aceite que por padrao vem com N.
     * @return Retorna o campo aceite que por padrao vem com N.
     */
    public String getAceite() {
        return aceite;
    }
    
    /**
     * Seta o campo aceite que por padrao vem com N.
     * @param aceite
     * Seta o campo aceite que por padrao vem com N.
     */
    public void setAceite(String aceite) {
        this.aceite = aceite;
    }
    
    /**
     * Retorna o campo especie do documento que por padrao vem com DV
     * @return Retorna o campo especie do documento que por padrao vem com DV
     */
    public String getEspecieDocumento() {
        return especieDocumento;
    }
    
    /**
     * Seta o campo especie do documento que por padrao vem com DV.
     * @param especieDocumento
     * Seta o campo especie do documento que por padrao vem com DV.
     */
    public void setEspecieDocumento(String especieDocumento) {
        this.especieDocumento = especieDocumento;
    }
    
    /**
     * Retorna a data do documento.
     * @return Retorna a data do documento.
     */
    public String getDataDocumento() {
        return dataDocumento;
    }
    
    /**
     * Seta a data do documento.
     * @param dataDocumento
     * Seta a data do documento.
     */
    public void setDataDocumento(String dataDocumento) {
        this.dataDocumento = dataDocumento;
    }
    
    /**
     * Retorna a data do processamento
     * @return Retorna a data do processamento.
     */
    public String getDataProcessamento() {
        return dataProcessamento;
    }
    
    /**
     * Seta a data do processamento.
     * @param dataProcessamento
     * Seta a data do processamento.
     */
    public void setDataProcessamento(String dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }
    
    /**
     * Seta o tipo da moeda
     * @param moeda
     * Seta o tipo da moeda.
     */
    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }
    
    /**
     * Retorna tipo de saida do arquivo
     * @return Retorna tipo de saida do arquivo
     */
    public String getTipoSaida() {
        return tipoSaida;
    }
    
    /**
     * Seta o tipo de saida do arquivo (html ou pdf)
     * @param tipoSaida
     * Seta o tipo de saida do arquivo (html ou pdf)
     */
    public void setTipoSaida(String tipoSaida) {
        this.tipoSaida = tipoSaida;
    }
    
    /**
     * Retorna o número digitável do código de barras
     * @return Retorna o número digitável do código de barras
     */
    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }
    
    /**
     * Seta o número digitável do código de barras
     * @param linhaDigitavel
     */
    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }
    
    /**
     * Retorna o número do código de barras
     * @return Retorna o número do código de barras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }
    
    /**
     * Seta o número do código de barras
     * @param codigoBarras
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    
    /**
     * Retorna o quinto campo da linha digitavel do codigo
     * @return Retorna o quinto campo da linha digitavel do codigo
     */
    public String getValorTitulo() {
        String valor = "";
        valor = getValorBoleto();
        valor = valor.replace(",","").replace(".","");
        String valorTitulo  =  Padl(RetZeros(valor),10,"0");
        return valorTitulo;
    }
    
    /**
     * Modulo 10 (212121)\n
     * Retorna o digito verificador de cada campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.
     * @param campo
     * @param mult
     * @return Retorna o digito verificador de cada campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.
     */
    public String getDigitoCampo(String campo, int mult) {
        //Esta rotina faz o calcula 212121
        
        int multiplicador = mult;
        int multiplicacao = 0;
        int soma_campo = 0;
        
        for (int i = 0; i < campo.length(); i++) {
            multiplicacao = Integer.parseInt(campo.substring(i,1+i)) * multiplicador;
            
            if (multiplicacao >= 10) {
                multiplicacao = Integer.parseInt(String.valueOf(multiplicacao).substring(0,1)) + Integer.parseInt(String.valueOf(multiplicacao).substring(1));
            }
            
            soma_campo = soma_campo + multiplicacao;
            
            // ALTERADO POR Amarildo dos Santos de Lima PARA SUBSTITUIR O COMENTARIO ABAIXO
            // valores assumidos: 212121...
            multiplicador = (multiplicador % 2) + 1;
            /*
            if (multiplicador == 2)
                multiplicador = 1;
            else
                multiplicador = 2;
             */
            
        }
        int dac = 10 - (soma_campo%10);
        
        if (dac == 10)
            dac = 0;
        
        campo = campo + String.valueOf(dac);
        
        return campo;
    }
    
    /**
     * ALTERADO POR Amarildo dos Santos de Lima - METODO ALTERNATIVO AO METODO getDigitoCampo(String, int)
     * Modulo 10 (212121)\n
     * Retorna o digito verificador de cada campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.\n
     * Nao tem a necessidade do mult e possui o mesmo efeito que o metodo getDigitoCampo(String, int)
     * @param campo
     * @return Retorna o digito verificador de cada campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.
     */
    public String getDigitoCampo(String campo) {
        //Esta rotina faz o calcula 212121
        
        int multiplicador = 2;
        int multiplicacao = 0;
        int soma_campo = 0;
        
        for (int i = campo.length(); i > 0; i--) {
            multiplicacao = Integer.parseInt(campo.substring(i-1,i)) * multiplicador;
            
            if (multiplicacao >= 10) {
                multiplicacao = Integer.parseInt(String.valueOf(multiplicacao).substring(0,1)) + Integer.parseInt(String.valueOf(multiplicacao).substring(1));
            }
            soma_campo += multiplicacao;
            
            // valores assumidos: 212121...
            multiplicador = (multiplicador % 2) + 1;
        }
        int dac = 10 - (soma_campo%10);
        
        if (dac == 10)
            dac = 0;
        
        campo = campo + String.valueOf(dac);
        
        return campo;
    }
    
    /**
     * Modulo 11
     * Retorna o digito verificador do codigo de barras (4o campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.
     * @param campo
     * @return Modulo 11 - Retorna o digito verificador do codigo de barras (4o campo da linha digitavel. Voce deve passar como parametro a string do campo conforme o seu banco.
     */
    public String getDigitoCodigoBarras(String campo) {
        //Esta rotina faz o calcula no modulo 11 - 23456789
        
        int multiplicador = 4;
        int multiplicacao = 0;
        int soma_campo = 0;
        
        for (int i = 0; i < campo.length(); i++) {
            multiplicacao = Integer.parseInt(campo.substring(i,1+i)) * multiplicador;
            
            soma_campo = soma_campo + multiplicacao;
            
            // ALTERADO POR Amarildo dos Santos de Lima PARA SUBSTITUIR O COMENTARIO ABAIXO
            // valores assumidos: 43298765432987...
            multiplicador = ((multiplicador+5) % 8) + 2;
            /*
            if (multiplicador == 4)
                multiplicador = 3;
             
            else if (multiplicador == 3)
                multiplicador = 2;
             
            else if (multiplicador == 2)
                multiplicador = 9;
             
            else if (multiplicador == 9)
                multiplicador = 8;
             
            else if (multiplicador == 8)
                multiplicador = 7;
             
            else if (multiplicador == 7)
                multiplicador = 6;
             
            else if (multiplicador == 6)
                multiplicador = 5;
             
            else if (multiplicador == 5)
                multiplicador = 4;
             */
        }
        
        int dac = 11 - (soma_campo%11);
        
        if (dac == 0 || dac == 1 || dac > 9)
            dac = 1;
        
        campo = String.valueOf(dac);
        
        return campo;
    }
    
    /**
     * Retorna o codigo do cliente. Caseo seja codigo do cliente da caixa este campo deve conter 6 posicoes.
     * @return Retorna o codigo do cliente.
     */
    public String getCodCliente() {
        return codCliente;
    }
    
    /**
     * Seta um codigo de cliente. Alguns bancos pedem este campo.
     * @param codCliente
     * Seta um codigo de cliente. Alguns bancos pedem este campo
     */
    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }
    
    /**
     * Retorna o IOS do Banco.
     * @return Retorna o IOS do Banco.
     */
    public String getIOS() {
        return getIos();
    }
    
    /**
     * Seta um codigo de IOS do Banco.
     * @param ios
     */
    public void setIOS(String ios) {
        this.setIos(ios);
    }
    
    /**
     * Retorna o numero do convenio
     * @return Retorna o numero do convenio
     */
    public String getNumConvenio() {
        return numConvenio;
    }
    
    /**
     * Seta o numero do convenio
     * @param numConvenio
     * Seta o numero do convenio
     */
    public void setNumConvenio(String numConvenio) {
        this.numConvenio = numConvenio;
    }
    
    public String getDvAgencia() {
        return dvAgencia;
    }
    
    public void setDvAgencia(String dvAgencia) {
        this.dvAgencia = dvAgencia;
    }
    
    public String getDvContaCorrente() {
        return dvContaCorrente;
    }
    
    public void setDvContaCorrente(String dvContaCorrente) {
        this.dvContaCorrente = dvContaCorrente;
    }
    
    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }
    
    public String getIos() {
        return ios;
    }
    
    public void setIos(String ios) {
        this.ios = ios;
    }
    
    /**
     * recupera o codigo de operacao
     * @return Retorna o codigo de operacao
     */
    public String getCodigoOperacao() {
        return codigoOperacao;
    }
    
    /**
     * Seta o codigo de operacao - necessario somente para caixa
     * @param codigoOperacao
     * Seta o codigo da operacao
     */
    public void setCodigoOperacao(String codigoOperacao) {
        this.codigoOperacao = codigoOperacao;
    }
    
    /**
     * Recupera o codigo fornecido pela agencia
     * @return Recupera o codigo fornecido pela agencia
     */
    public String getCodigoFornecidoAgencia() {
        return codigoFornecidoAgencia;
    }
    
    /**
     * Seta o codigo fornecido pela agencia - necessario somente para caixa.
     * @param codigoFornecidoAgencia
     * Seta o codigo fornecido pela agencia
     */
    public void setCodigoFornecidoAgencia(String codigoFornecidoAgencia) {
        this.codigoFornecidoAgencia = codigoFornecidoAgencia;
    }
    
    /**
     * Recupara o numero do documento
     * @return Recupera o numero do documento
     */
    public String getNoDocumento() {
        return noDocumento;
    }
    
    /**
     * Seta o numero do documento
     * @param noDocumento
     * Seta o numero do documento
     */
    public void setNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
    }
    
    /**
     * Completa uma String com zeros a esquerda
     * @param str String a ser completada
     * @param qtdZeros Quantidade de zeros que deseja a esquerda
     * Completa uma String com zeros a esquerda
     * @return 
     */
    public String completaZerosEsquerda(String str, int qtdZeros) {
        
        String zeros = "000000000000000000000000000000000000000";
        long dif = qtdZeros - str.length();
        
        return zeros.substring(0,Integer.parseInt(String.valueOf(dif)) )+ str;
    }
    
    /**
     * Pega o caminho da imagem de marketing para ser colocada na parte superior do boleto
     * @return 
     */
    public String getImagemMarketing() {
        return imagemMarketing;
    }
    
    /**
     * Seta a imagem de marketing que ira na parte superior do boleto
     * O tamanho ideal para a imagem é de: 1000x668 px
     * @param imagemMarketing Caminho da imagem
     */
    public void setImagemMarketing(String imagemMarketing) {
        this.imagemMarketing = imagemMarketing;
    }
    
    /**
     * Recupar o digito verificador do nosso numero
     * @return Recupera o digito verificador do nosso numero
     * @author Amarildo dos Santos de Lima
     */
    public String getDvNossoNumero() {
        return (dvNossoNumero!=null)? dvNossoNumero : "";
    }
    
    /**
     * Seta o digito verificar do nosso numero
     * @author Amarildo dos Santos de Lima
     * @param dvNossoNumero
     */
    public void setDvNossoNumero(String dvNossoNumero) {
        this.dvNossoNumero = getModulo11(dvNossoNumero,11);
    }

    /**
     * Recupar o digito verificador fornecido pela agencia
     * @return Recupar o digito verificador fornecido pela agencia
     * @author Amarildo dos Santos de Lima
     */
    public String getDvCodigoFornecidoAgencia() {
        return dvCodigoFornecidoAgencia;
    }

    /**
     * Seta o digito verificar do codigo fornecido pela Agencia
     * @author Amarildo dos Santos de Lima
     * @param dvCodigoFornecidoAgencia
     */
    public void setDvCodigoFornecidoAgencia(String dvCodigoFornecidoAgencia) {
        this.dvCodigoFornecidoAgencia = dvCodigoFornecidoAgencia;
    }
    
	/**
	 * 	getModulo10
         *      @param campo
	 * 	@return String dac
	 *      @author Amarildo dos Santos de Lima
	 */
    public String getModulo10(String campo) {
        
		int multiplicador = 2;
		int multiplicacao = 0;
		int soma_campo = 0;
		
		for (int i = campo.length(); i > 0; i--) {
			multiplicacao = Integer.parseInt(campo.substring(i-1,i)) * multiplicador;
			
			if (multiplicacao >= 10) {
				multiplicacao = Integer.parseInt(String.valueOf(multiplicacao).substring(0,1)) + Integer.parseInt(String.valueOf(multiplicacao).substring(1,2)); 
			}
			soma_campo = soma_campo + multiplicacao;
			
			if (multiplicador == 1)
				multiplicador = 2;
			else
				multiplicador = 1;
		}
		int dac = 10 - (soma_campo%10);
		
		if (dac > 9)
			dac = 0;

        return ((Integer)dac).toString();
    }
    
	/**
	 * 	getModulo11
         *      @param campo
         *      @param type
	 * 	@return int dac
	 *      @author Amarildo dos Santos de Lima
	 */
    public String getModulo11(String campo,int type) {
    	//Modulo 11 - 234567   (type = 7)
    	//Modulo 11 - 23456789 (type = 9)
        
    	int multiplicador = 2;
		int multiplicacao = 0;
		int soma_campo = 0;
		
		for (int i = campo.length(); i > 0; i--) {
			multiplicacao = Integer.parseInt(campo.substring(i-1,i)) * multiplicador;
			
			soma_campo = soma_campo + multiplicacao;
			
			multiplicador++;
			if (multiplicador > 7 && type == 7)
				multiplicador = 2;
			else if (multiplicador > 9 && type == 9)
				multiplicador = 2;
		}
		
		int dac = 11 - (soma_campo%11);
		
        if (dac > 9 && type == 7)
            dac = 0;
        else if ((dac == 0 || dac == 1 || dac > 9) && type == 9)
        	dac = 1;

        return ((Integer)dac).toString();
    }

}
