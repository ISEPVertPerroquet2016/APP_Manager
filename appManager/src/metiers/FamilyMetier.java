package metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.DAOException;
import dao.FamilyDao;
import entities.FamilyObject;

public class FamilyMetier
{
    private static final String FAMILY      = "family";
    private static final String DESCRIPTION = "description";
    private static final String CHAMP_NOM   = "nameFamily";

    private FamilyDao           familyDao;
    private String              resultat;
    private Map<String, String> erreurs     = new HashMap<String, String>();

    public FamilyMetier( FamilyDao familyDao )
    {
        this.familyDao = familyDao;
    }

    public FamilyObject createFamily( HttpServletRequest request )
    {
        // Récupération des champs du formulaire 
        String familyName = getValeurChamp( request, FAMILY );
        String description = getValeurChamp( request, DESCRIPTION );

        FamilyObject family = new FamilyObject();

        try
        {
            traiterName( familyName, family );
            family.setDescription( description );
            if ( erreurs.isEmpty() )
            {
                familyDao.create( family );
                resultat = "Succès de l'opération, la famille " + familyName + " a bien été ajoutée";
            } else
            {
                resultat = "Echec de l'opération";
            }
        } catch ( DAOException e )
        {
            resultat = "Echec de l'opération, une erreur imprévue est survenue";
            e.printStackTrace();
        }

        return family;
    }

    private void traiterName( String nameFamily, FamilyObject family )
    {
        try
        {
            validationName( nameFamily );
        } catch ( FormValidationException e )
        {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        family.setNameFamily( nameFamily );
    }

    //Validation du nom
    private void validationName( String nameFamily ) throws FormValidationException
    {
        if ( nameFamily != null )
        {
            if ( nameFamily.length() > 150 )
            {
                throw new FormValidationException( "Le nombre de caractère ne doit pas dépasser 150" );
            } else if ( familyDao.find( nameFamily ) != null )
            {
                throw new FormValidationException( "Cette famille existe déja" );
            }
        } else
        {
            throw new FormValidationException( "Merci de saisir une famille" );
        }
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
