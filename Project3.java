import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Project3 extends IllegalArgumentException {
    public static void main(String args[]){
        PuzzleGUI gui = new PuzzleGUI();//Creates a variable for the GUI so that it pops up when it is used in the program
        UnsortedWordList unsortedWordList = new UnsortedWordList();
        SortedWordList   sortedWordList = new SortedWordList();
    }

    public static ArrayList<String> transfer(String inputFile){
        TextFileInput inFile = new TextFileInput(inputFile);//Allows our program to read the file using TexFileInput
        String line = inFile.readLine();
        ArrayList<String> solutionList = new ArrayList<>();//List for solutions
        while (line != null) {//Reads every line of our text file until the entire text file has been read
        StringTokenizer myTokens = new StringTokenizer(line, ","); // Uses a tokenizer to break apart any texts with commas(,)
            while(myTokens.hasMoreTokens()) {//Loops to find every word
                String solution = myTokens.nextToken();//Puts the word into the array
                solutionList.add(solution);
            }
            line = inFile.readLine();//goes to the next line
        }
        inFile.close();//Closes the text file
        return solutionList;//Returns the array of each word that was inside of the text file
    }

    public static void print(PuzzleGUI gui, ArrayList<String> solutions){
        int score = 0;
        int wordCount = 0;
        String Letters = solutions.getFirst();//String for our letters that the user will use 
        Container myContentPane = gui.getContentPane();//Creates a container inside of the GUI 
        TextArea myTextArea = new TextArea();
        TextArea answers = new TextArea();
        char firstLetter = Letters.charAt(0);
        myContentPane.add(myTextArea);//Puts the text area into the content pane
        myContentPane.add(answers);//Puts the text area into the content pane
        myTextArea.setEditable(false);//User can't edit the GUI
        answers.setEditable(false);
        UnsortedWordList unsortedWordList = new UnsortedWordList();
        SortedWordList   sortedWordList = new SortedWordList();

        for(int i = 0;i < solutions.size();i++){//Loops to add items from file into unsorted word list
            Word w = new Word(solutions.get(i));
            unsortedWordList.add(w);
        }

        myTextArea.append(Letters);//Add letters that user has to use onto the left panel

        while(true){//Loops until the loop is broken
        try{//Try statement to check for exceptions
            String userInput = JOptionPane.showInputDialog("Spell a word with the letters on the left. Your word must contain the first letter. Type 'STOP' to end");
            //Puts up a window that asks the user for input and puts it into a variable

            if(!userInput.matches("[a-z]+")){//Throws an exception of the users input contains a character other than a lower case letter
                throw new IllegalArgumentException("Word must contain only lower case letters. " + "Input: " + userInput);
            }
            
            if(userInput.equalsIgnoreCase("stop")){//If the user enters "stop" the loop breaks and stops asking the user
                break;
            }
            //Word w = new Word(userInput);//Sends the user input to word node to add to linked list
            boolean x = false;//Variable to keep track of the users input to see if it is on the solutions list after it passes all of the tests
            for(int j = 0; j < userInput.length();j++){//Loop to check the characters of the users input
                if(userInput.length() < 5){//Puts up a message that the users input is too short and breaks the loop
                    JOptionPane.showMessageDialog(null,"The word must be atleast 5 letters long");
                    x = true;
                    break;
                }
                if(!Letters.contains(String.valueOf(userInput.charAt(j)))){//Puts up a message if the user uses a letter that they arent supposed to and breaks the loop
                    JOptionPane.showMessageDialog(null,"Invalid input! You must use only the letters given on the left");
                    x = true;
                    break;
                }
                if(!userInput.contains(String.valueOf(Letters.charAt(0)))){//Puts up a message that says the user has to use the first letter given
                    JOptionPane.showMessageDialog(null,"Your input must contain: " + firstLetter);
                    x = true;
                    break;
                }
                
            }

            boolean allLetters = true;//Variable to check if all letters were used
            for(int i = 0;i < userInput.length();i++){//Loop to check if user used all letters
                if(!userInput.contains(String.valueOf(Letters.charAt(i)))){//Checks if the user did not use the letter
                    allLetters = false;
                    break;
                }
            }

            boolean onList = false;//Variable to track if users input is on the solution list
            WordNode word = unsortedWordList.first.getNext();
            while(word != null){//Loops through the word list
                if(userInput.equals(word.getData().getWord())){//Checks to see if users input is on the solution list
                    onList = true;
                }
                word = word.getNext();//Next solution in file
            }

            if(x == false && onList == true){//Only checks for the word if the users input is valid
                WordNode repeat = sortedWordList.first.getNext();
                while(repeat != null){//Loops through the word list
                    if(userInput.equals(repeat.getData().getWord())){//Checks if the users input has been entered before
                        break;
                    }
                    if(repeat.getNext() == null){//Increases the word count if the users input is a new word
                        wordCount++;
                    }
                    repeat = repeat.getNext();//Next solution in list   
                }
            }   

            WordNode sol = unsortedWordList.first.getNext();
            while(sol != null){//Loops through the word list
                if(userInput.equals(sol.getData().getWord())){//Checks to see if users input is on the solution list
                    onList = true;
                    Word w = new Word(userInput);//Creates new word
                    sortedWordList.add(w);//Adds word to sorted list
                }
                sol = sol.getNext();//Next solution in file
            }
        
            if (onList == false && x == false) {//Puts up a message that if users input is not the solution list
                JOptionPane.showMessageDialog(null,"That word is not on the solutions list, Try Again! Enter 'Stop' to quit");
            }


            if(allLetters == true && onList == true){//Adds 3 points if user user input contains all letters
                score += 3;
            }

            if(onList == true && allLetters == false){//Gives the user only one point if the user gave a valid input but didnt use all letters
                score++;
            }

            answers.setText("");
            
            WordNode solution = sortedWordList.first.getNext();
            while(solution != null){//Puts the users input onto the GUI on the right panel at its correct position
                  answers.append(solution.getData().getWord()+"\n");
                  solution = solution.getNext();
            }
            if(x == false) {//Increase the amount of words guessed for the word the user guesses
            answers.append("Your score is: " + score + "\n" + "Words guessed: " + (wordCount + 1));
            }
            else {//If the first word is invalid then words guessed is displayed as 0
            	answers.append("Your score is: " + score + "\n" + "Words guessed: " + (wordCount));
            }
            

            if(wordCount == solutions.size() - 2){//Checks if the user has guessed every solution
                JOptionPane.showMessageDialog(null, "Congrats! You have guessed all the solutions on the list.");
                String userAnswer = JOptionPane.showInputDialog(null,"Would you like to play again? (Enter 'yes' to play again or anything else to end)");
                if(userAnswer.equalsIgnoreCase("yes")){//Clears the text area and resets score if the user chooses to play again
                    sortedWordList.clear();//Clears users previous answers
                    answers.setText("");
                    wordCount = 0;
                    score = 0;
                    answers.append("Your score is: " + score);
                }
                else{//Breaks the loop and ends the game
                    break;
                }
            }
        }
            catch(IllegalArgumentException e){//Catches the exception when the user uses an invalid character
            	System.out.println("Error: " + e.getMessage());
                JOptionPane.showMessageDialog(null,"Word must only contain lower case letters. Try Again!");
            }
        }
    }
}