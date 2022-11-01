package MoralMachine;

public class SocialStatus {
    private DMF dmf; //a numerical value representing social status's bearing on decision to save from -1 to 2
    private VV professional; //priority for saving persons with high social status
    private VV average; //priority for saving persons with avg social status
    private VV failure; //priority for saving persons with low social status
    private double unknown;

    public SocialStatus(DMF dmf, VV professional, VV average, VV failure) {
        this.dmf = dmf;
        this.professional = professional;
        this.average = average;
        this.failure = failure;
        this.unknown = (average.getValue() + professional.getValue() + average.getValue() ) / 3;
    }

    public DMF getDMF(){
        return this.dmf;
    }

    public VV getProfessionalVV(){
        return this.professional;
    }

    public VV getAverageVV(){
        return this.average;
    }

    public VV getFailureVV(){
        return this.failure;
    }

    public double getUnknownVV() {
        return unknown;
    }
}
