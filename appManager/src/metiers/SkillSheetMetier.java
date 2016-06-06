package metiers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.DAOException;
import dao.SkillSheetDao;
import entities.GroupObject;
import entities.Utilisateur;

public class SkillSheetMetier extends SkillManagementMetier
{
    private static final String SHEET_GROUP = "sheetGroup";

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
        List<Utilisateur> eleves = null;

        int groupID = getValeurIntegerChamp( request, SHEET_GROUP );

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
}
