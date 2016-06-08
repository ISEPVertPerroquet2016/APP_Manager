package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOUtilitaire;
import entities.Utilisateur;

/**
 * Servlet implementation class Accueil
 */
@WebServlet( "/Accueil" )
public class Accueil extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( DAOUtilitaire.USER );

        if ( utilisateur != null && ( DAOUtilitaire.PROFESSEUR.equals( utilisateur.getType() )
                || DAOUtilitaire.RESPONSABLE.equals( utilisateur.getType() ) ) )
        {
            this.getServletContext().getRequestDispatcher( DAOUtilitaire.ACCUEIL_VIEW_FORWARD ).forward( request,
                    response );
        } else if ( utilisateur != null && DAOUtilitaire.ELEVE.equals( utilisateur.getType() ) )
        {
            response.sendRedirect( DAOUtilitaire.SKILLS_SHEET_VIEW_REDIRECT );
        } else
        {
            response.sendRedirect( DAOUtilitaire.CONNEXION_VIEW_REDIRECT );
        }
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {

    }

}
