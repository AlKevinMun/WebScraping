import org.mortbay.jetty.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;

public class MainScrapp {
  int time = 2500;

  ArrayList<Games> games = new ArrayList<>();
  ArrayList<Map> Map = new ArrayList<>();
  ArrayList<Players> Players = new ArrayList<>();

  ArrayList<String> allHref = new ArrayList<>();

  ArrayList<String> hrefGames = new ArrayList<>();
  ArrayList<String> hrefMap = new ArrayList<>();
  ArrayList<String> hrefPlayer = new ArrayList<>();

public void iniciarRobo() throws InterruptedException {
  System.out.println(System.getenv("PATH"));
  System.out.println(System.getenv("HOME"));

  System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
  FirefoxOptions options = new FirefoxOptions();

  options.setBinary("/home/wavi/firefox/firefox");

  WebDriver driver = new FirefoxDriver(options);
  driver.get("http://awbw.amarriner.com");

  login(driver);

  Thread.sleep(time);

  conseguirDatos(driver);
}
  public void login(WebDriver driver){
    WebElement LoginEnter = driver.findElement(By.id("login-box-button"));
    LoginEnter.click();

    List<WebElement> LoginData = driver.findElements(By.className("login-input"));
    String[] user = {"Whatvin15", "kevin1234"};
    int i = 0;

    for (WebElement username : LoginData) {
      username.sendKeys(user[i]);
      i++;
      if (i == 2) break;
    }
    WebElement LoginBoton = driver.findElement(By.id("login"));
    LoginBoton.click();
  }

  public void conseguirDatos(WebDriver driver) throws InterruptedException {
    WebElement Profile = driver.findElement(By.id("profile-menu"));
    Profile.click();
    WebElement GoToProfile = driver.findElement(By.className("dropdown-menu-link"));
    GoToProfile.click();

    Thread.sleep(time);

    List<WebElement> elementos = driver.findElements(By.className("borderwhite"));


    for (WebElement elemento : elementos) {
      String href = elemento.findElement(By.tagName("a")).getAttribute("href");
      allHref.add(href);
    }

    for (String href : allHref) {
      System.out.println("Contenido de href: " + href);
    }


  }
}
