package metiers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import entities.SkillObject;

public class SkillMetier
{
    private static final String NAME_SKILL       = "name_skill";
    private static final String OBSERVATION_TEST = "observation_test";
    private static final String COEFFICIENT      = "coefficient";
    private static final String BASIC_SKILL      = "basic_skill";
    private static final String BASIC_REQUIRED   = "basic_required";
    private static final String BASIC_FAILED     = "basic_failed";
    private static final String MEDIUM_SKILL     = "medium_skill";
    private static final String MEDIUM_REQUIRED  = "medium_required";
    private static final String MEDIUM_FAILED    = "medium_failed";

    public SkillObject createSkill( HttpServletRequest request )
    {
        SkillObject skill = new SkillObject();

        /* Récupération des champs du formulaire */
        String skillName = getValeurChamp( request, NAME_SKILL );
        skill.setNameSkill( skillName );

        String observationTest = getValeurChamp( request, OBSERVATION_TEST );
        skill.setObservationTest( observationTest );

        int coefficient = getValeurIntegerChamp( request, COEFFICIENT );
        skill.setCoefficient( coefficient );

        String basicSkill = getValeurChamp( request, BASIC_SKILL );
        skill.setBasicSkill( basicSkill );

        String basicRequired = getValeurChamp( request, BASIC_REQUIRED );
        skill.setBasicRequired( basicRequired );

        String basicFailed = getValeurChamp( request, BASIC_FAILED );
        skill.setBasicFailed( basicFailed );

        String mediumSkill = getValeurChamp( request, MEDIUM_SKILL );
        skill.setMediumSkill( mediumSkill );

        String mediumRequired = getValeurChamp( request, MEDIUM_REQUIRED );
        skill.setMediumRequired( mediumRequired );

        String mediumFailed = getValeurChamp( request, MEDIUM_FAILED );
        skill.setMediumFailed( mediumFailed );

        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.getMessage();
        }

        /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306/bdd_appmanager";
        String utilisateur = "root";
        String motDePasse = "perroquetG11";
        Connection connexion = null;
        Statement statement = null;

        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

            /* Création de l'objet gérant les requêtes */
            statement = connexion.createStatement();

            // Execution de la requête d'insertion
            int statut = statement.executeUpdate(
                    "INSERT INTO Skill (name_skill, observation_test, coefficient, basic_skill, basic_required, basic_failed, medium_skill, medium_required, medium_failed, name_family) VALUES ('"
                            + skillName + "','" + observationTest + "','" + coefficient + "','" + basicSkill + "','"
                            + basicRequired + "','" + basicFailed + "','" + mediumSkill + "','" + mediumRequired + "','"
                            + mediumFailed + "','test')" );

        } catch ( SQLException e ) {
            e.getMessage();
        } finally {
            if ( statement != null ) {
                try {
                    /* Puis on ferme le Statement */
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( connexion != null ) {
                try {
                    /* Et enfin on ferme la connexion */
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }
        }

        return skill;
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon. (pour les String seulement)
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp )
    {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon. (pour les int seulement)
     */
    private static int getValeurIntegerChamp( HttpServletRequest request, String nomChamp )
    {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null ) {
            return 0;
        } else {
            return Integer.parseInt( valeur );
        }
    }
}

// int valeur = Integer.parseInt( request.getParameter( String.valueOf( nomChamp
// ) ) );
