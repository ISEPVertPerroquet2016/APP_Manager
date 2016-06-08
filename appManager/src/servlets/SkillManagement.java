package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOUtilitaire;
import dao.classesDAO.DAOFactory;
import dao.classesDAO.SkillManagementDao;
import entities.FamilyObject;
import entities.Utilisateur;
import metiers.metier.SkillManagementMetier;

/**
 * Servlet implementation class Family
 */
@WebServlet( "/SkillManagement" )
public class SkillManagement extends HttpServlet
{
    private static final long  serialVersionUID = 1L;

    private SkillManagementDao skillManagementDao;

    /**
     * methode instanciée une seule fois à la création du servlet lors du
     * lancement de l'application,
     */
    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.skillManagementDao = ( (DAOFactory) getServletContext().getAttribute( DAOUtilitaire.DAO_FACTORY ) )
                .getSkillManagementDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( DAOUtilitaire.USER );

        if ( utilisateur != null && DAOUtilitaire.RESPONSABLE.equals( utilisateur.getType() ) )
        {
            // Initialisation de l'objet metier
            SkillManagementMetier skillManagementMetier = new SkillManagementMetier( skillManagementDao );

            List<FamilyObject> families = skillManagementMetier.getFamilies();

            request.setAttribute( DAOUtilitaire.FAMILIES, families );

            // Transmission vers la page en charge de l'affichage des résultats 
            this.getServletContext().getRequestDispatcher( DAOUtilitaire.SKILL_MANAGEMENT_VIEW_FORWARD )
                    .forward( request, response );
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
