package test.java.carritocompras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.pageEvents.CC_PasosFuncionales;
import main.java.utils.GG_OpenCSV;
import main.java.utils.GG_Utils;
import test.java.GG_BaseTest;
import main.java.utils.CC_Parametros;

//Clase que contiene los Pasos Funcionales automatizados
public class CC_Test extends GG_BaseTest {
	
	public static int gloFilas = 0;

	@Test(enabled = true, dataProvider = "BCISeguros")
	public void CC_QA_Automatizacion(String args[]) throws InterruptedException {
		
		//String rutaDocumento = CC_Parametros.gloDir + "Documentos/";
		String rutaDocumento = "C:\\Estructura_Base\\CC_Logs\\data\\Documentos\\";
		GG_Utils.infoTestCase("BCI Seguros",
				"Validar la generación de un flujo de reembolso médico");
		
		CC_PasosFuncionales.iniciarSesion(args[0], args[1], args[2],args[3],"1");
		CC_PasosFuncionales.ingresoReembolso(args[2],args[3], "2");
		CC_PasosFuncionales.ingresoPrestacion(args[4],args[6],args[7],"3");
		
		if (args[7].toUpperCase().equalsIgnoreCase("CM")){
			
			CC_PasosFuncionales.prestacionConsultaMedica(rutaDocumento,args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],
					args[24],args[25],args[26],args[27],"4");
			
		}
		else if (args[7].toUpperCase().equalsIgnoreCase("ME")) {
			
			CC_PasosFuncionales.prestacionMedicamentos(rutaDocumento,args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],
					args[24],args[25],args[26],args[27],"4");
			
		}
		else if (args[7].toUpperCase().equalsIgnoreCase("EP")) {
			
			CC_PasosFuncionales.prestacionExamenesProcedimientos(rutaDocumento,args[8],args[9],args[10],args[11],args[12],args[13],
					args[14],args[15],args[16],args[17],args[24],args[25],args[26],args[27],"4");
			
		}
		else if (args[7].toUpperCase().equalsIgnoreCase("PS")) {
			
			CC_PasosFuncionales.prestacionOtrosProfesionales(rutaDocumento,args[8],args[9],args[10],args[11],args[12],args[13],
					args[14],args[15],args[16],args[17],args[24],args[25],args[26],args[27],"4");
			
		}
		else if (args[7].toUpperCase().equalsIgnoreCase("CD")) {
			
			CC_PasosFuncionales.prestacionConsultaDental(rutaDocumento,args[8],args[9],args[10],args[11],args[12],args[13],
					args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[24],
					args[25],args[26],args[27],"4");
			
		}
		else if (args[7].toUpperCase().equalsIgnoreCase("HO")) {
			
			CC_PasosFuncionales.prestacionHospitalizaciones(rutaDocumento,args[8],args[9],args[10],args[11],args[12],args[13],
					args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],
					args[25],args[26],args[27],"4");
			
		}
		else if (args[7].toUpperCase().equalsIgnoreCase("UM")) {

			CC_PasosFuncionales.prestacionUrgenciaMedica(rutaDocumento,args[8],args[9],args[10],args[11],args[12],args[13],
					args[14],args[15],args[16],args[17],args[18],args[19],args[24],args[25],args[26],args[27],"4");
			
		}
		else if (args[7].toUpperCase().equalsIgnoreCase("OP")) {
			
			CC_PasosFuncionales.prestacionOptica(rutaDocumento,args[8],args[9],args[10],args[11],args[12],args[13],
					args[14],args[15],args[16],args[17],args[24],args[25],args[26],args[27],"4");
			
		}
		else {
			
			System.out.println("Nada");
			
		}
		
		if (args[5].toUpperCase().equalsIgnoreCase("T")) {
			
			CC_PasosFuncionales.datosCuentaTitular(args[27],args[28], args[29], "5");
			
		}
		else if (args[5].toUpperCase().equalsIgnoreCase("A")) {
			
			CC_PasosFuncionales.datosCuentaAdicional(args[27],"5");
			
		}
		else {System.out.println("Nada");}
		
		CC_PasosFuncionales.obtenerNumeroSolicitud(args[0],args[2], "6");
	}
	
	@DataProvider(name = "BCISeguros")
	public Object[][] dataBrokerAPAlternative() throws CsvValidationException, InterruptedException, IOException {

		int xColumnas = CC_Parametros.gloColumnas;
		
		OpenTxt();
		System.out.println("Archivo leido: " + CC_Parametros.gloDir + "data/TotalCasosDePruebas.txt");
		System.out.println("Total Casos de Prueba a ejecutar: " + gloFilas + "."); //gloFilas - Total de Casos de Pruebas - NO incluye la fila 1 de Titulos
				
		Object[][] data = GG_OpenCSV.getCSVParameters(CC_Parametros.gloNombreCSV, gloFilas, xColumnas);
		return data;
	}
	
	public static void OpenTxt() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		gloFilas = 0;
		File archivo2 = new File(CC_Parametros.gloDir + "data/TotalCasosDePruebas.txt");

        if (archivo2.exists()) {

			try {
				// Apertura del fichero y creacion de BufferedReader para poder
				// hacer una lectura comoda (disponer del metodo readLine()).
				archivo = new File(CC_Parametros.gloDir + "data/TotalCasosDePruebas.txt");
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
	
				// Lectura del fichero
				String linea;
				String leido = null;
				while ((linea = br.readLine()) != null)
					leido = linea;
				
				gloFilas = Integer.valueOf(leido);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// En el finally cerramos el fichero, para asegurarnos
				// Que se cierra tanto si todo va bien como si salta una excepcion.
				try {
					if (null != fr) {
						fr.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
        }else {
        	System.out.println("Archivo no existe: " + CC_Parametros.gloDir + "data/TotalCasosDePruebas.txt");
    	}
	}
}
