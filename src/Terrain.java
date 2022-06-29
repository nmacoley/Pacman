import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private final String[] terrainOriginal =
            {
                    "|------------||------------|",
                    "|............||............|",
                    "|.----.-----.||.----.-----.|",
                    "|.|  |.|   |.||.|  |.|   |.|",
                    "|.----.-----.||.----.-----.|",
                    "|...G........P.........G...|",
                    "|.----.--.--------.--.----.|",
                    "|.----.||.---||---.||.----.|",
                    "|......||....||....||......|",
                    "|------||----||----||------|"
            };


    private final Tuile[][] terrain;

    Pacman pacman;

    public Terrain() {
        int n = terrainOriginal.length;
        int m = terrainOriginal[0].length();

        this.terrain = new Tuile[n][m];

        for (int i = 0; i < n; i++) {
            for (int l = 0; l < m; l++) {
                terrain[i][l] = new Tuile(terrainOriginal[i].charAt(l), i, l);
            }
        }
    }

    public void afficherTerrain() {
        String output = "";
        int n = terrainOriginal.length;
        int m = terrainOriginal[0].length();

        for (int i = 0; i < n; i++) {
            for (int l = 0; l < m; l++) {
                output = output.concat(terrain[i][l].representation());
            }
            output = output.concat("\n");
        }
        System.out.print(output);
    }

    public Pacman findPacman(){
        int n = terrainOriginal.length;
        int m = terrainOriginal[0].length();

        for (int i = 0; i < n; i++) {
            for (int l = 0; l < m; l++) {
                if (terrain[i][l].PacmanHere()){
                    return terrain[i][l].getPacman();
                }
            }
        }
        return null;
    }

    public List<Ghost> findGhosts(){
        int n = terrainOriginal.length;
        int m = terrainOriginal[0].length();

        List<Ghost> ghosts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int l = 0; l < m; l++) {
                if (terrain[i][l].GhostHere()){
                    ghosts.addAll(terrain[i][l].getGhosts());
                }
            }
        }
        return ghosts;
    }

    public Tuile recupererTuileTerrain(int x, int y){
        return terrain[x][y];
    }
}