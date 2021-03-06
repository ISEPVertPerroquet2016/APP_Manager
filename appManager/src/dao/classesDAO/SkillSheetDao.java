package dao.classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.DAOUtilitaire;
import dao.exceptions.DAOException;
import dao.interfacesDao.ISkillSheetDao;
import entities.FicheObject;
import entities.GroupObject;
import entities.Utilisateur;

public class SkillSheetDao extends SkillManagementDao implements ISkillSheetDao
{

    private static final String SQL_SELECT_GROUPS             = "SELECT DISTINCT groupe.id_group FROM groupe INNER JOIN utilisateur ON utilisateur.id_group = groupe.id_group WHERE utilisateur.id_group>0 AND utilisateur.userType='eleve'";
    private static final String SQL_SELECT_ELEVES             = "SELECT * FROM Utilisateur WHERE id_group=? and userType='eleve'";
    private static final String SQL_SELECT_FICHES             = "SELECT * FROM fiche WHERE user_id=? and name_family = ?";
    private static final String SQL_SELECT_FICHES_COLLECTIVES = "SELECT observation_collective, name_skill FROM fichecollective WHERE id_group = ? and name_family = ?";
    private static final String SQL_CREATE_FICHE              = "INSERT INTO fiche (name_family, name_skill, user_id, niveau, observation) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_FICHE              = "UPDATE fiche SET observation = ?, niveau = ? WHERE name_skill = ? AND user_id = ?";
    private static final String SQL_DELETE_FICHE              = "DELETE FROM fiche where name_skill = ? AND user_id = ?";
    private static final String SQL_CREATE_FICHE_COLLECTIVE   = "INSERT INTO fichecollective (name_family, name_skill, id_group, observation_collective) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_FICHE_COLLECTIVE   = "UPDATE fichecollective SET observation_collective = ? WHERE name_skill = ? AND id_group = ?";
    private static final String SQL_DELETE_FICHE_COLLECTIVE   = "DELETE FROM fichecollective where name_skill = ? AND id_group = ?";

    public SkillSheetDao( DAOFactory daoFactory )
    {
        super( daoFactory );
    }

    @Override
    public List<GroupObject> findGroups()
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        List<GroupObject> groups = null;

        try
        {

            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_SELECT_GROUPS );

            resultSet = preparedstatement.executeQuery();

            // Analyse du statut retourné par la requête d'insertion 
            while ( resultSet.next() )
            {
                if ( groups == null )
                {
                    groups = new ArrayList<GroupObject>();
                }
                GroupObject group = mapGroup( resultSet );
                groups.add( group );
            }

        } catch (

        SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedstatement, connexion );
        }

        return groups;
    }

    @Override
    public List<Utilisateur> findElevesByGroup( int id_group )
    {

        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        List<Utilisateur> eleves = null;

        try
        {

            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_SELECT_ELEVES );

            preparedstatement.setInt( 1, id_group );

            resultSet = preparedstatement.executeQuery();

            // Analyse du statut retourné par la requête d'insertion 
            while ( resultSet.next() )
            {
                if ( eleves == null )
                {
                    eleves = new ArrayList<Utilisateur>();
                }
                Utilisateur eleve = mapEleve( resultSet );
                eleves.add( eleve );
            }

        } catch (

        SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedstatement, connexion );
        }

        return eleves;
    }

    @Override
    public Map<String, FicheObject> findFiches( String nameFamily, int userID )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        Map<String, FicheObject> fiches = null;

        try
        {

            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_SELECT_FICHES );

            preparedstatement.setInt( 1, userID );
            preparedstatement.setString( 2, nameFamily );

            resultSet = preparedstatement.executeQuery();

            // Analyse du statut retourné par la requête d'insertion 
            while ( resultSet.next() )
            {
                if ( fiches == null )
                {
                    fiches = new HashMap<String, FicheObject>();
                }

                FicheObject fiche = mapFiche( resultSet );
                fiches.put( fiche.getNameSkill(), fiche );
            }

        } catch (

        SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedstatement, connexion );
        }

        return fiches;
    }

    public Map<String, String> findFichesCollectives( String nameFamily, int groupID )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        Map<String, String> observationsCollectives = null;

        try
        {

            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_SELECT_FICHES_COLLECTIVES );

            preparedstatement.setInt( 1, groupID );
            preparedstatement.setString( 2, nameFamily );

            resultSet = preparedstatement.executeQuery();

            // Analyse du statut retourné par la requête d'insertion 
            while ( resultSet.next() )
            {
                if ( observationsCollectives == null )
                {
                    observationsCollectives = new HashMap<String, String>();
                }
                String observationCollective = resultSet.getString( "observation_collective" );
                String nameSkill = resultSet.getString( "name_skill" );

                observationsCollectives.put( nameSkill, observationCollective );
            }

        } catch (

        SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedstatement, connexion );
        }

        return observationsCollectives;
    }

    @Override
    public void createFiche( String nameFamily, String nameSkill, int userID, String niveau, String observation )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;

        try
        {

            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_CREATE_FICHE );

            preparedstatement.setString( 1, nameFamily );
            preparedstatement.setString( 2, nameSkill );
            preparedstatement.setInt( 3, userID );
            preparedstatement.setString( 4, niveau );
            preparedstatement.setString( 5, observation );

            int statut = preparedstatement.executeUpdate();

            if ( statut == 0 )
            {
                throw new DAOException(
                        "Échec de la création d'une fiche de competence." );
            }
        } catch (

        SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( preparedstatement, connexion );
        }

    }

    @Override
    public void updateFiche( String nameSkill, int userID, String niveau, String observation )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_UPDATE_FICHE );

            preparedstatement.setString( 1, observation );
            preparedstatement.setString( 2, niveau );
            preparedstatement.setString( 3, nameSkill );
            preparedstatement.setInt( 4, userID );

            int statut = preparedstatement.executeUpdate();

            // Analyse du statut retourné par la requête d'insertion 
            if ( statut == 0 )
            {
                throw new DAOException(
                        "Échec de l'update d'une fiche de competence, la ligne n'a pas été modifiée dans la table." );
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
    public void deleteFiche( String nameSkill, int userID )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_DELETE_FICHE );

            preparedstatement.setString( 1, nameSkill );
            preparedstatement.setInt( 2, userID );

            int statut = preparedstatement.executeUpdate();

            // Analyse du statut retourné par la requête d'insertion 
            if ( statut == 0 )
            {
                throw new DAOException(
                        "Échec de la suppression d'une fiche de competence" );
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
    public void createFicheCollective( String nameFamily, String nameSkill, int groupID, String observationCollective )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;

        try
        {

            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_CREATE_FICHE_COLLECTIVE );

            preparedstatement.setString( 1, nameFamily );
            preparedstatement.setString( 2, nameSkill );
            preparedstatement.setInt( 3, groupID );
            preparedstatement.setString( 4, observationCollective );

            int statut = preparedstatement.executeUpdate();

            if ( statut == 0 )
            {
                throw new DAOException(
                        "Échec de la création d'une fiche collective de competence." );
            }
        } catch (

        SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( preparedstatement, connexion );
        }

    }

    @Override
    public void updateFicheCollective( String nameSkill, int groupID, String observationCollective )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_UPDATE_FICHE_COLLECTIVE );

            preparedstatement.setString( 1, observationCollective );
            preparedstatement.setString( 2, nameSkill );
            preparedstatement.setInt( 3, groupID );

            int statut = preparedstatement.executeUpdate();

            // Analyse du statut retourné par la requête d'insertion 
            if ( statut == 0 )
            {
                throw new DAOException(
                        "Échec de l'update d'une fiche collective de competence, la ligne n'a pas été modifiée dans la table." );
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
    public void deleteFicheCollective( String nameSkill, int groupID )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_DELETE_FICHE_COLLECTIVE );

            preparedstatement.setString( 1, nameSkill );
            preparedstatement.setInt( 2, groupID );

            int statut = preparedstatement.executeUpdate();

            // Analyse du statut retourné par la requête d'insertion 
            if ( statut == 0 )
            {
                throw new DAOException(
                        "Échec de la suppression d'une fiche collective de competence" );
            }

        } catch ( SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( preparedstatement, connexion );
        }

    }

    private FicheObject mapFiche( ResultSet resultSet ) throws SQLException
    {
        FicheObject fiche = new FicheObject();

        String nameFamily = resultSet.getString( "name_family" );
        String nameSkill = resultSet.getString( "name_skill" );
        int userID = resultSet.getInt( "user_id" );
        String niveau = resultSet.getString( "niveau" );
        String observation = resultSet.getString( "observation" );

        fiche.setNameFamily( nameFamily );
        fiche.setNameSkill( nameSkill );
        fiche.setUserID( userID );
        fiche.setNiveau( niveau );
        fiche.setObservation( observation );

        return fiche;
    }

    private GroupObject mapGroup( ResultSet resultSet ) throws SQLException
    {
        GroupObject group = new GroupObject();
        int idGroup = resultSet.getInt( "id_group" );
        group.setGroupID( idGroup );

        return group;
    }

    private Utilisateur mapEleve( ResultSet resultSet ) throws SQLException
    {
        Utilisateur eleve = new Utilisateur();

        int userID = resultSet.getInt( "user_id" );
        eleve.setUserID( userID );

        String firstname = resultSet.getString( "firstname" );
        eleve.setFirstname( firstname );

        String surname = resultSet.getString( "surname" );
        eleve.setSurname( surname );

        String type = resultSet.getString( "userType" );
        eleve.setType( type );

        int groupID = resultSet.getInt( "id_group" );
        eleve.setIdGroup( groupID );

        return eleve;
    }

}
