package dao.classesDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import dao.exceptions.DAOConfigurationException;

public class DAOFactory
{
    private static final String FICHIER_PROPERTIES       = "/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "utilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motDePasse";

    private String              url;
    private String              username;
    private String              password;

    DAOFactory( String url, String username, String password )
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */

    public static DAOFactory getInstance() throws DAOConfigurationException
    {

        Properties properties = new Properties();
        String url;
        String driver;
        String utilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null )
        {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try
        {
            //chargement du fichier puis lecture pour récupérer les données 
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            utilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );

        } catch ( IOException e )
        {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES,
                    e );
        }

        try
        {
            //chargement du driver MySql
            Class.forName( driver );
        } catch ( ClassNotFoundException e )
        {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        DAOFactory instance = new DAOFactory( url, utilisateur, motDePasse );

        return instance;
    }

    // Méthode chargée de fournir une connexion à la base de données 
    Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection( url, username, password );
    }

    // Méthodes de récupération de l'implémentation des différents DAO 

    public FamilyDao getFamilyDao()
    {
        return new FamilyDao( this );
    }

    public ConnexionDao getConnexionDao()
    {
        return new ConnexionDao( this );
    }

    public SkillManagementDao getSkillManagementDao()
    {
        return new SkillManagementDao( this );
    }

    public SkillDao getSkillDao()
    {
        return new SkillDao( this );
    }

    public SkillSheetDao getSkillSheetDao()
    {
        return new SkillSheetDao( this );
    }
    
    public GroupDao getGroupDao()
    {
    	return new GroupDao( this );
    }

}
