package MoralMachine;

public enum DMF { //Enum constructor assigning dimension multiplying int factors based on importance of dimension (ex. HEalth is important so it gets a 2)
    VERYIMPORTANT(3), IMPORTANT(2), SOMEWHATIMPORTANT(1), NOTIMPORTANT(0);

    private int value;

    public int getValue() {
        return this.value;
    }

    private DMF(int value) {
        this.value = value;
    }
}

