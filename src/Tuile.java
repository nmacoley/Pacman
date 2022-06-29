import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tuile {
    private char caracters;

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";

    private String couleur = ANSI_WHITE;
    private boolean passThrough = false;

    private boolean pacmanIci = false;
    private Pacman pacman;

    private final List<Ghost> ghosts = new ArrayList<>();

    private boolean ghostIci = false;
    private boolean pieceIci = false;

    public Tuile(char caracters, int monsterX, int monsterY) {
        this.caracters = caracters;
        switch (caracters) {
            case '.' -> {
                this.couleur = ANSI_YELLOW;
                this.passThrough = true;
                pieceIci = true;
            }
            default -> {
                this.couleur = ANSI_PURPLE;
                this.passThrough = true;
                pieceIci = true;
            }
            case '|', '-' -> {
                this.couleur = ANSI_BLACK;
            }
            case 'P' -> {
                this.pacmanIci = true;
                pacman = new Pacman(monsterX, monsterY);
                this.passThrough = true;
            }
            case 'G' -> {
                this.ghostIci = true;
                this.passThrough = true;
                ghosts.add(new Ghost(monsterX, monsterY));
            }
        }
    }

    public String representation() {
        if (pacmanIci) {
            return pacman.displayMonster();
        }

        if (ghostIci) {
            return ghosts.get(0).displayMonster();
        }

        return (couleur + caracters);
    }

    public boolean passingThrough() {
        return passThrough;
    }

    public boolean PacmanHere() {
        return pacmanIci;
    }

    public boolean GhostHere() {
        return ghostIci;
    }

    public void positionPacmanIci(boolean PacmanIci) {
        pacmanIci = PacmanIci;
    }

    public void positionGhostIci(boolean GhostIci) {
        ghostIci = GhostIci;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void positionPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public void newGhost(Ghost ghost) {
        ghosts.add(ghost);
    }

    public void resetTuilePacman() {
        this.caracters = ' ';
        this.pacman = null;
        this.pacmanIci = false;
        this.passThrough = true;
    }

    public void resetTuileGhost(Ghost ghost) {
        ghosts.remove(ghost);
        this.ghostIci = false;
        this.passThrough = true;
        if (pieceIci){
            this.caracters = '.';
        } else {
            this.caracters = ' ';
        }
    }
}
