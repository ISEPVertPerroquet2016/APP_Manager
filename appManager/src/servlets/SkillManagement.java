package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Family;
import metiers.SkillManagementMetier;

/**
 * Servlet implementation class Family
 */
@WebServlet( "/SkillManagement" )
public class SkillManagement extends HttpServlet
{
    private static final long  serialVersionUID = 1L;

    public static final String VUE              = "/pages/gerercomp.jsp";
    public static final String FAMILIES         = "families";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {

        // Initialisation de l'objet metier
        SkillManagementMetier skillManagementMetier = new SkillManagementMetier();

        List<String> familiesNames = skillManagementMetier.getFamiliesName( request );
        List<Family> families = skillManagementMetier.getFamilies( request, familiesNames );

        request.setAttribute( FAMILIES, families );

        // Transmission vers la page en charge de l'affichage des résultats
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {

    }

}
