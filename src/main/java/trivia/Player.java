package trivia;

public class Player {

    private static final int MAX_ROUNDS = 12;
    public static final int WINNING_VALUE = 6;

    private final String name;

    private int position;

    private int purse;

    public int getPurse() {
        return purse;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public boolean didPlayerWin() {
        return purse != WINNING_VALUE;
    }

    private boolean inPenaltyBox;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int roll) {
        position += roll;
        if (position >= MAX_ROUNDS) {
            position -= MAX_ROUNDS;
        }
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void enterPenaltyBox() {
        inPenaltyBox = true;
    }

    public void exitFromPenaltyBox() {
        inPenaltyBox = false;
    }
}
