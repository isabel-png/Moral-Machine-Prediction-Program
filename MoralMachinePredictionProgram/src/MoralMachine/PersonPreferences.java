package MoralMachine;

//will go through and format properly later (resize line length)
//go over exactly what the exception are so you can write code to exclude them from #ofapplicabledims

import java.io.IOException;

public class PersonPreferences {
    private String id, demographics, comment;
    private Type type; //is this proper java naming convention? or should i call it like memberType or smthn?
    private Gender gender;
    private Age age;
    private Pregnancy pregnant;
    private Lawfulness lawful;
    private SocialStatus status;
    private Health health;

    //default constructor
    public PersonPreferences() {
    }

    public PersonPreferences(String[] prefInfo) throws IOException{
        readText(prefInfo);
    }

    public String getId() {
        return id;
    }

    public String getDemographics() {
        return demographics;
    }

    public String getComment() {
        return comment;
    }

    public PersonPreferences(Type type,Gender gender,Age age,Pregnancy pregnant,Lawfulness lawful,SocialStatus status,Health health) {
        this.type = type;
        this.age = age;
        this.gender = gender;
        this.pregnant = pregnant;
        this.lawful = lawful;
        this.status = status;
        this.health = health;
    }


    public Type getType() {
        return this.type;
    }

    public Age getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Health getHealth() {
        return health;
    }

    public Lawfulness getLawful() {
        return lawful;
    }

    public Pregnancy getPregnant() {
        return pregnant;
    }

    public SocialStatus getStatus() {
        return status;
    }

    private void readText(String[] prefData) throws IOException {
        int indexData = 0, dataVVsize = -1, dataDMFsize = -1;
        VV dataVV[] = new VV[7];
        DMF dataDMF[] = new DMF[18];
        String pType[] = new String[3] , pAge[] = new String[5], pGender[] = new String[3], pPregnancy[] = new String[3], pSocialStatus[] = new String[4], pLawful[] = new String[4], pHealth[] = new String[4];



        //doing it with 1 preference for now but with multiple just make an array like:
        for (int i = 0; i < prefData.length; i++){
            String[] prefInfo = prefData[i].split("=");
            switch(prefInfo[0]){
                case "StartPerson":
                    id = prefInfo[1];
                    break;
                case "Demographics":
                    demographics = prefInfo[1];
                    break;
                case "Comment":
                    comment = prefInfo[1];
                    break;
                case "Type":
                    pType[0] = prefInfo[1];
                    break;
                case "Type.Human":
                    pType[1] = prefInfo[1];
                    break;
                case "Type.Animal":
                    pType[2] = prefInfo[1];
                    break;
                case "Age":
                    pAge[0] = prefInfo[1];
                    break;
                case "Age.Baby":
                    pAge[1] = prefInfo[1];
                    break;
                case "Age.Child":
                    pAge[2] = prefInfo[1];
                    break;
                case "Age.Adult":
                    pAge[3] = prefInfo[1];
                    break;
                case "Age.Elderly":
                    pAge[4] = prefInfo[1];
                    break;
                case "Gender":
                    pGender[0] = prefInfo[1];
                    break;
                case "Gender.Male":
                    pGender[1] = prefInfo[1];
                    break;
                case "Gender.Female":
                    pGender[2] = prefInfo[1];
                    break;
                case "Pregnancy":
                    pPregnancy[0] = prefInfo[1];
                    break;
                case "Pregnancy.NotPregnant":
                    pPregnancy[1] = prefInfo[1];
                    break;
                case "Pregnancy.Pregnant":
                    pPregnancy[2] = prefInfo[1];
                    break;
                case "SocialStatus":
                    pSocialStatus[0] = prefInfo[1];
                    break;
                case "SocialStatus.Professional":
                    pSocialStatus[1] = prefInfo[1];
                    break;
                case "SocialStatus.Average":
                    pSocialStatus[2] = prefInfo[1];
                    break;
                case "SocialStatus.Failure":
                    pSocialStatus[3] = prefInfo[1];
                    break;
                case "Lawfulness":
                    pLawful[0] = prefInfo[1];
                    break;
                case "Lawfulness.Abiding":
                    pLawful[1] = prefInfo[1];
                    break;
                case "Lawfulness.Breaking":
                    pLawful[2] = prefInfo[1];
                    break;
                case "Lawfulness.Passenger":
                    pLawful[3] = prefInfo[1];
                    break;
                case "Health":
                    pHealth[0] = prefInfo[1];
                    break;
                case "Health.Athlete":
                    pHealth[1] = prefInfo[1];
                    break;
                case "Health.Average":
                    pHealth[2] = prefInfo[1];
                    break;
                case "Health.Obese":
                    pHealth[3] = prefInfo[1];
                    break;
                case "EndPerson":
                    break;
                default:
                    break;
            }
        }
        type = new Type(DMF.valueOf(pType[0].toUpperCase()), VV.valueOf(pType[1].toUpperCase()), VV.valueOf(pType[2].toUpperCase()));
        age = new Age(DMF.valueOf(pAge[0].toUpperCase()), VV.valueOf(pAge[1].toUpperCase()), VV.valueOf(pAge[2].toUpperCase()), VV.valueOf(pAge[3].toUpperCase()), VV.valueOf(pAge[4].toUpperCase()));
        gender = new Gender(DMF.valueOf(pGender[0].toUpperCase()), VV.valueOf(pGender[1].toUpperCase()), VV.valueOf(pGender[2].toUpperCase()));
        pregnant = new Pregnancy(DMF.valueOf(pPregnancy[0].toUpperCase()), VV.valueOf(pPregnancy[1].toUpperCase()), VV.valueOf(pPregnancy[2].toUpperCase()));
        status = new SocialStatus(DMF.valueOf(pSocialStatus[0].toUpperCase()), VV.valueOf(pSocialStatus[1].toUpperCase()), VV.valueOf(pSocialStatus[2].toUpperCase()), VV.valueOf(pSocialStatus[3].toUpperCase()));
        lawful = new Lawfulness(DMF.valueOf(pLawful[0].toUpperCase()), VV.valueOf(pLawful[1].toUpperCase()), VV.valueOf(pLawful[2].toUpperCase()), VV.valueOf(pLawful[3].toUpperCase()));
        health = new Health(DMF.valueOf(pHealth[0].toUpperCase()), VV.valueOf(pHealth[1].toUpperCase()), VV.valueOf(pHealth[2].toUpperCase()), VV.valueOf(pHealth[3].toUpperCase()));

        /*for (int i = 2; i < indexData; i++){
            String[] prefInfo = prefData[i].split("=");
            if(prefInfo[0].contains(".")){
                dataVV[++dataVVsize] = VV.valueOf(prefInfo[1]);
            } else {
                dataDMF[++dataDMFsize] = DMF.valueOf(prefInfo[1]);
            }
        }

        type = new Type(dataDMF[0], dataVV[0], dataVV[1]);
        age = new Age(dataDMF[1], dataVV[2], dataVV[3], dataVV[4], dataVV[5]);
        gender = new Gender(dataDMF[2], dataVV[6], dataVV[7]);
        pregnant = new Pregnancy(dataDMF[3], dataVV[8], dataVV[9]);
        status = new SocialStatus(dataDMF[4], dataVV[10], dataVV[11], dataVV[12]);
        lawful = new Lawfulness(dataDMF[6], dataVV[13], dataVV[14], dataVV[15]);
        health = new Health(dataDMF[7], dataVV[16], dataVV[17], dataVV[18]);*/


    }



}
