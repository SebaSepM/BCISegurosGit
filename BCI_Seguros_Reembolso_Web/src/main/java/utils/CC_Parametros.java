package main.java.utils;

public interface CC_Parametros {
    String url = "http://clientes.qa.bciseguros.cl/HomePrivado?";
    
    String nombreAutomatizador = "[Flujo-Automatizado]";
    String nombreProyecto = "[*Proyecto*]";
    String gloDir = "./Archivos/";
    
    //String gloDir = "https://bciseguros.sharepoint.com/:f:/r/sites/CalidadTI/Documentos%20compartidos/General/Automatizaci%C3%B3n/BCI_Seguros_Reembolso_Web?csf=1&web=1&e=Wwx2fH/";
    
    //Archivo CSV con los datos de entrada
    //int gloFilas = 1; //Total de Casos de Pruebas - NO incluye la fila 1 de Titulos
    int gloColumnas = 30;
    String gloNombreCSV = "CSVBCISeguros.csv";
    //Fin
}
 