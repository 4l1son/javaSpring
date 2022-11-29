# javaSpring


# PARA INICIAR O PROJETO EM SPRING
spring initializr - https://start.spring.io/

DEPENDENCIAS
*************************************************************************************************************
GraalVM Native Support - Suporte para compilar aplicativos Springs em execultaveis nativos usando o compilador
de imagem nativa GraalVM

Lombok - Biblioteca de anotações java qe ajuda reduzir o codigo cliche

Spring web - Criar aplicações web RESTful , aplicações usando Spring MVC. Usa o Apache Tomcat como o conteiner
incorporado padrão

tomcat - para iniciar a porta onde sera rodado

O springboot tem um servidor embarcado nele, isso basta apenas rodar a aplicação que
ja sobe ela 
*****************************************************************************************************************

<h2>Criando o POM</h2>
Precisamos começar criando um Mavenfile. Em um editor de texto  e adicione o seguinte:pom.xmlpom.xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>myproject</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.5</version>
    </parent>

    <!-- Additional lines to be added here... -->



</project>

# Springboot
<p>
O Spring Boot fornece uma série de "Starters" que permitem adicionar jarros ao seu classpath. As nossas aplicações para testes de fumo utilizam-nona secção do POM. Theé um iniciador especial que fornece padrões úteis do Maven. Ele também fornece uma seçãode gerenciamento dedependências para que você possa omitir tags para dependências "abençoadas".spring-boot-starter-parentparentspring-boot-starter-parentversion

Outros "Starters" fornecem dependências que você provavelmente precisará ao desenvolver um tipo específico de aplicativo. Como estamos desenvolvendo um aplicativo Web, adicionamos dependência. Antes disso, podemos ver o que temos atualmente executando o seguinte comando:spring-boot-starter-web
</p>
$ mvn dependency:tree
<p align="left">
[INFO] com.example:myproject:jar:0.0.1-SNAPSHOT

O comando imprime uma representação em árvore das dependências do projeto. Você pode ver que não fornece dependências por si só. Para adicionar as dependências necessárias, edite o seu e adicione a dependência imediatamente abaixo daseção:mvn dependency:treespring-boot-starter-parentpom.xmlspring-boot-starter-webparent

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>

Se você executar novamente, verá que agora há várias dependências adicionais, incluindo o servidor Web Tomcat e o próprio Spring Boot.mvn dependency:tree

<p>



****************************************************************
<h2><b>O plug-in do Spring Boot Maven fornece suporte ao Spring Boot no Apache Maven. Ele permite que voce empacote
arquivos jar ou war executaveis, 

 <p>Execute aplicativos Spring Boot,gere informações de compilação e inicie seu aplicativo Spring Boot antes de executar testes de integração.
</p>
Se voce quiser usar versao de marco ou snapshot , tambem precisara adicionar os elementos
apropiados, conforme mostrado na listagem a segur: pluginRepository
</b>
</h2>
<pluginRepositories>
    <pluginRepository>
        <id>spring-snapshots</id>
        <url>https://repo.spring.io/snapshot</url>
    </pluginRepository>
    <pluginRepository>
        <id>spring-milestones</id>
        <url>https://repo.spring.io/milestone</url>
    </pluginRepository>
</pluginRepositories>

Os usuarios do Maven podem herdar do projeto para obter padroes sensatos. O projeto pai fornece os seguintes recursos: spring-boot-starter-parent. Como os arquivos e a Spring aceitam espaços reservados estilo Spring (), a filtragem do Maven é alterada para espaços reservados de uso. (Você pode substituir isso definindo uma propriedade Maven chamada.)application.propertiesapplication.yml${…​}@..@resource.delimiter

# Para configurar seu projeto para herdar do, defina o seguinte:spring-boot-starter-parentparent

<!-- Inherit defaults from Spring Boot -->
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.7.5</version>
</parent>

<strong>Você deve precisar especificar apenas o número da versão do Spring Boot nessa dependência. Se você importar iniciadores adicionais, poderá omitir com segurança o número da versão.</strong>

O plugin oferece uma série de propriedades do usuário, começando com, para permitir que você personalize a configuração a partir da linha de comando.spring-boot

Por exemplo, você pode ajustar os perfis para habilitar ao executar o aplicativo da seguinte maneira:

$ mvn spring-boot:run -Dspring-boot.run.profiles=dev,local
Se você quiser que ambos tenham um padrão enquanto permitem que ele seja substituído na linha de comando, você deve usar uma combinação de uma propriedade de projeto fornecida pelo usuário e configuração MOJO.

<project>
    <properties>
        <app.profiles>local,dev</app.profiles>
    </properties>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <profiles>${app.profiles}</profiles>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

O acima garante queandestão habilitados por padrão. Agora que uma propriedade dedicada foi exposta, isso também pode ser substituído na linha de comando:localdev




<h2> Empacotamento de arquivos executáveis</h2>
O plugin pode criar arquivos executáveis (arquivos jar e arquivos war) que contêm todas as dependências de um aplicativo e podem ser executados com.java -jar

Dependendo do seu aplicativo, convém ajustar como as camadas são criadas e adicionar novas. Isso pode ser feito usando um arquivo de configuração separado que deve ser registrado conforme mostrado abaixo:

<project>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <layers>
                        <enabled>true</enabled>
                        <configuration>${project.basedir}/src/layers.xml</configuration>
                    </layers>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

O arquivo de configuração descreve como um arquivo pode ser separado em camadas e a ordem dessas camadas. O exemplo a seguir mostra como a ordem padrão descrita acima pode ser definida explicitamente:

<layers xmlns="http://www.springframework.org/schema/boot/layers"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/boot/layers
                          https://www.springframework.org/schema/boot/layers/layers-2.7.xsd">
    <application>
        <into layer="spring-boot-loader">
            <include>org/springframework/boot/loader/**</include>
        </into>
        <into layer="application" />
    </application>
    <dependencies>
        <into layer="application">
            <includeModuleDependencies />
        </into>
        <into layer="snapshot-dependencies">
            <include>*:*:*SNAPSHOT</include>
        </into>
        <into layer="dependencies" />
    </dependencies>
    <layerOrder>
        <layer>dependencies</layer>
        <layer>spring-boot-loader</layer>
        <layer>snapshot-dependencies</layer>
        <layer>application</layer>
    </layerOrder>
</layers>
*****************************************************************************

Fluxo de trabalho de como o Spring Boot lida com solicitações e processos da API REST e retorna uma resposta:
![image](https://user-images.githubusercontent.com/111581261/204166512-458ca53b-fc87-4064-a2ac-d67e043f93af.png)



*****************************************************************************
# Em relação ao código
@RequestController
A primeira anotação em uma classe fornece dicas para quem le o código
mais conhecida como stereotype annotation , ela fornce diacas para quem le o código sobre
sobre o funcionamento de uma classe especifica no spring, no caso abaixo a nossa classe 
eh uma wecb entao a Spring a considera ao lidar con solicitações 

Theannotation fornece informações de "roteamento". 
Ele informa ao Spring que qualquer solicitação HTTP com o caminho deve ser mapeada 
para o método. Theannotation diz ao Spring para renderizar a cadeia de caracteres 
resultante diretamente de volta para o chamador.
@RequestMapping/home@RestController
