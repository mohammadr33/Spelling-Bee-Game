import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class FileMenuHandler extends Project3  implements ActionListener {
   JFrame jframe;
   public FileMenuHandler(JFrame jf){
      jframe = jf;
   }
   public void actionPerformed(ActionEvent event){
      String  menuName;
      menuName = event.getActionCommand();
      if(menuName.equals("Open")){//Goes to file explorer to open file if Open button is selected
        openFile(); 
      }
      else if(menuName.equals("Quit")){//Closes GUI and ends program if Quit button is selected
         System.exit(0);
      }
   } //actionPerformed

   private void openFile(){
      JFileChooser chooser;
      int status;
      chooser = new JFileChooser( );
      chooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
      status = chooser.showOpenDialog(null);
      if(status == JFileChooser.APPROVE_OPTION){//Reads the file chosen if user chooses an approved option
         readSource(chooser.getSelectedFile());
      }
      else{//Puts up a message that open file was cancelled
         JOptionPane.showMessageDialog(null, "Open File dialog canceled");
      }
   }//openFile
  
   private void readSource (File chosenFile){
      String chosenFileName = chosenFile.getAbsolutePath();
      ArrayList<String> answers = transfer(chosenFileName);
      PuzzleGUI gui = new PuzzleGUI();
      print(gui, answers);//Goes to print function in Project 3 file
      jframe.setVisible(true); 
   }
}
