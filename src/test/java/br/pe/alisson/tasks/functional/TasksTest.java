package br.pe.alisson.tasks.functional;




import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;




public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://172.17.0.1:4444/wd/hub"), cap);
		driver.navigate().to("http://172.17.0.1:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste de lontra2");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2028");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}
	
	@Test
	public void deveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2028");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}
	
	@Test
	public void deveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste de lontra2");
			
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}
	
	@Test
	public void naodeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste de lontra2");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2018");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}
	
}