//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


//*******************************************************************************
// Class Definition Section

public class CheeseWorld implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public Image ShipPic;
    public Image LaserPic;
    public Image Background;
    public Image Endgame;

    public Spaceship Delta;
    public Laser bullet;
    public Laser[] laserArray;

    public int score;
    public int counter,seconds;




    //Declare the objects used in the program
    //These are things that are made up of more than one variable type


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        CheeseWorld myApp = new CheeseWorld();   //creates a new instance of the game
        new Thread(myApp).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public CheeseWorld() {



        setUpGraphics();



        //variable and objects
        //load images
        Background = Toolkit.getDefaultToolkit().getImage("Space.jpg");
        ShipPic = Toolkit.getDefaultToolkit().getImage("Rocket.png"); //load the picture
        LaserPic = Toolkit.getDefaultToolkit().getImage("Laser.png");
        Endgame = Toolkit.getDefaultToolkit().getImage("GameOver.jpg");

        bullet= new Laser(100,100,LaserPic);

        //create (construct) the objects needed for the game
        Delta = new Spaceship(700, 200, 0, 0, ShipPic) ;
        laserArray = new Laser[15];
        for(int x=0;x<laserArray.length;x++){
            laserArray[x] = new Laser(100,100*x,LaserPic);
        }





    }// CheeseWorld()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up

    public void moveThings() {
        //calls the move( ) code in the objects

        Delta.move();
        bullet.move();
        for(int x=0;x<laserArray.length;x++){
            laserArray[x].move();
        }




        }


    public void checkIntersections() {


        for(int i=0; i< laserArray.length; i++) {
            System.out.println(Delta.rec);

            if (Delta.rec.intersects(laserArray[i].rec) && laserArray[i].isCrashing == false) {

                laserArray[i].isCrashing = true;
                Delta.isAlive = false;
            }
        }
            if(Delta.isAlive==false){
                Delta.xpos =2000;
                Delta.ypos =2000;
               
            }
        }

    public int getHEIGHT() {
        return HEIGHT;
    }


    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //put your code to draw things on the screen here.
        g.drawImage(Background, 0, 0,1000, 700,null);
        g.drawImage(Delta.pic, Delta.xpos, Delta.ypos, Delta.width, Delta.height,null);
        g.drawImage(bullet.pic,bullet.xpos, bullet.ypos, bullet.width, bullet.height, null);
        for(int x=0;x<laserArray.length;x++) {
            g.drawImage(laserArray[x].pic, laserArray[x].xpos, laserArray[x].ypos, laserArray[x].width, laserArray[x].height, null);

        }
        if(Delta.xpos>1500){
            g.drawImage(Endgame, 0, 0,1000, 700,null);
        }



        //print score to screen
        g.setColor(Color.WHITE);
        g.setFont(new Font("OCR A Std", Font.BOLD, 24));
        g.drawString("Score: "+ score,10,30);
        





        g.dispose();
        bufferStrategy.show();
    }

    public void run() {

        //for the moment we will loop things forever.
        while (true) {

            moveThings();  //move all the game objects
            checkIntersections();
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
            counter++;
            if(counter>30){
               // seconds++;
                score=score+100;
                counter=0;
            }
            if(Delta.xpos>1500){
                counter--;
            }

        }
    }

    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("CheeseWorld");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.addKeyListener(this);

        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    public void keyPressed(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        System.out.println("Key Pressed " + key + " Code: " + keyCode);
        if (keyCode == 68) {
            Delta.right = true;
            Delta.dx=4;
        }
        if (keyCode == 87) {
          Delta.up = true;
          Delta.dy=4;
        }
        if(keyCode ==65){
            Delta.left = true;
            Delta.dx = -4;
        }
        if(keyCode ==83){
            Delta.down = true;
            Delta.dy = -4;
        }

    }

    public BufferStrategy getBufferStrategy() {
        return bufferStrategy;
    }


    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        System.out.println("Key Released " + key + " Code: " + keyCode);
        if (keyCode == 68) {
            Delta.right = false;
            Delta.dx=0;
        }
        if (keyCode == 87) {
           Delta.up = false;
           Delta.dy=0;

        }
        if(keyCode ==65){
            Delta.left= false;
            Delta.dx=0;
        }
        if(keyCode==83){
            Delta.down=false;
            Delta.dy=0;
        }
    }

    public void keyTyped(KeyEvent event){
        char key = event.getKeyChar();
        System.out.println(key);
    }


}