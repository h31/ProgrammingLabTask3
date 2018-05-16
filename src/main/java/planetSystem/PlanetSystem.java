package planetSystem;

import java.util.List;

class PlanetSystem {

    Star star;

    List<Planet> planets;

    int t = 0;

    private int dt = 1;

    void setStar(Star star) {
        this.star = star;
    }

    void step() {
        for (Planet p : planets) {
            p.x = (int) (400 + p.a / 2 * Math.cos(2 * Math.PI / p.period * t));
            p.y = (int) (300 + p.b / 2 * Math.sin(2 * Math.PI / p.period * t));
        }
        t = t + dt;
    }

    void step(int dt) {
        t = t + dt;
    }

    PlanetSystem(Star star, List<Planet> planets) {
        this.star = star;
        this.planets = planets;
    }

    void pause() {
        dt = 0;
    }

    void play() {
        dt = 1;
    }

    void forward(int dt) {
        step(dt);
    }
}
