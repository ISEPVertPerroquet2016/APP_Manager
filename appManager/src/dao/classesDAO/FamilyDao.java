package dao.classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOUtilitaire;
import dao.exceptions.DAOException;
import dao.interfacesDao.IFamilyDao;
import entities.FamilyObject;

public class FamilyDao implements IFamilyDao
{
    private static final String SQL_CREATE_FAMILY  = "INSERT INTO familySkill (name_family, description) VALUES (?, ?)";
    private static final String SQL_SELECT_BY_NAME = "SELECT name_family FROM familyskill WHERE name_family = ?";

    private DAOFactory          daoFactory;

    public FamilyDao( DAOFactory daoFactory )
    {
        this.daoFactory = daoFactory;
    }

    @Override
    // Implémentation de la méthode createFamily() définie dans l'interface FamilyDao 
    public void create( FamilyObject family ) throws IllegalArgumentException, DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_CREATE_FAMILY );

            //remplissage des paramètres de la requête préparée
            preparedstatement.setString( 1, family.getNameFamily() );
            preparedstatement.setString( 2, family.getDescription() );

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
    public FamilyObject find( String familyName ) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        FamilyObject family = null;

        try
        {
            // Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();

            // Création de l'objet gérant les requêtes préparées 
            preparedstatement = connexion.prepareStatement( SQL_SELECT_BY_NAME );

            preparedstatement.setString( 1, familyName );

            resultSet = preparedstatement.executeQuery();

            // Analyse du statut retourné par la requête d'insertion 
            if ( resultSet.next() )
            {
                family = new FamilyObject();
                family.setNameFamily( familyName );
            }

        } catch ( SQLException e )
        {
            throw new DAOException( e );
        } finally
        {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedstatement, connexion );
        }

        return family;
    }

}
