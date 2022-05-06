package trivia;

public class Player {

    private final String name;

    private int position;

    private int purse;

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

    public void setPosition(int position) {
        this.position = position;
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
