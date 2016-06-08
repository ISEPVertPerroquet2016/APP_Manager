package dao.classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOUtilitaire;
import dao.exceptions.DAOException;
import dao.interfacesDao.ISkillManagementDao;
import entities.FamilyObject;
import entities.SkillObject;

public class SkillManagementDao implements ISkillManagementDao
{
    private static final String SQL_SELECT_FAMILIES_NAMES = "SELECT * FROM familyskill";
    private static final String SQL_SELECT_FAMILIES       = "SELECT * FROM skill";

    protected DAOFactory        daoFactory;

    public SkillManagementDao( DAOFactory daoFactory )
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<FamilyObject> findFamiliesNames()
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        List<FamilyObject> families = null;

        try
        {

            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_SELECT_FAMILIES_NAMES );

            resultSet = preparedstatement.executeQuery();

            // Analyse du statut retourné par la requête d'insertion 
            while ( resultSet.next() )
            {
                if ( families == null )
                {
                    families = new ArrayList<FamilyObject>();
                }
                FamilyObject family = new FamilyObject();
                family.setNameFamily( resultSet.getString( "name_family" ) );
                family.setDescription( resultSet.getString( "description" ) );
                families.add( family );
            }

        } catch (

        SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedstatement, connexion );
        }

        return families;
    }

    @Override
    public List<FamilyObject> findFamilies( List<FamilyObject> families )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultat = null;

        for ( FamilyObject family : families )
        {
            List<SkillObject> skills = new ArrayList<SkillObject>();
            family.setSkills( skills );
        }

        try
        {

            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_SELECT_FAMILIES );

            resultat = preparedstatement.executeQuery();

            // Analyse du statut retourné par la requête d'insertion 
            while ( resultat.next() )
            {
                map( resultat, families );
            }

        } catch (

        SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultat, preparedstatement, connexion );
        }

        return families;
    }

    private void map( ResultSet resultat, List<FamilyObject> families ) throws SQLException
    {
        SkillObject skill = new SkillObject();

        String nameSkill = resultat.getString( "name_skill" );
        skill.setNameSkill( nameSkill );

        int coefficient = resultat.getInt( "coefficient" );
        skill.setCoefficient( coefficient );

        String observationTest = resultat.getString( "observation_test" );
        skill.setObservationTest( observationTest );

        String basicSkill = resultat.getString( "basic_skill" );
        skill.setBasicSkill( basicSkill );

        String basicRequired = resultat.getString( "basic_required" );
        skill.setBasicRequired( basicRequired );

        String basicFailed = resultat.getString( "basic_failed" );
        skill.setBasicFailed( basicFailed );

        String mediumSkill = resultat.getString( "medium_skill" );
        skill.setMediumSkill( mediumSkill );

        String mediumRequired = resultat.getString( "medium_required" );
        skill.setMediumRequired( mediumRequired );

        String mediumFailed = resultat.getString( "medium_failed" );
        skill.setMediumFailed( mediumFailed );

        String nameFamily = resultat.getString( "name_family" );
        skill.setNameFamily( nameFamily );

        for ( FamilyObject famili : families )
        {
            if ( famili.getNameFamily().equals( nameFamily ) )
            {
                famili.getSkills().add( skill );
            }
        }
    }

}
