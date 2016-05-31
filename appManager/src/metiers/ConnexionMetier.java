package metiers;

import javax.servlet.http.HttpServletRequest;

import dao.ConnexionDao;
import dao.DAOException;
import entities.LDAPObject;
import entities.Utilisateur;

public class ConnexionMetier
{
    private static final String CHAMP_LOGIN     = "login";
    private static final String CHAMP_PASS      = "password";

    private String              erreurConnexion = "";
    private LDAPaccess          access;
    private ConnexionDao        connexionDao;

    public ConnexionMetier( ConnexionDao connexionDao )
    {
        this.connexionDao = connexionDao;
    }

    public LDAPObject getUser( HttpServletRequest request )
    {
        /* Récupération des champs du formulaire */
        String login = getValeurChamp( request, CHAMP_LOGIN );
        String password = getValeurChamp( request, CHAMP_PASS );

        LDAPaccess access = new LDAPaccess();
        LDAPObject connect = null;

        try
        {
            connect = access.LDAPget( login, password );
        } catch ( Exception e )
        {
            System.err.println( e.getMessage() );
        }
        return connect;
    }

    public Utilisateur connecterUtilisateur( LDAPObject ldap )
    {
        Utilisateur user = null;

        if ( ldap != null )
        {

            int userID = Integer.parseInt( ldap.getEmployeeNumber() );

            try
            {
                user = connexionDao.find( userID );
                if ( user == null )
                {
                    erreurConnexion = "La connexion a échouée, veuillez réessayer";
                }

            } catch ( DAOException e )
            {
                e.printStackTrace();
            }

        } else
        {
            erreurConnexion = "La connexion a échouée, veuillez réessayer";
        }

        return user;
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp )
    {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 )
        {
            return null;
        } else
        {
            return valeur;
        }
    }

    /**
     * @return the erreurConnexion
     */
    public String getErreurConnexion()
    {
        return erreurConnexion;
    }

    /**
     * @param erreurConnexion
     *            the erreurConnexion to set
     */
    public void setErreurConnexion( String erreurConnexion )
    {
        this.erreurConnexion = erreurConnexion;
    }

}
