import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.logging.Logger

import org.junit.After

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import utileria.accion as accion


TestData loginData = findTestData('LoginClaveIncorrecta')

int rows = loginData.getRowNumbers()

for (int i = 1; i <= rows; i++) {

	if (loginData.getObjectValue('estado', i).toString().equalsIgnoreCase('TRUE')) {

		String usuario = loginData.getObjectValue('usuario', i).toString()

		String clave = loginData.getObjectValue('clave', i).toString()

		//Abre el navegador en Amazon
		WebUI.openBrowser("https://www.amazon.com/")

		//Si se presenta el logo
		if(WebUI.waitForElementPresent(accion.crearTestObjectPorXpath('//*[@id="nav-logo"]'), 60)){
			//Maximiza la ventana
			WebUI.maximizeWindow()

			//Agrega punto de verificacion
			accion.agregarPuntoVerificacion("Se muestra la pantalla de inicio.", true, true)

			//Click en login
			WebUI.click(accion.crearTestObjectPorXpath('//*[@id="nav-link-accountList"]'))

			//Si se presenta el el titulo de login
			if(WebUI.waitForElementPresent(accion.crearTestObjectPorXpath('//*[@id="authportal-main-section"]//h1'), 60)){

				//Agrega punto de verificacion
				accion.agregarPuntoVerificacion("Se muestra el login", true, true)

				//introducir el correo
				WebUI.setText(accion.crearTestObjectPorXpath('//*[@id="ap_email"]'), usuario)
				
				//Agrega punto de verificacion
				accion.agregarPuntoVerificacion("Se introdujo el correo.", true, true)
				
				//click en continuar
				WebUI.click(accion.crearTestObjectPorXpath('//*[@id="continue"]'))

				//Si aparece el mensaje de error
				if(WebUI.waitForElementPresent(accion.crearTestObjectPorXpath('//*[@id="ap_password"]'), 60)){

					//Agrega punto de verificacion
					accion.agregarPuntoVerificacion("Se muestra el campo para introducir la clave.", true, true)
					
					//Introducir clave
					WebUI.setText(accion.crearTestObjectPorXpath('//*[@id="ap_password"]'), clave)
					
					//Agrega punto de verificacion
					accion.agregarPuntoVerificacion("Se introdujo la clave.", true, true)
					
					//Click en iniciar sesion
					WebUI.click(accion.crearTestObjectPorXpath('//*[@id="signInSubmit"]'))
					
					//Si se presenta el mensaje de error
					if(accion.crearTestObjectPorXpath('//*[@id="auth-error-message-box"]')){
						
						//Agrega punto de verificacion
						accion.agregarPuntoVerificacion("Se muestra el mensaje de error.", true, true)	
					}
					
					//Cerrar navegador	
					WebUI.closeBrowser();

				}else{
					//Agrega punto de verificacion
					accion.agregarPuntoVerificacion("No se muestra el campo para introducir la clave.", true, true)
				}
			}else{
				//Agrega punto de verificacion
				accion.agregarPuntoVerificacion("No se muestra el login", true, true)
			}
		}else{
			//Agrega punto de verificacion
			accion.agregarPuntoVerificacion("No se muestra la pantalla de inicio.", false, true)
		}
	}
}

