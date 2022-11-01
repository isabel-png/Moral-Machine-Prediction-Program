package MoralMachine;

import java.lang.*;

//SWITCH TO ARRAY OR READ IN DATA FROM THE ENCODED TXT FILE INTO CATEGORIES BASED ON THEIR PREDECESSORS

//will go through and format properly later (resize line length)
//go over exactly what the exception are so you can write code to exclude them from #ofapplicabledims
//could replace dimVals object wth an array, might be neater? what are the benfits of using an object over an array? easier to write to file?
//TODO:
public class Person {
    //since its reading it in from a encoded text file in text string format, ill make the categories string (although i
    // considered making another enum)
    //might get rid of the string variables and just automatically convert it to its corresponding int values (typeVV,
    // genderVV, etc.) when I add the txt parser part

    private int count;
    private PersonPreferences dimVals; //the values assigned to each dimension and their dimension values
    private String type, gender, age, pregnant, lawful, status, health; //is this proper java naming convention? or
    // should i call it like memberType or smthn?
    private int typeVV, genderVV, ageVV, pregnantVV, lawfulVV, statusVV, healthVV, frequency;
    private int typeDMF, genderDMF, ageDMF, pregnantDMF, lawfulDMF, statusDMF, healthDMF;

    private double lsv = 0; //variable for life saving value

    //private String[] possibleDimVals[category][value];

    public Person(PersonPreferences dimVals,int count,String type,String age,String gender,String pregnant,String status,
                  String lawful,String health){
       this.dimVals = dimVals;
       this.count = count;
       this.type = type;
       this.age = age;
       this.gender = gender;
       this.pregnant = pregnant;
       this.status = status;
       this.lawful = lawful;
       this.health = health;
       getDimVals();
       findVVs();
       calculateLSV();
    }

    public double getLsv() {
        return lsv;
    }

    public void getDimVals(){
        typeDMF = dimVals.getType().getDMF().getValue();
        genderDMF = dimVals.getGender().getDMF().getValue();
        ageDMF = dimVals.getAge().getDMF().getValue();
        pregnantDMF = dimVals.getPregnant().getDMF().getValue();
        lawfulDMF = dimVals.getLawful().getDMF().getValue();
        statusDMF = dimVals.getStatus().getDMF().getValue();
        healthDMF = dimVals.getHealth().getDMF().getValue();
    }

    public Person(String csvfile){
        readPeopleText();
    }

    //instead of creating a separate findVV method for each dimension value object (Type/Age/etc), i just created one
    //big method that finds the corresponding dimension value object, and gets the VV for the specified class for everything
    // Would speartinng them into methods based on their dimension type be better/ like one to find the value for Type, one
    //to find the VV for the age value, and so on?
    private int findVV(String value){
        value = value.toLowerCase();

        //could i maybe do an array of strings containing all the words, put that array into a for loop, and then
        //iterate through the loop checking of each string equalled a VV category and if it did, taking that value?
        //will probably have to insert some try catch methods here in case it tries to access an incorrect object type
        //i think im gonna have to use inheritance so i can get unkowns since i have no way of knowing which unkwon to use rn
        switch (value.toLowerCase()){
            case "human" : return dimVals.getType().getHumanVV().getValue();
            case "animal" : return dimVals.getType().getAnimalVV().getValue();
            case "male" : return dimVals.getGender().getMaleVV().getValue();
            case "female" : return dimVals.getGender().getFemaleVV().getValue();
            case "elderly" : return dimVals.getAge().getElderlyVV().getValue();
            case "adult" : return dimVals.getAge().getAdultVV().getValue();
            case "child" : return dimVals.getAge().getChildVV().getValue();
            case "baby" : return dimVals.getAge().getBabyVV().getValue();
            case "athlete" : return dimVals.getHealth().getAthleteVV().getValue();
            case "average" : return dimVals.getHealth().getAverageVV().getValue();
            case "obese" : return dimVals.getHealth().getObeseVV().getValue();
            case "pregnant" : return dimVals.getPregnant().getPregnantVV().getValue();
            case "notpregnant" : return dimVals.getPregnant().getNotPregnantVV().getValue();
            case "professional" : return dimVals.getStatus().getProfessionalVV().getValue();
            case "normal" : return dimVals.getStatus().getAverageVV().getValue();  //note: had to rename average to normal
                                                                    // since average already exists for health dimension
            case "misfit" : return dimVals.getStatus().getFailureVV().getValue();
            case "abiding" : return dimVals.getLawful().getAbidingVV().getValue();
            case "breaking" : return dimVals.getLawful().getBreakingVV().getValue();
            case "passenger" : return dimVals.getLawful().getPassengerVV().getValue();
            case "notapplicable" : return 0;
            case "unknown" : return 0;
            default:
                //System.out.println(value.toLowerCase());
                return (-1000);

        }


    }

   /* could maybe instead of the long switch statement do like
   for(int i = 0; i < 2; i++){
         go through a 2d array with all the type, gender, age etc dimensions and get their corresponding DMF and VVs
         might have to use an overloading insode loop or use Object argument and instanceOf for the different objects
         (type vs age vs ...)
    } */

    //matches the int values for the dimension's values with the moral Preference values so we can compute the LSV
    private void findVVs(){
        typeVV = findVV(type);
        if(type.equals("unknown")){
            typeVV = (int)dimVals.getType().getUnknownVV();
        }
        ageVV = findVV(age);
        if(age.equals("unknown")){
            typeVV = (int)dimVals.getAge().getUnknownVV();
        }
        genderVV = findVV(gender);
        if(gender.equals("unknown")){
            typeVV = (int)dimVals.getGender().getUnknownVV();
        }
        healthVV = findVV(health);
        if(health.equals("unknown")){
            typeVV = (int)dimVals.getHealth().getUnknownVV();
        }
        statusVV = findVV(status);
        if(status.equals("unknown")){
            typeVV = (int)dimVals.getStatus().getUnknownVV();
        }
        lawfulVV = findVV(lawful);
        if(lawful.equals("unknown")){
            typeVV = (int)dimVals.getLawful().getUnknownVV();
        }
        pregnantVV = findVV(pregnant);
        if(pregnant.equals("unknown")){
            typeVV = (int)dimVals.getPregnant().getUnknownVV();
        }
    }

    //checking the found VV's for exceptions and missing values.
    private void checkVVs(){
        typeVV = findVV(type);
        ageVV = findVV(age);
        genderVV = findVV(gender);
        healthVV = findVV(health);
        statusVV = findVV(status);
        lawfulVV = findVV(lawful);
        pregnantVV = findVV(pregnant);
    }

    //calculate Life Saving Value
    private void calculateLSV () {
        //assignUnknownValues();
        lsv = ( sumOverDimensions() / numAppDimensions() ) * count ;
        /*LSV_life = SUM_over_dimensions(DMF_dimension * VV_value-in-dimension) /
                number_of_applicable_dimensions
        Note: For unknown values, assign the average of the known values, e.g., if
        the Sex VV for male is M and the Sex VV for female is F, a baby gets
        (M + F)/2.0.
                Note: for inapplicable values, assign 0, e.g., the social status VV for
        a baby is 0. */
    }

    //calculates by passing any type of dimension/factor Object to method and using instanceOf s for each dimension
    private int sumOverDimensions(){
        /*System.out.println((typeDMF * typeVV) + " " + (ageDMF *  ageVV) + " " + (genderDMF * genderVV) + " " + (pregnantDMF * pregnantVV) + " " + (statusDMF * statusVV)
                    + " " + (lawfulDMF * lawfulVV) + " " + (healthDMF * healthVV)); */
        return ( (typeDMF * typeVV) + (ageDMF *  ageVV) + (genderDMF * genderVV) + (pregnantDMF * pregnantVV) +
                (statusDMF * statusVV) + (lawfulDMF * lawfulVV) + (healthDMF * healthVV) );
    }

    //determines the number of applicable dimensions for a person / scenario member
    private int numAppDimensions() {
        int appDims = 7;
        if(type.equals("unknown") || type.equals("notapplicable")){
            appDims--;
        }
        if(age.equals("unknown") || age.equals("notapplicable")){
            appDims--;
        }
        if(gender.equals("unknown") || gender.equals("notapplicable")){
            appDims--;
        }
        if(health.equals("unknown") || health.equals("notapplicable")){
            appDims--;
        }
        if(status.equals("unknown") || status.equals("notapplicable")){
            appDims--;
        }
        if(lawful.equals("unknown") || lawful.equals("notapplicable")){
            appDims--;
        }
        if(pregnant.equals("unknown") || pregnant.equals("notapplicable")){
            appDims--;
        }
        return appDims;
    }

    //assigns averages to unknown vv's, and assigns 0 for inapplicable values
    private void assignUnknownValues(){
        //TODO: read in unknown values
        //ex: if the word pregnancy comes before the word unkown, assign pregnncy to unknown
        //prolly just need to reqrite the switch method and read everything in based on category


    }

    public void readPeopleText(){
        //TODO: read in from encoded text file
    }




}
