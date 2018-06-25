package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Ship {
    private static List<Ship> allShips = new ArrayList<>();

    public List<Point> getPoints() {
        return points;
    }

    private enum Course {
        HORIZONTAL,
        VERTICAL
    }

    private Course course;
    private List<Point> points;
    private Point start;
    private int size;

    private void createShipPoints() {
        for (int i = 0; i < size; i++) {
            points.add(new Point(start.x + (course == Course.HORIZONTAL ? i : 0),
                    start.y + (course == Course.VERTICAL ? i : 0)));
        }
    }

    private Ship(int x, int y, int size, int course) {
        points = new ArrayList<>();
        start = new Point(x, y);
        this.size = size;
        switch (course) {
            case 0:
                this.course = Course.HORIZONTAL;
                break;
            case 1:
                this.course = Course.VERTICAL;
                break;
        }
        createShipPoints();
        allShips.add(this);
    }

    private static boolean shipsIntersect (Ship otherShip) {
        if (allShips.size() <= 1) return false;
        for (Ship ship: allShips.subList(0, allShips.size() - 1)) {
            for (Point point: ship.getPoints()) {
                for (Point otherPoint: otherShip.getPoints()) {
                    for (int dx = -1; dx < 2; dx++) {
                        for (int dy = -1; dy < 2; dy++) {
                            if ((otherPoint.x == point.x + dx) && (otherPoint.y == point.y + dy)) {
                                allShips.remove(otherShip);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isNormalPos(Ship ship) {
        Point head = ship.getPoints().get(0);
        Point tail = ship.getPoints().get(ship.getPoints().size() - 1);
        return (isNormalPos(head) && isNormalPos(tail));
    }

    private static boolean isNormalPos (Point point) {
        return ((point.x >= 0) && (point.x <= 9) && (point.y >= 0) && (point.y <= 9));
    }

    public static List<Ship> randomGenerateShips() {
        List<Ship> ships = new ArrayList<>();
        for (int size = 4; size >= 1; size--) {
            for (int num = 5; num > size; num--) {
                Random random = new Random();
                Ship ship;
                do {
                    int x = random.nextInt(10);
                    int y = random.nextInt(10);
                    int course = random.nextInt(2);
                    ship = new Ship(x, y, size, course);
                }
                while (shipsIntersect(ship) || !isNormalPos(ship));
                ships.add(ship);
            }
        }
        allShips.clear();
        return ships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Ship ship = (Ship) o;
        return size == ship.size &&
                Objects.equals(points, ship.points) &&
                Objects.equals(start, ship.start) &&
                course == ship.course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, start, size, course);
    }
}
