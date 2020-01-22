package Drones;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;

/**
 *  Class to create a canvas used by the various GUIs
 */
public class ObjectCanvas {
	int ySize = 512;
    int xSize = 512;				// size of canvas
    GraphicsContext gc;

    /**
     * constructor sets up relevant Graphics context and size of canvas
     * @param g
     * @param xcs
     */
    public ObjectCanvas(GraphicsContext g, int xcs, int ycs) {
        gc = g;
        xSize = xcs;
        ySize = ycs;
    }
    /**
     * get the size of X in the canvas
     * @return xsize
     */
    public int getXCanvasSize() {
        return xSize;
    }
    /**
     * get the size of Y in the canvas
     * @return ysize
     */
    public int getYCanvasSize() {
        return ySize;
    }

    /**
     * clear the canvas
     */
    public void clearCanvas() {
        gc.setFill(Color.BROWN);
        gc.fillRect(0, 0, xSize, ySize);
    }
    
    public void setFill (String col) {
        gc.setFill(Color.valueOf(col));
    }
    /**
     * show rectangle in current colour at x,y and its radian
     * @param x
     * @param y
     * @param rad
     */
    public void drawRect(double x, double y, double rad, String col) {
        setFill(col);									// set the fill colour
    	gc.fillRect(x-rad, y-rad, rad*2, rad*2);	// fill circle
    }
    /**
     * show the ball at position x,y , radius r in colour defined by col
     * @param x
     * @param y
     * @param rad
     * @param colour
     */
    public void drawCircle(double x, double y, double rad, String colour) {
        setFill(colour);									// set the fill colour
        gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0, 360, ArcType.ROUND);	// fill circle
    }  
    /**
     * write the string s at position x,y
     * @param x
     * @param y
     * @param s
     */
    public void drawText (double x, double y, String s) {
        gc.setTextAlign(TextAlignment.CENTER);							// set alignments
        gc.setTextBaseline(VPos.CENTER);							
        gc.setFill(Color.WHITE);										// colour the text white
        gc.fillText(s, x, y);						// print the score
    }

    /**
     * draw int i at position x,y
     * @param x
     * @param y
     * @param i
     */
    public void showInt (double x, double y, int i) {
        drawText (x, y, Integer.toString(i));
    }
}
