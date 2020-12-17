package edu.foothill;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.text.*;
import java.util.*;
/**
 * <H1>CS1A GUI Assignment 5 </H1> <br>
 * Quarter: Fall 2020 <br>
 * <p>
 * This program can display one of ten movie posters that can be chosen from a drop-down menu.  
 * When run, this program first displays a control panel called "Gallery" containing the drop-down 
 * list, two text fields, and the Set Size button (this window is the ControlsFrame). It also 
 * displays another window with the first poster in the drop-down list. When the user chooses
 * a different poster, the poster display will be updated.
 * </p>
 * <p>
 * The text fields, height and width, accept user input; once the Set Size button is selected, the 
 * user inputed values will be used to resize the poster. When the user selects a different poster
 * from the drop-down list, the poster size will default to the original size. 
 * </p>
 * 
 *  @author Aryan Gorwade
 *  @author <partner prefers anonymity>
 */


public class ControlsFrame extends JFrame
{
    private int MAX_NUMBER_OF_POSTERS = 10; 
    private int selectedMoviePoster = 0;
    
    // Initialize all the controls in the JFrame
    JLabel chooseInstructions;
    JButton setSizeButton;
    JLabel moviePoster;
    JLabel widthLabel;
    JTextField width;
    JLabel heightLabel;
    JTextField height;
    JPanel panelMain;

    private final String[] MOVIE_POSTERS =
    { "Tron", "Matrix", "Endgame", "Crash", "Wonder Woman", "Spider-Man 3", "Casablanca", "Black Panther", "Brave",
            "Civil War" };

    private final String[] POSTER_PATHS = new String[MAX_NUMBER_OF_POSTERS];
    {
        POSTER_PATHS[0] = "4859886671_cef0598bf3_b.jpg";
        POSTER_PATHS[1] = "The Matrix.jpg";
        POSTER_PATHS[2] = "Endgame.jpg";
        POSTER_PATHS[3] = "Crash.jpg";
        POSTER_PATHS[4] = "Wonder Woman.jpg";
        POSTER_PATHS[5] = "Spider Man 3.jpg";
        POSTER_PATHS[6] = "Casablanca.jpg";
        POSTER_PATHS[7] = "Black Panther.jpg";
        POSTER_PATHS[8] = "Brave.jpg";
        POSTER_PATHS[9] = "Captain America Civil War.jpg";

    }

    JFrame imageDisplay = new JFrame(); // Creates the JFrame that will display the poster. 
    ImageDisplay display = new ImageDisplay();

    /**
     * This method updates the poster display window according to the item in the drop down menu the user has selected.
     * It repaints the display and adds the poster so that movie posters are not superimposed. 
     * @param moviePosterNumber index corresponding to the selected movie poster
     */
    public void updatePosterDisplay(int moviePosterNumber)
    {
        imageDisplay.repaint();
        imageDisplay.add(display);
        display.setImagePath(POSTER_PATHS[moviePosterNumber]);
        imageDisplay.setSize(1000, 2000);
        imageDisplay.setVisible(true);

    }

    /**
     * This method organizes the panel on which all the controls (poster selection, height and width 
     * text fields, set size button,and instructions) are displayed. 
     * It also includes listeners that keep track of the user inputs, and the actions triggered. 
     * @param title The title of the ControlsFrame
     * @throws HeadlessException
     */
    public ControlsFrame(String title) throws HeadlessException
    {
        // pass the title up to the JFrame constructor
        super("Gallery");

        // set up controls needed in the frame
        chooseInstructions = new JLabel("Choose a poster from the dropdown.");
        setSizeButton = new JButton("Set Size");
        widthLabel = new JLabel("Width");
        width = new JTextField(4);
        heightLabel = new JLabel("Height");
        height = new JTextField(4);

        // use border layout and put one above component in frame
        panelMain = new JPanel(new GridLayout(1, 1, 10, 10));
        setLayout(new BorderLayout(20, 10));
        add(chooseInstructions, BorderLayout.NORTH);
        this.setSize(300, 200);
        setVisible(true);

        // create one sub-panel to put into panelMain
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        add(panelBottom);
        panelBottom.setBorder(new TitledBorder("Movie Poster"));

        // put a combo box in panelBottom
        JComboBox cmboMOVIE_POSTERS = new JComboBox(MOVIE_POSTERS);
        panelBottom.add(cmboMOVIE_POSTERS);
        panelBottom.add(widthLabel);
        panelBottom.add(width);
        panelBottom.add(heightLabel);
        panelBottom.add(height);
        panelBottom.add(setSizeButton);

        if (cmboMOVIE_POSTERS.getSelectedIndex() == selectedMoviePoster)
        {
            updatePosterDisplay(selectedMoviePoster);
        }

        // ItemListener for cmboMOVIE_POSTERS
        class MoviePosterListener implements ItemListener
        {
            public void itemStateChanged(ItemEvent e)
            {
                selectedMoviePoster = cmboMOVIE_POSTERS.getSelectedIndex();
                updatePosterDisplay(selectedMoviePoster);
            }
        }

        // register the item listener
        cmboMOVIE_POSTERS.addItemListener(new MoviePosterListener());

        class changeSizeListener implements ActionListener
        {
            // event handler for JButton
            public void actionPerformed(ActionEvent e)
            {
                String userWidth;
                String userHeight;
                
                // Get user input from the text areas
                userWidth = width.getText();
                userHeight = height.getText();

                // Convert user input to integers
                int userWidthInt = Integer.parseInt(userWidth);
                int userHeightInt = Integer.parseInt(userHeight);

                imageDisplay.setSize(userWidthInt, userHeightInt); // resize the movie poster according to user input
            }
        }

        // register the button action listener
        setSizeButton.addActionListener(new changeSizeListener());
    }

}
