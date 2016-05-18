package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metiers.FamilyMetier;

/**
 * Servlet implementation class Family
 */
@WebServlet( "/SkillManagement" )
public class SkillManagement extends HttpServlet
{
    private static final long  serialVersionUID = 1L;

    public static final String VUE              = "/pages/gerercomp.jsp";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        /* Transmission vers la page en charge de l'affichage des résultats */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        /* Initialisation de l'objet Java et récupération des messages */
        FamilyMetier familyMetier = new FamilyMetier();

        /* Enregistrement de la famille */

        /* Transmission vers la page en charge de l'affichage des résultats */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

}
