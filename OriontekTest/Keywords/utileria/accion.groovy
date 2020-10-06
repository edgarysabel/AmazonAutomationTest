package utileria

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public abstract class accion {

	public static TestObject crearTestObjectPorXpath(String xpath){

		TestObject result = null

		if(!xpath.equals("") || xpath !=null){

			TestObject tempObject = new TestObject(xpath)

			tempObject.addProperty("xpath", ConditionType.EQUALS, xpath)

			if (tempObject != null) {

				result = tempObject
			}

			return result
		} else {

			return null
		}
	}

	public static void agregarPuntoVerificacion(String mensaje, boolean desicion, boolean postearPrintScreen = false){

		if(mensaje){
			if (desicion == true) {
				KeywordUtil.markPassed(mensaje)
			}
			else{
				KeywordUtil.markFailed(mensaje)
			}

			if(postearPrintScreen){
				WebUI.takeScreenshot();
			}
		}
	}
}
