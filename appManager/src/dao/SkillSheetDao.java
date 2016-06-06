package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.GroupObject;
import entities.Utilisateur;

public class SkillSheetDao extends SkillManagementDao implements ISkillSheetDao
{

    private static final String SQL_SELECT_GROUPS = "SELECT * FROM groupe";
    private static final String SQL_SELECT_ELEVES = "SELECT * FROM Utilisateur WHERE id_group=?";

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

    private GroupObject mapGroup( ResultSet resultSet ) throws SQLException
    {
        GroupObject group = new GroupObject();
        int idGroup = resultSet.getInt( "id_group" );
        int idProf = resultSet.getInt( "id_prof" );
        group.setGroupID( idGroup );
        group.setIdProf( idProf );
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
