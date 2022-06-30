import java.util.Objects;

public class game {
    public static String PACMAN = "PACMAN";
    public static char PACMAN_FORM = 'P';

    public static String GHOST = "GHOST";
    public static char GHOST_FORM = 'G';
    private int x;
    private int y;

    private final String type;

    public game(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public String displayMonster() {
        if (Objects.equals(this.type, PACMAN)) {
            return Tuile.ANSI_YELLOW + PACMAN_FORM;
        } else {
            return Tuile.ANSI_RED + GHOST_FORM;
        }
    }

    public boolean right(Terrain terrain) {
        Tuile currentTuile = terrain.recupererTuileTerrain(this.x, this.y);
        Tuile nextTuile = terrain.recupererTuileTerrain(this.x, this.y + 1);
        if (nextTuile.passingThrough() && !nextTuile.GhostHere()) {
            adjustElement(currentTuile, nextTuile);
            this.y++;
            return true;
        } else if (nextTuile.passingThrough() && nextTuile.GhostHere()){
            return false;
        }
        return true;
    }

    public boolean left(Terrain terrain) {
        Tuile currentTuile = terrain.recupererTuileTerrain(this.x, this.y);
        Tuile nextTuile = terrain.recupererTuileTerrain(this.x, this.y - 1);
        if (nextTuile.passingThrough() && !nextTuile.GhostHere()) {
            adjustElement(currentTuile, nextTuile);
            this.y--;
            return true;
        } else if (nextTuile.passingThrough() && nextTuile.GhostHere()){
            return false;
        }
        return true;
    }

    public boolean up(Terrain terrain) {
        Tuile currentTuile = terrain.recupererTuileTerrain(this.x, this.y);
        Tuile nextTuile = terrain.recupererTuileTerrain(this.x - 1, this.y);
        if (nextTuile.passingThrough() && !nextTuile.GhostHere()) {
            adjustElement(currentTuile, nextTuile);
            this.x--;
            return true;
        } else if (nextTuile.passingThrough() && nextTuile.GhostHere()){
            return false;
        }
        return true;
    }

    public boolean down(Terrain terrain) {
        Tuile currentTuile = terrain.recupererTuileTerrain(this.x, this.y);
        Tuile nextTuile = terrain.recupererTuileTerrain(this.x + 1, this.y);
        if (nextTuile.passingThrough() && !nextTuile.GhostHere()) {
            adjustElement(currentTuile, nextTuile);
            this.x++;
            return true;
        } else if (nextTuile.passingThrough() && nextTuile.GhostHere()){
            return false;
        }
        return true;
    }

    private void adjustElement(Tuile currentTuile,Tuile nextTuile){
        if (Objects.equals(this.type, PACMAN)) {
            nextTuile.positionPacmanIci(true);
            nextTuile.positionPacman((Pacman) this);
            currentTuile.resetTuilePacman();
        } else if (Objects.equals(this.type, GHOST)) {
            nextTuile.positionGhostIci(true);
            nextTuile.newGhost((Ghost) this);
            currentTuile.resetTuileGhost((Ghost) this);
        }
    }

}
