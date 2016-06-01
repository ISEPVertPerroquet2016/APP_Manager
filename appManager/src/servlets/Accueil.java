package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Utilisateur;

/**
 * Servlet implementation class Accueil
 */
@WebServlet( "/Accueil" )
public class Accueil extends HttpServlet
{
    private static final long  serialVersionUID = 1L;

    public static final String ACCUEIL_VIEW     = "/pages/AccueilTuteur.jsp";
    public static final String CONNEXION_VIEW   = "/appManager/Connexion";
    public static final String SKILL_SHEET_VIEW = "/appManager/SkillsSheet";
    public static final String USER             = "user";
    public static final String PROFESSEUR       = "professeur";
    public static final String ELEVE            = "eleve";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( USER );

        if ( utilisateur != null && PROFESSEUR.equals( utilisateur.getType() ) )
        {
            this.getServletContext().getRequestDispatcher( ACCUEIL_VIEW ).forward( request, response );
        } else if ( utilisateur != null && ELEVE.equals( utilisateur.getType() ) )
        {
            response.sendRedirect( SKILL_SHEET_VIEW );
        } else
        {
            response.sendRedirect( CONNEXION_VIEW );
        }
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {

    }

}
