import java.awt.*;

public class Section {

    private int x1;
    private Graphics2D g;
    private int y1;
    private int x2;
    private int y2;
    private Color color;
    private Color color1;



    public Section(final int x1, final int y1, final int x2, final int y2, Color color, Color color1, final Graphics2D g) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.color1 = color1;
        this.g = g;
    }

    public Color getColor() {
        return color;
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

    public void setColor(Color color) {
        this.color = color;
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



    private float floatPart(float x) {
        int tmp = (int) x;
        return x - tmp;
    }
    private void drawPixel(int x, int y){
        g.drawLine(x, y, x, y);
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
        if (dx == 0){
            for (int i = y1; i <= y2; i ++){
                Color colorItog = interpolate(x1, i , x1, x2, y1, y2, color, color1);
                System.out.println(colorItog);
                g.setColor(colorItog);
                drawPixel(x1, i);
            }
        }
        if (dy == 0){
            for (int i = x1; i <= x2; i ++){
                Color colorItog = interpolate(i, y1 , x1, x2, y1, y2, color, color1);
                System.out.println(colorItog);
                g.setColor(colorItog);
                drawPixel(i, y1);
            }
        }
        /*if (dx == 0 || dy == 0) {
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);
            return;
        }*/
        float gradient = 0;
        if (dx > dy) {
            gradient = (float) dy / dx;
            float intery = y1 + gradient;
            g.setColor(color);
            drawPixel(x1, y1);
            for (int x = x1; x < x2; ++x) {
                Color colorItog = interpolate(x, (int)intery + 1 , x1, x2, y1, y2, color, color1);
                g.setColor(new Color(colorItog.getRed(), colorItog.getGreen(), colorItog.getBlue(), (int) (255 - floatPart(intery) * 255))); //Меняем яркость
                drawPixel(x, (int)intery);
                g.setColor(new Color(colorItog.getRed(), colorItog.getGreen(), colorItog.getBlue(), (int) (floatPart(intery) * 255)));
                drawPixel(x, (int)intery + 1);
                intery += gradient;
            }
            g.setColor(color1);
            drawPixel(x2, y2);
        } else {
            gradient = (float) dx / dy;
            float interx = x1 + gradient;
            g.setColor(color);
            drawPixel(x1, y1);
            for (int y = y1; y < y2; ++y) {
                Color colorItog = interpolate((int)interx + 1, y , x1, x2, y1, y2, color, color1);
                g.setColor(new Color(colorItog.getRed(), colorItog.getGreen(), colorItog.getBlue(), (int) (255 - floatPart(interx) * 255)));
                drawPixel((int)interx, y);
                g.setColor(new Color(colorItog.getRed(), colorItog.getGreen(), colorItog.getBlue(), (int) (floatPart(interx) * 255)));
                drawPixel((int)interx + 1, y);
                interx += gradient;
            }
            g.setColor(color1);
            drawPixel(x2, y2);
        }

    }

    public static Color interpolate(int x, int y, int x1, int x2, int y1, int y2, Color color, Color color1){
        double d1 = Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2));
        double d2 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double t = d1 / d2;

        double red = (1 - t) * color.getRed() + t * color1.getRed();
        double green =(1 - t) * color.getGreen() + t * color1.getGreen();
        double blue = (1 - t) * color.getBlue() + t * color1.getBlue();

        return new Color((int)(red), (int)(green),(int)(blue), 1);
    }


}

