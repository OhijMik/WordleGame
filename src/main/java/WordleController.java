import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * The wordleController class runs from the start of the program
 * 
 * @author (Jiho Kim) 
 * @version (1.0)
 */
public class WordleController {
    @FXML
    TextField guessWord;    
    @FXML
    Label errorLabel = new Label();    
    @FXML
    Label endScreen = new Label();    
    @FXML
    Button restartButton = new Button();    
    @FXML
    Button hint = new Button();
    
    int round = 1;
    Boolean newGame = true;
    String[] guessString;
    char[] correctChar;
    boolean hintToggle = false;
    Random randNum = new Random();
    int winCondition = 0;
    String correctWord;
    boolean error = false;
    boolean[] rightGuess = {false, false, false, false, false};

    // Label of row A
    @FXML
    private Label[] boxA = new Label[5];

    @FXML
    private Label boxA1 = new Label();
    @FXML
    private Label boxA2 = new Label();
    @FXML
    private Label boxA3 = new Label();
    @FXML
    private Label boxA4 = new Label();
    @FXML
    private Label boxA5 = new Label();

    // Label of row B
    @FXML
    private Label[] boxB = new Label[5];

    @FXML
    private Label boxB1 = new Label();
    @FXML
    private Label boxB2 = new Label();
    @FXML
    private Label boxB3 = new Label();
    @FXML
    private Label boxB4 = new Label();
    @FXML
    private Label boxB5 = new Label();

    // Label of row C
    @FXML
    private Label[] boxC = new Label[5];

    @FXML
    private Label boxC1 = new Label();
    @FXML
    private Label boxC2 = new Label();
    @FXML
    private Label boxC3 = new Label();
    @FXML
    private Label boxC4 = new Label();
    @FXML
    private Label boxC5 = new Label();

    // Label of row D
    @FXML
    private Label[] boxD = new Label[5];

    @FXML
    private Label boxD1 = new Label();
    @FXML
    private Label boxD2 = new Label();
    @FXML
    private Label boxD3 = new Label();
    @FXML
    private Label boxD4 = new Label();
    @FXML
    private Label boxD5 = new Label();

    // Label of row E
    @FXML
    private Label[] boxE = new Label[5];

    @FXML
    private Label boxE1 = new Label();
    @FXML
    private Label boxE2 = new Label();
    @FXML
    private Label boxE3 = new Label();
    @FXML
    private Label boxE4 = new Label();
    @FXML
    private Label boxE5 = new Label();

    // Label of row F
    @FXML
    private Label[] boxF = new Label[5];

    @FXML
    private Label boxF1 = new Label();
    @FXML
    private Label boxF2 = new Label();
    @FXML
    private Label boxF3 = new Label();
    @FXML
    private Label boxF4 = new Label();
    @FXML
    private Label boxF5 = new Label();


    /**
     * The checkGuess class runs when the check button is pressed and checks the guess word
     * 
     * @author (Jiho Kim) 
     * @version (1.0)
     */
    @FXML
    private void checkGuess() throws IOException  {
        // Only runs at the start of a game
        if (newGame == true) {
            // Reading the text file with the words
            Scanner s = new Scanner(new File("C:/Users/jihok/Documents/GitHub/javafx-assignment-OhijMik/bf89ae7d52a3efb36cda9ea2c8e0612c-afb450ffe22ea3e7f979fb49ecbb0d002cdc03cb/wordleWordListClean.txt"));
            String[] words = new String[14777];
            int count = 0;
            // While the file has more lines, put the words in the words[]
            while (s.hasNextLine()){
                words[count] = (s.nextLine());
                count++;
            }
            s.close();

            // Sets the correct word
            correctWord = (words[randNum.nextInt(words.length)]);
            System.out.println(correctWord);
        }

        newGame = false;

        
        // Makes the guess a string
        String guess = guessWord.getText().toLowerCase();
        char[] guessChar = guess.toCharArray();

        correctChar = correctWord.toCharArray();

        winCondition = 0;

        // Checks if the guess doesn't contain numbers
        for (int i = 0 ; i < 5 ; i++) {
            if (guessChar[i] == '1' || guessChar[i] == '2' || guessChar[i] == '3' || guessChar[i] == '4' || guessChar[i] == '5'
             || guessChar[i] == '6' || guessChar[i] == '7' || guessChar[i] == '8' || guessChar[i] == '9' || guessChar[i] == '0') {
                error = true;
            }
        }
        // Checks if the guess is 5 letters
        if (guess.length() != 5) {
            error = true;
        }

        // Checks if there are any errors
        if (error == false) {
            errorLabel.setText("");

            // Runs the class setword to put the letters on the boxes
            setWord(guessChar);

            // fills the boxes with grey if the none of the words are in the correct word
            for (int i = 0 ; i < 5 ; i++) {
                if (round == 1) {
                    boxA[i].setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
                }
                else if (round == 2) {
                    boxB[i].setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
                }
                else if (round == 3) {
                    boxC[i].setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
                }
                else if (round == 4) {
                    boxD[i].setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
                }
                else if (round == 5) {
                    boxE[i].setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
                }
                else if (round == 6) {
                    boxF[i].setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
                }
            }
            // fills the boxes with yellow if the words are in the word but not in the right place
            for (int i = 0 ; i < 5 ; i++) {
                for (int x = 0 ; x < 5 ; x++) {
                    if (guessChar[i] == correctChar[x]) {
                        if (round == 1) {
                            boxA[i].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
                        }
                        else if (round == 2) {
                            boxB[i].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
                        }
                        else if (round == 3) {
                            boxC[i].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
                        }
                        else if (round == 4) {
                            boxD[i].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
                        }
                        else if (round == 5) {
                            boxE[i].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
                        }
                        else if (round == 6) {
                            boxF[i].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
                        }
                        
                    }
                }
            }
            // fills the boxes with green if the words are in the right place
            for (int i = 0 ; i < 5 ; i++) {
                if (guessChar[i] == correctChar[i]) {
                    rightGuess[i] = true;
                    if (round == 1) {
                        boxA[i].setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
                    }
                    else if (round == 2) {
                        boxB[i].setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
                    }
                    else if (round == 3) {
                        boxC[i].setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
                    }
                    else if (round == 4) {
                        boxD[i].setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
                    }
                    else if (round == 5) {
                        boxE[i].setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
                    }
                    else if (round == 6) {
                        boxF[i].setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
                    }
                    
                }
            }
            // Checks if the word is right
            for (int i = 0 ; i < 5 ; i++) {
                if (guessChar[i] == correctChar[i]) {
                    winCondition++;
                }
            }

            // Shows the hint button if it is after round 3
            round += 1;
            if (round > 3) {
                hint.toFront();
            }

            guessWord.setText("");

            // Win condition
            if (winCondition >= 5) {
                endScreen.setFont(Font.font("regular", FontWeight.BOLD, FontPosture.REGULAR, 44));
                endScreen.setText("You Won!");
                endScreen.toFront();
                endScreen.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                hint.toBack();

                restartButton.toFront();
            }

            // Lose condition
            if (round > 6) {
                endScreen.setFont(Font.font("regular", FontWeight.BOLD, FontPosture.REGULAR, 16));
                endScreen.setText("The correct word was " + correctWord);
                endScreen.toFront();
                endScreen.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                hint.toBack();

                restartButton.toFront();
            }
        }
        // If there is an error
        else {
            guessWord.setText("");
            errorLabel.setText("Please enter a 5 letter word not containing numbers");
            error = false;
        }

    }

    /**
     * The restart class runs when the restart button is pressed and restarts the whole screen for a new game
     * 
     * @author (Jiho Kim) 
     * @version (1.0)
     */
    @FXML
    private void restart() throws IOException  {
        endScreen.setText("");
        endScreen.toBack();
        endScreen.setBackground(new Background(new BackgroundFill(null, null, null)));

        restartButton.toBack();
        winCondition = 0;
        round = 1;
        newGame = true;
        hint.toBack();
        error = false;
        

        // Sets all the boxes to remove the letters and reset the color
        for (int i = 0 ; i < 5 ; i++) {
            rightGuess[i] = false;
            boxA[i].setText("");
            boxA[i].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            boxB[i].setText("");
            boxB[i].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            boxC[i].setText("");
            boxC[i].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            boxD[i].setText("");
            boxD[i].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            boxE[i].setText("");
            boxE[i].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            boxF[i].setText("");
            boxF[i].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
    }

    /**
     * The giveHint class runs when the hint button is pressed and shows the hint to the player
     * 
     * @author (Jiho Kim) 
     * @version (1.0)
     */
    @FXML
    private void giveHint() throws IOException  {

        // Toggling the hint button
        if (hintToggle == false) {
            hintToggle = true;
        } else {
            hintToggle = false;
        }

        // If the hint button is pressed
        if (hintToggle == true) {
            // Choosing a random number and picking which hint it shows
            if (rightGuess[0] == false) {
                endScreen.setText("The first letter of the word is " + correctChar[0]);
            } else if (rightGuess[1] == false) {
                endScreen.setText("The second letter of the word is " + correctChar[1]);
            } else if (rightGuess[2] == false) {
                endScreen.setText("The third letter of the word is " + correctChar[2]);
            } else if (rightGuess[3] == false) {
                endScreen.setText("The fourth letter of the word is " + correctChar[3]);
            } else if (rightGuess[4] == false) {
                endScreen.setText("The last letter of the word is " + correctChar[4]);
            }
            endScreen.setFont(Font.font("regular", FontWeight.BOLD, FontPosture.REGULAR, 13));
            endScreen.toFront();
            endScreen.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            
            hint.toFront();
            hint.setText("Back");
        }
        // When back button is pressed
        else {
            endScreen.setText("");
            endScreen.toBack();
            endScreen.setBackground(new Background(new BackgroundFill(null, null, null)));

            hint.setText("Hint");
        }
    }
        

    /**
     * The setWord class puts each letters of the guess word into the box
     * 
     * @author (Jiho Kim) 
     * @version (1.0)
     */
    @FXML
    private void setWord(char[] guessChar) throws IOException  {
        boxA[0] = boxA1;
        boxA[1] = boxA2;
        boxA[2] = boxA3;
        boxA[3] = boxA4;
        boxA[4] = boxA5;
        boxB[0] = boxB1;
        boxB[1] = boxB2;
        boxB[2] = boxB3;
        boxB[3] = boxB4;
        boxB[4] = boxB5;
        boxC[0] = boxC1;
        boxC[1] = boxC2;
        boxC[2] = boxC3;
        boxC[3] = boxC4;
        boxC[4] = boxC5;
        boxD[0] = boxD1;
        boxD[1] = boxD2;
        boxD[2] = boxD3;
        boxD[3] = boxD4;
        boxD[4] = boxD5;
        boxE[0] = boxE1;
        boxE[1] = boxE2;
        boxE[2] = boxE3;
        boxE[3] = boxE4;
        boxE[4] = boxE5;
        boxF[0] = boxF1;
        boxF[1] = boxF2;
        boxF[2] = boxF3;
        boxF[3] = boxF4;
        boxF[4] = boxF5;

        //Putting each letters inside of the boxes
        for (int i = 0 ; i < 5 ; i++) {
            if (round == 1) {
                boxA[i].setText(String.valueOf(guessChar[i]));
            }
            else if (round == 2) {
                boxB[i].setText(String.valueOf(guessChar[i]));
            }
            else if (round == 3) {
                boxC[i].setText(String.valueOf(guessChar[i]));
            }
            else if (round == 4) {
                boxD[i].setText(String.valueOf(guessChar[i]));
            }
            else if (round == 5) {
                boxE[i].setText(String.valueOf(guessChar[i]));
            }
            else if (round == 6) {
                boxF[i].setText(String.valueOf(guessChar[i]));
            }
        }
        
        
        
    }

}

