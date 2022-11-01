package MoralMachine;

public class Lawfulness {
    private DMF dmf; //a numerical value representing lawfulness's bearing on decision to save from -1 to 2
    private VV abiding; //priority for saving persons abiding by traffic/pedestrian laws
    private VV breaking; //priority for saving persons breaking traffic/pedestrian laws
    private VV passenger; //priority for saving passengers
    private double unknown; //VV returned if lawfulness is unknown

    public Lawfulness(DMF dmf, VV abiding, VV breaking, VV passenger) {
        this.dmf = dmf;
        this.abiding = abiding;
        this.breaking = breaking;
        this.passenger = passenger;
        this.unknown = (abiding.getValue() + breaking.getValue() + passenger.getValue() ) / 3;
    }

    public DMF getDMF(){
        return this.dmf;
    }

    public VV getAbidingVV(){
        return this.abiding;
    }

    public VV getBreakingVV(){
        return this.breaking;
    }

    public VV getPassengerVV(){
        return this.passenger;
    }

    public double getUnknownVV() {
        return unknown;
    }
}
