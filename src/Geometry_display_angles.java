/* 
 * Bhattarai, Kanchan 
 * EECS 1510 - Intro to Object Oriented Programming Project 3
 * Dr. Joseph Hobbs
 * 
 * This program uses JavaFX to create a triangle that shows you angle while dragging the vertex.
 *
 * 
 */

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Geometry_display_angles extends Application
{
	//Everything on JavaFx Starts from here
    @Override
    public void start(Stage primaryStage)
    {
    	//Creating a Pane and Scene
    	Pane layout = new Pane();
    	Scene scene = new Scene(layout,800,500);  	

        //Creating three circles of random color at the vertex of the triangle  
        Circle[] circle = {new Circle(((int)(10+Math.random()*780)), ((int)(10+Math.random()*480)), 7), new Circle(((int)(10+Math.random()*780)), ((int)(10+Math.random()*480)), 7), new Circle(((int)(10+Math.random()*780)), ((int)(10+Math.random()*480)), 7)};
        //Creating three lines to form the triangle
        Line[] lines = new Line[3];
        //Creating an array to store the angle
        Text[] angle = new Text[3];
        
        //Placing the center of the circle and vertex of the triangle on the same point and assigning random color to the circle
        for (int i = 0; i < lines.length; i++) {
            int j = (i + 1 >= circle.length) ? 0 : i + 1;
            lines[i] = new Line(
            		circle[i].getCenterX(), circle[i].getCenterY(),
                    circle[j].getCenterX(), circle[j].getCenterY());
              		circle[i].setFill(Color.rgb((int)(Math.random()*250), (int)(Math.random()*250), (int)(Math.random()*250)));

          }

       // Activity during dragging the circle  
        for (int i = 0; i < circle.length; i++) {
              angle[i] = new Text();
              final int j = i; 
              circle[i].setOnMouseDragged(e-> {
                  circle[j].setCenterX(e.getX());
                  circle[j].setCenterY(e.getY());
                  Changeline(lines,circle,angle);
                  circle[j].setFill(Color.rgb((int)(Math.random()*250), (int)(Math.random()*250), (int)(Math.random()*250)));
              });
          }
  
        //Adding circle, text and lines in the pane
        layout.getChildren().addAll(circle[0], circle[1], circle[2],lines[0], lines[1], lines[2], angle[0], angle[1], angle[2]);
    	
        //Setting scene to stage and showing scene 
        primaryStage.setScene(scene);
        primaryStage.setTitle("Geometry:display angles");
        primaryStage.show();
        //Displaying angles at the start of the program 
        Changeline(lines,circle,angle); 
    }	
    //Properties during Dragging
    private void Changeline(Line[] lines, Circle[] circle, Text[] texts)
	{
    	//Placing the vertex of the triangle and center of the circle at same point during dragging
    	for (int i = 0; i < lines.length; i++) 
    	{
            int j = (i + 1 >= circle.length) ? 0 : i + 1;
            lines[i].setStartX(circle[i].getCenterX());
            lines[i].setStartY(circle[i].getCenterY());
            lines[i].setEndX(circle[j].getCenterX());
            lines[i].setEndY(circle[j].getCenterY());
            //Assigning random color to the line during dragging
            lines[i].setStroke(Color.rgb((int)(Math.random()*250),(int)(Math.random()*250),(int)(Math.random()*250)));
    	}
    	//Array to store the length of line during dragging
    	double[] length = new double[3];
    	//Array to store the angle of line during dragging
    	double[] angles = new double[3];
    	
    	//Calculating the length of the line and assigning the length to the length array
    	for (int i = 0; i < lines.length; i++)
    	{
            int j = (i + 1 >= circle.length) ? 0 : i + 1;
            Point2D A = new Point2D(circle[i].getCenterX(),circle[i].getCenterY());
            Point2D B = new Point2D(circle[j].getCenterX(),circle[j].getCenterY());
            length[i]= (double) A.distance(B);          
    	}
    	//Assigning length the variables a, b, and c for the simplicity of calculation
    	double a = length[1];
    	double b = length[2];
    	double c = length[0];
    	//Calculating the angles using cosine angle formula
        angles[0] = Math.toDegrees(Math.acos((Math.pow(a, 2) - Math.pow(b, 2) - Math.pow(c, 2)) / (-2 * b * c)));
        angles[1] = Math.toDegrees(Math.acos((Math.pow(b, 2) - Math.pow(a, 2) - Math.pow(c, 2)) / (-2 * a * c)));
        angles[2] = Math.toDegrees(Math.acos((Math.pow(c, 2) - Math.pow(b, 2) - Math.pow(a, 2)) / (-2 * a * b)));
        //Placing the angles in the vertex
        for (int i = 0; i < 3; i++) 
        {
            texts[i].setX(circle[i].getCenterX()+5);
            texts[i].setY(circle[i].getCenterY() - 10);
            texts[i].setText(String.format("%.2f", angles[i]));
        }
	}
    //Run the application
    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
