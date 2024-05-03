import javax.swing.*;
import java.awt.*;

public class PuzzleGUI extends JFrame {//Displays the GUI
  public PuzzleGUI(){
    setSize(500,400);//Sets the size of the GUI
    setLayout(new GridLayout(1,2));//Sets the amount of rows and columns the GUI will have
    setLocation(500,250);//Sets the location that the GUI will pop up in when the program runs
    setTitle("Find All Possible Matches");//Sets the title on top of the GUI;
    createFileMenu();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Creates a button on top of the GUI that lets the user exit the tab
    setVisible(true);//Makes the GUI visible
  }
  private void createFileMenu( ) {
    JMenuItem item;
    JMenuBar menuBar  = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    FileMenuHandler fmh  = new FileMenuHandler(this);
    item = new JMenuItem("Open");//Creates Open option button in the drop down menu
    item.addActionListener(fmh);
    fileMenu.add(item);
    fileMenu.addSeparator();//Creates a line to seperate the buttons
    item = new JMenuItem("Quit");//Creates Quit option button in the drop down menu
    item.addActionListener(fmh);
    fileMenu.add(item);
    setJMenuBar(menuBar);
    menuBar.add(fileMenu);
  } //createMenu
}
