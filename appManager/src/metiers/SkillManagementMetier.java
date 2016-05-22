package metiers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import entities.Family;
import entities.SkillObject;

public class SkillManagementMetier
{
    private List<Family> families;
    private List<String> familiesNames;

    public List<String> getFamiliesName( HttpServletRequest request )
    {
        familiesNames = new ArrayList<String>();

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e )
        {
            e.getMessage();
        }

        // Connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/bdd_appmanager";
        String utilisateur = "root";
        String motDePasse = "perroquetG11";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try
        {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

            // Création de l'objet gérant les requêtes
            statement = connexion.createStatement();

            // Execution de la requête
            resultat = statement.executeQuery( "SELECT name_family FROM familyskill" );

            while ( resultat.next() )
            {
                String family = resultat.getString( "name_family" );
                familiesNames.add( family );
            }

        } catch ( SQLException e )
        {
            e.getMessage();
        } finally
        {
            if ( resultat != null )
            {
                try
                {
                    // On commence par fermer le ResultSet
                    resultat.close();
                } catch ( SQLException ignore )
                {
                }
            }
            if ( statement != null )
            {
                try
                {
                    // Puis on ferme le Statement
                    statement.close();
                } catch ( SQLException ignore )
                {
                }
            }
            if ( connexion != null )
            {
                try
                {
                    // Et enfin on ferme la connexion
                    connexion.close();
                } catch ( SQLException ignore )
                {
                }
            }
        }

        return familiesNames;
    }

    public List<Family> getFamilies( HttpServletRequest request, List<String> familiesNames )
    {
        families = new ArrayList<Family>();

        for ( String familyName : familiesNames )
        {

            Family family = new Family();
            family.setNameFamily( familyName );
            List<SkillObject> skills = new ArrayList<SkillObject>();
            family.setSkills( skills );
            families.add( family );
        }

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e )
        {
            e.getMessage();
        }

        // Connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/bdd_appmanager";
        String utilisateur = "root";
        String motDePasse = "perroquetG11";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try
        {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

            // Création de l'objet gérant les requêtes
            statement = connexion.createStatement();

            // Execution de la requête
            resultat = statement.executeQuery( "SELECT * FROM `bdd_appmanager`.`skill` order by name_family;" );

            while ( resultat.next() )
            {
                SkillObject skill = new SkillObject();

                String nameSkill = resultat.getString( "name_skill" );
                skill.setNameSkill( nameSkill );

                int coefficient = resultat.getInt( "coefficient" );
                skill.setCoefficient( coefficient );

                String observationTest = resultat.getString( "observation_test" );
                skill.setObservationTest( observationTest );

                String basicSkill = resultat.getString( "basic_skill" );
                skill.setBasicSkill( basicSkill );

                String basicRequired = resultat.getString( "basic_required" );
                skill.setBasicRequired( basicRequired );

                String basicFailed = resultat.getString( "basic_failed" );
                skill.setBasicFailed( basicFailed );

                String mediumSkill = resultat.getString( "medium_skill" );
                skill.setMediumSkill( mediumSkill );

                String mediumRequired = resultat.getString( "medium_required" );
                skill.setMediumRequired( mediumRequired );

                String mediumFailed = resultat.getString( "medium_failed" );
                skill.setMediumFailed( mediumFailed );

                String nameFamily = resultat.getString( "name_family" );
                skill.setNameFamily( nameFamily );

                for ( Family famili : families )
                {
                    if ( famili.getNameFamily().equals( nameFamily ) )
                    {
                        famili.getSkills().add( skill );
                    }
                }
            }

        } catch ( SQLException e )
        {
            e.getMessage();
        } finally
        {
            if ( resultat != null )
            {
                try
                {
                    // On commence par fermer le ResultSet
                    resultat.close();
                } catch ( SQLException ignore )
                {
                }
            }
            if ( statement != null )
            {
                try
                {
                    // Puis on ferme le Statement
                    statement.close();
                } catch ( SQLException ignore )
                {
                }
            }
            if ( connexion != null )
            {
                try
                {
                    // Et enfin on ferme la connexion
                    connexion.close();
                } catch ( SQLException ignore )
                {
                }
            }
        }
        return families;
    }

}
