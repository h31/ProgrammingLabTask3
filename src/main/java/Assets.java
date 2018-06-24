import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 64, height = 64;

    public static BufferedImage grass, pit, selected, dangers, knight, sectoid;

    public static void init() {
        SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("textures/64x64.png"));

        grass = tileSheet.corp(width * 10, height * 4, width, height);
        pit = tileSheet.corp(0, 0,width, height);
        dangers = tileSheet.corp(width * 7,height * 4,width,height);
        selected = tileSheet.corp(width * 5,height * 10, width, height);
        knight = tileSheet.corp(width * 4, height * 7, width, height);
        sectoid = tileSheet.corp(0, height * 10, width, height);
    }
}
