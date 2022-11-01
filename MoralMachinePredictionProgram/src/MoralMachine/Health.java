package MoralMachine;

public class Health {
    private DMF dmf; //a numerical value representing health's bearing on decision to save from -1 to 2
    private VV athlete; //priority for saving persons who are professional athletes/very physically fit
    private VV average; //priority for saving persons with average health/fitness
    private VV obese; //priority for saving overweight/physically unhealthy persons
    private double unknown;

    public Health(DMF dmf, VV athlete, VV average, VV obese) {
        this.dmf = dmf;
        this.athlete = athlete;
        this.average = average; //can i reuse average here, or should i change it to normal or smthn?
        this.obese = obese;
        this.unknown = (athlete.getValue() + average.getValue() + obese.getValue() ) / 3;
    }

    public DMF getDMF(){
        return this.dmf;
    }

    public VV getAthleteVV(){
        return this.athlete;
    }

    public VV getAverageVV(){
        return this.average;
    }

    public VV getObeseVV(){
        return this.obese;
    }

    public double getUnknownVV() {
        return unknown;
    }
}
