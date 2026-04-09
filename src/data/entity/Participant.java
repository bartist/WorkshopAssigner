package data.entity;

import java.util.ArrayList;

public class Participant {
    private final String name;
    private final ArrayList<String> preferences;
    private int currentPreference;
    private final ArrayList<String> assigned;
    private int preferenceIndex = 0;

    public Participant(String name, ArrayList<String> preferences){
        this.name = name;
        this.preferences = preferences;
        this.currentPreference = 0;
        this.assigned = new ArrayList<>();
    }

    public boolean hasNextPreference() {
        return this.currentPreference < this.preferences.size();
    }

    public String getNextPreference() {
        String preference = preferences.get(currentPreference);

        currentPreference++;
        if(currentPreference < preferences.size()) preferenceIncrement();

        return preference;
    }
    private void preferenceIncrement(){
        while(assigned.contains(preferences.get(currentPreference))){
            currentPreference++;
        }
    }

    private void reset(){
        currentPreference = 0;
        preferenceIncrement();
    }

    public void choose(String choice) {
        assigned.add(choice);
        preferenceIndex += preferences.indexOf(choice);
        reset();
    }

    public String createPrintable() {
        StringBuilder assignedAsString = new StringBuilder();
        for(String workshop : assigned) assignedAsString.append(";").append(workshop);
        return name + ";" + assignedAsString.substring(1) + ";" + preferenceIndex;
    }

    public int comparePreferenceIndex(Participant participant) {
        return Integer.compare(participant.preferenceIndex, this.preferenceIndex);
    }

    public int compareName(Participant participant) {
        return CharSequence.compare(this.name, participant.name);
    }
}
