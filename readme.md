## Beschreibung

Dieses Projekt ist ein Beispiel Projekt für die Verwendung von Selenium Webdriver, welches im Rahmen 
von "EMQ und QUS - Entwicklungsmethoden und Qualitätssicherung" im Themenbereich "Web-Test" verwendet werden kann.
Dazu wird im Folgenden die Installation sovie Verwendung beschrieben.

## Benötigte Software
Um das Projekt installieren und starten zu können wird folgendes benötigt.
1. [Apache Maven](https://maven.apache.org/) 
2. Java JDK 1.8

## Installation

Das Beispielprojekt kann entweder über CMD oder in einer Entwicklungsumgebung installiert werden.
Über CMD muss zum Projektordner navigiert werden, wo auch die Pom.xml liegt und folgendes Kommando ausgeführt werden.
`mvn install`
In einer Entwicklungsumgebung kann das Projekt als ein Mavenprojekt eingebunden werden und dort als `mvn install` ausgeführt werden. Dabei werden die benötigten Dependencies heruntergeladen, wie die verschiedenen Webdriver und andere Bibliotheken.

## Benutzung
Da bei der Installation die ausführbaren Dateien der einzelnen Webdriver für das jeweilige System heruntergeladen werden, müssen diese, um diese Verwenden zu können in den Ordner \target\resources kopiert werden. Die einzelnen ausführbaren Dateien der Webdriver sind auch in Unterordnern in \target\resources zu finden.


In der Klasse src\test\java\emq\webdriver\example\AbstractEMQ kann der zu benutzende Webdriver, also Chrome, Firefox und weitere ausgewählt werden.

```
/**
* Definiert den zu verwenden Webdriver
*/
@BeforeClass
public static void setDriver() {
	ConfigDrivers conf = new ConfigDrivers();
	driver = conf.getChrome();
	driver.manage().window().maximize();
}
```
In diesem Codebeispiel wird der Webdriver für Google Chrome verwendet, was durch `driver = conf.getChrome();`
definiert wird.
Die folgenden Webdriver können momentan durch die ConfigDrivers Klasse verwendet werden:
* getFirefox();  --> Für Firefox
* getChrome();   --> Für Google Chrome

Every Testcase needs to extends the AbstractEMQ class in order to use the webdriver




