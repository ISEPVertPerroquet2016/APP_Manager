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
import dao.SkillSheetDao;
import entities.FamilyObject;
import entities.Utilisateur;
import metiers.SkillSheetMetier;

@WebServlet( "/SkillsSheet" )
public class SkillsSheet extends HttpServlet
{
    //views
    public static final String SKILLS_SHEET_VIEW = "/pages/affichercomm.jsp";
    public static final String CONNEXION_VIEW    = "/appManager/Connexion";

    //atributes
    public static final String USER              = "user";
    public static final String PROFESSEUR        = "professeur";
    public static final String ELEVE             = "eleve";
    public static final String FAMILIES          = "families";

    public static final String DAO_FACTORY       = "daoFactory";

    private SkillSheetDao      skillSheetDao;

    /**
     * methode instanciée une seule fois à la création du servlet lors du
     * lancement de l'application,
     */
    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.skillSheetDao = ( (DAOFactory) getServletContext().getAttribute( DAO_FACTORY ) ).getSkillSheetDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( USER );

        if ( utilisateur != null )
        {
            if ( PROFESSEUR.equals( utilisateur.getType() ) || ELEVE.equals( utilisateur.getType() ) )
            {
                SkillSheetMetier skillSheetMetier = new SkillSheetMetier( skillSheetDao );

                List<String> familiesNames = skillSheetMetier.getFamiliesName( request );
                List<FamilyObject> families = skillSheetMetier.getFamilies( familiesNames );

                request.setAttribute( FAMILIES, families );

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