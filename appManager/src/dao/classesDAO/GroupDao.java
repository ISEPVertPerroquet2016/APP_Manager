package dao.classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOUtilitaire;
import dao.exceptions.DAOException;
import dao.interfacesDao.IGroupDao;
import entities.GroupObject;

public class GroupDao implements IGroupDao
{

    private static final String SQL_CREATE_GROUP = "INSERT INTO groupe (id_group) VALUES (?)";
    private static final String SQL_SELECT_BY_ID = "SELECT id_group FROM groupe WHERE id_group = ?";

    private DAOFactory          daoFactory;

    public GroupDao( DAOFactory daoFactory )
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create( GroupObject group )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_CREATE_GROUP );

            //remplissage des paramètres de la requête préparée
            preparedstatement.setInt( 1, group.getGroupID() );

            // Execution de la requête d'insertion
            int statut = preparedstatement.executeUpdate();

            // Analyse du statut retourné par la requête d'insertion 
            if ( statut == 0 )
            {
                throw new DAOException( "Echec de la cr�ation du groupe." );
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
    public GroupObject find( int groupID )
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        GroupObject group = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_SELECT_BY_ID );

            preparedstatement.setInt( 1, groupID );

            resultSet = preparedstatement.executeQuery();

            // Analyse du statut retourné par la requête d'insertion 
            if ( resultSet.next() )
            {
                group = new GroupObject();
                group.setGroupID( groupID );
            }

        } catch ( SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedstatement, connexion );
        }

        return group;

    }

}
