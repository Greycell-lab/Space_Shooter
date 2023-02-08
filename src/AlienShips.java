import java.net.URL;

public enum AlienShips {
    SHIP1(AlienShips.class.getResource("/alien.png")),
    SHIP2(AlienShips.class.getResource("/alien1.png")),
    SHIP3(AlienShips.class.getResource("/alien2.png")),
    SHIP4(AlienShips.class.getResource("/alien3.png")),
    SHIP5(AlienShips.class.getResource("/alien4.png")),
    SHIP6(AlienShips.class.getResource("/alien5.png"));
    final URL fileName;
    AlienShips(URL fileName){
        this.fileName = fileName;
    }
}
