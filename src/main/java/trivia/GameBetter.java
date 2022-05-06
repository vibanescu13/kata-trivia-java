package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {

    private static final int MAX_ROUNDS = 12;
    private static final int MAX_QUESTIONS = 50;
    private static final int MAX_PLAYERS = 6;
    private static final int WINNING_VALUE = 6;



    private ArrayList<Player> players = new ArrayList<>();

    private int[] places = new int[MAX_PLAYERS];
    private int[] purses = new int[MAX_PLAYERS];

    private List<String> popQuestions = new LinkedList<>();
    private List<String> scienceQuestions = new LinkedList<>();
    private List<String> sportsQuestions = new LinkedList<>();
    private List<String> rockQuestions = new LinkedList<>();

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public GameBetter() {
        for (int i = 0; i < MAX_QUESTIONS; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add(("Science Question " + i));
            sportsQuestions.add(("Sports Question " + i));
            rockQuestions.add("Rock Question " + i);
        }
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer).getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true; // TO DO: probably the user should get out of penalty box.
                // players.get(currentPlayer).exitFromPenaltyBox();

                System.out.println(players.get(currentPlayer).getName() + " is getting out of the penalty box");
                newPosition(roll);
            } else {
                System.out.println(players.get(currentPlayer).getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            newPosition(roll);
        }

    }

    private void newPosition(int roll) {
        changePlayerPosition(roll);

        System.out.println(players.get(currentPlayer).getName()
                + "'s new location is "
                + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void changePlayerPosition(int roll) {
        places[currentPlayer] += roll;
        if (places[currentPlayer] >= MAX_ROUNDS) {
            places[currentPlayer] -= MAX_ROUNDS;
        }
    }

    // Used a new enum Class for the Question Categories
    private void askQuestion() {
        if (Category.POP.equals(currentCategory()))
            System.out.println(popQuestions.remove(0));
        if (Category.SCIENCE.equals(currentCategory()))
            System.out.println(scienceQuestions.remove(0));
        if (Category.SPORTS.equals(currentCategory()))
            System.out.println(sportsQuestions.remove(0));
        if (Category.ROCK.equals(currentCategory()))
            System.out.println(rockQuestions.remove(0));
    }

    // Reduced multiple if statements using the Modulo Operator
    private Category currentCategory() {
        switch (places[currentPlayer] % 4){
            case 0: return Category.POP;
            case 1: return Category.SCIENCE;
            case 2: return Category.SPORTS;
            default: return Category.ROCK;
        }

    }

    public boolean wasCorrectlyAnswered() {
        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                return returnWinner();
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }
        } else {
            return returnWinner();
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
        players.get(currentPlayer).enterPenaltyBox();

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return purses[currentPlayer] != WINNING_VALUE;
    }

    private boolean returnWinner() {
        System.out.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer).getName()
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;

        return winner;
    }
}
