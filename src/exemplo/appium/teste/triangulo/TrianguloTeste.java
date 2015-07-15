package exemplo.appium.teste.triangulo;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class TrianguloTeste {

	private AndroidDriver<WebElement> driver;
	private DesiredCapabilities capacidades;
	
	@Before
	public void setUp() throws Exception {
		File diretorio = new File("apps");
		File app = new File(diretorio, "TrianguloApp.apk");
		
		capacidades = new DesiredCapabilities();
		capacidades.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capacidades.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capacidades.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.ANDROID);
		
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capacidades);
	}

	@After
	public void tearDown() throws Exception {
		driver.closeApp();
	}
	
	@Test
	public void testeTrianguloEquilatero() {
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado1")).sendKeys("3");
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado2")).sendKeys("3");
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado3")).sendKeys("6");
		
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/btnCalcular")).click();
		
		assertEquals("O triângulo é Equilátero",
				driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtResultado")).getText());
	}
	
	@Test
	public void testeTrianguloIsosceles() {
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado1")).sendKeys("3");
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado2")).sendKeys("3");
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado3")).sendKeys("6");
		
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/btnCalcular")).click();
		
		assertEquals("O triângulo é Isósceles",
				driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtResultado")).getText());
	}
	
	@Test
	public void testeTrianguloEscaleno() {
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado1")).sendKeys("3");
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado2")).sendKeys("6");
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado3")).sendKeys("9");
		
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/btnCalcular")).click();
		
		assertEquals("O triângulo é Escaleno",
				driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtResultado")).getText());
	}

}
