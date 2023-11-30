import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase es el equivalente a la clase controlador
 */
public class MainScrapp {
  /**
   * Contiene la cantidad de milisegundos para establecer a todos los sleeps / waits que se utilizan a lo largo de todo el fichero.
   */
  int time = 2500;

  ArrayList<Game> games = new ArrayList<>();
  ArrayList<Map> maps = new ArrayList<>();
  ArrayList<Player> players = new ArrayList<>();

  ArrayList<String> allHref = new ArrayList<>();

  ArrayList<String> hrefGames = new ArrayList<>();
  ArrayList<String> hrefMap = new ArrayList<>();
  ArrayList<String> hrefPlayer = new ArrayList<>();
  ArrayList<String> hrefComm = new ArrayList<>();

  /**
   * Este es el código principal para todo lo que viene siendo el WebScrapping.
   * @throws InterruptedException Este throws es obligatorio debido a que hacemos uso del Thread Sleep
   */
  public void iniciarRobo() throws InterruptedException {
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    FirefoxOptions options = new FirefoxOptions();

    options.setBinary("/home/wavi/firefox/firefox");

    WebDriver driver = new FirefoxDriver(options);
    driver.get("http://awbw.amarriner.com"); // En esta línea se debe poner la página en la que se quiere hacer el scrappeo de información. En esta aplicación es AWBW.

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


  /**
   * Este método guarda los datos de una clase a un fichero CSV. Dentro de la clase se debe poner la ruta del fichero, junto a la clase de la que guardar la información.
   */
  public void guardarCSV(){
    // En la siguiente variable es necesario poner la ruta en la que deseas guardar el fichero.
    String csvGame="src/main/game.csv";
    String csvMap="src/main/map.csv";
    String csvPlayer="src/main/player.csv";
    String csvFilePathGames="src/main/games.csv";
    try {
      CSVWriter writer1 = new CSVWriter(new FileWriter(csvGame));
      CSVWriter writer2 = new CSVWriter(new FileWriter(csvMap));
      CSVWriter writer3 = new CSVWriter(new FileWriter(csvPlayer));
      CSVWriter writer4 = new CSVWriter(new FileWriter(csvFilePathGames));
      String[] data1 = {"NAME"};
      writer1.writeNext(data1);
      data1 = new String[]{"NAME", "CREATOR", "MAX_PLAYERS", "SIZE"};
      writer2.writeNext(data1);
      data1 = new String[]{"NAME", "LAST_ACTIVITY", "OFFICIAL_RATING", "WLD", "COMMANDER_WR"};
      writer3.writeNext(data1);
      data1 = new String[]{"GAME_NAME", "MAP_NAME","PLAYER_NAME"};
      writer4.writeNext(data1);

      for (Game games : this.games) {
        String[] data = {
                games.getGameName()
                };
        writer1.writeNext(data);
      }
      for (Map maps : this.maps){
        String[] data = new String[] {
                maps.getName(),
                maps.getCreator(),
                maps.getMaxPlayers(),
                maps.getSize(),
        };
        writer2.writeNext(data);
      }
      for (Player players : this.players){
        String[] data = new String[]{
                players.getPlayerName(),
                players.getLastActivity(),
                players.getOfficialRating(),
                players.getWld(),
                String.valueOf(players.getCommanderWR())
        };
        writer3.writeNext(data);
      }
      for (Game games : this.games){
        String nombreplayers = "";
        int savedPlayers=1;
        for (Player player : games.getPlayers()){
          if (savedPlayers!=games.getPlayers().size())nombreplayers += player.getPlayerName() + ",";
          else nombreplayers += player.getPlayerName();
          savedPlayers++;
        }
        String[] data = new String[]{
                games.getGameName(),
                String.valueOf(games.getMap().getName()),
                nombreplayers
        };
        writer4.writeNext(data);
      }
      writer4.close();
      writer3.close();
      writer2.close();
      writer1.close();

      System.out.println("Datos guardados correctamente en el archivo CSV.");
    } catch (IOException e) {
      e.printStackTrace();
    }
/*
    try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
      // Escribe las líneas de datos al archivo CSV
      for (Game game : games) {
        String[] data = {game.getGameName(),String.valueOf(game.getMap()),String.valueOf(game.getPlayers())};
        writer.writeNext(data);
      }

    System.out.println("Datos guardados correctamente en el archivo CSV.");
  } catch (IOException e) {
      e.printStackTrace();
    }
 */
  }

  /**
   * El siguiente método guarda los datos de una clase en concreto a un fichero XML, y lo estructura con la jerarquía en concreto de dicha clase. La ruta del fichero se declara dentro del mismo método.
   */
  public void guardarXML(){
    // En la siguiente variable es necesario poner la ruta en la que deseas guardar el fichero.
    String xmlFilePath="src/main/games.xml";
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document document = dBuilder.newDocument();


      //La siguiente línea es para declarar cuál es el nodo raíz del documento XML

      Node rootNode = document.createElement("games");
      document.appendChild(rootNode);

      //El siguiente for es para que cree un nodo por cada game, lo mismo para cualquier bucle que se vea de este tipo

      for(Game game: games){
        Node gameNode = document.createElement("game");
        rootNode.appendChild(gameNode);

        Node gameName = document.createElement("name");
        gameName.appendChild(document.createTextNode(game.getGameName()));
        gameNode.appendChild(gameName);


        Node mapNode = document.createElement("map");
        gameNode.appendChild(mapNode);

        Node mapName = document.createElement("name");
        mapName.appendChild(document.createTextNode(game.getMap().getName()));
        mapNode.appendChild(mapName);

        Node mapCreator = document.createElement("creator");
        mapCreator.appendChild(document.createTextNode(game.getMap().getCreator()));
        mapNode.appendChild(mapCreator);

        Node mapMaxPlayers = document.createElement("maxPlayers");
        mapMaxPlayers.appendChild(document.createTextNode(game.getMap().getMaxPlayers()));
        mapNode.appendChild(mapMaxPlayers);

        Node mapSize = document.createElement("size");
        mapSize.appendChild(document.createTextNode(game.getMap().getSize()));
        mapNode.appendChild(mapSize);

        for(Player player : game.getPlayers()){
          Node playerNode = document.createElement("player");
          gameNode.appendChild(playerNode);

          Node playerName = document.createElement("name");
          playerName.appendChild(document.createTextNode(player.getPlayerName()));
          playerNode.appendChild(playerName);

          Node playerLastActivity = document.createElement("lastActivity");
          playerLastActivity.appendChild(document.createTextNode(player.getLastActivity()));
          playerNode.appendChild(playerLastActivity);

          Node playerOfficialRating = document.createElement("officialRating");
          playerOfficialRating.appendChild(document.createTextNode(player.getOfficialRating()));
          playerNode.appendChild(playerOfficialRating);

          Node playerWLD = document.createElement("wld");
          playerWLD.appendChild(document.createTextNode(player.getWld()));
          playerNode.appendChild(playerWLD);

          Node commNode = document.createElement("comm");
          playerNode.appendChild(commNode);
          for(String string : player.getCommanderWR()){

            List<String> commSeparate = List.of(string.split(" "));

            Node commName = document.createElement("name");
            commName.appendChild(document.createTextNode(commSeparate.get(0)));
            commNode.appendChild(commName);

            Node commWinrate = document.createElement("winrate");
            commWinrate.appendChild(document.createTextNode(commSeparate.get(2)));
            commNode.appendChild(commWinrate);
          }
        }
      }

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes"); // El yes es para que tabule de forma automática

      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(new File(xmlFilePath));
      transformer.transform(source, result);

      System.out.println("Datos guardados correctamente en el archivo XML");

    } catch (ParserConfigurationException | TransformerException e) {
        throw new RuntimeException(e);
    }
  }


  /**
   * El siguiente código encuentra el botón para hacer login en la página web, lo clica e inserta los datos para hacer iniciar session. En este caso se utiliza mi cuenta, pero si se quiere utilizar otra solo se debe cambiar la array llamada "user".
   * @param driver El siguiente parámetro hace referencia a la página a la que se quiere acceder.
   */
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

  /**
   * El siguiente método entra en el perfil de la cuenta a la que se ha iniciado sesión, y almacena todos los elementos "a" que se encuentren en la caja de "Current Games".
   * @param driver El siguiente parámetro hace referencia a la página a la que se quiere acceder.
   * @throws InterruptedException Este throws se encuentra aquí por el uso del Thread sleep.
   */
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

  /**
   * Este método sirve para organizar todos los enlaces conseguidos anteriormente, y poder asi funcionar de una manera más ordenada.
   */
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

  /**
   * En el siguiente método se entra link por link de cada partida, y se va buscando la información necesaria tanto en la página como en las variables de esta clase para crear un game y almacenarlo en una lista de ellos.
   * @param driver El siguiente parámetro hace referencia a la página a la que se quiere acceder.
   * @throws InterruptedException
   */
  public void datosGames(WebDriver driver) throws InterruptedException {

    for (String link : hrefGames) {
      driver.get(link);
      String name;
      String mapName;
      Map map = null;
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

      for(Map map2 : maps){
        if(map2.getName().equals(mapName)){
          map = map2;
        }
      }

      for(String player1 : listNamePlayers){
        for (Player player2 : players){
          if(player1.contains(player2.getPlayerName())) playerList.add(player2);
        }
      }

      games.add(new Game(name, map, playerList));
    }
  }

  /**
   * En el siguiente método se entra link por link de cada mapa, y se va buscando la información necesaria en la página para crear un map y almacenarlo en una lista de ellos.
   * @param driver El siguiente parámetro hace referencia a la página a la que se quiere acceder.
   */
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
   * En el siguiente método se entra link por link de cada uno de los jugadores, y se va buscando la información necesaria en la página para crear correctamente un player y almacenarlo en una lista.
   * @param driver El siguiente parámetro hace referencia a la página a la que se quiere acceder.
   */
  public void datosPlayer(WebDriver driver) {
    System.out.println("Entrando a datos player");
    for (String link : hrefPlayer) {
      driver.get(link);
      System.out.println("moviéndose a " + link);
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
   * Se le proporciona la variable que almacena el nombre, y mediante el uso de un Regex se almacena únicamente el nombre sin ningún dato o espacio extra.
   * @param string En este caso se le debe dar el nombre del jugador.
   * @return Devuelve el nombre del jugador en un mejor formato.
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


