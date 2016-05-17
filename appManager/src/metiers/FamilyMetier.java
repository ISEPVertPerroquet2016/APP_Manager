package metiers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class FamilyMetier
{
    private static final String FAMILY = "family";

    public String createFamily( HttpServletRequest request )
    {
        /* Récupération des champs du formulaire */
        String family = getValeurChamp( request, FAMILY );

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

            // Execution d'une requête
            int statut = statement.executeUpdate(
                    "INSERT INTO familySkill (name_family) VALUES ('" + family + "')" );

            // Formatage pour affichage dans la JSP finale.

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

        return family;
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
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
}
