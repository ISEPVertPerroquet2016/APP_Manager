package metiers.metier;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.classesDAO.SkillSheetDao;
import dao.exceptions.DAOException;
import entities.FamilyObject;
import entities.FicheObject;
import entities.GroupObject;
import entities.SkillObject;
import entities.Utilisateur;

public class SkillSheetMetier extends SkillManagementMetier
{
    private static final String SHEET_GROUP               = "sheetGroup";
    private static final String CHAMP_FAMILY_SELECTED     = "familySelected";
    private static final String CHAMP_ELEVE_SELECTED      = "eleveSelected";
    private static final String CHAMP_OBSERVATION         = "observationEleve";
    private static final String CHAMP_OBSERVATION_UPDATED = "observationEleveUpdated";
    private static final String CHAMP_NIVEAU              = "niveauRadio";
    private static final String CHAMP_NIVEAU_UPDATED      = "niveauRadioUpdated";
    private static final String CHAMP_VALIDER_FICHE       = "validerFiche";
    private static final String CHAMP_VALIDER_FICHE_VALUE = "Valider";

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

    public Map<String, FicheObject> getFiche( String name_family, int eleveID )
    {
        Map<String, FicheObject> fiches = null;

        try
        {
            fiches = skillSheetDao.findFiches( name_family, eleveID );

        } catch ( DAOException e )
        {
            e.printStackTrace();
        }

        return fiches;
    }

    public Map<String, String> getFicheCollective( String name_family, int groupID )
    {
        Map<String, String> fichesCollectives = null;

        try
        {
            fichesCollectives = skillSheetDao.findFichesCollectives( name_family, groupID );

        } catch ( DAOException e )
        {
            e.printStackTrace();
        }

        return fichesCollectives;
    }

    public void editFiche( HttpServletRequest request, FamilyObject familySelected, int eleveID )
    {
        if ( CHAMP_VALIDER_FICHE_VALUE.equals( getValeurChamp( request, CHAMP_VALIDER_FICHE ) ) )
        {
            try
            {
                for ( SkillObject skill : familySelected.getSkills() )
                {
                    String oldObservation = getValeurChamp( request, CHAMP_OBSERVATION + skill.getNameSkill() );
                    String newObservation = getValeurChamp( request, CHAMP_OBSERVATION_UPDATED + skill.getNameSkill() );

                    String oldNiveau = getValeurChamp( request, CHAMP_NIVEAU + skill.getNameSkill() );
                    String newNiveau = getValeurChamp( request, CHAMP_NIVEAU_UPDATED + skill.getNameSkill() );

                    if ( oldObservation == null && oldNiveau == null )
                    {
                        if ( newObservation == null && "NN".equals( newNiveau ) )
                        {
                            //rien
                        } else
                        {
                            skillSheetDao.createFiche( skill.getNameFamily(), skill.getNameSkill(), eleveID, newNiveau,
                                    newObservation );
                        }

                    } else if ( oldObservation != null || oldNiveau != null )
                    {

                        if ( newObservation == null && "NN".equals( newNiveau ) )
                        {
                            skillSheetDao.deleteFiche( skill.getNameSkill(), eleveID );
                        } else
                        {
                            skillSheetDao.updateFiche( skill.getNameSkill(), eleveID, newNiveau,
                                    newObservation );
                        }
                    }
                }

            } catch ( DAOException e )
            {
                e.printStackTrace();
            }
        }

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
