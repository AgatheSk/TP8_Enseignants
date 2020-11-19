package champollion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ServicePrevu {
    //map clé : un enseignant , valeurs ; un set de ses ue

    private HashMap<Enseignant, HashMap<UE, int[]>> myEnseignants;

    public ServicePrevu() {
        this.myEnseignants = new HashMap<>();
    }

    public void addUE(int volCm, int volTd, int volTp, Enseignant enseignant, UE ue) {
        int[] tabCours = {volCm, volTd, volTp};
        // enseignant existe pas
        if (!this.myEnseignants.containsKey(enseignant)) {
            HashMap<UE, int[]> ueenseignee = new HashMap<>();
            ueenseignee.put(ue, tabCours);
            this.myEnseignants.put(enseignant, ueenseignee);
        } else {
            //si ue existe pas 
            if (!this.myEnseignants.get(enseignant).containsKey(ue)){
               this.myEnseignants.get(enseignant).put(ue, tabCours);
            }else{
                int[] tabUE = this.myEnseignants.get(enseignant).get(ue);
                int[] total={tabUE[0]+volCm, tabUE[1]+volTd, tabUE[2]+volTp};
                this.myEnseignants.get(enseignant).put(ue,total);
            }
        }
    }

    /**
     * Retourne un tableau des heures travaillés pour un enseignant et son ue 1:
     * cm, 2:td, 3:tp
     *
     * @param enseignant
     * @param ue
     * @return
     */
    public int[] getHeuresUE(Enseignant enseignant, UE ue) {
        if (this.myEnseignants.containsKey(enseignant) || this.myEnseignants.get(enseignant).containsKey(ue)) {
            return this.myEnseignants.get(enseignant).get(ue);
        }else{
            throw new NullPointerException();
        }
        //return new int[3];
    }
    
    public HashMap<UE, int[]> getUE(Enseignant enseignant){
        if (this.myEnseignants.containsKey(enseignant)){
            return this.myEnseignants.get(enseignant);
        }else{
            return new HashMap<>();
        }
    }

}
