package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Utilisateur;

@WebServlet( "/SkillsSheet" )
public class SkillsSheet extends HttpServlet
{
    public static final String SKILLS_SHEET_VIEW = "/pages/affichercomm.jsp";
    public static final String CONNEXION_VIEW    = "/appManager/Connexion";

    public static final String USER              = "user";
    public static final String PROFESSEUR        = "professeur";
    public static final String ELEVE             = "eleve";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( USER );

        if ( utilisateur != null )
        {
            if ( PROFESSEUR.equals( utilisateur.getType() ) || ELEVE.equals( utilisateur.getType() ) )
            {
                this.getServletContext().getRequestDispatcher( SKILLS_SHEET_VIEW ).forward( request, response );
            } else
            {
                response.sendRedirect( CONNEXION_VIEW );
            }
        }
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {

    }
}