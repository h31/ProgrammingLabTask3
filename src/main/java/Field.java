import java.util.ArrayList;
import java.util.Random;

class Field {

    private static Coord size;
    private static ArrayList<Coord> allCoords;
    private static Random random = new Random();

    static void setSize(Coord size) {
        Field.size = size;
        allCoords = new ArrayList<>();
        for (int y = 0; y < size.y; y++)
            for (int x = 0; x < size.x; x++)
                allCoords.add(new Coord(x, y));
    }

    static Coord getSize() {
        return size;
    }

    static ArrayList<Coord> getAllCoords() {
        return allCoords;
    }

    static boolean inRange(Coord coord){
        return coord.x >= 0 && coord.x < size.x && coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord(){
        return new Coord(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Coord> getCoordAround(Coord coord){
        ArrayList<Coord> list = new ArrayList<>();
        if (coord.x % 2 == 0){
           list.add(new Coord(coord.x - 1, coord.y));
           list.add(new Coord(coord.x - 1, coord.y - 1));
           list.add(new Coord(coord.x, coord.y + 1));
           list.add(new Coord(coord.x + 1, coord.y - 1));
           list.add(new Coord(coord.x + 1, coord.y));
           list.add(new Coord(coord.x, coord.y - 1));
        } else {
           list.add(new Coord(coord.x - 1, coord.y));
           list.add(new Coord(coord.x - 1, coord.y + 1));
           list.add(new Coord(coord.x, coord.y + 1));
           list.add(new Coord(coord.x + 1, coord.y + 1));
           list.add(new Coord(coord.x + 1, coord.y));
           list.add(new Coord(coord.x, coord.y - 1));
       }
       return list;
    }
}
