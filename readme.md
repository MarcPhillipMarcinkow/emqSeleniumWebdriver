## Beschreibung

Dieses Projekt ist ein Beispiel Projekt für die Verwendung von Selenium Webdriver, welches im Rahmen 
von "EMQ und QUS - Entwicklungsmethoden und Qualitätssicherung" im Themenbereich "Web-Test" verwendet werden kann.
Das Projekt ist in Java implementiert und im Folgenden wird die Installation sowie Verwendung beschrieben.

## Benötigte Software
Um das Projekt installieren und starten zu können wird folgendes benötigt.
1. [Apache Maven](https://maven.apache.org/) 
2. Java JDK 1.8 

## Installation

Das Beispielprojekt kann entweder über CMD oder in einer Entwicklungsumgebung installiert werden.
Über CMD muss zum Projektordner navigiert werden, wo auch die Pom.xml liegt und folgendes Kommando ausgeführt werden.
`mvn install`
In einer Entwicklungsumgebung kann das Projekt als ein Mavenprojekt eingebunden werden und dort als `mvn install` installiert werden. Dabei werden die benötigten Dependencies heruntergeladen, wie die verschiedenen Webdriver und andere Bibliotheken.
Die Beispiel Test-Cases werden dabei auch ausgeführt, was bei nicht vorhanden sein von Google-Chrome zu Fehlern führen kann. Dies beeinträchtigt jedoch nicht die Installation und kann im Fehlerfall ignoriert werden.

Falls es Probleme mit Java gibt wie zum Beispiel: 
No compiler is provided in this environment. Perhaps you are running on a JRE rather than a JDK?

Sollte überprüft werden, ob die Umgebungsvariable richtig gesetzt ist, sodass die JDK verwendet wird. 
* [Umgebungsvariablen Windows 10](http://www.programmierenlernenhq.de/java-8-installieren-auf-windows-10-pc/)
* [Umgebungsvariable Windows 7](http://www.java-forum.org/thema/java-umgebungsvariable-einstellen-unter-windows-7.94072/)
* [Umgebungsvariable MAC](https://www.mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/)

## Verwendung

Das Grundgerüst kann verwendet werden, um neue Testcases für das eigene Projekt zu erstellen. Die Beispiele die schon implementiert sind können dabei als Anhaltspunkt dienen, wie diese in diesem Grundgerüst aufgebaut sein sollten.
Auch werden einige Vorzüge von Webdriver in diesen schon dargestellt, wie Parametrisierung, Pageobject-Design-Pattern und das Warten auf bestimmte Ereignisse.

### Starten von Testcases
Die Testcases können in einer IDE einzelnd als JUnit Testcase gestartet werden oder via Maven mit `mvn test` für alle oder `mvn -Dtest="TestKlassenname" test` für spezifische Testcases. Um den Beispiel Testcase searchTest zu starten wäre es 
`mvn -Dtest=searchTest test`


### Konfigurierung

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
definiert wird. Dieser ist auch im Projekt momentan definiert.

Die folgenden Webdriver können momentan durch die ConfigDrivers Klasse verwendet werden:
* getFirefox();  --> Für Firefox
* getChrome();   --> Für Google Chrome
* getIE(); --> Für InternetExplorer
* getOpera(); --> Für Opera
* getEdge(); --> Für Edge
* getPhantomJS(); --> Für den Headless Browser Phantomjs

Der Browser muss dazu auf dem System installiert sein, also wenn man z.B. mit Firefox testen will, dann benötigt man einen installierten Firefox.



## Erstellung eines Testcases

Jeder Testcase, der unter src/test/java erstellt wird, muss die abstrakte Klasse AbstractEMQ extenden, da diese den zuverwendenen Webdriver instanziert und dieser für diese Klasse verwendet wird.
Testcases verwenden Pageobjects mit denen die Funktionalität der Seite geprüft wird. Deshalb sollte die Erstellung eines Testcases und Pageobjects parallel erfolgen. Möchte ich bei der Authentifierung den Login testen,
dann muss ich die Felder ausfüllen. Im Pageobject werden dann die Felder vordefiniert und Methoden geschrieben. Diese werden dann im Testcase verwendet.

### Erstellung eines Pageobjects

Für ein Pageobjekt wird eine neue Klasse erzeugt. Wichtig dabei ist, das ein Konstruktor benötigt wird, ohne den die vordefinierten Webelemente nicht lokalisiert werden können. Für das Pageobjekt Login wird daher folgender Konstruktor benötigt

```
public Login(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}
```

Es wird der Driver benötigt, welches den Browser manipuliert und schon definiert und instanziert wurde, sowie müssen die vordefinierten Webelemente initialisiert werden, was durch `PageFactory.initElements(driver, this);` geschiet.
Diese vordefinierten WebElemente werden durch die Annotation `@FindBy` definiert. Das `By` ist dabei die Art der Lokalisierung also per Xpath, ClassName, id oder andere Möglichkeiten, wie es in der Dokumentation beschrieben wurde.
Folgendes Beispiel zeigt eine Vordefinierung des Beispiels für den Login, welches sich auch in diesem Projekt befindet.

```
@FindBy(name = "e")
WebElement email;

@FindBy(name = "input")
WebElement password;

@FindBy(tagName = "button")
WebElement submit;
```

Solche definierten Elemente können im weiteren Verlauf in Methoden verwendet werden und müssen so nur einmal definiert werden. Für das Einloggen kann so eine Methode definiert werden, welche dieses durchführt.

```
public void login(String loginEmail, String loginPassword) {
	logger.info("Authentifizieren mit Email: " + loginEmail + " und Passwort: " + loginPassword);
	email.sendKeys(loginEmail);
	password.sendKeys(loginPassword);
	logger.info("Klicke auf Login");
	submit.submit();
}
```

So wurden die vordefinierten Elemente in einer Methode verwendet, damit der Driver das Login-Formular ausfüllt und abschickt.

Es kann jedoch nicht immer jedes Element vordefiniert werden. Es gibt Elemente, vorallen auf dynamischeren Webseiten, die initial nicht vorhanden sind und erst durch bestimmte Events erscheinen. Auch Animationen oder Änderungen brauchen eine gewisse Zeit, bis diese auf der Webseite sichtbar sind. Solche Elemente werden nicht am Anfang durch @FindBy definiert.

Im Testcase cartTest, welches den Warenkorb testet wird ein Produkt zum Warenkorb hinzugefügt. Nach den Klicken des Buttons zum Hinzufügen vergehen 1-2 Sekunden, bis die Anzahl der Objekte sich im Warenkorb ändern. Um solch ein Verhalten zu implementieren, gibt es den WebDriverWait. Folgendes Beispiel lässt den Browser maximal 10 Sekunden warten, bis sich der Text in einen Element zu einen Bestimmten Wert ändert.
```
/**
* Überprüft, ob die Anzahl der Produkte im Warenkorb sich ändern
* @param amount die Amzahl der zubeinhaltenen Produkte
* @return true wenn die Anzahl sich geändert hat, sonst false
*/
public Boolean checkCartForHavingItems(String amount) {
		return (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElement(cartCount, amount));
	}
```
Es gibt verschiedene ExpectedConditions, sodass für jeden Anwendungsfall etwas zu finden ist.
