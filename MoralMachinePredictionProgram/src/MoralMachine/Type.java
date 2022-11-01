package MoralMachine;
//should i just have used an interface/abstract class for the DMFs / VVs (dimension multiplying factor / value)?
//is this really the best way?

public class Type {
    private DMF dmf; //a numerical value representing type's bearing on decision to save from -1 to 2
    private VV human; //priority for saving humans
    private VV animal; //priority for saving animals
    private double unknown;

    public Type(DMF dmf, VV human, VV animal) {
        this.dmf = dmf;
        this.human = human;
        this.animal = animal;
        this.unknown = (human.getValue() + animal.getValue() ) / 2;
    }

    public DMF getDMF(){
        return this.dmf;
    }

    public VV getHumanVV(){
        return this.human;
    }

    public VV getAnimalVV(){
        return this.animal;
    }

    public double getUnknownVV() {
        return unknown;
    }
}