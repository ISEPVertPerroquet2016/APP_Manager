package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connexion.ConnexionMetier;
import entities.LDAPObject;

@WebServlet( "/Connexion" )
public class Connexion extends HttpServlet
{

    public static final String CONNEXION = "/WEB-INF/connexion.jsp";
    public static final String ACCUEIL   = "/WEB-INF/accueilTest.jsp";

    public static final String USER      = "user";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( CONNEXION ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        ConnexionMetier connexion = new ConnexionMetier();

        LDAPObject user = connexion.connecterUtilisateur( request );

        // Récupération de la session depuis la requête
        HttpSession session = request.getSession();

        session.setAttribute( USER, user );

        this.getServletContext().getRequestDispatcher( ACCUEIL ).forward( request, response );

        // response.sendRedirect( ACCUEIL );
    }
}