package main.java.pageObjects;
//Dentro de esta clase se almacenaran todos los localizadores que se ocuparan.
public interface CC_Localizadores {

	// LOGIN
	// input de ingreso
	String inputLoginRut = "//*[@id=\"rutlogin\"]"; //XPATH
	String inputLoginClave = "//*[@id=\"clavelogin\"]"; //XPATH
	
	// INGRESO A REEMBOLSO
	// button de ingreso al sistema
	String buttonLoginIngresa = "//*[@id=\"btn-ingreso\"]"; //XPATH
	String buttonReembolso = "//*[@id=\"funcionalidad5\"]"; //XPATH
	String buttonReembolsoImagen = "//*[@id=\"ReemSol\"]/a"; //XPATH
	String buttonIngresoBeneficiario = "/html/body/app-root/app-componentes/app-beneficiario/div/div[1]/div/div[1]/div/div[2]/div/div[BN]/a"; //XPATH
									   
	String listBeneficiariosNombre = "/html/body/app-root/app-componentes/app-beneficiario/div/div[1]/div/div[1]/div/div[2]/div/div[BN]/a/span[1]/span[2]"; //XPATH
	String listBeneficiariosRut =   "/html/body/app-root/app-componentes/app-beneficiario/div/div[1]/div/div[1]/div/div[2]/div/div/a/span[1]/span[3]"; //XPATH
	
	// SELECCIÓN DE PRESTACIÓN
	// button selección de prestación
	String buttonPrestacionConsultaMedica = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/div[1]/a"; //XPATH
	String buttonPrestacionMedicamentos = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/div[2]/a"; //XPATH
	String buttonPrestacionExamenes = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/div[3]/a"; //XPATH
	String buttonPrestacionProfesionales = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/div[4]/a"; //XPATH
	String buttonPrestacionConsultaDental = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/div[5]/a"; //XPATH
	String buttonPrestacionHospitalizaciones = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/div[6]/a"; //XPATH
	String buttonPrestacionUrgencias = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/div[7]/a"; //XPATH
	String buttonPrestacionOptica = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/div[8]/a"; //XPATH
	
	String listPrestacion = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/div/a/span[1]/span[1]"; //XPATH
	String buttonPrestacion = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/div[BN]/a"; //XPATH
	
	// input ingreso datos solicitud reembolso
	String inputRutPrestador = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[1]/div/div[2]/form/div[2]/div/label/input"; //XPATH
	String inputFechaDocumento = "//*[@id=\"fecha-prtesentacion\"]"; //XPATH
	String inputMontoBoleta = "//*[@id=\"montosolicitud\"]"; //XPATH
	String inputNumeroBoleta = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[1]/div/div[2]/form/div[6]/div[3]/label/input"; //XPATH
		
	// button datos de solicitud reembolso
	String buttonContinuarSolicitud = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[1]/div/div[5]/div/div[2]/button"; //XPATH
	
	// label
	String labelUsuario = "//*[@id=\"content\"]/div[1]/div[2]/aside/p[1]/strong"; //XPATH
	//String labelUsuarioReembolso = "/html/body/app-root/app-componentes/app-beneficiario/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/article[1]/figcaption/h5"; //XPATH
	
	String labelPrestacion = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[1]/div/div[2]/div/h2"; //XPATH
	
	// VALIDACIÓN RESUMEN
	// label
	String labelResumenSolicitud = "/html/body/app-root/app-componentes/app-beneficiario/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/section[1]/figcaption/h3"; //XPATH
	String labelResumenAsegurado = "/html/body/app-root/app-componentes/app-beneficiario/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/section[2]/article/div/h2"; //XPATH
	String labelResumenPrestacion = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/section[2]/article[2]/div/h2"; //XPATH
	String labelResumenDocumentacion = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/section[2]/article[3]/div/h2"; //XPATH
		
	// button
	String buttonResumenAsegurado = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/section[2]/article[1]/div/button"; //XPATH
	String buttonResumenPrestacion = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/section[2]/article[2]/div/button"; //XPATH
	String buttonResumenDocumentacion = "/html/body/app-root/app-componentes/app-datos-reembolsos-container/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/section[2]/article[3]/div/button"; //XPATH
	
	// label 
	String labelResumenAseguradoTexto = "/html/body/app-root/app-componentes/app-prestacion/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/section[2]/article[1]/div/div/div/h4"; //XPATH
	String labelResumenPrestacionTexto = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/section[2]/article[2]/div/div/div/h3"; //XPATH
	String labelResumenDocumentacionTexto = "/html/body/app-root/app-componentes/app-datos-reembolsos-container/div/div[1]/div/div[2]/app-desplegables-resumen/div/div/section[2]/article[3]/div/div/div/h3"; //XPATH
	
	// PRESTACIÓN
	// label
	String labelTituloConsultaMedica = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[1]/div/div[2]/form/div[1]/h2"; //XPATH
	
	// CARGAR IMAGENES Y DOCUMENTOS
	// input upload
	String uploadDoc1 = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[1]/div/div[2]/form/div[12]/div[1]/div/div[2]/a/input"; //XPATH
	String uploadDoc2 = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[1]/div/div[2]/form/div[12]/div[2]/div/div[2]/a/input"; //XPATH
	String uploadDoc3 = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[1]/div/div[2]/form/div[12]/div[3]/div/div[2]/a/input"; //XPATH
	String uploadDoc4 = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[1]/div/div[2]/form/div[12]/div[4]/div/div[2]/a/input"; //XPATH
	String uploadDoc5 = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[1]/div/div[2]/form/div[12]/div[5]/div/div[2]/a/input"; //XPATH
	String uploadDoc6 = "/html/body/app-root/app-componentes/app-form-prestacion/div/div[1]/div/div[1]/div/div[2]/form/div[12]/div[6]/div/div[2]/a/input"; //XPATH
	
	String buttonDocumentoDoble 	= "/html/body/app-root/app-componentes/app-form-prestacion/app-validacion-error/p-dialog/div/div/div[3]/div[1]/a"; //XPATH
	String buttonDocumentoAdicional = "/html/body/app-root/app-componentes/app-form-prestacion/app-validacion-error/p-dialog/div/div/div[3]/div[1]/a"; //XPATH
	String buttonDocumentoPeso 		= "/html/body/app-root/app-componentes/app-form-prestacion/app-validacion-error/p-dialog/div/div/div[3]/div[1]/a"; //XPATH
	
	// DATOS BANCARIOS
	// input
	String inputEditarCorreo = "//*[@id=\"correo\"]"; //XPATH
	String inputConfirmarCorreo = "//*[@id=\"confirmCorreo\"]"; //XPATH
	String inputEditarTelefono = "//*[@id=\"telefono\"]"; //XPATH
	
	// button
	String buttonEditarCorreoTelefono = "/html/body/app-root/app-componentes/app-datos-reembolsos-container/div/div[1]/div/div[1]/app-form-datos/div/div[2]/div/div[2]/a"; //XPATH
	String buttonEditarGuardar = "/html/body/app-root/app-componentes/app-datos-reembolsos-container/div/div[1]/div/div[1]/app-form-personal-data/div/div[4]/div/div[2]/button"; //XPATH
	String buttonEditarContinuar = "/html/body/app-root/app-componentes/app-datos-reembolsos-container/div/div[1]/div/div[1]/app-form-personal-data/app-validacion-confirmacion/p-dialog/div/div/div[3]/div[1]/a"; //XPATH
	String buttonEditarEntendido = "/html/body/app-root/app-componentes/app-datos-reembolsos-container/div/div[1]/div/div[1]/app-form-personal-data/app-validacion-error/p-dialog/div/div/div[3]/div[1]/a"; //XPATH
	String buttonDatosBancariosContinuar = "/html/body/app-root/app-componentes/app-datos-reembolsos-container/div/div[1]/div/div[1]/app-form-datos/div/div[6]/div/div[2]/button"; //XPATH
	String buttonAceptarTerminos = "/html/body/app-root/app-componentes/app-datos-reembolsos-container/div/div[1]/div/div[1]/app-form-datos/p-dialog/div/div/div[3]/p[1]/button"; //XPATH
	String buttonFinalizar = "/html/body/app-root/app-componentes/app-confirmacion/div/div/div/div/div/div/div[4]/div/div/button"; //XPATH
	
	// label
	String labelMensajeFinal = "/html/body/app-root/app-componentes/app-confirmacion/div/div/div/div/div/div/div[2]/div/h2"; //XPATH
	String labelNumeroSolicitud = "/html/body/app-root/app-componentes/app-confirmacion/div/div/div/div/div/div/div[2]/div/h2/strong"; //XPATH
	
}