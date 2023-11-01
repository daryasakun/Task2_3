import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    public DrawPanel() {

    }
    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;
        Section sect = new Section(250, 300, 150, 540, Color.GREEN, Color.BLUE, g);

        //System.out.println(Section.interpolate(110, 20, 120, 100, 234, 11, Color.RED, Color.BLUE));
        sect.draw();

    }


}
