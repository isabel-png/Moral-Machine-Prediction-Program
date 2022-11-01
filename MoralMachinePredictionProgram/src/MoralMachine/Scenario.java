package MoralMachine;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Scenario {

    private static String comment, id;
    private static PersonPreferences preferences;

    public Scenario(ArrayList<String> scenarioData, PersonPreferences preferences) {

        ArrayList<Group> groupsInScenario;

        this.preferences = preferences;
        groupsInScenario = readScenarioFile(scenarioData);
        chooseSaveGroup(groupsInScenario);
    }

    public static String getComment() {
        return comment;
    }

    public static String getId() {
        return id;
    }

    public static ArrayList<Group> readScenarioFile(ArrayList<String> scenarioData) {

        int scenarioDataIndex = 0;
        String[] scenarioInfo;
        ArrayList<Group> groupsInScenario = new ArrayList<>();

        while (scenarioDataIndex < scenarioData.size()) {
            while (!scenarioData.get(scenarioDataIndex).startsWith("EndScenario")) {
                if (scenarioData.get(scenarioDataIndex).startsWith("Comment")) {
                    scenarioInfo = scenarioData.get(scenarioDataIndex).split("=");
                } else {
                    scenarioInfo = scenarioData.get(scenarioDataIndex).split("[=,]");
                }
                scenarioDataIndex++;
                switch (scenarioInfo[0]) {
                    case "StartScenario":
                        id = scenarioInfo[1];
                        break;
                    case "Comment":
                        comment = scenarioInfo[1];
                        break;
                    case "StartGroup":
                        scenarioDataIndex = readGroup(scenarioData,scenarioDataIndex,scenarioInfo[1],
groupsInScenario);
                        break;
                    default:
                        break;
                }
            }
            scenarioDataIndex++;
        }
        return(groupsInScenario);
    }

    public static int readGroup(ArrayList<String> groupData, int scenarioDataIndex, String groupName,
ArrayList<Group> groupsInScenario) {

        String[] attributesAndValues, attributeAndValue;
        int attributeIndex;
        HashMap<String,String> attributeValues = null;

        ArrayList<Person> groupMembers = new  ArrayList<Person>();
        while(!groupData.get(scenarioDataIndex).startsWith("EndGroup")) {
            if (groupData.get(scenarioDataIndex).startsWith("Count")) {
                attributesAndValues = groupData.get(scenarioDataIndex).split(",");
                attributeValues = new HashMap<>();
                for (attributeIndex = 0;attributeIndex < attributesAndValues.length;attributeIndex++) {
                    attributeAndValue = attributesAndValues[attributeIndex].split("=");
                    if (
!attributeAndValue[0].equals("Count") &&
!attributeAndValue[0].equals("Type") &&
!attributeAndValue[0].equals("Age") &&
!attributeAndValue[0].equals("Gender") &&
!attributeAndValue[0].equals("Pregnancy") &&
!attributeAndValue[0].equals("SocialStatus") &&
!attributeAndValue[0].equals("Lawfulness") &&
!attributeAndValue[0].equals("Health")) {
                        System.out.println("Unknown attribute " + attributeAndValue[0] + " in " +
 groupData.get(scenarioDataIndex));
                    } else {
                        attributeValues.put(attributeAndValue[0],attributeAndValue[1]);
                    }
                }
                groupMembers.add(new Person(preferences,Integer.parseInt(attributeValues.get("Count")),
attributeValues.get("Type"),attributeValues.get("Age"),attributeValues.get("Gender"),attributeValues.get("Pregnancy"),
attributeValues.get("SocialStatus"),attributeValues.get("Lawfulness"),attributeValues.get("Health")));
            }
            scenarioDataIndex++;
        }
        groupsInScenario.add(new Group(groupName,preferences,groupMembers));
        scenarioDataIndex++; //----Eats the EndGroup
        return(scenarioDataIndex);
    }

    //couldve just done split("[,=]" placed into an array then i+1 and placed relevant details into object
   /* public int readGroup (String[] groupData, int i, Group group){
        int c;
        String t, a, g, p, ss, l, h;
        while(!groupData[i].equals("EndGroup")){
            String[] person = groupData[i].split(",");
            for (String s : person) {
                String[] attributes = s.split("=");
                switch (attributes[0]) {
                    case "Count":
                        c = Integer.parseInt(attributes[0]);
                        break;
                    case "Type":
                        t = attributes[0];
                        break;
                    case "Age":
                        a = attributes[0];
                        break;
                    case "Gender":
                        g = attributes[0];
                        break;
                    case "Pregnancy":
                        p = attributes[0];
                        break;
                    case "SocialStatus":
                        ss = attributes[0];
                        break;
                    case "Lawfulness":
                        l = attributes[0];
                        break;
                    case "Health":
                        h = attributes[0];
                        break;
                    default:
                        break;
                }
            }

            i++;
        }

        return i;
    } */

    public static void chooseSaveGroup(ArrayList<Group> groupsToChooseFrom) {

        ArrayList<Group> groupsToSave = new ArrayList<>();
        int groupIndex;

        groupsToSave.add(groupsToChooseFrom.get(0));
        groupIndex = 1;
        while (groupIndex < groupsToChooseFrom.size()) {
            if (groupsToChooseFrom.get(groupIndex).getGlsv() >= groupsToSave.get(0).getGlsv()) {
                if (groupsToChooseFrom.get(groupIndex).getGlsv() > groupsToSave.get(0).getGlsv()) {
                    groupsToSave = new ArrayList<>();
                }
                groupsToSave.add(groupsToChooseFrom.get(groupIndex));
            }
            groupIndex++;
        }

        if (Scenarios.printCSVFormat) {
            System.out.print(id + "," + preferences.getId() + "," + preferences.getDemographics());
        } else {
            System.out.println("Scenario ID : " + id);
            System.out.println("Scenario    : " + comment);
            System.out.println("Person ID   : " + preferences.getId());
            System.out.println("Person      : " + preferences.getDemographics());
        }
        groupIndex = 0;
        while (groupIndex < groupsToChooseFrom.size()) {
            if (Scenarios.printCSVFormat) {
                System.out.print("," + groupsToChooseFrom.get(groupIndex).getName() + "," +
groupsToChooseFrom.get(groupIndex).getGlsv() + "," + (
groupsToSave.contains(groupsToChooseFrom.get(groupIndex)) ? "SAVED" : "KILLED"));
            } else {
                System.out.printf("Group %-10s with GLSV of %5.2f is %s\n",groupsToChooseFrom.get(groupIndex).getName(),
                groupsToChooseFrom.get(groupIndex).getGlsv(),groupsToSave.contains(groupsToChooseFrom.get(groupIndex)) ?
"SAVED" : "KILLED");
            }
            groupIndex++;
        }
        if (Scenarios.printCSVFormat) {
            System.out.println();
        } else {
            System.out.println(
"---------------------------------------------------------------------------------------------------------------------");
        }
    }
}
