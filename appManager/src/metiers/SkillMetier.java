package metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.DAOException;
import dao.SkillDao;
import entities.SkillObject;

public class SkillMetier
{
    private static final String NAME_SKILL       = "name_skill";
    private static final String OBSERVATION_TEST = "observation_test";
    private static final String COEFFICIENT      = "coefficient";
    private static final String BASIC_SKILL      = "basic_skill";
    private static final String BASIC_REQUIRED   = "basic_required";
    private static final String BASIC_FAILED     = "basic_failed";
    private static final String MEDIUM_SKILL     = "medium_skill";
    private static final String MEDIUM_REQUIRED  = "medium_required";
    private static final String MEDIUM_FAILED    = "medium_failed";
    private static final String NAME_FAMILY      = "familyName";

    private static final String CHAMP_NOM        = "nameSkill";

    private SkillDao            skillDao;
    private String              resultat;
    private Map<String, String> erreurs          = new HashMap<String, String>();

    public SkillMetier( SkillDao skillDao )
    {
        this.skillDao = skillDao;
    }

    public SkillObject createSkill( HttpServletRequest request )
    {
        SkillObject skill = null;

        try
        {
            skill = map( request );

            if ( erreurs.isEmpty() )
            {
                skillDao.create( skill );
                resultat = "Succès de l'opération, la compétence " + skill.getNameSkill() + " a bien été ajoutée";
            } else
            {
                resultat = "Echec de l'opération";
            }
        } catch ( DAOException e )
        {
            resultat = "Echec de l'opération, une erreur imprévue est survenue";
            e.printStackTrace();
        }

        return skill;
    }

    private SkillObject map( HttpServletRequest request )
    {
        SkillObject skill = new SkillObject();

        /* Récupération des champs du formulaire */
        String skillName = getValeurChamp( request, NAME_SKILL );
        traiterName( skillName, skill );

        String observationTest = getValeurChamp( request, OBSERVATION_TEST );
        skill.setObservationTest( observationTest );

        int coefficient = getValeurIntegerChamp( request, COEFFICIENT );
        skill.setCoefficient( coefficient );

        String basicSkill = getValeurChamp( request, BASIC_SKILL );
        skill.setBasicSkill( basicSkill );

        String basicRequired = getValeurChamp( request, BASIC_REQUIRED );
        skill.setBasicRequired( basicRequired );

        String basicFailed = getValeurChamp( request, BASIC_FAILED );
        skill.setBasicFailed( basicFailed );

        String mediumSkill = getValeurChamp( request, MEDIUM_SKILL );
        skill.setMediumSkill( mediumSkill );

        String mediumRequired = getValeurChamp( request, MEDIUM_REQUIRED );
        skill.setMediumRequired( mediumRequired );

        String mediumFailed = getValeurChamp( request, MEDIUM_FAILED );
        skill.setMediumFailed( mediumFailed );

        String nameFamily = getValeurChamp( request, NAME_FAMILY );
        skill.setNameFamily( nameFamily );

        return skill;
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon. (pour les String seulement)
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

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
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

    private void traiterName( String nameSkill, SkillObject skill )
    {
        try
        {
            validationName( nameSkill );
        } catch ( FormValidationException e )
        {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        skill.setNameSkill( nameSkill );
    }

    //Validation du nom
    private void validationName( String nameSkill ) throws FormValidationException
    {
        if ( nameSkill != null )
        {
            if ( nameSkill.length() > 150 )
            {
                throw new FormValidationException( "Le nombre de caractère ne doit pas dépasser 150" );
            } else if ( skillDao.find( nameSkill ) != null )
            {
                throw new FormValidationException( "Cette compétence existe déja" );
            }
        } else
        {
            throw new FormValidationException( "Merci de saisir une compétence" );
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
