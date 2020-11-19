package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {

    Enseignant untel;
    UE uml, java;

    @BeforeEach
    public void setUp() {
        untel = new Enseignant("untel", "untel@gmail.com");
        uml = new UE("UML");
        java = new UE("Programmation en java");
    }

    @Test
    public void testNouvelEnseignantSansService() {
        assertEquals(0, untel.heuresPrevues(),
                "Un nouvel enseignant doit avoir 0 heures prévues");
    }

    @Test
    public void testAjouteHeures() {
        // 10h TD pour UML
        untel.ajouteEnseignement(uml, 0, 10, 0);

        assertEquals(10, untel.heuresPrevuesPourUE(uml),
                "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

        // 20h TD pour UML
        untel.ajouteEnseignement(uml, 0, 20, 0);

        assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");

    }

    @Test
    public void testCalculHeures() {
        untel.ajouteEnseignement(uml, 10, 20, 10);
        untel.ajouteEnseignement(uml, 10, 5, 30);
        int calcul = (int) ((10 + 10) * 1.5 + (20 + 5) + (10 + 30) * 0.75);
        assertEquals(calcul, untel.heuresPrevues(), ("L'enseignant doit maintenant avoir " + calcul + " heures prévues"));
    }

    @Test
    public void testHeuresPlanifiees() {
        Salle s = new Salle("B007", 30);
        Intervention i1 = new Intervention(s, uml, untel, "12/12/12 16h00", 2);
        Intervention i2 = new Intervention(s, java, untel, "12/12/12 8h30", 3);
        untel.addInterventionsPlanifiees(i2);
        untel.addInterventionsPlanifiees(i1);
        assertEquals(2 + 3, untel.heuresPlanifies(), "L'enseignant doit avoir 5 heures planifiées.");
    }

    @Test
    public void estEnSsService() {
        Salle s = new Salle("B007", 30);
        Intervention i1 = new Intervention(s, uml, untel, "12/12/12 16h00", 2);
        untel.ajouteEnseignement(uml, 0, 3, 0);
        untel.addInterventionsPlanifiees(i1);
        assertEquals(2 < 3, untel.enSousService(), "L'enseignenant est censé être en sous-service");

    }
    
    @Test
    public void pasEnSsService() {
        Salle s = new Salle("B007", 30);
        Intervention i1 = new Intervention(s, uml, untel, "12/12/12 16h00", 3);
        untel.ajouteEnseignement(uml, 0, 3, 0);
        untel.addInterventionsPlanifiees(i1);
        assertFalse(untel.enSousService(), "L'enseignenant n'est pas en sous-service");

    }
    
    @Test
    public void ajoutUE(){
        untel.ajouteEnseignement(uml, 0, 10, 0);
        assertEquals(10, untel.heuresPrevuesPourUE(uml), "On est censé avori 10 heures de cours pour UML");
    }
    
    @Test
    public void sansUE(){
        try{
            untel.heuresPrevuesPourUE(java);
            fail("On ne peut pas récuperer d'heures pour une matière inexistante");
        }catch (NullPointerException E){
            //test réussi
        }
    }
    
}
