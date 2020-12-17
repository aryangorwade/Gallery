package edu.foothill;
import javax.swing.JFrame;


public class Gallery 
{
   public static void main(String[] args)
   {
	   ControlsFrame controls = new ControlsFrame("Gallery");
	   controls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       controls.setSize(500, 250);
       controls.setVisible(true);
   }
}