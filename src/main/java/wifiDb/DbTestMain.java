package wifiDb;
public class DbTestMain {
    public static void main(String[] args) {
        WifiService wifiService = new WifiService();
        // dbInsert
        wifiService.dbInsert();

        // dbSelect -> 현재 위치
        wifiService.dbSelect(127.0710272,37.6635392);

        // dbDelect


    }
}
