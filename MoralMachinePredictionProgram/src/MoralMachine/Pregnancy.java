package MoralMachine;

public class Pregnancy {
    private DMF dmf; //numerical value representing pregnancy's bearing on decision to save on a scale of -1 to 2
    private VV pregnant; //numerical value representing priority for saving pregnant people
    private VV notPregnant; //numerical value representing priority for non-pregnant
    private double unknown;

    public Pregnancy(DMF dmf, VV notPregnant, VV pregnant){
        this.dmf = dmf;
        this.pregnant = pregnant;
        this.notPregnant = notPregnant;
        this.unknown = (pregnant.getValue() + notPregnant.getValue() ) / 2;
    }

    public DMF getDMF() { //ask about naming conventions? should i leave it as getMale or can i change to getMAlePriority etc
        return dmf;
    }

    public VV getPregnantVV() {
        return pregnant;
    }

    public VV getNotPregnantVV() {
        return notPregnant;
    }

    public double getUnknownVV() {
        return unknown;
    }
}
