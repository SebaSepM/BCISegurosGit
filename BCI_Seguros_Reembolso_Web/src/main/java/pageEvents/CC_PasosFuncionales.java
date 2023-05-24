package main.java.pageEvents;

import java.io.File;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.pageObjects.CC_Localizadores;
import main.java.utils.GG_ElementFetch;
import main.java.utils.GG_Eventos;
import main.java.utils.GG_Utils;
import main.java.utils.GG_Validations;
import test.java.carritocompras.CC_Test;

import java.lang.Math;

//En esta clase se ejecutan los Pasos de la Página.
public class CC_PasosFuncionales extends CC_Test{
	
	public CC_PasosFuncionales(WebDriver driver) {
		CC_Test.driver = driver;
	}

	public static void iniciarSesion(String rut, String clave, String nombreTitular, String apellidoTitular ,String xNumero) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();

		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			Thread.sleep(1000);

			//Se ingresa el rut del usuario
			WebElement inputNombreUsuarioElement = elementFetch.getWebElement("XPATH", CC_Localizadores.inputLoginRut);
			wait.until(ExpectedConditions.visibilityOf(inputNombreUsuarioElement));
			GG_Eventos.writeOnInput(inputNombreUsuarioElement, rut);
			
			Thread.sleep(3000);

			//Se escribe la Clave
			WebElement inputContrasenaElement = elementFetch.getWebElement("XPATH", CC_Localizadores.inputLoginClave);
			wait.until(ExpectedConditions.visibilityOf(inputContrasenaElement));
			GG_Eventos.writeOnInput(inputContrasenaElement, clave);
			
			Thread.sleep(5000);

			//Click en Botón de ingreso
			WebElement buttonIniciarSesionElement = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonLoginIngresa);
			wait.until(ExpectedConditions.elementToBeClickable(buttonIniciarSesionElement));
			GG_Eventos.clickButton(buttonIniciarSesionElement);
			
			Thread.sleep(2000);
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void ingresoReembolso (String nombreTitular, String apellidoTitular, String xNumero) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String solicitud = "Seguro Colectivo Temporal De Vida";
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
		
			//Se ingresa a página de reembolso (click en pestaña)
			WebElement buttonReembolso = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonReembolso);
			wait.until(ExpectedConditions.elementToBeClickable(buttonReembolso));
			GG_Eventos.clickButton(buttonReembolso);
		
			//Se ingresa a página de reembolso (click en la imagen)
			WebElement buttonImagenReembolso = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonReembolsoImagen);
			wait.until(ExpectedConditions.elementToBeClickable(buttonImagenReembolso));
			GG_Eventos.clickButton(buttonImagenReembolso);
			
			//Validar nombre titular
			/*WebElement usuario = elementFetch.getWebElement("XPATH", CC_Localizadores.labelUsuarioReembolso);
			wait.until(ExpectedConditions.visibilityOf(usuario));
			String textoPagina = usuario.getText();
			
			GG_Validations.trueBooleanCondition(textoPagina.equalsIgnoreCase(titular),
				"Se ha ingresado correctamente a la página con el usuario: " + textoPagina,
				"No se ha ingresado correctamente a la página: ", currentEvent);*/
			
			//Validar resumen solicitud
			WebElement resumenSolicitud = elementFetch.getWebElement("XPATH", CC_Localizadores.labelResumenSolicitud);
			wait.until(ExpectedConditions.visibilityOf(resumenSolicitud));
			String pasoActual = resumenSolicitud.getText();
			
			GG_Validations.trueBooleanCondition(pasoActual.equalsIgnoreCase(solicitud),
				"Resumen de solicitud en el paso correcto " + pasoActual,
				"Mensaje de solicitud no corresponde ", currentEvent);
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}

	}
	
	public static void ingresoPrestacion (String poseeBeneficiario, String rut, String prestacion, String xNumero) {
		
	String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
	String tituloPrestacion = "";
	
	// Se genera una variable del titulo de la prestación segun la abreviación puesta en el archivo CSV
	if (prestacion.toUpperCase().equalsIgnoreCase("CM")){
		tituloPrestacion = "Consulta Médica";
	}
	else if (prestacion.toUpperCase().equalsIgnoreCase("ME")){
		tituloPrestacion = "Medicamentos";
	}
	else if (prestacion.toUpperCase().equalsIgnoreCase("EP")){
		tituloPrestacion = "Exámenes O Procedimientos";
	}
	else if (prestacion.toUpperCase().equalsIgnoreCase("PS")){
		tituloPrestacion = "Otros Profesionales De La Salud";
	}
	else if (prestacion.toUpperCase().equalsIgnoreCase("CD")){
		tituloPrestacion = "Consulta Dental";
	}
	else if (prestacion.toUpperCase().equalsIgnoreCase("HO")){
		tituloPrestacion = "Hospitalizaciones";
	}
	else if (prestacion.toUpperCase().equalsIgnoreCase("UM")){
		tituloPrestacion = "Urgencias Médicas";
	}
	else if (prestacion.toUpperCase().equalsIgnoreCase("OP")){
		tituloPrestacion = "Óptica";
	}
	else {GG_Utils.outputInfo("Abreviación no existe");}
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);
			

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			// Validar si la persona posee beneficiarios 
			
			if (poseeBeneficiario.toUpperCase().equalsIgnoreCase("SI")) {
				validarResumenTitulo("A");
				
				Thread.sleep(2000);
				
				List<WebElement> listaBeneficiarios = elementFetch.getListWebElements("XPATH", CC_Localizadores.listBeneficiariosRut);
				wait.until(ExpectedConditions.visibilityOfAllElements(listaBeneficiarios));
				
				Integer cantidad = listaBeneficiarios.size();
				System.out.println("Cantidad de beneficiarios " + cantidad);
				Integer local = 2;
				
				//Se recorre la Lista para buscar al beneficiario
				for (int i = 0; i < listaBeneficiarios.size(); i++) {
					local += 1;
					String rutObtenido = listaBeneficiarios.get(i).getText();
					if ((rutObtenido.equalsIgnoreCase(rut))) {
						
						//Obtener nombre del beneficiario seleccionado
						WebElement nombreBeneficiario = elementFetch.getWebElement("XPATH", CC_Localizadores.listBeneficiariosNombre.replace("BN", String.valueOf(local)));
						wait.until(ExpectedConditions.visibilityOf(nombreBeneficiario));
						String nombre = nombreBeneficiario.getText();
						
						//Hacer Click en el Beneficiario seleccionado
						WebElement buttonAgregarElement = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonIngresoBeneficiario.replace("BN", String.valueOf(local)));
						GG_Eventos.clickButton(buttonAgregarElement);
						
						GG_Utils.outputInfo("Nombre beneficiario seleccionado: " + nombre);
						
						break;	
					}	
				}
			}
			else {GG_Utils.outputInfo("Titular no tiene beneficiarios");}
			
			Thread.sleep(2000);
			
			validarContenidoResumen(rut,"A");
			
			validarResumenTitulo("p");
			
			//Selección de prestación
			
			List<WebElement> listaPrestaciones = elementFetch.getListWebElements("XPATH", CC_Localizadores.listPrestacion);
			wait.until(ExpectedConditions.visibilityOfAllElements(listaPrestaciones));
			
			Integer cantidad = listaPrestaciones.size();
			System.out.println("Cantidad de prestaciones " + cantidad);
			Integer local = 0;
			
			//Se recorre la Lista para buscar la prestación
			for (int i = 0; i < listaPrestaciones.size(); i++) {
				local += 1;
				String prestacionObtenida = listaPrestaciones.get(i).getText();
				if ((prestacionObtenida.equalsIgnoreCase(tituloPrestacion))) {
					
					//Hacer Click en el Beneficiario seleccionado
					WebElement buttonAgregarElement = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonPrestacion.replace("BN", String.valueOf(local)));
					GG_Eventos.clickButton(buttonAgregarElement);
					
					GG_Utils.outputInfo("Prestación seleccionada: " + tituloPrestacion);
					
					break;	
				}	
			}
			
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
		
	}
	
	public static void prestacionConsultaMedica (String rutaDocumento, String prestador, String fecha, String monto, String numeroBoleta, String documento1,
			String documento2, String documento3, String documento4, String documentoDuplicado, String documentoExtra, String documentoLimitePeso,
			String cantidad, String xNumero){
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String prestacion = "Consulta Médica";
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			validarContenidoResumen(prestacion,"p");
			
			Thread.sleep(2000);
			
			validarResumenTitulo("d");
			
			Thread.sleep(2000);
			
			ingresarDatosReembolso(prestador,fecha,monto,numeroBoleta);
			
			Thread.sleep(2000);
			
			cargar2BoxDocumentos(rutaDocumento,documento1,documento2,documento3,documento4);
			
			Thread.sleep(2000);
			
			cargarDocumentoDuplicado(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			cargarDocumentoAdicional(rutaDocumento,documentoExtra,CC_Localizadores.uploadDoc1);
			
			Thread.sleep(2000);
			
			cargarDocumentoPeso(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			WebElement buttonContinuar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonContinuarSolicitud);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuar));
			GG_Eventos.clickButton(buttonContinuar);
			
			Thread.sleep(4000);
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void prestacionMedicamentos (String rutaDocumento, String prestador, String fecha, String monto, String numeroBoleta, String documento1, 
			String documento2, String documento3, String documento4, String cantidad, String documentoDuplicado, 
			String documentoExtra, String documentoLimitePeso, String xNumero){
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String prestacion = "Medicamentos";
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			validarContenidoResumen(prestacion,"p");
			
			validarResumenTitulo("d");
			
			ingresarDatosReembolso(prestador,fecha,monto,numeroBoleta);
			
			cargar2BoxDocumentos(rutaDocumento,documento1,documento2,documento3,documento4);
			
			Thread.sleep(2000);
			
			cargarDocumentoDuplicado(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			cargarDocumentoAdicional(rutaDocumento,documentoExtra,CC_Localizadores.uploadDoc1);
			
			Thread.sleep(2000);
			
			cargarDocumentoPeso(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			WebElement buttonContinuar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonContinuarSolicitud);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuar));
			GG_Eventos.clickButton(buttonContinuar);
			
			Thread.sleep(4000);
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void prestacionExamenesProcedimientos (String rutaDocumento, String prestador, String fecha, String monto, String numeroBoleta, String documento1, 
			String documento2, String documento3, String documento4, String documento5, String documento6, String documentoDuplicado,
			String documentoExtra, String documentoLimitePeso, String cantidad, String xNumero){
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String prestacion = "Exámenes O Procedimientos";
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			validarContenidoResumen(prestacion,"p");
			
			validarResumenTitulo("d");
			
			ingresarDatosReembolso(prestador,fecha,monto,numeroBoleta);
			
			cargar3BoxDocumentos(rutaDocumento,documento1,documento2,documento3,documento4,documento5,documento6);
			
			Thread.sleep(2000);
			
			cargarDocumentoDuplicado(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			cargarDocumentoAdicional(rutaDocumento,documentoExtra,CC_Localizadores.uploadDoc1);
			
			Thread.sleep(2000);
			
			cargarDocumentoPeso(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			WebElement buttonContinuar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonContinuarSolicitud);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuar));
			GG_Eventos.clickButton(buttonContinuar);
			
			Thread.sleep(4000);
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void prestacionOtrosProfesionales (String rutaDocumento, String prestador, String fecha, String monto, String numeroBoleta, String documento1, 
			String documento2, String documento3, String documento4, String documento5, String documento6, String documentoDuplicado, 
			String documentoExtra, String documentoLimitePeso, String cantidad, String xNumero) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String prestacion = "Otros Profesionales De La Salud";
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			validarContenidoResumen(prestacion,"p");
			
			validarResumenTitulo("d");
			
			ingresarDatosReembolso(prestador,fecha,monto,numeroBoleta);
			
			cargar3BoxDocumentos(rutaDocumento,documento1,documento2,documento3,documento4,documento5,documento6);
			
			Thread.sleep(2000);
			
			cargarDocumentoDuplicado(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			cargarDocumentoAdicional(rutaDocumento,documentoExtra,CC_Localizadores.uploadDoc1);
			
			Thread.sleep(2000);
			
			cargarDocumentoPeso(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			WebElement buttonContinuar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonContinuarSolicitud);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuar));
			GG_Eventos.clickButton(buttonContinuar);
			
			Thread.sleep(4000);
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}

	public static void prestacionConsultaDental (String rutaDocumento, String prestador, String fecha, String monto, String numeroBoleta, String documento1, 
			String documento2, String documento3, String documento4, String documento5, String documento6, String documento7, 
			String documento8, String documento9, String documento10, String documento11, String documentoDuplicado, 
			String documentoExtra, String documentoLimitePeso, String cantidad, String xNumero){
	
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String prestacion = "Consulta Dental";
	
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
		
			validarContenidoResumen(prestacion,"p");
		
			validarResumenTitulo("d");
		
			ingresarDatosReembolso(prestador,fecha,monto,numeroBoleta);
		
			cargar4BoxDocumentosDental(rutaDocumento,documento1,documento2,documento3,documento4,documento5,documento6,documento7,documento8,documento9,documento10,documento11);
		
			Thread.sleep(2000);
			
			cargarDocumentoDuplicado(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			cargarDocumentoAdicional(rutaDocumento,documentoExtra,CC_Localizadores.uploadDoc1);
			
			Thread.sleep(2000);
			
			cargarDocumentoPeso(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			WebElement buttonContinuar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonContinuarSolicitud);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuar));
			GG_Eventos.clickButton(buttonContinuar);
		
			Thread.sleep(4000);
		
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void prestacionHospitalizaciones (String rutaDocumento, String prestador, String fecha, String monto, String numeroBoleta, String documento1, 
			String documento2, String documento3, String documento4, String documento5, String documento6, String documento7,
			String documento8, String documento9, String documento10, String documento11, String documento12, 
			String documentoDuplicado, String documentoExtra, String documentoLimitePeso, String cantidad, String xNumero){
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String prestacion = "Hospitalizaciones";
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
		
			validarContenidoResumen(prestacion,"p");
		
			validarResumenTitulo("d");
		
			ingresarDatosReembolso(prestador,fecha,monto,numeroBoleta);
			
			cargar6BoxDocumentos(rutaDocumento,documento1,documento2,documento3,documento4,documento5,documento6
					,documento7,documento8,documento9,documento10,documento11,documento12);
		
			Thread.sleep(2000);
			
			cargarDocumentoDuplicado(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			cargarDocumentoAdicional(rutaDocumento,documentoExtra,CC_Localizadores.uploadDoc1);
			
			Thread.sleep(2000);
			
			//cargarDocumentoPeso(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			WebElement buttonContinuar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonContinuarSolicitud);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuar));
			GG_Eventos.clickButton(buttonContinuar);
		
			Thread.sleep(4000);
		
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void prestacionUrgenciaMedica (String rutaDocumento, String prestador, String fecha, String monto, String numeroBoleta, String documento1, 
			String documento2, String documento3, String documento4, String documento5, String documento6, String documento7,
			String documento8, String documentoDuplicado, String documentoExtra, String documentoLimitePeso, String cantidad,
			String xNumero) {
		
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String prestacion = "Urgencias Médicas";
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
		
			validarContenidoResumen(prestacion,"p");
		
			validarResumenTitulo("d");
		
			ingresarDatosReembolso(prestador,fecha,monto,numeroBoleta);
		
			cargar4BoxDocumentos(rutaDocumento,documento1,documento2,documento3,documento4,documento5,documento6,documento7,documento8);
		
			Thread.sleep(2000);
			
			cargarDocumentoDuplicado(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			cargarDocumentoAdicional(rutaDocumento,documentoExtra,CC_Localizadores.uploadDoc1);
			
			Thread.sleep(2000);
			
			cargarDocumentoPeso(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			WebElement buttonContinuar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonContinuarSolicitud);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuar));
			GG_Eventos.clickButton(buttonContinuar);
		
			Thread.sleep(4000);
		
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void prestacionOptica (String rutaDocumento, String prestador, String fecha, String monto, String numeroBoleta, String documento1, 
			String documento2, String documento3, String documento4, String documento5, String documento6, String documentoDuplicado, 
			String documentoExtra, String documentoLimitePeso, String cantidad, String xNumero){
		
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String prestacion = "Óptica";
	
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
		
			validarContenidoResumen(prestacion,"p");
		
			validarResumenTitulo("d");
		
			ingresarDatosReembolso(prestador,fecha,monto,numeroBoleta);
		
			cargar3BoxDocumentos(rutaDocumento,documento1,documento2,documento3,documento4,documento5,documento6);
		
			Thread.sleep(2000);
			
			cargarDocumentoDuplicado(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			cargarDocumentoAdicional(rutaDocumento,documentoExtra,CC_Localizadores.uploadDoc1);
			
			Thread.sleep(2000);
			
			cargarDocumentoPeso(rutaDocumento,documentoDuplicado,CC_Localizadores.uploadDoc2);
			
			Thread.sleep(2000);
			
			WebElement buttonContinuar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonContinuarSolicitud);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuar));
			GG_Eventos.clickButton(buttonContinuar);
		
			Thread.sleep(4000);
		
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	
	public static void datosCuentaTitular (String contenido, String correo, String telefono, String xNumero) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			validarContenidoResumen(contenido,"d");
			
			Thread.sleep(2000);
			
			//Ingresar para editar datos
			WebElement buttonEditarDatos = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonEditarCorreoTelefono);
			buttonEditarDatos.click();
			
			Thread.sleep(2000);
			
			//Se ingresa correo nuevo
			WebElement inputIngresarCorreo = elementFetch.getWebElement("XPATH", CC_Localizadores.inputEditarCorreo);
			wait.until(ExpectedConditions.visibilityOf(inputIngresarCorreo));
			GG_Eventos.writeOnInput(inputIngresarCorreo, correo);
			
			Thread.sleep(2000);
			
			//Se confirma correo nuevo
			WebElement inputConfirmarCorreo = elementFetch.getWebElement("XPATH", CC_Localizadores.inputConfirmarCorreo);
			wait.until(ExpectedConditions.visibilityOf(inputConfirmarCorreo));
			GG_Eventos.writeOnInput(inputConfirmarCorreo, correo);
			
			Thread.sleep(2000);
			
			//Se ingresa telefono nuevo
			WebElement inputIngresarTelefono = elementFetch.getWebElement("XPATH", CC_Localizadores.inputEditarTelefono);
			wait.until(ExpectedConditions.visibilityOf(inputIngresarTelefono));
			GG_Eventos.writeOnInput(inputIngresarTelefono, telefono);
			
			validarMinimoCaracteres(telefono);
			
			Thread.sleep(2000);
			
			//Click en Botón Guardar
			WebElement buttonGuardar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonEditarGuardar);
			wait.until(ExpectedConditions.elementToBeClickable(buttonGuardar));
			GG_Eventos.clickButton(buttonGuardar);
			
			Thread.sleep(2000);
			
			//Click en Botón Continuar
			WebElement buttonContinuar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonEditarContinuar);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuar));
			GG_Eventos.clickButton(buttonContinuar);
			
			Thread.sleep(2000);
			
			//Click en Botón Entendido
			WebElement buttonEntendido = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonEditarEntendido);
			wait.until(ExpectedConditions.elementToBeClickable(buttonEntendido));
			GG_Eventos.clickButton(buttonEntendido);
			
			Thread.sleep(2000);
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
		
	}
	
	public static void datosCuentaAdicional (String contenido, String xNumero) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			validarContenidoResumen(contenido,"d");
			
			Thread.sleep(2000);
			
			//Click en Botón Continuar
			WebElement buttonContinuar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonEditarContinuar);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuar));
			GG_Eventos.clickButton(buttonContinuar);
			
			Thread.sleep(2000);
			
			//Click en Botón Entendido
			WebElement buttonEntendido = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonEditarEntendido);
			wait.until(ExpectedConditions.elementToBeClickable(buttonEntendido));
			GG_Eventos.clickButton(buttonEntendido);
			
			Thread.sleep(2000);
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void obtenerNumeroSolicitud (String rut, String nombre, String xNumero) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			GG_Utils.outputInfo(xNumero + ") PASO FUNCIONAL iniciado: " + currentEvent);

			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
		
			//Click en Botón Continuar
			WebElement buttonContinuarDatos = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonDatosBancariosContinuar);
			wait.until(ExpectedConditions.elementToBeClickable(buttonContinuarDatos));
			GG_Eventos.clickButton(buttonContinuarDatos);
		
			Thread.sleep(2000);
		
			//Click en Botón Aceptar Términos
			WebElement buttonAceptarTerminos = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonAceptarTerminos);
			wait.until(ExpectedConditions.elementToBeClickable(buttonAceptarTerminos));
			GG_Eventos.clickButton(buttonAceptarTerminos);
			
			Thread.sleep(2000);
			
			//Validar Nombre de persona solicitante
			WebElement mensaje = elementFetch.getWebElement("XPATH", CC_Localizadores.labelMensajeFinal);
			wait.until(ExpectedConditions.visibilityOf(mensaje));
			
			//Obtener Numero de solicitud
			WebElement numeroSolicitud = elementFetch.getWebElement("XPATH", CC_Localizadores.labelNumeroSolicitud);
			wait.until(ExpectedConditions.visibilityOf(numeroSolicitud));
			String solicitud = numeroSolicitud.getText();
			
			GG_Utils.outputInfo("Número de solicitud " + solicitud + " asociado a rut: " + rut);
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	//Metodos de Apoyo
	
	public static void cargar2BoxDocumentos (String rutaDocumento,String doc1, String doc2, String doc3, String doc4) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			//Cargar Documentos
			//Imagen o Documento Primer Recuadro
			String documento1 = rutaDocumento + doc1 ;
			File file1 = new File(documento1);
			String cargaDocumento1 = file1.getAbsolutePath();
		
			WebElement cargarImagenDocumento1 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc1);
			cargarImagenDocumento1.sendKeys(cargaDocumento1);
			
			Thread.sleep(2000);
			
			if (doc2 != "") {
				
				String documento2 = rutaDocumento + doc2 ;
				File file2 = new File(documento2);
				String cargaDocumento2 = file2.getAbsolutePath();
			
				WebElement cargarImagenDocumento2 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc1);
				cargarImagenDocumento2.sendKeys(cargaDocumento2);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el primer recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el primer recuadro");
				
			}
			
			//Imagen o Documento Segundo Recuadro
			String documento3 = rutaDocumento + doc3 ;
			File file3 = new File(documento3);
			String cargaDocumento3 = file3.getAbsolutePath();
		
			WebElement cargarImagenDocumento3 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc2);
			cargarImagenDocumento3.sendKeys(cargaDocumento3);
			
			Thread.sleep(2000);
			
			if (doc4 != "") {
				
				String documento4 = rutaDocumento + doc4 ;
				File file4 = new File(documento4);
				String cargaDocumento4 = file4.getAbsolutePath();
			
				WebElement cargarImagenDocumento4 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc2);
				cargarImagenDocumento4.sendKeys(cargaDocumento4);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el segundo recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el segundo recuadro");
				
			}
			
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
		
	}
	
	public static void cargar3BoxDocumentos (String rutaDocumento,String doc1, String doc2, String doc3, String doc4, String doc5, String doc6) {
	
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			//Cargar Documentos
			//Imagen o Documento Primer Recuadro
			String documento1 = rutaDocumento + doc1 ;
			File file1 = new File(documento1);
			String cargaDocumento1 = file1.getAbsolutePath();
		
			WebElement cargarImagenDocumento1 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc1);
			cargarImagenDocumento1.sendKeys(cargaDocumento1);
			
			Thread.sleep(2000);
			
			if (doc2 != "") {
				
				String documento2 = rutaDocumento + doc2 ;
				File file2 = new File(documento2);
				String cargaDocumento2 = file2.getAbsolutePath();
			
				WebElement cargarImagenDocumento2 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc1);
				cargarImagenDocumento2.sendKeys(cargaDocumento2);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el primer recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el primer recuadro");
				
			}
			//Imagen o Documento Segundo Recuadro
			String documento3 = rutaDocumento + doc3 ;
			File file3 = new File(documento3);
			String cargaDocumento3 = file3.getAbsolutePath();
		
			WebElement cargarImagenDocumento3 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc2);
			cargarImagenDocumento3.sendKeys(cargaDocumento3);
			
			Thread.sleep(2000);
			
			if (doc4 != "") {
				
				String documento4 = rutaDocumento + doc4 ;
				File file4 = new File(documento4);
				String cargaDocumento4 = file4.getAbsolutePath();
			
				WebElement cargarImagenDocumento4 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc2);
				cargarImagenDocumento4.sendKeys(cargaDocumento4);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el segundo recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el segundo recuadro");
				
			}
			
			//Imagen o Documento Tercer Recuadro
			String documento5 = rutaDocumento + doc5 ;
			File file5 = new File(documento5);
			String cargaDocumento5 = file5.getAbsolutePath();
		
			WebElement cargarImagenDocumento5 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc3);
			cargarImagenDocumento5.sendKeys(cargaDocumento5);
			
			Thread.sleep(2000);
			
			if (doc6 != "") {
				
				String documento6 = rutaDocumento + doc6 ;
				File file6 = new File(documento6);
				String cargaDocumento6 = file6.getAbsolutePath();
			
				WebElement cargarImagenDocumento6 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc3);
				cargarImagenDocumento6.sendKeys(cargaDocumento6);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el tercer recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el tercer recuadro");
				
			}
		
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
		
	}

	public static void cargar4BoxDocumentos (String rutaDocumento,String doc1, String doc2, String doc3, String doc4, String doc5, String doc6, String doc7, String doc8) {
	
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			//Cargar Documentos
			//Imagen o Documento Primer Recuadro
			String documento1 = rutaDocumento + doc1 ;
			File file1 = new File(documento1);
			String cargaDocumento1 = file1.getAbsolutePath();
		
			WebElement cargarImagenDocumento1 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc1);
			cargarImagenDocumento1.sendKeys(cargaDocumento1);
			
			Thread.sleep(2000);
			
			if (doc2 != "") {
				
				String documento2 = rutaDocumento + doc2 ;
				File file2 = new File(documento2);
				String cargaDocumento2 = file2.getAbsolutePath();
			
				WebElement cargarImagenDocumento2 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc1);
				cargarImagenDocumento2.sendKeys(cargaDocumento2);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el primer recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el primer recuadro");
				
			}
			
			//Imagen o Documento Segundo Recuadro
			String documento3 = rutaDocumento + doc3 ;
			File file3 = new File(documento3);
			String cargaDocumento3 = file3.getAbsolutePath();
		
			WebElement cargarImagenDocumento3 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc2);
			cargarImagenDocumento3.sendKeys(cargaDocumento3);
			
			Thread.sleep(2000);
			
			if (doc4 != "") {
				
				String documento4 = rutaDocumento + doc4 ;
				File file4 = new File(documento4);
				String cargaDocumento4 = file4.getAbsolutePath();
			
				WebElement cargarImagenDocumento4 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc2);
				cargarImagenDocumento4.sendKeys(cargaDocumento4);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el segundo recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el segundo recuadro");
				
			}
			
			//Imagen o Documento Tercer Recuadro
			String documento5 = rutaDocumento + doc5 ;
			File file5 = new File(documento5);
			String cargaDocumento5 = file5.getAbsolutePath();
		
			WebElement cargarImagenDocumento5 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc3);
			cargarImagenDocumento5.sendKeys(cargaDocumento5);
			
			Thread.sleep(2000);
			
			if (doc6 != "") {
				
				String documento6 = rutaDocumento + doc6 ;
				File file6 = new File(documento6);
				String cargaDocumento6 = file6.getAbsolutePath();
			
				WebElement cargarImagenDocumento6 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc3);
				cargarImagenDocumento6.sendKeys(cargaDocumento6);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el tercer recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el tercer recuadro");
				
			}
		
			//Imagen o Documento Cuarto Recuadro
			String documento7 = rutaDocumento + doc7 ;
			File file7 = new File(documento7);
			String cargaDocumento7 = file7.getAbsolutePath();
	
			WebElement cargarImagenDocumento7 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc4);
			cargarImagenDocumento7.sendKeys(cargaDocumento7);
		
			Thread.sleep(2000);
			
			if (doc8 != "") {
				
				String documento8 = rutaDocumento + doc8 ;
				File file8 = new File(documento8);
				String cargaDocumento8 = file8.getAbsolutePath();
		
				WebElement cargarImagenDocumento8 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc4);
				cargarImagenDocumento8.sendKeys(cargaDocumento8);
			
				GG_Utils.outputInfo("Se cargan 2 documentos en el cuarto recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el cuarto recuadro");
				
			}
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	
	}
	
	public static void cargar4BoxDocumentosDental (String rutaDocumento,String doc1, String doc2, String doc3, String doc4, String doc5, String doc6, String doc7, String doc8, String doc9, String doc10, String doc11) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			//Cargar Documentos
			//Imagen o Documento Primer Recuadro
			String documento1 = rutaDocumento + doc1 ;
			File file1 = new File(documento1);
			String cargaDocumento1 = file1.getAbsolutePath();
		
			WebElement cargarImagenDocumento1 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc1);
			cargarImagenDocumento1.sendKeys(cargaDocumento1);
			
			Thread.sleep(2000);
			
			if (doc2 != "") {
				
				String documento2 = rutaDocumento + doc2 ;
				File file2 = new File(documento2);
				String cargaDocumento2 = file2.getAbsolutePath();
			
				WebElement cargarImagenDocumento2 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc1);
				cargarImagenDocumento2.sendKeys(cargaDocumento2);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el primer recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el primer recuadro");
				
			}
			
			//Imagen o Documento Segundo Recuadro
			String documento3 = rutaDocumento + doc3 ;
			File file3 = new File(documento3);
			String cargaDocumento3 = file3.getAbsolutePath();
		
			WebElement cargarImagenDocumento3 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc2);
			cargarImagenDocumento3.sendKeys(cargaDocumento3);
			
			Thread.sleep(2000);
			
			if (doc4 != "") {
				
				String documento4 = rutaDocumento + doc4 ;
				File file4 = new File(documento4);
				String cargaDocumento4 = file4.getAbsolutePath();
			
				WebElement cargarImagenDocumento4 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc2);
				cargarImagenDocumento4.sendKeys(cargaDocumento4);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el segundo recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el segundo recuadro");
				
			}
			
			//Imagen o Documento Tercer Recuadro
			String documento5 = rutaDocumento + doc5 ;
			File file5 = new File(documento5);
			String cargaDocumento5 = file5.getAbsolutePath();
		
			WebElement cargarImagenDocumento5 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc3);
			cargarImagenDocumento5.sendKeys(cargaDocumento5);
			
			Thread.sleep(2000);
			
			if (doc6 != "") {
				
				String documento6 = rutaDocumento + doc6 ;
				File file6 = new File(documento6);
				String cargaDocumento6 = file6.getAbsolutePath();
			
				WebElement cargarImagenDocumento6 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc3);
				cargarImagenDocumento6.sendKeys(cargaDocumento6);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el tercer recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el tercer recuadro");
				
			}
		
			//Imagen o Documento Cuarto Recuadro
			String documento7 = rutaDocumento + doc7 ;
			File file7 = new File(documento7);
			String cargaDocumento7 = file7.getAbsolutePath();
	
			WebElement cargarImagenDocumento7 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc4);
			cargarImagenDocumento7.sendKeys(cargaDocumento7);
		
			Thread.sleep(2000);
			
			if (doc8 != "") {
				
				String documento8 = rutaDocumento + doc8 ;
				File file8 = new File(documento8);
				String cargaDocumento8 = file8.getAbsolutePath();
		
				WebElement cargarImagenDocumento8 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc4);
				cargarImagenDocumento8.sendKeys(cargaDocumento8);
			
				GG_Utils.outputInfo("Se cargan 2 documentos en el cuarto recuadro");
				
				Thread.sleep(2000);
			
			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el tercer recuadro");
				
			}
				
				
			if (doc9 != "") {
					
				String documento9 = rutaDocumento + doc9 ;
				File file9 = new File(documento9);
				String cargaDocumento9 = file9.getAbsolutePath();
			
				WebElement cargarImagenDocumento9 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc4);
				cargarImagenDocumento9.sendKeys(cargaDocumento9);
				
				GG_Utils.outputInfo("Se cargan 3 documentos en el cuarto recuadro");
					
				Thread.sleep(2000);	
			} else {
				
				GG_Utils.outputInfo("Se carga 2 documento en el tercer recuadro");
				
			}
				
			if (doc10 != "") {
					
				String documento10 = rutaDocumento + doc10 ;
				File file10 = new File(documento10);
				String cargaDocumento10 = file10.getAbsolutePath();
			
				WebElement cargarImagenDocumento10 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc4);
				cargarImagenDocumento10.sendKeys(cargaDocumento10);
				
				GG_Utils.outputInfo("Se cargan 4 documentos en el cuarto recuadro");
					
				Thread.sleep(2000);
			} else {
				
				GG_Utils.outputInfo("Se carga 3 documento en el tercer recuadro");
				
			}
					
			if (doc11 != "") {
						
				String documento11 = rutaDocumento + doc11 ;
				File file11 = new File(documento11);
				String cargaDocumento11 = file11.getAbsolutePath();
				
				WebElement cargarImagenDocumento11 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc4);
				cargarImagenDocumento11.sendKeys(cargaDocumento11);
					
				GG_Utils.outputInfo("Se cargan 5 documentos en el cuarto recuadro");
						
				Thread.sleep(2000);	

			} else {
				
				GG_Utils.outputInfo("Se carga 4 documento en el cuarto recuadro");
				
			}
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	
	}

	public static void cargar6BoxDocumentos (String rutaDocumento,String doc1, String doc2, String doc3, String doc4, String doc5, String doc6, String doc7, String doc8, String doc9, String doc10, String doc11, String doc12) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			//Cargar Documentos
			//Imagen o Documento Primer Recuadro
			String documento1 = rutaDocumento + doc1 ;
			File file1 = new File(documento1);
			String cargaDocumento1 = file1.getAbsolutePath();
		
			WebElement cargarImagenDocumento1 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc1);
			cargarImagenDocumento1.sendKeys(cargaDocumento1);
			
			Thread.sleep(2000);
			
			if (doc2 != "") {
				
				String documento2 = rutaDocumento + doc2 ;
				File file2 = new File(documento2);
				String cargaDocumento2 = file2.getAbsolutePath();
			
				WebElement cargarImagenDocumento2 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc1);
				cargarImagenDocumento2.sendKeys(cargaDocumento2);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el primer recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el primer recuadro");
				
			}
			
			//Imagen o Documento Segundo Recuadro
			String documento3 = rutaDocumento + doc3 ;
			File file3 = new File(documento3);
			String cargaDocumento3 = file3.getAbsolutePath();
		
			WebElement cargarImagenDocumento3 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc2);
			cargarImagenDocumento3.sendKeys(cargaDocumento3);
			
			Thread.sleep(2000);
			
			if (doc4 != "") {
				
				String documento4 = rutaDocumento + doc4 ;
				File file4 = new File(documento4);
				String cargaDocumento4 = file4.getAbsolutePath();
			
				WebElement cargarImagenDocumento4 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc2);
				cargarImagenDocumento4.sendKeys(cargaDocumento4);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el segundo recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el segundo recuadro");
				
			}
			
			//Imagen o Documento Tercer Recuadro
			String documento5 = rutaDocumento + doc5 ;
			File file5 = new File(documento5);
			String cargaDocumento5 = file5.getAbsolutePath();
		
			WebElement cargarImagenDocumento5 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc3);
			cargarImagenDocumento5.sendKeys(cargaDocumento5);
			
			Thread.sleep(2000);
			
			if (doc6 != "") {
				
				String documento6 = rutaDocumento + doc6 ;
				File file6 = new File(documento6);
				String cargaDocumento6 = file6.getAbsolutePath();
			
				WebElement cargarImagenDocumento6 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc3);
				cargarImagenDocumento6.sendKeys(cargaDocumento6);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el tercer recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el tercer recuadro");
				
			}
		
			//Imagen o Documento Cuarto Recuadro
			String documento7 = rutaDocumento + doc7 ;
			File file7 = new File(documento7);
			String cargaDocumento7 = file7.getAbsolutePath();
	
			WebElement cargarImagenDocumento7 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc4);
			cargarImagenDocumento7.sendKeys(cargaDocumento7);
		
			Thread.sleep(2000);
			
			if (doc6 != "") {
				
				String documento8 = rutaDocumento + doc8 ;
				File file8 = new File(documento8);
				String cargaDocumento8 = file8.getAbsolutePath();
		
				WebElement cargarImagenDocumento8 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc4);
				cargarImagenDocumento8.sendKeys(cargaDocumento8);
			
				GG_Utils.outputInfo("Se cargan 2 documentos en el cuarto recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el cuarto recuadro");
				
			}
			
			//Imagen o Documento Quinto Recuadro
			String documento9 = rutaDocumento + doc9 ;
			File file9 = new File(documento9);
			String cargaDocumento9 = file9.getAbsolutePath();
		
			WebElement cargarImagenDocumento9 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc5);
			cargarImagenDocumento9.sendKeys(cargaDocumento9);
			
			Thread.sleep(2000);
			
			if (doc10 != "") {
				
				String documento10 = rutaDocumento + doc10 ;
				File file10 = new File(documento10);
				String cargaDocumento10 = file10.getAbsolutePath();
			
				WebElement cargarImagenDocumento10 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc5);
				cargarImagenDocumento10.sendKeys(cargaDocumento10);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el quinto recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el quinto recuadro");
				
			}
			
			//Imagen o Documento Sexto Recuadro
			String documento11 = rutaDocumento + doc11 ;
			File file11 = new File(documento11);
			String cargaDocumento11 = file11.getAbsolutePath();
		
			WebElement cargarImagenDocumento11 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc6);
			cargarImagenDocumento11.sendKeys(cargaDocumento11);
			
			Thread.sleep(2000);
			
			if (doc12 != "") {
				
				String documento12 = rutaDocumento + doc12 ;
				File file12 = new File(documento12);
				String cargaDocumento12 = file12.getAbsolutePath();
			
				WebElement cargarImagenDocumento12 = elementFetch.getWebElement("XPATH", CC_Localizadores.uploadDoc6);
				cargarImagenDocumento12.sendKeys(cargaDocumento12);
				
				GG_Utils.outputInfo("Se cargan 2 documentos en el sexto recuadro");
				
				Thread.sleep(2000);

			} else {
				
				GG_Utils.outputInfo("Se carga 1 documento en el sexto recuadro");
				
			}
		
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
		
	}
	
	public static void cargarDocumentoDuplicado (String rutaDocumento, String documento, String localizador) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
		
			if (documento != "") {
				String doc = rutaDocumento + documento ;
				File file = new File(doc);
				String cargaDocumento = file.getAbsolutePath();
	
				WebElement cargarImagenDocumento = elementFetch.getWebElement("XPATH", localizador);
				cargarImagenDocumento.sendKeys(cargaDocumento);
			
				GG_Utils.outputInfo("Se intento cargar un documento ya utilizado");
			
				Thread.sleep(2000);
				
				WebElement buttonTotalDoc = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonDocumentoDoble);
				wait.until(ExpectedConditions.elementToBeClickable(buttonTotalDoc));
				GG_Eventos.clickButton(buttonTotalDoc);}
			
			else {GG_Utils.outputInfo("No se intento cargar un documento ya utilizado");}
			}catch (Exception e) {
				GG_Utils.eventFailed(currentEvent, e.getMessage());
			}
		}
	
	public static void cargarDocumentoAdicional (String rutaDocumento, String documento, String localizador) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
		
			if (documento != "") {
				String doc = rutaDocumento + documento ;
				File file = new File(doc);
				String cargaDocumento = file.getAbsolutePath();
	
				WebElement cargarImagenDocumento = elementFetch.getWebElement("XPATH", localizador);
				cargarImagenDocumento.sendKeys(cargaDocumento);
			
				GG_Utils.outputInfo("Se intento cargar un documento adicional");
				
				Thread.sleep(2000);
			
				WebElement buttonTotalDoc = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonDocumentoDoble);
				wait.until(ExpectedConditions.elementToBeClickable(buttonTotalDoc));
				GG_Eventos.clickButton(buttonTotalDoc);}
		
			else {GG_Utils.outputInfo("No se intento cargar un documento adicional");}	
		}catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void cargarDocumentoPeso (String rutaDocumento, String documento, String localizador) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			GG_ElementFetch elementFetch = new GG_ElementFetch();
		
			if (documento != "") {
				String doc = rutaDocumento + documento ;
				File file = new File(doc);
				String cargaDocumento = file.getAbsolutePath();
	
				WebElement cargarImagenDocumento = elementFetch.getWebElement("XPATH", localizador);
				cargarImagenDocumento.sendKeys(cargaDocumento);
			
				GG_Utils.outputInfo("Se excede el peso limite aceptado por el total de los archivos: 30MB");
				
				Thread.sleep(2000);
			
				WebElement buttonTotalDoc = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonDocumentoPeso);
				wait.until(ExpectedConditions.elementToBeClickable(buttonTotalDoc));
				GG_Eventos.clickButton(buttonTotalDoc);}
		
			else {GG_Utils.outputInfo("El peso total de los archivos es menor a 30MB");}
		}catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	

	public static void ingresarDatosReembolso (String xPrestador, String xFecha, String xMonto, String xBoleta) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		try {
			GG_ElementFetch elementFetch = new GG_ElementFetch();
			
			//Rut Prestador
			WebElement inputRutPrestador = elementFetch.getWebElement("XPATH", CC_Localizadores.inputRutPrestador);
			GG_Eventos.writeOnInput(inputRutPrestador, xPrestador);
			
			validarRut(xPrestador);
			
			Thread.sleep(3000);
			
			//Fecha
			WebElement inputFecha = elementFetch.getWebElement("XPATH", CC_Localizadores.inputFechaDocumento);
			GG_Eventos.writeOnInput(inputFecha, xFecha);
			
			Thread.sleep(3000);
			
			//Monto Boleta
			WebElement inputMonto = elementFetch.getWebElement("XPATH", CC_Localizadores.inputMontoBoleta);
			GG_Eventos.writeOnInput(inputMonto, xMonto);
			esNumerico(xMonto);
			
			Thread.sleep(3000);
			
			//Número Boleta
			WebElement inputBoleta = elementFetch.getWebElement("XPATH", CC_Localizadores.inputNumeroBoleta);
			GG_Eventos.writeOnInput(inputBoleta, xBoleta);
			esNumerico(xBoleta);
			
			Thread.sleep(1000);
			
			//DownScroll
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(0,500)");
			Thread.sleep(2000);
		
		
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void validarResumenTitulo (String paso) {
		
		String resumen = "";
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		if (paso == "A" || paso == "a") {
			resumen = "ASEGURADO";
			try {
				
				WebDriverWait wait = new WebDriverWait(driver, 50);
				GG_ElementFetch elementFetch = new GG_ElementFetch();
			
				//Validar resumen
				WebElement pasoPrestacion = elementFetch.getWebElement("XPATH", CC_Localizadores.labelResumenAsegurado);
				wait.until(ExpectedConditions.visibilityOf(pasoPrestacion));
				String pasoActual = pasoPrestacion.getText();
				
				GG_Validations.trueBooleanCondition(pasoActual.equalsIgnoreCase(resumen),
						"Resumen de reembolso en paso: " + pasoActual,
						"No se ha ingresado correctamente a la página: ", currentEvent);
			
			} catch (Exception e) {
				GG_Utils.eventFailed(currentEvent, e.getMessage());
			}}
		else if (paso == "P" || paso == "p") {
			try {
				resumen = "PRESTACIÓN";
				WebDriverWait wait = new WebDriverWait(driver, 50);
				GG_ElementFetch elementFetch = new GG_ElementFetch();
			
				//Validar resumen
				WebElement pasoPrestacion = elementFetch.getWebElement("XPATH", CC_Localizadores.labelResumenPrestacion);
				wait.until(ExpectedConditions.visibilityOf(pasoPrestacion));
				String pasoActual = pasoPrestacion.getText();
				
				GG_Validations.trueBooleanCondition(pasoActual.equalsIgnoreCase(resumen),
						"Resumen de reembolso en paso: " + pasoActual,
						"No se ha ingresado correctamente a la página: ", currentEvent);
			
			} catch (Exception e) {
				GG_Utils.eventFailed(currentEvent, e.getMessage());
			}}
		else if (paso == "D" || paso == "d") {
			resumen = "DOCUMENTACIÓN";
			try {
				
				WebDriverWait wait = new WebDriverWait(driver, 50);
				GG_ElementFetch elementFetch = new GG_ElementFetch();
			
				//Validar resumen
				WebElement pasoPrestacion = elementFetch.getWebElement("XPATH", CC_Localizadores.labelResumenDocumentacion);
				wait.until(ExpectedConditions.visibilityOf(pasoPrestacion));
				String pasoActual = pasoPrestacion.getText();
				
				GG_Validations.trueBooleanCondition(pasoActual.equalsIgnoreCase(resumen),
						"Resumen de reembolso en paso: " + pasoActual,
						"No se ha ingresado correctamente a la página: ", currentEvent);
			
			} catch (Exception e) {
				GG_Utils.eventFailed(currentEvent, e.getMessage());
			}}
		else {GG_Utils.outputInfo("Condición no existe programada");}
	}
	
	public static void validarContenidoResumen (String contenido, String paso) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		
		if (paso == "A" || paso == "a") {
			
			try {
				
				WebDriverWait wait = new WebDriverWait(driver, 50);
				GG_ElementFetch elementFetch = new GG_ElementFetch();
				
				//Click para ver mensaje resumen
				WebElement buttonDesplegar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonResumenAsegurado);
				GG_Eventos.clickButton(buttonDesplegar);
				
				//Validar resumen
				WebElement pasoPrestacion = elementFetch.getWebElement("XPATH", CC_Localizadores.labelResumenAseguradoTexto);
				wait.until(ExpectedConditions.visibilityOf(pasoPrestacion));
				String pasoActual = pasoPrestacion.getText();
				
				GG_Validations.trueBooleanCondition(pasoActual.equalsIgnoreCase(contenido),
						"Asegurado : " + contenido,
						"Asegurado no validado: ", currentEvent);
	
			} catch (Exception e) {
				GG_Utils.eventFailed(currentEvent, e.getMessage());
			}}
		else if (paso == "P" || paso == "p") {
			try {
				
				WebDriverWait wait = new WebDriverWait(driver, 50);
				GG_ElementFetch elementFetch = new GG_ElementFetch();
				
				//Click para ver mensaje resumen
				WebElement buttonDesplegar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonResumenPrestacion);
				GG_Eventos.clickButton(buttonDesplegar);
				
				//Validar resumen
				WebElement pasoPrestacion = elementFetch.getWebElement("XPATH", CC_Localizadores.labelResumenPrestacionTexto);
				wait.until(ExpectedConditions.visibilityOf(pasoPrestacion));
				String pasoActual = pasoPrestacion.getText();
				
				GG_Validations.trueBooleanCondition(pasoActual.equalsIgnoreCase(contenido),
						"Prestación actual : " + pasoActual,
						"No se ha ingresado correctamente a la página: ", currentEvent);
			
			} catch (Exception e) {
				GG_Utils.eventFailed(currentEvent, e.getMessage());
			}}
		else if (paso == "D" || paso == "d") {
			
			try {
				
				WebDriverWait wait = new WebDriverWait(driver, 50);
				GG_ElementFetch elementFetch = new GG_ElementFetch();
				
				//Click para ver mensaje resumen
				WebElement buttonDesplegar = elementFetch.getWebElement("XPATH", CC_Localizadores.buttonResumenDocumentacion);
				GG_Eventos.clickButton(buttonDesplegar);
				
				//Validar resumen
				WebElement pasoPrestacion = elementFetch.getWebElement("XPATH", CC_Localizadores.labelResumenDocumentacionTexto);
				wait.until(ExpectedConditions.visibilityOf(pasoPrestacion));
				String pasoActual = pasoPrestacion.getText();
				String contenidoActual = "Total de documentos: " + contenido;
				
				GG_Validations.trueBooleanCondition(pasoActual.equalsIgnoreCase(contenidoActual),
						"Cantidad de documentos : " + contenido,
						"No se ha ingresado correctamente a la página: ", currentEvent);
			
			} catch (Exception e) {
				GG_Utils.eventFailed(currentEvent, e.getMessage());
			}}
		else {GG_Utils.outputInfo("Condición no existe programada");}
	}
	
	public static void validarRut (String contenido) {
		
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String verificar = "";
		
			try {
				//Formato que valida: 12345678-k
				Integer largoContenido = contenido.length();
		
				if (largoContenido<10) {
					contenido = "0"+contenido;
				}
		
				Integer c1 = Integer.parseInt(contenido.substring(7,8))*2;
				Integer c2 = Integer.parseInt(contenido.substring(6,7))*3;
				Integer c3 = Integer.parseInt(contenido.substring(5,6))*4;
				Integer c4 = Integer.parseInt(contenido.substring(4,5))*5;
				Integer c5 = Integer.parseInt(contenido.substring(3,4))*6;
				Integer c6 = Integer.parseInt(contenido.substring(2,3))*7;
				Integer c7 = Integer.parseInt(contenido.substring(1,2))*2;
				Integer c8 = Integer.parseInt(contenido.substring(0,1))*3;
		
				String digito = contenido.substring(9,10);
		
				Integer suma = c1+c2+c3+c4+c5+c6+c7+c8;
				Integer division = Math.floorDiv(suma, 11);
				Integer multiplicacion = division*11;
				Integer resta = suma-multiplicacion;
				Integer dig = 11-resta;
				
				if (dig == 10) { 
					verificar = "K";  
				}	
				else if (dig == 11) {
					verificar = "0";
				}
				else {
					verificar = Integer.toString(dig);
				}
				
				GG_Validations.trueBooleanCondition(verificar.equalsIgnoreCase(digito),
						"Rut cumple con el formato : " + contenido,
						"Error, rut no cumple con el formato: ", currentEvent);
			} catch (Exception e) {
				GG_Utils.eventFailed(currentEvent, e.getMessage());
			}
	}
	
	public static void esNumerico(String valor){     
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		Integer valorizar = Integer.parseInt(valor);
		
		try{
	        if(valorizar!= null){
	            GG_Utils.outputInfo("Campo solo números validado");
	        }
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
	public static void validarMinimoCaracteres(String contenido){     
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		Integer largoContenido = contenido.length();
		
		try{
	        if(largoContenido < 8){
	            GG_Utils.outputInfo("Largo minimo del télefono debe ser 8 caracteres");
	        }
	        else {
	        	GG_Utils.outputInfo("Télefono cumple con el minimo de caracteres: 8");
	        }
		} catch (Exception e) {
			GG_Utils.eventFailed(currentEvent, e.getMessage());
		}
	}
	
}
