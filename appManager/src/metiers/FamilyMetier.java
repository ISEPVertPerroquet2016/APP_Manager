package metiers;

import javax.servlet.http.HttpServletRequest;

import dao.DAOException;
import dao.FamilyDao;
import entities.FamilyObject;

public class FamilyMetier
{
    private static final String FAMILY = "family";

    private FamilyDao           familyDao;

    public FamilyMetier( FamilyDao familyDao )
    {
        this.familyDao = familyDao;
    }

    public FamilyObject createFamily( HttpServletRequest request )
    {
        // Récupération des champs du formulaire 
        String familyName = getValeurChamp( request, FAMILY );

        FamilyObject family = new FamilyObject();
        family.setNameFamily( familyName );

        try
        {
            familyDao.create( family );
        } catch ( DAOException e )
        {
            e.getMessage();
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
        if ( valeur == null || valeur.trim().length() == 0 )
        {
            return null;
        } else
        {
            return valeur;
        }
    }
}
