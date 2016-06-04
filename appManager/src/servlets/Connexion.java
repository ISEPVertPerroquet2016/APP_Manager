package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConnexionDao;
import dao.DAOFactory;
import entities.LDAPObject;
import entities.Utilisateur;
import metiers.ConnexionMetier;

@WebServlet( "/Connexion" )
public class Connexion extends HttpServlet
{

    //views
    public static final String CONNEXION_VIEW     = "/pages/index.jsp";
    public static final String SKILLS_SHEET_VIEW  = "/appManager/SkillsSheet";
    public static final String ACCUEIL_VIEW       = "/appManager/Accueil";
    public static final String ACCUEIL_TEST       = "/WEB-INF/accueilTest.jsp";

    //attributes
    public static final String ELEVE              = "eleve";
    public static final String PROFESSEUR         = "professeur";
    public static final String ATT_FORM_CONNEXION = "formConnexion";
    public static final String USER               = "user";

    public static final String DAO_FACTORY        = "daoFactory";

    private ConnexionDao       connexionDao;

    /**
     * methode instanciée une seule fois à la création du servlet lors du
     * lancement de l'application,
     */
    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.connexionDao = ( (DAOFactory) getServletContext().getAttribute( DAO_FACTORY ) ).getConnexionDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {

        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( CONNEXION_VIEW ).forward( request, response );

    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        ConnexionMetier connexion = new ConnexionMetier( connexionDao );

        LDAPObject ldap = connexion.getUser( request );
        Utilisateur user = connexion.connecterUtilisateur( ldap );

        // Récupération de la session depuis la requête
        HttpSession session = request.getSession();

        request.setAttribute( ATT_FORM_CONNEXION, connexion );

        if ( user == null )
        {
            this.getServletContext().getRequestDispatcher( CONNEXION_VIEW ).forward( request, response );
        } else
        {
            session.setAttribute( USER, user );
        }

        if ( ELEVE.equals( user.getType() ) )
        {
            //this.getServletContext().getRequestDispatcher( ACCUEIL_TEST ).forward( request, response );
            response.sendRedirect( SKILLS_SHEET_VIEW );
        } else if ( PROFESSEUR.equals( user.getType() ) )
        {
            response.sendRedirect( ACCUEIL_VIEW );
        }

        // response.sendRedirect( ACCUEIL );
    }
}