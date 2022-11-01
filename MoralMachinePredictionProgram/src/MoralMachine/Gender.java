package MoralMachine;

public class Gender {
    private DMF dmf; //a numerical value representing gender's bearing on decision to save from -1 to 2
    private VV male; //preference to save males
    private VV female; //preference to save females
    private double unknown; //preference for unknown

    public Gender(DMF dmf, VV male, VV female){
        this.dmf = dmf;
        this.male = male;
        this.female = female;
        this.unknown = ( male.getValue() + female.getValue() ) / 2;
    }

    public DMF getDMF() { //ask about naming conventions? should i leave it as getMale or can i canhge to getMAlePriority etc
        return dmf;
    }

    public VV getMaleVV() {
        return male;
    }

    public VV getFemaleVV() {
        return female;
    }

    public double getUnknownVV() {
        return unknown;
    }
}
