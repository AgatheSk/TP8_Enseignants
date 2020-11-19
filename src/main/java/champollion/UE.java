package champollion;

import java.util.HashSet;
import java.util.Set;

public class UE {

    private final String myIntitule;
    private Set<Intervention> myIntervention;

    public UE(String intitule) {
        myIntitule = intitule;
        myIntervention = new HashSet<>();
    }

    public String getIntitule() {
        return myIntitule;
    }

    public Set<Intervention> getMyIntervention() {
        return myIntervention;
    }

}
