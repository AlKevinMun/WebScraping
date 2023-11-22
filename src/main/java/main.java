public class main {
    public static void main(String[] args) throws InterruptedException {

        MainScrapp mainScrapp = new MainScrapp();

        mainScrapp.iniciarRobo();
        mainScrapp.guardarCSV();
        mainScrapp.guardarXML();
    }
}
