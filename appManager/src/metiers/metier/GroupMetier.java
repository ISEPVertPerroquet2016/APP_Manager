package metiers.metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.classesDAO.GroupDao;
import dao.exceptions.DAOException;
import entities.GroupObject;
import metiers.exceptions.FormValidationException;

public class GroupMetier
{
    private static final String GROUP       = "groupe";
    private static final String CHAMP_GROUP = "group";

    private GroupDao            groupDao;
    private String              resultat;
    private Map<String, String> erreurs     = new HashMap<String, String>();

    public GroupMetier( GroupDao groupDao )
    {
        this.groupDao = groupDao;
    }

    public GroupObject createGroup( HttpServletRequest request )
    {
        int groupID = getValeurIntegerChamp( request, GROUP );

        GroupObject group = null;

        try
        {
            group = new GroupObject();
            traiterGroup( groupID, group );

            // traiterName( groupeName, group );
            if ( erreurs.isEmpty() )
            {
                groupDao.create( group );
                resultat = "Succ�s de l'op�ration, le groupe: " + groupID + " a bien �t� ajout�e";
            } else
            {
                resultat = "Echec de l'op�ration";
            }
        } catch ( DAOException e )
        {
            resultat = "Echec de l'op�ration, une erreur impr�vue est survenue";
            e.printStackTrace();
        }

        return group;
    }

    /*
     * Méthode utilitaire qui retourne 0 si un champ est vide, et son contenu
     * sinon. (pour les int seulement)
     */
    private static int getValeurIntegerChamp( HttpServletRequest request, String nomChamp )
    {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 )
        {
            return 0;
        } else
        {
            return Integer.parseInt( valeur );
        }
    }

    private void traiterGroup( int groupID, GroupObject group )
    {
        try
        {
            validationGroup( groupID );
        } catch ( FormValidationException e )
        {
            setErreur( CHAMP_GROUP, e.getMessage() );
        }
        group.setGroupID( groupID );
    }

    //Validation du groupe id
    private void validationGroup( int groupID ) throws FormValidationException
    {
        if ( groupID == 0 || groupID < 0 )
        {
            throw new FormValidationException( "Merci de saisir un groupe de valeur supérieure à 0" );
        } else if ( groupID > 100 )
        {
            throw new FormValidationException( "Merci de saisir un groupe inférieur à 100" );
        }
    }

    private void setErreur( String champ, String message )
    {
        erreurs.put( champ, message );
    }

    public String getResultat()
    {
        return resultat;
    }

    public Map<String, String> getErreurs()
    {
        return erreurs;
    }

}
