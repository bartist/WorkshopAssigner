package data.entity;

import java.util.ArrayList;

public class Participant {
    private final String name;
    private final ArrayList<String> preferences;
    private int currentPreference;
    private final ArrayList<String> assigned;

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
        preferenceIncrement();

        return preference;
    }
    private void preferenceIncrement(){
        while(assigned.contains(preferences.get(currentPreference))){
            currentPreference++;
        }
    }

    public void reset(){
        currentPreference = 0;
        preferenceIncrement();
    }

    // todo: add preference ranking for sorting worst-matched preference to best
}
