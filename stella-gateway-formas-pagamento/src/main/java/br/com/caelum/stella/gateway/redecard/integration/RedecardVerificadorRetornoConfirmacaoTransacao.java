package br.com.caelum.stella.gateway.redecard.integration;

import br.com.caelum.stella.gateway.core.ProblematicTransactionException;

public class RedecardVerificadorRetornoConfirmacaoTransacao {
	private String retorno;

	public RedecardVerificadorRetornoConfirmacaoTransacao(String retorno) {
		this.retorno = retorno;
	}
	
	public RedecardConfirmacaoTransacaoReturn verificaRetorno(){
		if(!"".equals(retorno)){
			String[] params = retorno.split("&");
			int codigoRetorno = Integer.valueOf(params[0].split("=")[1]);
			String mensagemRetorno = params[1].split("=")[1];
			RedecardConfirmacaoTransacaoReturn confirmacaoTransacaoReturn = new RedecardConfirmacaoTransacaoReturn(codigoRetorno,mensagemRetorno);
			if(codigoRetorno!=0){
				throw new ProblematicTransactionException("A transa��o n�o pode ser confirmada ("+confirmacaoTransacaoReturn.getMensagemRetorno()+")",confirmacaoTransacaoReturn);
			}
			return confirmacaoTransacaoReturn;
		}
		throw new RedecardConfirmacaoSemDadosException("Os dados de retorno n�o foram enviados, solicite novamente",null);		
	}

}