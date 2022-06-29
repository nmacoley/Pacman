import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestionnaireTouches extends JFrame implements KeyListener {

    public static final int left = 37;
    public static final int up = 38;
    public static final int right = 39;
    public static final int down = 40;
    private static GestionnaireTouches gestionnaireTouches;
    boolean touchePressee;
    int touche;

    public GestionnaireTouches() {
        if (gestionnaireTouches != null) {
            System.err.println("Attention, création d'un second gestionnaire de touches !");
            System.exit(1);
        }
        addKeyListener(this);
        setVisible(true);
    }

    public static GestionnaireTouches getGestionnaireTouches() {
        if (gestionnaireTouches == null) {
            gestionnaireTouches = new GestionnaireTouches();
        }
        return gestionnaireTouches;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        touchePressee = true;
        touche = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(touche == e.getKeyCode())
            touchePressee = false;

    }

    public Integer getKey() {
        if (!touchePressee)
            return null;

        return touche;
    }
}
