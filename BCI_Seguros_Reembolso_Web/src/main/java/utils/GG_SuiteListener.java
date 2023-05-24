package main.java.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import test.java.GG_BaseTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GG_SuiteListener implements ITestListener, IAnnotationTransformer {
	public static ExtentTest logger;
	
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	
    	//Obtener Fecha y Hora para la Evidencia del Pasó OK.
	    LocalTime hhora = LocalTime.now();
	    DateTimeFormatter f_t = DateTimeFormatter.ofPattern("HHmmss");
	    
	    LocalDate ffecha = LocalDate.now();
	    DateTimeFormatter f_d = DateTimeFormatter.ofPattern("yyyyMMdd");
		
	    String xHora = hhora.format(f_t).toString();
	    String xFecha = ffecha.format(f_d).toString();       
        
        String xSufijo = xFecha + "_" + xHora;
        //Fin

        String fileName = CC_Parametros.gloDir + File.separator + "screenshots" + File.separator + "passed" + File.separator + result.getMethod().getMethodName() + "_" + xSufijo;
        File f = ((TakesScreenshot) GG_BaseTest.driver).getScreenshotAs(OutputType.FILE);
        
        try {
            FileUtils.copyFile(f, new File(fileName + ".png"));
            
            //Guardar Nombre Archivo en temporal, para usarlo en el Reporte de la Automatización
            String fileName2 = CC_Parametros.gloDir + File.separator + "screenshots" + File.separator + 
            	   "passed" + File.separator + "Archivo_Paso.txt";
            File xArchivo = new File(fileName2);
            
			if (xArchivo.exists()) {
				xArchivo.delete();
			}

			xArchivo.createNewFile();
			BufferedWriter archivoIndice = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(xArchivo, true), "Windows-1252"));
			archivoIndice.write(fileName + ".png");
			archivoIndice.close();            
            //Fin
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
