package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOUtilitaire
{
    /**
     * factory
     */
    public static final String DAO_FACTORY                   = "daoFactory";

    /**
     * users
     */
    public static final String USER                          = "user";
    public static final String PROFESSEUR                    = "professeur";
    public static final String RESPONSABLE                   = "responsable";
    public static final String ELEVE                         = "eleve";

    /**
     * attributes
     */
    public static final String ATT_FORM_SKILL                = "formSkill";
    public static final String ATT_FORM_CONNEXION            = "formConnexion";
    public static final String ATT_FORM_FAMILY               = "formFamily";
    public static final String SKILL                         = "skill";
    public static final String FAMILY_NAME                   = "familyName";
    public static final String FAMILY                        = "family";
    public static final String FAMILIES                      = "families";
    public static final String SHEET_GROUPS                  = "sheetGroups";
    public static final String SHEET_ELEVES                  = "sheetEleves";

    /**
     * forward views
     */
    public static final String CONNEXION_VIEW_FORWARD        = "/pages/index.jsp";
    public static final String ACCUEIL_VIEW_FORWARD          = "/pages/AccueilTuteur.jsp";
    public static final String SKILL_VIEW_FORWARD            = "/pages/nouvelleComp.jsp";
    public static final String FAMILY_VIEW_FORWARD           = "/pages/nouvelleFamille.jsp";
    public static final String SKILL_MANAGEMENT_VIEW_FORWARD = "/pages/gerercomp.jsp";
    public static final String SKILLS_SHEET_VIEW_FORWARD     = "/pages/affichercomm.jsp";

    /**
     * redirect views
     */
    public static final String CONNEXION_VIEW_REDIRECT       = "/appManager/Connexion";
    public static final String SKILLS_SHEET_VIEW_REDIRECT    = "/appManager/SkillsSheet";
    public static final String ACCUEIL_VIEW_REDIRECT         = "/appManager/Accueil";

    /*
     * Constructeur caché par défaut (car c'est une classe finale utilitaire,
     * contenant uniquement des méthode appelées de manière statique)
     */
    private DAOUtilitaire()
    {
    }

    /* Fermeture silencieuse du resultset */
    public static void fermetureSilencieuse( ResultSet resultSet )
    {
        if ( resultSet != null )
        {
            try
            {
                resultSet.close();
            } catch ( SQLException e )
            {
                System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
            }
        }
    }

    /* Fermeture silencieuse du statement */
    public static void fermetureSilencieuse( Statement statement )
    {
        if ( statement != null )
        {
            try
            {
                statement.close();
            } catch ( SQLException e )
            {
                System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
            }
        }
    }

    /* Fermeture silencieuse de la connexion */
    public static void fermetureSilencieuse( Connection connexion )
    {
        if ( connexion != null )
        {
            try
            {
                connexion.close();
            } catch ( SQLException e )
            {
                System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
            }
        }
    }

    /* Fermetures silencieuses du statement et de la connexion */
    public static void fermeturesSilencieuses( Statement statement, Connection connexion )
    {
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

    /* Fermetures silencieuses du resultset, du statement et de la connexion */
    public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion )
    {
        fermetureSilencieuse( resultSet );
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

}
