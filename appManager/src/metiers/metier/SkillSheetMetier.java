package metiers.metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.classesDAO.SkillSheetDao;
import dao.exceptions.DAOException;
import entities.FamilyObject;
import entities.GroupObject;
import entities.Utilisateur;

public class SkillSheetMetier extends SkillManagementMetier
{
    private static final String SHEET_GROUP           = "sheetGroup";
    private static final String CHAMP_FAMILY_SELECTED = "familySelected";
    private static final String CHAMP_ELEVE_SELECTED  = "eleveSelected";

    private SkillSheetDao       skillSheetDao;

    public SkillSheetMetier( SkillSheetDao skillSheetDao )
    {
        super( skillSheetDao );
        this.skillSheetDao = skillSheetDao;
    }

    public List<GroupObject> getGroups()
    {
        List<GroupObject> groups = null;

        try
        {
            groups = skillSheetDao.findGroups();

        } catch ( DAOException e )
        {
            e.printStackTrace();
        }

        return groups;
    }

    public List<Utilisateur> getElevesByGroup( HttpServletRequest request )
    {
        int groupID = getValeurIntegerChamp( request, SHEET_GROUP );

        return getElevesByGroup( request, groupID );
    }

    public List<Utilisateur> getElevesByGroup( HttpServletRequest request, int groupID )
    {
        List<Utilisateur> eleves = null;

        try
        {
            eleves = skillSheetDao.findElevesByGroup( groupID );

        } catch ( DAOException e )
        {
            e.printStackTrace();
        }

        return eleves;
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

    public FamilyObject getFamilyByName( HttpServletRequest request, List<FamilyObject> families )
    {
        String familyName = getValeurChamp( request, CHAMP_FAMILY_SELECTED );

        if ( familyName != null )
        {
            for ( FamilyObject famili : families )
            {
                if ( familyName.equals( famili.getNameFamily() ) )
                {
                    return famili;
                }
            }
        }

        return null;
    }

    public int getEleveIDSelected( HttpServletRequest request )
    {
        return getValeurIntegerChamp( request, CHAMP_ELEVE_SELECTED );
    }

}
