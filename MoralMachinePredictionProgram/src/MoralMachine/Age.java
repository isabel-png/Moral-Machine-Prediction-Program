package MoralMachine;

public class Age {

    private DMF dmf; //numerical value representing age's bearing on decision to save on a scale of -1 to 2
    private VV elderlyVal; //numerical value representing priority for saving elderly people
    private VV adultVal; //numerical value representing priority for saving adults
    private VV childVal; //numerical value representing priority for saving children
    private VV babyVal; //numerical value representing priority for saving babies
    private double unknown; //the VV is the age is unknown

    public Age(DMF dmf, VV babyVal, VV childVal, VV adultVal, VV elderlyVal ){
        this.elderlyVal = elderlyVal;
        this.adultVal = adultVal;
        this.childVal = childVal;
        this.babyVal = babyVal;
        this.dmf = dmf ;
        this.unknown = (elderlyVal.getValue() + adultVal.getValue() + childVal.getValue() + babyVal.getValue()) / 4;
    }

    public VV getElderlyVV(){
        return this.elderlyVal;
    }

    public VV getAdultVV(){
        return this.adultVal;
    }

    public VV getChildVV(){
        return this.childVal;
    }

    public VV getBabyVV(){
        return this.babyVal;
    }

    public DMF getDMF(){
        return this.dmf;
    }

    public double getUnknownVV() {
        return unknown;
    }

    //private double calculateVVxDMF()

}

