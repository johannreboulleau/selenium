# POC sur Selenium

## Pré-requis :
 - installation du driver pour Firefox (geckodriver https://github.com/mozilla/geckodriver/releases) : Selenium pour communiquer avec Firefox a besoin de drivers

## Documentation utile :
 - http://atatorus.developpez.com/tutoriels/java/test-application-web-avec-selenium/
 - http://www.seleniumhq.org/docs/03_webdriver.jsp
 - http://www.seleniumhq.org/docs/02_selenium_ide.jsp
 - https://github.com/FluentLenium/FluentLenium
 - http://fluentlenium.org

## Site officiel :
 - https://addons.mozilla.org/en-US/firefox/addon/selenium-ide/

## Selenium IDE :
 - astuces :
    - utiliser la commande "waitForElementPresent" pour attendre que la page se charge complétement
    - click droit sous Firefox => Selenium propose une aide pour les commandes possibles

## Export Selenium IDE -> Selenium Driver (Java / JUnit 4/ WebDriver) :
 - correction suite à l'export
    - de l'URL
    - ajout du driver geckodriver
    - la langue peut bloquer le test
 - conclusion : test non concluant, trop de code à changer => je n'ai pas réussi à faire fonctionner le test. (je pense que les iframes ou autres posent problème)

## FluentLenium
 - https://github.com/FluentLenium/FluentLenium
 - http://fluentlenium.org
 - http://thierry-leriche-dessirier.developpez.com/tutoriels/javaweb/tester-webapp-fluentlenium-5min/

 Penser à ajouter "-Dwebdriver.gecko.driver=C:\geckodriver\geckodriver.exe" dans les options des TU ou Properties System

## Maven
 - lancement sous Windows : "mvn clean package -Dwebdriver.gecko.driver=./geckodriver.exe"
 - lancement sous CentOS : "mvn clean package -Dwebdriver.gecko.driver=./geckodriver"


## Jenkins
 - souci rencontré pour lancer Firefox sur le serveur de Jenkins (), car limité à Firefox 45 à cause de la version CentOS top ancienne

## RemoteDriver
 Docs :
  - http://www.seleniumhq.org/docs/03_webdriver.jsp#running-standalone-selenium-server-for-use-with-remotedrivers
  - http://www.seleniumhq.org/docs/03_webdriver.jsp#webdriver-and-the-selenium-server
  - https://github.com/SeleniumHQ/selenium/wiki/Grid2

Télécharger le jar http://selenium-release.storage.googleapis.com/index.html?path=3.4/selenium-server-standalone-3.4.0.jar

 En local
    Commandes à lancer dans 2 invites de command (dans l'ordre) :
     - java -jar selenium-server-standalone-3.4.0.jar -role hub -port 9090
     - java -Dwebdriver.gecko.driver="C:\geckodriver\geckodriver.exe"  -jar selenium-server-standalone-3.4.0.jar -role webdriver -hub http://localhost:9090/grid/register -port 7777

  Les ports ont été volontairement modifiés, car je rencontrais des erreurs.



