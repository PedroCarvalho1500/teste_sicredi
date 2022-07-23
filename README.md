# prova_sicredi


```
cd existing_repo
git remote add origin https://gitlab.com/usertrelloapi/prova_sicredi.git
git branch -M main
git push -uf origin main
```


## Getting started

Após clonar o repositório, insira o webdriver no qual os testes serão executados dentro da pasta prova_sicredi\projeto_sicredi\src\test\java\tests\drivers.

## Add your files

O projeto foi criado como tipo MAVEN. Dentro do arquivo pom.xml, insira as dependências a seguir:



<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>br.com.grocerycrud.selenium</groupId>
    <artifactId>projeto_sicredi</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>


    <dependencies>
        <!-- https://mvnrepository.com/artifact/junit/junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>


        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.6.0</version>
        </dependency>


    	<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
    	<dependency>
        	<groupId>commons-io</groupId>
        	<artifactId>commons-io</artifactId>
        	<version>2.6</version>
    	</dependency>

    </dependencies>
</project>






## Executing the Tests and Checking Evidences

Para visualizar os testes, basta executar o arquivo Desafio_Sicredi_Test.java na IDE desejada. Os prints são gerados para os casos de sucesso a fim de provar que o teste foi realizado corretamente, assim como para alguns casos de erros cruciais, a fim de facilitar o mapeamento do problema.

A pasta PRINTS_TESTES contém os prints dos testes realizados. Ela se encontra em prova_sicredi\projeto_sicredi\src\test\java\suport
