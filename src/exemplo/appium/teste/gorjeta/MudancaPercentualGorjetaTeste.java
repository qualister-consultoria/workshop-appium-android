package exemplo.appium.teste.gorjeta;

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

public class MudancaPercentualGorjetaTeste {

	private AndroidDriver<WebElement> driver;
	private DesiredCapabilities capacidades;
	
	@Before
	public void setUp() throws Exception {
		File diretorio = new File("apps");
		File app = new File(diretorio, "Fastip.apk");
		
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
	public void testeMudancaPercentualGorjeta() {
		driver.findElement(By.id("org.traeg.fastip:id/menu_settings")).click();
		
		WebElement percentual = driver.findElement(By.id("org.traeg.fastip:id/txt_percentual_settings"));
		percentual.clear();
		percentual.sendKeys("10");
		
		driver.findElement(By.id("org.traeg.fastip:id/btn_Salvar")).click();
		
		WebElement valorConta = driver.findElement(By.id("org.traeg.fastip:id/valorTotalText"));
		valorConta.clear();
		valorConta.sendKeys("200");
		
		driver.findElement(By.id("org.traeg.fastip:id/btn_calcular")).click();
		
		assertEquals("10%", driver.findElement(By.id("org.traeg.fastip:id/txt_percentual")).getText());
		assertEquals("$20,00", driver.findElement(By.id("org.traeg.fastip:id/txt_valor_gorjeta")).getText());
		assertEquals("$220,00", driver.findElement(By.id("org.traeg.fastip:id/txt_total_conta")).getText());
		
	}

}
