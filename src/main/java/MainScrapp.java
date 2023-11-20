import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainScrapp {
  int time = 2500;

  ArrayList<Game> games = new ArrayList<>();
  ArrayList<Map> maps = new ArrayList<>();
  ArrayList<Player> players = new ArrayList<>();

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
    Thread.sleep(time);
    datosGames(driver);

    driver.quit();
  }

  public void login(WebDriver driver) {
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

  public void organizarDatos() {
    int t = 0;

    for (String link : allHref) {
      if (t == 1) hrefGames.add(link);
      if (t == 5) hrefMap.add(link);
      if (t > 5 && t <= 7) hrefPlayer.add(link);
      if (t == 8) t = 0;
      t++;
    }
    for (String href : hrefGames) {
      System.out.println("Contenido de games: " + href);
    }
    for (String href : hrefMap) {
      System.out.println("Contenido de mapas: " + href);
    }
    for (String href : hrefPlayer) {
      System.out.println("Contenido de jugadores: " + href);
    }

  }

  public void datosGames(WebDriver driver) throws InterruptedException {

    for (String link : hrefGames) {
      driver.get(link);
      String name;
      String mapName;
      Map map;
      List<Player> playerList = new ArrayList<>();
      List<String> listNamePlayers = new ArrayList<>();

      WebElement element = driver.findElement(By.xpath("/html/body/div[3]/section/div[2]/div[1]/div[1]/a"));
      name = element.getText();

      WebElement element1 = driver.findElement(By.xpath("/html/body/div[3]/section/div[2]/div[1]/div[3]/span[3]"));
      mapName = element1.getText();

      Thread.sleep(1000);

      List<WebElement> elementos = driver.findElements(By.className("player-username"));
      for (WebElement element2 : elementos) {
        listNamePlayers.add(element2.getText());
      }

      System.out.println(name);
      System.out.println(mapName);
      listNamePlayers.forEach(System.out::println);


    }

  }

  public void datosMap(WebDriver driver) {

    for (String link : hrefMap) {
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

      maps.add(new Map(name, creator, maxPlayers, size));
    }

  }

  /**
   * Que hace el metodo
   *
   * @param driver explicar que es el driver
   */
  public void datosPlayer(WebDriver driver) {
    System.out.println("Entrando a datos player");
    for (String link : hrefPlayer) {
      driver.get(link);
      System.out.println("moviendose a " + link);
      String pname;
      String lastActivity;
      String or;
      String wld;
      ArrayList<String> cWR = new ArrayList<>();

      WebElement element = driver.findElement(By.xpath("/html/body/div[3]/section/div[2]/table[1]/tbody/tr/td[1]/table/tbody/tr[2]/td"));
      String cadenaEntera = element.getText();
      List<String> a = List.of(cadenaEntera.split(" "));
      pname = a.get(1);
      pname = transformarNombrePlayer(pname);
      if (players.isEmpty()) {
        lastActivity = a.get(4);
        or = a.get(7);
        wld = a.get(13) + a.get(14) + a.get(15) + a.get(16) + a.get(17);

        WebElement element1 = driver.findElement(By.xpath("/html/body/div[3]/section/div[2]/table[1]/tbody/tr/td[2]/table[1]/tbody/tr[2]/td"));

        List<WebElement> elementosA = element1.findElements(By.tagName("a"));

        List<String> c = new ArrayList<>();
        int temp = 1;
        for (WebElement link2 : elementosA) {
          String href = link2.getAttribute("href");
          hrefComm.add(href);
          String cadenaEntera2 = element1.getText();
          c = List.of(cadenaEntera2.split(" "));
        }
        for (String href : hrefComm) {
          List<String> b = List.of(href.split("#"));
          cWR.add(b.get(1) + " " + "-" + " " + c.get(temp).substring(0, 4));
          temp++;
        }
        hrefComm.clear();
        System.out.println(pname);
        System.out.println(lastActivity);
        System.out.println(or);
        System.out.println(wld);
        for (String winrate : cWR) {
          System.out.println(winrate);
        }
        players.add(new Player(pname, lastActivity, or, wld, cWR));
      } else {
        Player equality = new Player(pname, null, null, null, null);
        for (int i = 0; i < players.size(); i++) {
          if (!players.contains(equality)) {
            lastActivity = a.get(4);
            or = a.get(7);
            wld = a.get(13) + a.get(14) + a.get(15) + a.get(16) + a.get(17);

            WebElement element1 = driver.findElement(By.xpath("/html/body/div[3]/section/div[2]/table[1]/tbody/tr/td[2]/table[1]/tbody/tr[2]/td"));

            List<WebElement> elementosA = element1.findElements(By.tagName("a"));

            List<String> winrate = new ArrayList<>();
            int temp = 1;
            for (WebElement link2 : elementosA) {
              String href = link2.getAttribute("href");
              hrefComm.add(href);
              String cadenaEntera2 = element1.getText();
              winrate = List.of(cadenaEntera2.split(" "));
            }
            for (String href : hrefComm) {
              List<String> NombreComm = List.of(href.split("#"));
              System.out.println(NombreComm.get(1));
              System.out.println(winrate.get(temp));
              cWR.add(NombreComm.get(1) + " " + "-" + " " + winrate.get(temp).substring(0, 4));
              temp++;
            }
            hrefComm.clear();
            System.out.println(pname);
            System.out.println(lastActivity);
            System.out.println(or);
            System.out.println(wld);
            for (String winrateString : cWR) {
              System.out.println(winrateString);
            }
            players.add(new Player(pname, lastActivity, or, wld, cWR));
          }
        }
      }
    }
  }

  /**
   * Se le propociona la variable que almacena el nombre, y mediante el uso de un Regex se almacena unicamente el nombre sin ningun dato o espacio extra.
   * @param string En este caso se le debe dar el nombre del jugador.
   * @return
   */
  public String transformarNombrePlayer(String string) {
    final String regex = "([\\w\\d]+)\\n";

    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(string);

    while (matcher.find()) {
      System.out.println("Full match: " + matcher.group(0));
      return matcher.group(1);
    }
    return null;
  }
}


