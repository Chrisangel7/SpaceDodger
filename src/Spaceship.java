//Cheese class 
//Bounces

import java.awt.*;
import java.awt.event.KeyEvent;

public class Spaceship {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.

    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public Rectangle rec;
    public Image pic;
    public int hits;
    public boolean right;
    public boolean up;
    public boolean left;
    public boolean down;
    public boolean isCrashing;

    // METHOD DEFINITION SECTION

    //This is a constructor that takes 3 parameters.  This allows us to specify the object's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.



    public Spaceship(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 100;
        height = 150;
        dx = dxParameter;
        dy = dyParameter;
        pic = picParameter;
        isAlive = true;
        rec = new Rectangle(xpos+20, ypos +10, width, height);


    } // constructor


    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {

            ypos = ypos-dy;

            xpos = xpos+dx;


        if (xpos > 1000 - width || xpos < 0) {
            xpos = -xpos;
        }
        if(ypos>700-height || ypos<0){
            ypos=-ypos;
        }
        rec = new Rectangle(xpos, ypos, width, height);


    }


    }

 //end of the Cheese object class  definition

