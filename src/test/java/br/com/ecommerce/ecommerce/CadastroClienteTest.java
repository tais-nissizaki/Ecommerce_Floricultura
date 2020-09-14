package br.com.ecommerce.ecommerce;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class CadastroClienteTest {
	
	private ChromeDriver driver;
	
	@Before
	public void inicializa() {
	}
	
	
	@Test
	public void deveAdicionarUmCliente() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\taisk\\Development\\tools\\chromedriver_win32\\chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.get("http://localhost:4200/cliente/cadastro");
		
//		clientes.novo().cadastro("maria.silva@teste.com.br","Maria$ilva");
//		
//		assertTrue(usuarios.existeNaLista("maria.silva@teste.com.br","Maria$ilva"));
		
		WebElement login = driver.findElement(By.name("email"));
		WebElement senha = driver.findElement(By.name("senha"));
		WebElement confirmaSenha = driver.findElement(By.name("confirmaSenha"));
		WebElement nome = driver.findElement(By.name("nome"));
		WebElement cpf = driver.findElement(By.name("cpf"));
		WebElement cbGenero = driver.findElement(By.id("genero"));
		WebElement dtNasc = driver.findElement(By.name("dtNasc"));
		WebElement cbTipoTelefone = driver.findElement(By.id("tipoTelefone"));
		WebElement ddd = driver.findElement(By.name("ddd"));
		WebElement telefone = driver.findElement(By.name("telefone"));
		WebElement descricao = driver.findElement(By.name("descricao"));
		WebElement tipoLogradouro = driver.findElement(By.name("tipoLogradouro"));
		WebElement logradouro = driver.findElement(By.name("logradouro"));
		WebElement numero = driver.findElement(By.name("numero"));
		WebElement tipoResidencia = driver.findElement(By.name("tipoResidencia"));
		WebElement observacoes = driver.findElement(By.name("observacoes"));
		WebElement bairro = driver.findElement(By.name("bairro"));
		WebElement cidade = driver.findElement(By.name("cidade"));
		WebElement cbEstado = driver.findElement(By.id("estado"));
		WebElement cep = driver.findElement(By.name("cep"));
		
		
		login.sendKeys("joana.silva@teste.com.br");
		senha.sendKeys("Joana$ilva");
		confirmaSenha.sendKeys("Joana$ilva");
		nome.sendKeys("Joana da Silva");
		cpf.sendKeys("261.163.980-90");
		cbGenero.click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and text()='FEMININO']")).click();
		dtNasc.click();
		driver.findElement(By.xpath("//div[contains(@class, 'mat-calendar-body-cell-content') and text()='1']")).click();
		cbTipoTelefone.click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and text()='RESIDENCIAL']")).click();
		ddd.sendKeys("11");
		telefone.sendKeys("45671234");
		descricao.sendKeys("minha casa");
		tipoLogradouro.sendKeys("Estrada");
		logradouro.sendKeys("dos vegetais");
		numero.sendKeys("150");
		tipoResidencia.sendKeys("casa");
		observacoes.sendKeys("");
		bairro.sendKeys("Vegetarios");
		cidade.sendKeys("Mogi das Cruzes");
		cbEstado.click();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and text()='SP']")).click();
		cep.sendKeys("08773875");
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement botaoSalvar = driver.findElement(By.id("btnSalvar"));
		botaoSalvar.click();
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// garantir que o cliente foi adicionado!
		
		driver.get("http://localhost:8080/clientes?txtCpf=261.163.980-90");
		
		boolean achouLogin = driver.getPageSource().contains("joana.silva@teste.com.br");
		boolean achouNome = driver.getPageSource().contains("Joana da Silva");
		boolean achouCpf = driver.getPageSource().contains("261.163.980-90");
		boolean achouGenero = driver.getPageSource().contains("FEMININO");
		boolean achouDtNasc = driver.getPageSource().contains("2020-09-01");
		boolean achouTipoTelefone = driver.getPageSource().contains("RESIDENCIAL");
		boolean achouDdd = driver.getPageSource().contains("11");
		boolean achouTelefone = driver.getPageSource().contains("45671234");
		boolean achouDescricao = driver.getPageSource().contains("minha casa");
		boolean achouTipoLogradouro = driver.getPageSource().contains("Estrada");
		boolean achouLogradouro = driver.getPageSource().contains("dos vegetais");
		boolean achouTipoResidencia = driver.getPageSource().contains("casa");
		boolean achouObservacoes = driver.getPageSource().contains("");
		boolean achouBairro = driver.getPageSource().contains("Vegetarios");
		boolean achouCidade = driver.getPageSource().contains("Mogi das Cruzes");
		boolean achouEstado = driver.getPageSource().contains("SP");
		boolean achouCep = driver.getPageSource().contains("08773875");
		
		assertTrue(achouLogin);
		assertTrue(achouNome);
		assertTrue(achouCpf);
		assertTrue(achouGenero);
		assertTrue(achouDtNasc);
		assertTrue(achouTipoTelefone);
		assertTrue(achouDdd);
		assertTrue(achouTelefone);
		assertTrue(achouDescricao);
		assertTrue(achouTipoLogradouro);
		assertTrue(achouLogradouro);
		assertTrue(achouTipoResidencia);
		assertTrue(achouObservacoes);
		assertTrue(achouBairro);
		assertTrue(achouCidade);
		assertTrue(achouEstado);
		assertTrue(achouCep);
		

		driver.close();
		
	}
	
	@After
	public void finaliza() {
	}
	
}
