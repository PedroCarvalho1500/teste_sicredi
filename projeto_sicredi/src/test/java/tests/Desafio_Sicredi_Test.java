package tests;

import junit.framework.AssertionFailedError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import suport.Generator;
import suport.Screenshot;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Desafio_Sicredi_Test
{
    private WebDriver browser;
    private WebElement mensagemSucessoAdd;
    private String TextMessageCustomerAdd;
    private WebElement mensagemConfirmacaoExclusao;
    private String mensagemPopUpExclusao;
    private WebElement mensagemRegistroExcluido;
    private String textoRegistroExcluido;
    private WebElement checkboxAll;
    private List<WebElement> desafio1executado;
    private String currentPath;
    private WebElement formularioCustomer;

    public void ExcluirItem()
    {
        //CLICK ON DELETE
        checkboxAll = browser.findElement(By.cssSelector("input[class*='select-all']"));
        try {
            while (checkboxAll.isSelected() == false) {
                for (int i = 0; i < 3; i++) {
                    checkboxAll.click();
                }
            }
            browser.findElement(By.xpath("//td[@class='no-border-left ']/div/a[@title='Delete']")).click();
        } catch (Exception d)
        {
            System.out.println("ERRO AO CLICAR EM DELETE! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
            Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_error_on_click_delete.png");
            assertEquals("true","false");
            return;
        }


        //VALIDATE TEXT MESSAGE TO DELETE THE ITEM
        try {
            mensagemConfirmacaoExclusao = browser.findElement(By.cssSelector("p[class='alert-delete-multiple-one']"));
            mensagemPopUpExclusao = "";
//            Thread.sleep(2000);
            mensagemPopUpExclusao = mensagemConfirmacaoExclusao.getText();
        } catch (Exception f)
        {
            System.out.println("ERRO AO OBTER O TEXTO DO POPUP DE CONFIRMAÇÃO DA EXCLUSÃO! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
            Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_error_pop_up_message_delete.png");
            assertEquals("true","false");
            return;
        }


        Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_pop_up_to_delete.png");
        assertEquals("Are you sure that you want to delete this 1 item?", mensagemPopUpExclusao);


        //CONFIRM DELETE ACTION
        try {
            browser.switchTo().activeElement();
            browser.findElement(By.cssSelector("div[class='modal-footer']>button[class*='delete-multiple-confirmation-button']")).click();
        } catch (Exception h) {
            System.out.println("ERRO AO CONFIRMAR A EXCLUSÃO! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
            Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_erro_confirm_delete.png");
            assertEquals("true","false");
            return;
        }


        //ASSERT DELETED ITEM MESSAGE
        try {
            Thread.sleep(2500);
            mensagemRegistroExcluido = browser.findElement(By.cssSelector("span>p"));
        } catch (Exception xw) {
            System.out.println("NÃO FOI POSSÍVEL ENCONTRAR A MENSAGEM DE REGISTRO EXCLUÍDO!");
            assertEquals("true","false");
            return;
        }

//        Thread.sleep(5000);
//            textoRegistroExcluido = mensagemRegistroExcluido.getText();
        try {
            textoRegistroExcluido = mensagemRegistroExcluido.getText();
        } catch (Exception ert)
        {
            assertEquals("true","false");
            return;
        }

        try {
            assertEquals(true, mensagemRegistroExcluido.isDisplayed());
        } catch (AssertionError err2) {
            System.out.println("MENSAGEM DE REGISTRO EXCLUÍDO NÃO EXIBIDA! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
            Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_error_message_delete_confirmation.png");
            assertEquals("true","false");
            return;
        }

        Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_delete_successfully.png");
        assertEquals("Your data has been successfully deleted from the database.", textoRegistroExcluido);

    }

    public void PreencherCamposCadastro()
    {
        formularioCustomer = browser.findElement(By.cssSelector("div[class*='table-container']"));
        try
        {
            Thread.sleep(4000);
            formularioCustomer.findElement(By.id("field-customerName")).sendKeys("Teste Sicredi");
            formularioCustomer.findElement(By.id("field-contactLastName")).sendKeys("Teste");
            formularioCustomer.findElement(By.id("field-contactFirstName")).sendKeys("Pedro");
            formularioCustomer.findElement(By.id("field-phone")).sendKeys("51 9999-9999");
            formularioCustomer.findElement(By.id("field-addressLine1")).sendKeys("Av Assis Brasil, 3970");
            formularioCustomer.findElement(By.id("field-addressLine2")).sendKeys("Torre D");
            formularioCustomer.findElement(By.id("field-city")).sendKeys("Porto Alegre");
            formularioCustomer.findElement(By.id("field-state")).sendKeys("RS");
            formularioCustomer.findElement(By.id("field-postalCode")).sendKeys("91000-000");
            formularioCustomer.findElement(By.id("field-country")).sendKeys("Brasil");
            formularioCustomer.findElement(By.id("field-salesRepEmployeeNumber")).sendKeys("1576578");
            formularioCustomer.findElement(By.id("field-creditLimit")).sendKeys("200");
            browser.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        }catch(Exception x)
        {
            System.out.println("ERRO AO PREENCHER OS VALORES NOS CAMPOS DE CADASTRO!");
            assertEquals("true","false");
            return;
        }

    }



    @Before
    public void setup() throws IOException,AssertionError
    {
        //MAPING THE DIRECTORY
        currentPath = new java.io.File(".").getCanonicalPath();

        //CONFIG THE CHROMEDRIVER
        System.setProperty("webdriver.chrome.driver",currentPath+"\\src\\test\\java\\tests\\drivers\\chromedriver.exe");
        System.out.println("PATH "+currentPath);
        //OPENING THE BROWSER
        browser = new ChromeDriver();

        //ACESSING THE WEB PAGE
        try
        {
            browser.get("https://www.grocerycrud.com/v1.x/demo/my_boss_is_in_a_hurry/bootstrap");
            Thread.sleep(6000);
            browser.manage().window().maximize();
        }catch(Exception y)
        {
            System.out.println("ERRO AO ACESSAR A PÁGINA!");
            assertEquals("true","false");
            return;
        }
    }



    @Test
    public void AdicionarNovoCustomer() throws IOException,AssertionFailedError, AssertionError, Exception
    {

        try
        {
            //SELECT BOOTSTRAP V4 THEME
            try
            {
                browser.findElement(By.cssSelector("#switch-version-select")).click();
                browser.findElement(By.cssSelector("option[value*='v4']")).click();
                Thread.sleep(1000);
            }catch(Exception z)
            {
                System.out.println("ERRO AO CLICAR NO SELECT BOX THEME!");
                assertEquals("true","false");
                return;
            }

            //CLICK ON BUTTON ADD CUSTOMER
            try
            {
                browser.findElement(By.linkText("Add Record")).click();
            }catch(Exception w)
            {
                System.out.println("ERRO AO CLICAR NO BOTÃO DE ADICIONAR CUSTOMER!");
                assertEquals("true","false");
                return;
            }


            //INSERT VALUES ON THE FIELDS
            PreencherCamposCadastro();


            //CLICK ON BUTTON SAVE
            try
            {
                browser.findElement(By.id("form-button-save")).click();
                Thread.sleep(4000);
            }catch(Exception w)
            {
                System.out.println("ERRO AO CLICAR NO BOTÃO DE SALVAR! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
                Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_error_on_saving_customer.png");
                assertEquals("true","false");
                return;
            }

            //CHECK SUCCESS MESSAGE
            try
            {
                mensagemSucessoAdd = formularioCustomer.findElement(By.cssSelector("#report-success>p"));
                TextMessageCustomerAdd = mensagemSucessoAdd.getText();

                try {
                    assertEquals(true, mensagemSucessoAdd.isDisplayed());
                }catch(AssertionError err)
                {
                    System.out.println("MENSAGEM DE SUCESSO NÃO EXIBIDA!");
                    assertEquals("true","false");
                    return;
                }

                Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_add_customer_successfully.png");
                assertEquals("Your data has been successfully stored into the database. Edit Record or Go back to list",TextMessageCustomerAdd);

            }catch(Exception n)
            {
                System.out.println("ERRO AO VALIDAR A MENSAGEM DE SUCESSO!");
                assertEquals("true","false");
                return;
            }

        }
        catch(Exception x)
        {
            System.out.println("ERRO AO INSTANCIAR O BROWSER!");
            assertEquals("true","false");
            return;
        }
    }



    @Test
    public void ExcluirCustomerAdicionado() throws AssertionError, InterruptedException {
        //EXECUTANDO OS PASSOS DO DESAFIO1
        browser.findElement(By.cssSelector("#switch-version-select")).click();
        browser.findElement(By.cssSelector("option[value*='v4']")).click();

        try
        {
            browser.findElement(By.cssSelector("input[placeholder*='CustomerName']")).sendKeys("Teste Sicredi");
            Thread.sleep(5000);
        } catch (Exception b)
        {
            System.out.println("ERRO AO CLICAR NO CAMPO SEARCH FIELD!");
            assertEquals("true","false");
            return;
        }

        desafio1executado = browser.findElements(By.xpath("//*[contains(text(),'Teste Sicredi')]"));
        if (desafio1executado.size() == 0)
        {

            //CLICK ON GO BACK TO LIST
            try {
                browser.findElement(By.cssSelector("button[id*='back-button']")).click();
                browser.findElement(By.cssSelector("span[aria-hidden='true']")).click();
                Thread.sleep(4000);
            } catch (Exception a)
            {
                System.out.println("ERRO AO CLICAR NO BOTÃO DE GO BACK TO LIST! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
                Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_error_on_click_go_back_to_list.png");
                assertEquals("true","false");
                return;
            }


            //CLICK ON THE SEARCH FIELD AND INPUT VALUE "TESTE SICREDI"
            try {
                browser.findElement(By.cssSelector("input[placeholder*='CustomerName']")).clear();
                browser.findElement(By.cssSelector("input[placeholder*='CustomerName']")).sendKeys("Teste Sicredi");
                Thread.sleep(4000);
            } catch (Exception b)
            {
                System.out.println("ERRO AO CLICAR NO CAMPO SEARCH FIELD!");
                assertEquals("true","false");
                return;
            }

            ExcluirItem();

        }

        else
        {
            checkboxAll = browser.findElement(By.cssSelector("input[class*='select-all']"));
            try {
                while (checkboxAll.isSelected() == false) {
                    for (int i = 0; i < 3; i++) {
                        checkboxAll.click();
                    }
                }
                browser.findElement(By.xpath("//td[@class='no-border-left ']/div/a[@title='Delete']")).click();
            } catch (Exception d)
            {
                System.out.println("ERRO AO CLICAR EM DELETE! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
                Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_error_on_click_delete.png");
                assertEquals("true","false");
                return;
            }


            //VALIDATE TEXT MESSAGE TO DELETE THE ITEM
            try {
                mensagemConfirmacaoExclusao = browser.findElement(By.cssSelector("p[class='alert-delete-multiple-one']"));
                mensagemPopUpExclusao = "";
//            Thread.sleep(2000);
                mensagemPopUpExclusao = mensagemConfirmacaoExclusao.getText();
            } catch (Exception f)
            {
                System.out.println("ERRO AO OBTER O TEXTO DO POPUP DE CONFIRMAÇÃO DA EXCLUSÃO! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
                Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_error_pop_up_message_delete.png");
                assertEquals("true","false");
                return;
            }

            //CONFIRM DELETE ACTION
            try {
                browser.switchTo().activeElement();
                browser.findElement(By.cssSelector("div[class='modal-footer']>button[class*='delete-multiple-confirmation-button']")).click();
            } catch (Exception h) {
                System.out.println("ERRO AO CONFIRMAR A EXCLUSÃO! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
                Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_error_confirm_delete.png");
                assertEquals("true","false");
                return;
            }


            //ASSERT DELETED ITEM MESSAGE
            try {
                Thread.sleep(2500);
                mensagemRegistroExcluido = browser.findElement(By.cssSelector("span>p"));
            } catch (Exception xw) {
                System.out.println("NÃO FOI POSSÍVEL ENCONTRAR A MENSAGEM DE REGISTRO EXCLUÍDO! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
                Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_error_confirm_message_delete.png");
                assertEquals("true","false");
                return;
            }


            try {
                textoRegistroExcluido = mensagemRegistroExcluido.getText();
            } catch (Exception ert)
            {
                assertEquals("true","false");
                return;
            }

            try {
                assertEquals(true, mensagemRegistroExcluido.isDisplayed());
            } catch (AssertionError err2) {
                System.out.println("MENSAGEM DE REGISTRO EXCLUÍDO NÃO EXIBIDA");
                assertEquals("true","false");
                return;
            }

            Thread.sleep(4000);
            browser.findElement(By.linkText("Add Record")).click();
            PreencherCamposCadastro();

            try {
                browser.findElement(By.cssSelector("button[id*='back-button']")).click();
                browser.findElement(By.cssSelector("span[aria-hidden='true']")).click();
                Thread.sleep(4000);
            } catch (Exception a)
            {
                System.out.println("ERRO AO CLICAR NO BOTÃO DE GO BACK TO LIST! ACESSE A PASTA DE PRINTS PARA VISUALIZAR O PROBLEMA!");
                Screenshot.takePrint(browser, currentPath+"\\src\\test\\java\\suport\\PRINTS_TESTES\\"+Generator.data_hora_file() + "_error_on_click_go_back_to_list.png");
                assertEquals("true","false");
                return;
            }


            try {
                browser.findElement(By.cssSelector("input[placeholder*='CustomerName']")).clear();
                browser.findElement(By.cssSelector("input[placeholder*='CustomerName']")).sendKeys("Teste Sicredi");
                Thread.sleep(4000);
            } catch (Exception b)
            {
                System.out.println("ERRO AO CLICAR NO CAMPO SEARCH FIELD!");
                assertEquals("true","false");
                return;
            }

            ExcluirItem();
        }

    }


    @After
    public void teardown()
    {
        browser.quit();
    }

}
