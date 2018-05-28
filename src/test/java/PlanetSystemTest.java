
import GUI.PlanetConfigurationPanel;
import GUI.PlanetSystemSimulatorFrame;
import Logic.Planet;
import Logic.PlanetSystem;
import Logic.Star;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanetSystemTest {

    @Test
    public void stepTest() {
        PlanetSystem pS = new PlanetSystem(new Star("Солнце", 5, 1));
        Planet p1 = new Planet("1", 10, 250, 200, 2);
        Planet p2 = new Planet("2", 15, 470, 100, 2);
        Planet p3 = new Planet("3", 20, 600, 500, 2);
        pS.addPlanet(p1);
        pS.addPlanet(p2);
        pS.addPlanet(p3);
        pS.forward(2000);
        assertEquals(275, p1.x);
        assertEquals(296, p1.y);
        assertEquals(477, p2.x);
        assertEquals(347, p2.y);
        assertEquals(596, p3.x);
        assertEquals(488, p3.y);
        PlanetSystem pS2 = new PlanetSystem(new Star("Солнце", 5, 1));
        Planet p4 = new Planet("1", 30, 150, 120, 2);
        pS2.addPlanet(p4);
        pS2.step();
        assertEquals(475, p4.x);
        assertEquals(300, p4.y);
        pS2.step();
        pS2.step();
        pS2.step();
        pS2.step();
        pS2.step();
        pS2.step();
        pS2.step();
        assertEquals(474, p4.x);
        assertEquals(301, p4.y);
    }

    @Test
    public void readTest() {
        try {
            PrintWriter pw = new PrintWriter("files/data");
            pw.close();
            FileWriter fr = new FileWriter("files/data");
            fr.write("Солнце 50 2 2 Меркурий 5 100 100 2 Венера 10 150 120 1");
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PlanetConfigurationPanel p = new PlanetConfigurationPanel();
        assertEquals(2, p.readCountOfPlanets());
        PlanetSystem pS = new PlanetSystemSimulatorFrame("d").readPlanetSystem();
        assertEquals("Солнце", pS.star.name);
        assertEquals(50, pS.star.r);
        assertEquals(2, pS.planets.size());
        assertEquals("Меркурий", pS.planets.get(0).name);
        assertEquals(5, pS.planets.get(0).r);
        assertEquals(100, pS.planets.get(0).a);
        assertEquals(100, pS.planets.get(0).b);
        assertEquals("Венера", pS.planets.get(1).name);
        assertEquals(10, pS.planets.get(1).r);
        assertEquals(150, pS.planets.get(1).a);
        assertEquals(120, pS.planets.get(1).b);
    }
}