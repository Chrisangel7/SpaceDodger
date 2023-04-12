
import java.awt.*;

public class Laser {

    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public int dx;
    public int dy;
    public boolean isAlive;
    public Image pic;
    public Rectangle rec;
    public boolean isCrashing;



    public Laser(int xParameter, int yParameter, Image picParameter){
        xpos = xParameter;
        ypos = yParameter;
        isAlive=true;
        width=50;
        height=35;
        pic= picParameter;

        if((int)(Math.random()*11)<5) {
            dx = -dx;
        }

        dy=(int)(Math.random()*7+1);
        dx=(int)(Math.random()*7+1);

        if((int)(Math.random()*11)<5){
            dy=-dy;
        }

        rec= new Rectangle (xpos, ypos, width, height);

    }

    public void move(){
        xpos=xpos+dx;
        ypos =ypos+dy;

        if(xpos>1000-width){
            dx=-dx;
        }
        if (ypos>700-height){
            dy=-dy;
        }
        if(xpos<0){
            dx=-dx;
        }
        if(ypos<0){
            dy=-dy;
        }
        rec= new Rectangle (xpos, ypos, width, height);

    }

}


