package test_bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class TestJDBC
{

    /* La liste qui contiendra tous les résultats de nos essais */

    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests( HttpServletRequest request )
    {
        try {
            messages.add( "Chargement du driver..." );
            Class.forName( "com.mysql.jdbc.Driver" );
            messages.add( "Driver chargé !" );
        } catch ( ClassNotFoundException e ) {
            messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
                    + e.getMessage() );
        }

        /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306/bdd_appmanager";
        String utilisateur = "root";
        String motDePasse = "perroquetG11";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            messages.add( "Connexion à la base de données..." );
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            messages.add( "Connexion réussie !" );

            /* Création de l'objet gérant les requêtes */
            statement = connexion.createStatement();
            messages.add( "Objet requête créé !" );

            /* Exécution d'une requête d'écriture */
            resultat = statement
                    .executeQuery( "SELECT student_number, firstname, surname, id_group  FROM eleve;" );
            messages.add( "Requête \"SELECT student_number, firstname, surname, id_group  FROM eleve;\" effectuée !" );

            /*
             * // Execution d'une requête int statut = statement.executeUpdate(
             * "INSERT INTO eleve (student_number, firstname, surname, id_group) VALUES (8978, 'arnaud', 'tessandier', 1)"
             * );
             * 
             * // Formatage pour affichage dans la JSP finale. messages.add(
             * "Résultat de la requête d'insertion : " + statut + "." )
             */;

            /* Récupération des données du résultat de la requête de lecture */

            while ( resultat.next() ) {
                int numberStudent = resultat.getInt( 1 );
                String firstname = resultat.getString( 2 );
                String surname = resultat.getString( 3 );
                int idGroup = resultat.getInt( 4 );

                /* Formatage des données pour affichage dans la JSP finale. */
                messages.add( "Données retournées par la requête : numberStudent = " + numberStudent + ", firstname = "
                        + firstname
                        + ", surname = "
                        + surname + ", idGroup = " + idGroup + "." );

                /* Traiter ici les valeurs récupérées. */
            }

        } catch ( SQLException e ) {
            messages.add( "Erreur lors de la connexion : <br/>" + e.getMessage() );
        } finally {
            messages.add( "Fermeture de l'objet ResultSet." );
            if ( resultat != null ) {
                try {
                    /* On commence par fermer le ResultSet */
                    resultat.close();
                } catch ( SQLException ignore ) {

                }
            }
            messages.add( "Fermeture de l'objet Statement." );
            if ( statement != null ) {
                try {
                    /* Puis on ferme le Statement */
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages.add( "Fermeture de l'objet Connection." );
            if ( connexion != null ) {
                try {
                    /* Et enfin on ferme la connexion */
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }
        }

        return messages;
    }
}
