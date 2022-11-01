package MoralMachine;

public enum VV { //Enum constructor to assign int values to each value in each dimension (ex. babyVV = 2 if rlly want to save)
    REALLYWANTTOSAVE(2), KINDOFWANTTOSAVE(1), INDIFFERENT(0), WILLINGTOKILL(-1);

    private int value;

    public int getValue() {
        return this.value;
    }

    private VV(int value) {
        this.value = value;
    }
}
