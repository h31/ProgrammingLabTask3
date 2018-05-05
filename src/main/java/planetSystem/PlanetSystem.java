package planetSystem;

import java.util.List;

public class PlanetSystem {

    Star star;

    List<Planet> planets;

    public void setStar(Star star) {
        this.star = star;
    }

    public PlanetSystem(Star star, List<Planet> planets) {
        this.star = star;
        this.planets = planets;
    }

    public void pause() {
        for (Planet p:planets) {
            p.pause();
        }
    }

    public void play() {
        for (Planet p:planets) {
            p.play();
        }
    }

    public void forward(int dt) {
        for (Planet p:planets) {
            p.step(dt);
        }
    }
}
