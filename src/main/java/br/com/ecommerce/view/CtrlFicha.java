package br.com.ecommerce.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.controle.ConsultarFichaCommand;
import br.com.ecommerce.controle.SalvarFichaCommand;
import br.com.ecommerce.model.domain.FichaTecnica;

@RestController 
@RequestMapping("/ficha")
public class CtrlFicha {
	@Autowired
	private ConsultarFichaCommand consFichaCmd;
	
	@Autowired
	private SalvarFichaCommand salvarFichaCmd;

	@GetMapping
	public List <FichaTecnica> obterFichaTecnicas(){
		FichaTecnica fichaTecnica= new FichaTecnica();
		return (List<FichaTecnica>) consFichaCmd.executar(fichaTecnica);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody FichaTecnica fichaTecnica) {
		
		salvarFichaCmd.executar(fichaTecnica);
	}
}