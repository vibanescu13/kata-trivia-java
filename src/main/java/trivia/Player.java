package trivia;

public class Player {

    private final String name;

    private int position;

    private int purse;

//    private boolean isInPenaltyBox;

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
}
