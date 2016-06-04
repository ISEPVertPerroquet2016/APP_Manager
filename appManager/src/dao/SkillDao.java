package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.FamilyObject;
import entities.SkillObject;

public class SkillDao implements ISkillDao
{

    private static final String SQL_CREATE_SKILL = "INSERT INTO Skill (name_skill, observation_test, coefficient, basic_skill, basic_required, basic_failed, medium_skill, medium_required, medium_failed, name_family) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_BY_NAME = "SELECT name_skill FROM Skill WHERE name_skill = ?";
    
    private DAOFactory          daoFactory;

    public SkillDao( DAOFactory daoFactory )
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create( SkillObject skill )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_CREATE_SKILL );

            //remplissage des paramètres de la requête préparée
            preparedstatement.setString( 1, skill.getNameSkill() );
            preparedstatement.setString( 2, skill.getObservationTest() );
            preparedstatement.setInt( 3, skill.getCoefficient() );
            preparedstatement.setString( 4, skill.getBasicSkill() );
            preparedstatement.setString( 5, skill.getBasicRequired() );
            preparedstatement.setString( 6, skill.getBasicFailed() );
            preparedstatement.setString( 7, skill.getMediumSkill() );
            preparedstatement.setString( 8, skill.getMediumRequired() );
            preparedstatement.setString( 9, skill.getMediumFailed() );
            preparedstatement.setString( 10, skill.getNameFamily() );

            // Execution de la requête d'insertion
            int statut = preparedstatement.executeUpdate();

            // Analyse du statut retourné par la requête d'insertion 
            if ( statut == 0 )
            {
                throw new DAOException( "Échec de la création de la famille, aucune ligne ajoutée dans la table." );
            }

        } catch ( SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( preparedstatement, connexion );
        }

    }

    @Override
    public SkillObject find( String skillName )
    {
    	Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        SkillObject skill = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_SELECT_BY_NAME );

            preparedstatement.setString( 1, skillName );

            resultSet = preparedstatement.executeQuery();

            // Analyse du statut retourné par la requête d'insertion 
            if ( resultSet.next() )
            {
                skill = new SkillObject();
                skill.setNameFamily( skillName );
            }

        } catch ( SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedstatement, connexion );
        }

        return null;
    }

}
