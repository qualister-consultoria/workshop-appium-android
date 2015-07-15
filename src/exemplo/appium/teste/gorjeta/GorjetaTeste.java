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

public class GorjetaTeste {

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
	public void testeValorFinalConta() {
		driver.findElement(By.id("org.traeg.fastip:id/valorTotalText")).sendKeys("100");
		driver.findElement(By.id("org.traeg.fastip:id/btn_calcular")).click();
		
		assertEquals("15%", driver.findElement(By.id("org.traeg.fastip:id/txt_percentual")).getText());
		assertEquals("$15,00", driver.findElement(By.id("org.traeg.fastip:id/txt_valor_gorjeta")).getText());
		assertEquals("$115,00", driver.findElement(By.id("org.traeg.fastip:id/txt_total_conta")).getText());
	}

}
