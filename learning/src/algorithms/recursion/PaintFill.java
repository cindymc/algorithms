package algorithms.recursion;

/**
 * The paint starts from one pixel and moves out.  All adjoining pizels with the same original color get the color fill
 * with the new color.  The fill moves out until it reaches a boundary that has a different color.
 *
 * Base case:
 *  - the current pixel doesn't have the same original color, so it's a border (stop)
 *  - the current pixel is beyond the screen boundaries
 *
 * Recursive case:
 *  - move outward from the start pixel, coloring individual pixels
 *
 * Complexity is O(N) because we have to visit every cell
 *
 * Created by cindymc on 8/4/16.
 */
public class PaintFill
{
    public static void main(String [] args)
    {
        Pixel[][] screen = new Pixel[3][3];
        screen[0][0] = new Pixel("GREEN");
        screen[0][1] = new Pixel("RED");
        screen[0][2] = new Pixel("RED");
        screen[1][0] = new Pixel("GREEN");
        screen[1][1] = new Pixel("RED");
        screen[1][2] = new Pixel("RED");
        screen[2][0] = new Pixel("GREEN");
        screen[2][1] = new Pixel("RED");
        screen[2][2] = new Pixel("GREEN");

        paintFill(screen, 1,1, "RED", "BLUE");

        for (int i=0; i<screen[0].length; i++)
        {
            for (int j=0; j<screen.length; j++)
            {
                System.err.println(i + ", " + j + ": " + screen[i][j].getColor());
            }
        }
    }

    static void paintFill(Pixel[][] displayScreen, int x, int y, String originalColor, String newColor)
    {
        // Don't go beyond the screen
        if (x<0 || y<0 || x >= displayScreen[0].length || y >= displayScreen.length) {return;}

        // If current cell is of a different color, we've reached a boundary.
        // TODO: why is this not [x][y] ?
        if (displayScreen[x][y].getColor() != originalColor) {return;}

        // OK, update color of current cell
        if (displayScreen[x][y].getColor() == originalColor)
        {
            displayScreen[x][y].setColor(newColor);

            // Pixel on left
            paintFill(displayScreen, x-1, y, originalColor, newColor);

            // On right
            paintFill(displayScreen, x+1, y, originalColor, newColor);

            // On top and bottom
            paintFill(displayScreen, x, y+1, originalColor, newColor);
            paintFill(displayScreen, x, y-1, originalColor, newColor);
        }


    }


    private static class Pixel
    {
        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        private String color;

        public Pixel(String color) {
            this.color = color;
        }
    }
}
