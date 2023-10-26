import java.awt.*;

public class Section {

    private int x1;
    private Graphics2D g;
    private int y1;
    private int x2;
    private int y2;


    public Section(final int x1, final int y1, final int x2, final int y2, final Graphics2D g) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.g = g;
    }

    public int getX1() {
        return x1;
    }

    public Graphics2D getG() {
        return g;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setG(Graphics2D g) {
        this.g = g;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    void draw(final Graphics gr) {

    }


    private float fractionalPart(float x) {
        int tmp = (int) x;
        return x - tmp; //вернёт дробную часть числа
    }

    public void draw() {
        if (x2 < x1) {
            x1 += x2;
            x2 = x1 - x2;
            x1 -= x2;
            y1 += y2;
            y2 = y1 - y2;
            y1 -= y2;
        }
        int dx = x2 - x1;
        int dy = y2 - y1;
        //Если линия параллельна одной из осей, то не нужно как-то менять яркость
        if (dx == 0 || dy == 0) {
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);
            return;
        }
        float gradient = 0;
        if (dx > dy) {
            gradient = (float) dy / dx;
            float intery = y1 + gradient;
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x1, y1);
            for (int x = x1; x < x2; ++x) {
                g.setColor(new Color(0, 0, 0, (int) (255 - fractionalPart(intery) * 255))); //Меняем яркость
                g.drawLine(x, (int)intery, x, (int)intery);
                g.setColor(new Color(0, 0, 0, (int) (fractionalPart(intery) * 255)));
                g.drawLine(x, (int)intery + 1, x, (int)intery + 1);
                intery += gradient;
            }
            g.setColor(Color.BLACK);
            g.drawLine(x2, y2, x2, y2);
        } else {
            gradient = (float) dx / dy;
            float interx = x1 + gradient;
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x1, y1);
            for (int y = y1; y < y2; ++y) {
                g.setColor(new Color(0, 0, 0, (int) (255 - fractionalPart(interx) * 255)));
                g.drawLine((int)interx, y, (int)interx, y);
                g.setColor(new Color(0, 0, 0, (int) (fractionalPart(interx) * 255)));
                g.drawLine((int)interx + 1, y, (int)interx + 1, y);
                interx += gradient;
            }
            g.setColor(Color.BLACK);
            g.drawLine(x2, y2, x2, y2);
        }

    }
}

