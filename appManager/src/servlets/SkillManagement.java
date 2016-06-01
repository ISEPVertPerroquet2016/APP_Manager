package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.SkillManagementDao;
import entities.FamilyObject;
import entities.Utilisateur;
import metiers.SkillManagementMetier;

/**
 * Servlet implementation class Family
 */
@WebServlet( "/SkillManagement" )
public class SkillManagement extends HttpServlet
{
    private static final long  serialVersionUID = 1L;

    public static final String DAO_FACTORY      = "daoFactory";
    public static final String VUE              = "/pages/gerercomp.jsp";
    public static final String FAMILIES         = "families";
    public static final String USER             = "user";
    public static final String ELEVE            = "eleve";

    private SkillManagementDao skillManagementDao;

    /**
     * methode instanciée une seule fois à la création du servlet lors du
     * lancement de l'application,
     */
    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.skillManagementDao = ( (DAOFactory) getServletContext().getAttribute( DAO_FACTORY ) )
                .getSkillManagementDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( USER );

        if ( utilisateur != null && ELEVE.equals( utilisateur.getType() ) )
        {
            // Initialisation de l'objet metier
            SkillManagementMetier skillManagementMetier = new SkillManagementMetier( skillManagementDao );

            List<String> familiesNames = skillManagementMetier.getFamiliesName( request );
            List<FamilyObject> families = skillManagementMetier.getFamilies( familiesNames );

            request.setAttribute( FAMILIES, families );

            // Transmission vers la page en charge de l'affichage des résultats 
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }

    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {

    }

}
