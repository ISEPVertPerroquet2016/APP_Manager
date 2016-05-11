package connexion;

import entities.LDAPObject;

public class ConnexionMetier
{
    private static final String CHAMP_LOGIN = "login";
    private static final String CHAMP_PASS  = "password";

    private LDAPaccess          access;

    public LDAPObject connecterUtilisateur( HttpServletRequest request )
    {
        /* Récupération des champs du formulaire */
        String login = getValeurChamp( request, CHAMP_LOGIN );
        String password = getValeurChamp( request, CHAMP_PASS );

        LDAPaccess access = new LDAPaccess();
        LDAPObject connect = new LDAPObject();

        try {
            connect = access.LDAPget( login, password );
        } catch ( Exception e ) {
            System.err.println( e.getMessage() );
            System.exit( 1 );
        }

        return connect;
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
