package Logic;

import java.util.ArrayList;
import java.util.List;

public class PlanetSystem {

    public Star star;

    public List<Planet> planets;

    public int t = 0;

    private int dt = 1;

    public void addPlanet(Planet p) {
        planets.add(p);
    }

    public void step() {
        for (Planet p : planets) {
            p.x = (int) (400 + p.a / 2 * Math.cos(2 * Math.PI / p.period * t));
            p.y = (int) (300 + p.b / 2 * Math.sin(2 * Math.PI / p.period * t));
        }
        t = t + dt;
    }

    public void step(int dt) {
        t = t + dt;
    }

    public PlanetSystem(Star star) {
        this.star = star;
        planets = new ArrayList<>();
    }

    public PlanetSystem(Star star, List<Planet> planets) {
        this.star = star;
        this.planets = planets;
    }

    public void pause() {
        dt = 0;
    }

    public void play() {
        dt = 1;
    }

    public void forward(int dt) {
        step(dt);
    }
}
