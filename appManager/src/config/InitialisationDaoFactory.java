package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.classesDAO.DAOFactory;

public class InitialisationDaoFactory implements ServletContextListener
{
    private static final String DAO_FACTORY = "daoFactory";

    private DAOFactory          daoFactory;

    @Override
    //la DaoFactory est instanciée une seule fois au lancement de l'application
    public void contextInitialized( ServletContextEvent event )
    {
        // Récupération du ServletContext lors du chargement de l'application 
        ServletContext servletContext = event.getServletContext();

        // Instanciation de notre DAOFactory 
        this.daoFactory = DAOFactory.getInstance();

        // Enregistrement dans un attribut ayant pour portée toute l'application 
        servletContext.setAttribute( DAO_FACTORY, this.daoFactory );
    }

    @Override
    public void contextDestroyed( ServletContextEvent event )
    {
        //rien à réaliser lors de la fermeture pour l'instant
    }

}
