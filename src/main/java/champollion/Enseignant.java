package champollion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Enseignant extends Personne {

    // TODO : rajouter les autres méthodes présentes dans le diagramme UML
    private List<Intervention> interventionsPlanifiees;
    private ServicePrevu servicesPrevus;

    public Enseignant(String nom, String email) {
        super(nom, email);
        interventionsPlanifiees = new ArrayList<>();
        this.servicesPrevus = new ServicePrevu();
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures
     * équivalent TD" Pour le calcul : 1 heure de cours magistral vaut 1,5 h
     * "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut
     * 0,75h "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        HashMap<UE, int[]> ue = this.servicesPrevus.getUE(this);
        int total = 0;
        for (int[] t : ue.values()) {
            total += t[0] * 1.5 + t[1] + t[2] * 0.75;
        }
        return (int) (float) total;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE
     * spécifiée en "heures équivalent TD" Pour le calcul : 1 heure de cours
     * magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent
     * TD" 1 heure de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        int[] cours = this.servicesPrevus.getHeuresUE(this, ue);
        return (int) (cours[0] * 1.5 + cours[1] + 0.75 * cours[2]);
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        this.servicesPrevus.addUE(volumeCM, volumeTD, volumeTP, this, ue);
    }

    /**
     *retourne les heures (en heure TD) qui se sont déroulées
     * @return
     */
    public int heuresPlanifies() {
        int total = 0;
        for (Intervention i : this.interventionsPlanifiees) {
            total += i.getDuree();
        }
        return total;
    }

    /**
     * est en sous service si heures planifiees < heures prevues
     * @return 
     */
    public boolean enSousService() {
        return this.heuresPlanifies() < this.heuresPrevues();
    }

    public void addInterventionsPlanifiees(Intervention interventionsPlanifiees) {
        this.interventionsPlanifiees.add(interventionsPlanifiees);
    }

}
