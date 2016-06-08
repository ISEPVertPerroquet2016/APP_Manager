package metiers.metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.classesDAO.SkillDao;
import dao.exceptions.DAOException;
import entities.SkillObject;
import metiers.exceptions.FormValidationException;

public class SkillMetier
{
    private static final String NAME_SKILL         = "name_skill";
    private static final String OBSERVATION_TEST   = "observation_test";
    private static final String COEFFICIENT        = "coefficient";
    private static final String BASIC_SKILL        = "basic_skill";
    private static final String BASIC_REQUIRED     = "basic_required";
    private static final String BASIC_FAILED       = "basic_failed";
    private static final String MEDIUM_SKILL       = "medium_skill";
    private static final String MEDIUM_REQUIRED    = "medium_required";
    private static final String MEDIUM_FAILED      = "medium_failed";
    private static final String NAME_FAMILY        = "familyName";

    private static final String CHAMP_NOM          = "nameSkill";
    private static final String CHAMP_COEFFICIENT  = "coefficient";
    private static final String CHAMP_BASIC_SKILL  = "basicSkill";
    private static final String CHAMP_MEDIUM_SKILL = "mediumSkill";

    private SkillDao            skillDao;
    private String              resultat;
    private Map<String, String> erreurs            = new HashMap<String, String>();

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
                resultat = "Succès de l'opération, la compétence " + "\"" + skill.getNameSkill() + "\""
                        + " a bien été ajoutée";
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
        traiterCoefficient( coefficient, skill );

        String basicSkill = getValeurChamp( request, BASIC_SKILL );
        traiterBasicSkill( basicSkill, skill );

        String basicRequired = getValeurChamp( request, BASIC_REQUIRED );
        skill.setBasicRequired( basicRequired );

        String basicFailed = getValeurChamp( request, BASIC_FAILED );
        skill.setBasicFailed( basicFailed );

        String mediumSkill = getValeurChamp( request, MEDIUM_SKILL );
        traiterMediumSkill( mediumSkill, skill );

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

    private void traiterCoefficient( int coefficient, SkillObject skill )
    {
        try
        {
            validationCoefficient( coefficient );
        } catch ( FormValidationException e )
        {
            setErreur( CHAMP_COEFFICIENT, e.getMessage() );
        }
        skill.setCoefficient( coefficient );
    }

    //Validation du coefficient
    private void validationCoefficient( int coefficient ) throws FormValidationException
    {
        if ( coefficient == 0 || coefficient < 0 )
        {
            throw new FormValidationException( "Merci de saisir un coefficient de valeur supérieure à 0" );
        } else if ( coefficient > 100 )
        {
            throw new FormValidationException( "Merci de saisir un coefficient inférieur à 100" );
        }
    }

    private void traiterBasicSkill( String basicSkill, SkillObject skill )
    {
        try
        {
            validationBasicSkill( basicSkill );
        } catch ( FormValidationException e )
        {
            setErreur( CHAMP_BASIC_SKILL, e.getMessage() );
        }
        skill.setBasicSkill( basicSkill );
    }

    private void validationBasicSkill( String basicSkill ) throws FormValidationException
    {
        if ( basicSkill != null )
        {
            if ( basicSkill.length() > 100 )
            {
                throw new FormValidationException( "Le nombre de caractère ne doit pas dépasser 100" );
            }
        } else
        {
            throw new FormValidationException( "Merci de remplir la compétence de base" );
        }
    }

    private void traiterMediumSkill( String mediumSkill, SkillObject skill )
    {
        try
        {
            validationMediumSkill( mediumSkill );
        } catch ( FormValidationException e )
        {
            setErreur( CHAMP_MEDIUM_SKILL, e.getMessage() );
        }
        skill.setMediumSkill( mediumSkill );
    }

    private void validationMediumSkill( String mediumSkill ) throws FormValidationException
    {
        if ( mediumSkill != null )
        {
            if ( mediumSkill.length() > 100 )
            {
                throw new FormValidationException( "Le nombre de caractère ne doit pas dépasser 100" );
            }
        } else
        {
            throw new FormValidationException( "Merci de remplir la compétence de intermédiaire" );
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
