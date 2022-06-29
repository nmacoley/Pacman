import java.util.List;

public class Main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws InterruptedException {


        GestionnaireTouches gestionnaireTouches = new GestionnaireTouches();

        Terrain terrain = new Terrain();
        Pacman pacman = terrain.findPacman();

        List<Ghost> ghosts = terrain.findGhosts();

        boolean play = true;

        while (play) {

            clearScreen();

            terrain.afficherTerrain();

            if (gestionnaireTouches.touchePressee) {
                if (gestionnaireTouches.touche == GestionnaireTouches.down) {
                    for (Ghost ghost : ghosts) {
                        ghost.down(terrain);
                    }
                    play = pacman.down(terrain);
                } else if (gestionnaireTouches.touche == GestionnaireTouches.up) {
                    for (Ghost ghost : ghosts) {
                        ghost.up(terrain);
                    }
                    play = pacman.up(terrain);
                } else if (gestionnaireTouches.touche == GestionnaireTouches.left) {
                    for (Ghost ghost : ghosts) {
                        ghost.left(terrain);
                    }
                    play = pacman.left(terrain);
                } else if (gestionnaireTouches.touche == GestionnaireTouches.right) {
                    for (Ghost ghost : ghosts) {
                        ghost.right(terrain);
                    }
                    play = pacman.right(terrain);
                }
            }

            Thread.sleep(100);

        }

        System.out.print("Game Over");
    }


}
