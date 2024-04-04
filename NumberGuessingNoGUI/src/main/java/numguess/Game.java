package numguess;

import java.util.Scanner;
import java.util.Random;

public class Game {
    public int randomNum;
    public int guesses;
    public boolean correct; 

    //Creates a scanner object to read user input from the terminal
    Scanner termLine = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        game.prompts();
    }


    //Method for asking the user for the minimum range, maximum range and the amount of guesses they want.
    public void prompts() {
        correct = false;
        String min;
        String max;
        String guesses_ph;
        boolean valid_min = false;
        //A do while loop to ensure the user input is all integers
        do {
            System.out.println("Enter the minimum range: ");
            min = termLine.nextLine();
            valid_min = scanLine(min);
        } while(!valid_min);

        boolean valid_max = false;
        //A do while loop to ensure the user input is all integers
        do {
            System.out.println("Enter the maximum range: ");
            max = termLine.nextLine();
            valid_max = scanLine(max);
        } while(!valid_max);

        boolean valid_guesses = false;
        //A do while loop to ensure the user input is all integers
        do {
            System.out.println("Enter the amount of guesses you want: ");
            guesses_ph = termLine.nextLine();
            valid_guesses = scanLine(guesses_ph);
            if(valid_guesses) {
                guesses = Integer.parseInt(guesses_ph);
            }
        } while(!valid_guesses);
        createNum(Integer.parseInt(min), Integer.parseInt(max));
        startGame();
    }


    //Scans the user input for any non-integers. Return true if the input is all integers. Return false if user input contains a non-integer.
    public boolean scanLine(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e) {
            System.out.println(s + " is not a valid integer");
            return false;
        }
    }

    /*
     * Method to check if the user guesses is correct. It will prompt for new guesses continously if the user is still within the valid guess amount.
     * If the user wins the game, it will say correct, if not it will prompt the correct number. Regardless of right or wrong, it asks if the user wants to try again.
     */
    public void startGame() {
        while(!correct) {
            Boolean valid_guess = false;
            String guess;
            //A do while loop to ensure the user input is all integers
            do {
                System.out.println("Enter your guess: ");
                guess = termLine.nextLine();
                valid_guess = scanLine(guess);

            } while(!valid_guess);

            checkGuess(Integer.parseInt(guess));
            if(guesses == 0) {
                System.out.println("You have no more guesses, the correct number was: " + randomNum);
            }
        }
        System.out.println("Do you want to go again? Write Yes or No");
        String redo = termLine.nextLine();
        if(redo.equals("Yes")) {
            prompts();
        }
    }

    //Method to check if the guess the user made is correct. If not it will prompt whether the target integer is lower or higher than the input.
    public void checkGuess(int guess) {
        if(guess == randomNum) {
            System.out.println("Correct!");
            correct = true;
        } else if(guess < randomNum) {
            guesses--;
            System.out.println("The random number is higher, remaining guesses: " + guesses);
        } else if(guess > randomNum) {
            guesses--;
            System.out.println("The random number is lower, remaining guesses: " + guesses);
        }
    }

    //Method to create random number within the target range
    public void createNum(int min, int max) {
        Random rand = new Random();
        randomNum = rand.ints(min, max).findFirst().getAsInt();
    }
    
}