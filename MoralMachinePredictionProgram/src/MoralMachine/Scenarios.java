package MoralMachine;

import java.lang.*;
import java.io.*;
import java.util.ArrayList;

public class Scenarios {

    public static boolean printCSVFormat;

    public static void main(String[] args) {

        ArrayList<PersonPreferences> peoplePreferences;
        ArrayList<ArrayList<String>> scenarios;
        int personIndex,scenarioIndex;
        int argOffet = 0;

        printCSVFormat = false;
        while (args[argOffet].startsWith("--")) {
            switch (args[argOffet]) {
                case "--csv":
                    printCSVFormat = true;
                    break;
                default:
                    break;
            }
            argOffet++;
        }
        try {
            peoplePreferences = readPeoplePreferenceFile(args[argOffet+0]);
        } catch (IOException e) {
            System.out.println("ERROR: Reading people preferences file " + args[argOffet+0]);
            return;
        }

        try {
            scenarios = readScenarioFile(args[argOffet+1]);
        } catch (IOException e) {
            System.out.println("ERROR: Reading scenarios file " + args[argOffet+1]);
            return;
        }

        for(scenarioIndex = 0; scenarioIndex < scenarios.size(); scenarioIndex++) {
            for (personIndex = 0; personIndex < peoplePreferences.size(); personIndex++) {
                new Scenario(scenarios.get(scenarioIndex),peoplePreferences.get(personIndex));
            }
        }
    }

    private static ArrayList<PersonPreferences> readPeoplePreferenceFile(String fileName) throws IOException {

        BufferedReader fromBufferedReader;
        String line;
        ArrayList<String> onePersonStrings;
        ArrayList<PersonPreferences> peoplePreferences = new ArrayList<>();
        boolean inPerson;

        fromBufferedReader = new BufferedReader(new FileReader(fileName));
        line = fromBufferedReader.readLine();
        onePersonStrings = new ArrayList<>();
        inPerson = false;
        while (line != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                if (line.startsWith("StartPerson")) {
                    onePersonStrings = new ArrayList<>();
                    inPerson = true;
                    onePersonStrings.add(line);
                } else if (inPerson) {
                    onePersonStrings.add(line);
                    if (line.startsWith("EndPerson")) {
                        peoplePreferences.add(new PersonPreferences(onePersonStrings.toArray(new String[0])));
                        inPerson = false;
                    }
                }
            }
            line = fromBufferedReader.readLine();
        }
        fromBufferedReader.close();
        return(peoplePreferences);
    }

    public static ArrayList<ArrayList<String>> readScenarioFile(String fileName) throws IOException {

        BufferedReader fromBufferedReader;
        String line;
        ArrayList<String> oneScenarioStrings;
        ArrayList<ArrayList<String>> scenarios = new ArrayList<>();
        boolean inScenario;

        fromBufferedReader = new BufferedReader(new FileReader(fileName));
        line = fromBufferedReader.readLine();
        oneScenarioStrings = new ArrayList<>();
        inScenario = false;
        while (line != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                if (line.startsWith("StartScenario")) {
                    inScenario = true;
                    oneScenarioStrings = new ArrayList<>();
                    oneScenarioStrings.add(line);
                } else if (inScenario) {
                    oneScenarioStrings.add(line);
                    if (line.startsWith("EndScenario")) {
                        scenarios.add(oneScenarioStrings);
                        inScenario = false;
                    }
                }
            }
            line = fromBufferedReader.readLine();
        }
        fromBufferedReader.close();
        return(scenarios);
    }
}
