package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.FamilyObject;
import entities.SkillObject;

public class SkillManagementDao implements ISkillManagementDao
{
    private static final String SQL_SELECT_FAMILIES_NAMES = "SELECT name_family FROM familyskill";
    private static final String SQL_SELECT_FAMILIES       = "SELECT * FROM skill";

    private DAOFactory          daoFactory;

    public SkillManagementDao( DAOFactory daoFactory )
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<String> findFamiliesNames()
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        List<String> familiesNames = null;

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
                if ( familiesNames == null )
                {
                    familiesNames = new ArrayList<String>();
                }
                familiesNames.add( resultSet.getString( "name_family" ) );
            }

        } catch (

        SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedstatement, connexion );
        }

        return familiesNames;
    }

    @Override
    public List<FamilyObject> findFamilies( List<String> familiesNames )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultat = null;
        List<FamilyObject> families = new ArrayList<FamilyObject>();

        for ( String familyName : familiesNames )
        {

            FamilyObject family = new FamilyObject();
            family.setNameFamily( familyName );
            List<SkillObject> skills = new ArrayList<SkillObject>();
            family.setSkills( skills );
            families.add( family );
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
