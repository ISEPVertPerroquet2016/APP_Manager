package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOUtilitaire;
import dao.classesDAO.DAOFactory;
import dao.classesDAO.SkillDao;
import entities.SkillObject;
import entities.Utilisateur;
import metiers.metier.SkillMetier;

/**
 * Servlet implementation class Family
 */
@WebServlet( "/Skill" )
public class Skill extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private SkillDao          skillDao;

    /**
     * methode instanciée une seule fois à la création du servlet lors du
     * lancement de l'application,
     */
    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.skillDao = ( (DAOFactory) getServletContext().getAttribute( DAOUtilitaire.DAO_FACTORY ) ).getSkillDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( DAOUtilitaire.USER );

        if ( utilisateur != null && DAOUtilitaire.RESPONSABLE.equals( utilisateur.getType() ) )
        {
            String nameFamily = request.getParameter( "nameFamily" );
            request.setAttribute( DAOUtilitaire.FAMILY_NAME, nameFamily );

            // Transmission vers la page en charge de l'affichage des résultats 
            this.getServletContext().getRequestDispatcher( DAOUtilitaire.SKILL_VIEW_FORWARD ).forward( request,
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
        // Initialisation de l'objet Metier
        SkillMetier skillMetier = new SkillMetier( skillDao );

        SkillObject skill = skillMetier.createSkill( request );

        /* Enregistrement de la famille */
        request.setAttribute( DAOUtilitaire.SKILL, skill );
        request.setAttribute( DAOUtilitaire.FAMILY_NAME, skill.getNameFamily() );
        request.setAttribute( DAOUtilitaire.ATT_FORM_SKILL, skillMetier );

        /* Transmission vers la page en charge de l'affichage des résultats */
        // response.sendRedirect( "/appManager/Skill?nameFamily=" + skill.getNameFamily() );

        this.getServletContext().getRequestDispatcher( DAOUtilitaire.SKILL_VIEW_FORWARD ).forward( request, response );
    }

}
