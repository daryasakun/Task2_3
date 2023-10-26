import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    public DrawPanel() {

    }
    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;
        Section sect = new Section(12, 20, 200, 400, g);
        sect.draw();

    }


}
