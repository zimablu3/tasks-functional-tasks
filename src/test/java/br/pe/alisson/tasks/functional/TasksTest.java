package br.pe.alisson.tasks.functional;




import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;




public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
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
	public void deveSalvarTarefaSemDescricao() {
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
	public void deveSalvarTarefaSemData() {
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
	public void naodeveSalvarTarefaComDataPassada() {
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