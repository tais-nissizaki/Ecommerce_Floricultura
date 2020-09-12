package br.com.ecommerce.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.dao.ClienteDAO;
import br.com.ecommerce.dao.IDAO;
import br.com.ecommerce.model.domain.Cliente;
import br.com.ecommerce.model.domain.EntidadeDominio;
import br.com.ecommerce.negocio.ComplementarDt;
import br.com.ecommerce.negocio.CriarClassificacao;
import br.com.ecommerce.negocio.Criptografar;
import br.com.ecommerce.negocio.GerarLogCliente;
import br.com.ecommerce.negocio.IStrategy;
import br.com.ecommerce.negocio.ValidarCliente;

@Component
public class Fachada implements IFachada{
	private Map<String, List<IStrategy>> mapaAntesPesistencia;
	private Map<String, List<IStrategy>> mapaDepoisPesistencia;
	
	private Map<String, List<IStrategy>> mapaAntesInativar;
	private Map<String, List<IStrategy>> mapaDepoisInativar;
	
	private Map<String, List<IStrategy>> mapaAntesConsultar;
	private Map<String, List<IStrategy>> mapaDepoisConsultar;
	
	private Map<String, IDAO> mapaDaos;
	
	
	public Fachada(ComplementarDt compDtCad, CriarClassificacao criarClassificacao, Criptografar criptografar,	GerarLogCliente gLog, 
		ValidarCliente vCliente, ClienteDAO cliDAO) {
		
		List<IStrategy> rnsAntesCliente = new ArrayList<IStrategy>();
			
		List<IStrategy> rnsDepoisCliente = new ArrayList<IStrategy>();	
		
		//Lista de regras executadas antes da persistencia de cliente	
		rnsAntesCliente.add(compDtCad);
		rnsAntesCliente.add(criarClassificacao);
		rnsAntesCliente.add(vCliente);
		rnsAntesCliente.add(criptografar);
	
		//Lista de regras executadas depois da persistencia de cliente	
		rnsDepoisCliente.add(gLog);
		
				
		mapaAntesPesistencia = new HashMap<String, List<IStrategy>>();
		
		
		String nmCliente = Cliente.class.getName();
				
		mapaAntesPesistencia.put(nmCliente, rnsAntesCliente);
		
		
		mapaDepoisPesistencia = new HashMap<String, List<IStrategy>>();
		mapaDepoisPesistencia.put(nmCliente, rnsDepoisCliente);			
				
		mapaDaos = new HashMap<String, IDAO>();
		mapaDaos.put(nmCliente, cliDAO);
	
		mapaAntesConsultar = new HashMap<String, List<IStrategy>>();
		mapaDepoisConsultar = new HashMap<String, List<IStrategy>>();
		
		mapaAntesInativar = new HashMap<String, List<IStrategy>>();
		mapaDepoisInativar = new HashMap<String, List<IStrategy>>();
		
	}

	@Override
	@Transactional
	public String salvar(EntidadeDominio entidade) {
		String nmClass = entidade.getClass().getName();
		List<IStrategy> rnsAntes = mapaAntesPesistencia.get(nmClass);
		
		StringBuilder sb = executarStrategies(rnsAntes, entidade);		
		
		if(sb.length()==0) {
			//se nao houve erros
			IDAO dao = mapaDaos.get(nmClass);
			boolean salvouComSucesso = dao.salvar(entidade);
			if(!salvouComSucesso) {
				return "Falha ao gravar o produto na base de dados";
			}
			List<IStrategy> rnsDepois = mapaDepoisPesistencia.get(nmClass);
			sb.append(executarStrategies(rnsDepois, entidade));
		}else {
			return sb.toString();
		}
		
		return null;
	}
	
	private StringBuilder executarStrategies(List<IStrategy> strategies, EntidadeDominio entidade) {
		StringBuilder sb = new StringBuilder();
		if(strategies != null) {
			for(IStrategy rn:strategies) {
				String msg = rn.processar(entidade);
				if(msg!= null) {
					sb.append(msg);
				}
			}
		}
		return sb;
	}

	@Override
	public  String alterar(EntidadeDominio entidade) {
		String nmClass = entidade.getClass().getName();
		List<IStrategy> rnsAntes = mapaAntesPesistencia.get(nmClass);
		
		StringBuilder sb = executarStrategies(rnsAntes, entidade);		
		
		if(sb.length()==0) {
			//se nao houve erros
			IDAO dao = mapaDaos.get(nmClass);
			dao.alterar(entidade);
			List<IStrategy> rnsDepois = mapaDepoisPesistencia.get(nmClass);
			sb = executarStrategies(rnsDepois, entidade);
		}else {
			return sb.toString();
		}
		
		return null;

	}

	@Override
	public String inativar(EntidadeDominio entidade) {
		String nmClass = entidade.getClass().getName();
		List<IStrategy> rnsAntes = mapaAntesInativar.get(nmClass);
		
		StringBuilder sb = executarStrategies(rnsAntes, entidade);		
		
		if(sb.length()==0) {
			//se nao houve erros
			IDAO dao = mapaDaos.get(nmClass);
			dao.inativar(entidade);
			List<IStrategy> rnsDepois = mapaDepoisInativar.get(nmClass);
			sb = executarStrategies(rnsDepois, entidade);
		}else {
			return sb.toString();
		}
		
		return null;

	}


	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		String nmClass = entidade.getClass().getName();
		List<IStrategy> rnsAntes = mapaAntesConsultar.get(nmClass);
		List<EntidadeDominio> listaRetorno = null;
		
		StringBuilder sb = executarStrategies(rnsAntes, entidade);		
		
		if(sb.length()==0) {
			//se nao houve erros
			IDAO dao = mapaDaos.get(nmClass);
			listaRetorno = dao.consultar(entidade);
			List<IStrategy> rnsDepois = mapaDepoisConsultar.get(nmClass);
			sb = executarStrategies(rnsDepois, entidade);
		}else {
			return listaRetorno;
		}
		
		return listaRetorno;

	}

}
