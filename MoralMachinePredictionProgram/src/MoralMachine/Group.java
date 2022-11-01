package MoralMachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Group {
    private ArrayList<Person> groupMembers;
    private String groupName;
    private double glsv;
    private PersonPreferences prefs;

    public Group(String groupName,PersonPreferences prefs,ArrayList<Person> groupMembers){
        //TODO: read group info from encoded text file
        this.groupName = groupName;
        this.prefs = prefs;
        this.groupMembers = groupMembers;
        calculateGLSV();
    }

    public Group(){
        //TODO: read group info from encoded text file
        populateGroup();
        calculateGLSV();
    }

    public double getGlsv() {
        return glsv;
    }

    public String getName() {
        return groupName;
    }

    public String toString() {
        return(getName() + " " + getGlsv());
    }
    public ArrayList<Person> getGroup() {
        return groupMembers;
    }

    private void populateGroup(){
        boolean end = false;
        int i = 0;

        while(end != true){
            groupMembers.get(i).readPeopleText();
        }
    }

    private void calculateGLSV(){
        glsv= 0;

        for(int i = 0; i < groupMembers.size(); i++){
            //int length = sizeof(group)/sizeof(group[0]);
            glsv += groupMembers.get(i).getLsv();
        }
    }

    private void readText(String fileName) throws IOException {
        BufferedReader fromBufferedReader;
        String line;
        int indexData = 0;
        String[] prefData = new String[30];

        fromBufferedReader = new BufferedReader(new FileReader(fileName));
        line = fromBufferedReader.readLine();
        while (!line.contains("EndScenario")) {
            if(line != null){
                prefData[indexData] = line;
                indexData++;
                line = fromBufferedReader.readLine();
            }
        }
        enterText(prefData, indexData);

        fromBufferedReader.close();
    }

    private void enterText(String[] prefData, int index){
        int pIndex = -1;
        String[][] persons = new String[100][100];
        for (int i = 3; i < index; i++){

            String[] prefInfo = prefData[i].split(",=\\n");
            persons[++pIndex] = prefInfo;
        }

        for (int i = 0; i < pIndex; i++){
            groupMembers.add(new Person(prefs, 1, persons[i][0], persons[i][1], persons[i][2], persons[i][3], persons[i][4], persons[i][5], persons[i][6]));
        }

    }
}
