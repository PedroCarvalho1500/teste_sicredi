package suport;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import sun.security.jgss.wrapper.GSSCredElement;

import java.io.File;
import java.sql.SQLOutput;

public class Screenshot
{
    public static void takePrint(WebDriver browser, String file)
    {
        File screenshot = ((TakesScreenshot) (browser)).getScreenshotAs(OutputType.FILE);


        try {
            FileUtils.copyFile(screenshot, new File(file));
        } catch (Exception e) {
            System.out.println("Ocorreu um Erro ao Salvar o Arquivo " + e.getMessage());
        }
    }
}
