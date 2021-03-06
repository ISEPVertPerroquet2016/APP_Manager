package dao.classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOUtilitaire;
import dao.exceptions.DAOException;
import dao.interfacesDao.IConnexionDao;
import entities.Utilisateur;

public class ConnexionDao implements IConnexionDao
{
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM utilisateur WHERE user_id = ?";

    private DAOFactory          daoFactory;

    public ConnexionDao( DAOFactory daoFactory )
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public Utilisateur find( int userID ) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        Utilisateur user = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_SELECT_BY_ID );

            preparedstatement.setInt( 1, userID );

            resultSet = preparedstatement.executeQuery();

            // Analyse du statut retourné par la requête d'insertion 
            if ( resultSet.next() )
            {
                user = map( resultSet );
            }

        } catch ( SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedstatement, connexion );
        }

        return user;
    }

    private Utilisateur map( ResultSet resultset ) throws SQLException
    {
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setUserID( resultset.getInt( "user_id" ) );
        utilisateur.setFirstname( resultset.getString( "firstname" ) );
        utilisateur.setSurname( resultset.getString( "surname" ) );
        utilisateur.setType( resultset.getString( "userType" ) );

        int group = resultset.getInt( "id_group" );

        if ( group > -1 )
        {
            utilisateur.setIdGroup( group );
        }

        return utilisateur;
    }

}
