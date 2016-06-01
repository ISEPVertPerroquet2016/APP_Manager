package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.SkillDao;
import entities.SkillObject;
import entities.Utilisateur;
import metiers.SkillMetier;

/**
 * Servlet implementation class Family
 */
@WebServlet( "/Skill" )
public class Skill extends HttpServlet
{
    private static final long  serialVersionUID = 1L;

    public static final String DAO_FACTORY      = "daoFactory";
    public static final String SKILL            = "skill";
    public static final String ATT_FORM_SKILL   = "formSkill";
    public static final String FAMILY_NAME      = "familyName";
    public static final String USER             = "user";
    public static final String ELEVE            = "eleve";
    public static final String VUE              = "/pages/nouvelleComp.jsp";

    private SkillDao           skillDao;

    /**
     * methode instanciée une seule fois à la création du servlet lors du
     * lancement de l'application,
     */
    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.skillDao = ( (DAOFactory) getServletContext().getAttribute( DAO_FACTORY ) ).getSkillDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( USER );

        if ( utilisateur != null && ELEVE.equals( utilisateur.getType() ) )
        {
            String nameFamily = request.getParameter( "nameFamily" );
            request.setAttribute( FAMILY_NAME, nameFamily );

            // Transmission vers la page en charge de l'affichage des résultats 
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        // Initialisation de l'objet Metier
        SkillMetier skillMetier = new SkillMetier( skillDao );

        SkillObject skill = skillMetier.createSkill( request );

        /* Enregistrement de la famille */
        request.setAttribute( SKILL, skill );
        request.setAttribute( ATT_FORM_SKILL, skillMetier );

        /* Transmission vers la page en charge de l'affichage des résultats */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

}
