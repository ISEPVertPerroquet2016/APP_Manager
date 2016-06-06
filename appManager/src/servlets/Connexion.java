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
import dao.DAOUtilitaire;
import entities.LDAPObject;
import entities.Utilisateur;
import metiers.ConnexionMetier;

@WebServlet( "/Connexion" )
public class Connexion extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    private ConnexionDao      connexionDao;

    /**
     * methode instanciée une seule fois à la création du servlet lors du
     * lancement de l'application,
     */
    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.connexionDao = ( (DAOFactory) getServletContext().getAttribute( DAOUtilitaire.DAO_FACTORY ) )
                .getConnexionDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {

        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( DAOUtilitaire.CONNEXION_VIEW_FORWARD ).forward( request,
                response );

    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        ConnexionMetier connexion = new ConnexionMetier( connexionDao );

        LDAPObject ldap = connexion.getUser( request );
        Utilisateur user = connexion.connecterUtilisateur( ldap );

        // Récupération de la session depuis la requête
        HttpSession session = request.getSession();

        request.setAttribute( DAOUtilitaire.ATT_FORM_CONNEXION, connexion );

        if ( user == null )
        {
            this.getServletContext().getRequestDispatcher( DAOUtilitaire.CONNEXION_VIEW_FORWARD ).forward( request,
                    response );
        } else
        {
            session.setAttribute( DAOUtilitaire.USER, user );
        }

        if ( DAOUtilitaire.ELEVE.equals( user.getType() ) )
        {
            //this.getServletContext().getRequestDispatcher( ACCUEIL_TEST ).forward( request, response );
            response.sendRedirect( DAOUtilitaire.SKILLS_SHEET_VIEW_REDIRECT );
        } else if ( DAOUtilitaire.PROFESSEUR.equals( user.getType() ) )
        {
            response.sendRedirect( DAOUtilitaire.ACCUEIL_VIEW_REDIRECT );
        }

    }
}