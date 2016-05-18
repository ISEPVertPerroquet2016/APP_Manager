package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.SkillObject;
import metiers.SkillMetier;

/**
 * Servlet implementation class Family
 */
@WebServlet( "/Skill" )
public class Skill extends HttpServlet
{
    private static final long  serialVersionUID = 1L;

    public static final String SKILL            = "skill";
    public static final String VUE              = "/pages/nouvelleComp.jsp";

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
        SkillMetier skillMetier = new SkillMetier();

        SkillObject skill = skillMetier.createSkill( request );

        /* Enregistrement de la famille */
        request.setAttribute( SKILL, skill );

        /* Transmission vers la page en charge de l'affichage des résultats */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

}
