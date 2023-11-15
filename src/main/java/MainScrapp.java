import org.mortbay.jetty.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.*;

public class MainScrapp {
  int time = 2500;

  ArrayList<Games> games = new ArrayList<>();
  ArrayList<Map> map = new ArrayList<>();
  ArrayList<Players> player = new ArrayList<>();
  ArrayList<String> comm = new ArrayList<>();

  ArrayList<String> allHref = new ArrayList<>();

  ArrayList<String> hrefGames = new ArrayList<>();
  ArrayList<String> hrefMap = new ArrayList<>();
  ArrayList<String> hrefPlayer = new ArrayList<>();
  ArrayList<String> hrefComm = new ArrayList<>();


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
  Thread.sleep(time);
  organizarDatos();
  Thread.sleep(time);
  datosMap(driver);
  Thread.sleep(time);
  datosPlayer(driver);
  driver.quit();
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

    WebElement elemento = driver.findElement(By.xpath("/html/body/div[3]/section/div[2]/table[2]/tbody/tr/td[1]/table[1]/tbody/tr[2]/td"));

    List<WebElement> elementosA = elemento.findElements(By.tagName("a"));

    for (WebElement link : elementosA) {
      String href = link.getAttribute("href");
      allHref.add(href);
    }

    for (String hre : allHref) {
      System.out.println("Contenido de href: " + hre);
    }
  }

  public void organizarDatos(){
  int t = 0;

  for(String link : allHref){
    if (t==1) hrefGames.add(link);
    if (t==5) hrefMap.add(link);
    if (t>5 && t<=7) hrefPlayer.add(link);
    if (t==8) t=0;
    t++;
  }
  for (String href : hrefGames){ System.out.println("Contenido de games: " + href);}
  for (String href : hrefMap){ System.out.println("Contenido de mapas: " + href);}
  for (String href : hrefPlayer){ System.out.println("Contenido de jugadores: " + href);}

  }

  public void datosGames(WebDriver driver){

  for (String link : hrefGames){
    String name;
  }

  }

  public void datosMap(WebDriver driver){

    for (String link : hrefMap){
      driver.get(link);
      String name;
      String creator;
      String maxPlayers;
      String size;

      WebElement element = driver.findElement(By.className("bordertitle"));
      name = element.getText();

      WebElement element1 = driver.findElement(By.className("game-header-body"));
      String cadenaEntera = element1.getText();
      List<String> a = List.of(cadenaEntera.split(" "));
      maxPlayers = a.get(8);
      size = a.get(11);

      WebElement element2 = driver.findElement(By.xpath("/html/body/div[3]/section/div[2]/div[1]/div[2]/a"));

      creator = element2.getText();

      System.out.println(name);
      System.out.println(creator);
      System.out.println(maxPlayers);
      System.out.println(size);

      map.add(new Map(name, creator,maxPlayers,size));
    }

  }

  public void datosPlayer(WebDriver driver){

  for(String link : hrefPlayer){
    driver.get(link);
    String pname;
    String lastActivity;
    String or;
    String wld;
    String cWR;

    WebElement element = driver.findElement(By.xpath("/html/body/div[3]/section/div[2]/table[1]/tbody/tr/td[1]/table/tbody/tr[2]/td"));
    String cadenaEntera = element.getText();
    List<String> a = List.of(cadenaEntera.split(" "));

    //a.forEach(System.out::println);

    pname = a.get(1);
    //for (Players players : player) {
    /*if (players.getPlayerName().equals(pname)){

    } else {*/
      lastActivity = a.get(4);
      or = a.get(7);
      wld = a.get(13)+a.get(14)+a.get(15)+a.get(16)+a.get(17);

      System.out.println(pname);
      System.out.println(lastActivity);
      System.out.println(or);
      System.out.println(wld);
      WebElement element1 = driver.findElement(By.xpath("/html/body/div[3]/section/div[2]/table[1]/tbody/tr/td[2]/table[1]/tbody/tr[2]/td"));

      List<WebElement> elementosA = element1.findElements(By.tagName("a"));
      if (hrefComm.size() < 1) {

        for (WebElement link2 : elementosA) {
          String href = link2.getAttribute("href");
          hrefComm.add(href);
        }
        for (String href : hrefComm) {
          List<String> b = List.of(href.split("#"));
          System.out.println(b.get(1));
          comm.add(b.get(1));
        }
      }
      Collections.sort(comm);

      String cadenaEntera2 = element1.getText();
      List<String> b = List.of(cadenaEntera.split(" "));

    for (String comm : comm){

    }



    }

    }
  }

  //}

//}
