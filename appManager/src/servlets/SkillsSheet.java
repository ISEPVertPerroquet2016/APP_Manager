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
import dao.DAOUtilitaire;
import dao.SkillSheetDao;
import entities.FamilyObject;
import entities.GroupObject;
import entities.Utilisateur;
import metiers.SkillSheetMetier;

@WebServlet( "/SkillsSheet" )
public class SkillsSheet extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    private SkillSheetDao     skillSheetDao;

    /**
     * methode instanciée une seule fois à la création du servlet lors du
     * lancement de l'application,
     */
    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.skillSheetDao = ( (DAOFactory) getServletContext().getAttribute( DAOUtilitaire.DAO_FACTORY ) )
                .getSkillSheetDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( DAOUtilitaire.USER );

        if ( utilisateur != null )
        {
            if ( DAOUtilitaire.PROFESSEUR.equals( utilisateur.getType() )
                    || DAOUtilitaire.ELEVE.equals( utilisateur.getType() ) )
            {
                SkillSheetMetier skillSheetMetier = new SkillSheetMetier( skillSheetDao );

                List<String> familiesNames = skillSheetMetier.getFamiliesName();
                List<FamilyObject> families = skillSheetMetier.getFamilies( familiesNames );
                List<GroupObject> groups = skillSheetMetier.getGroups();

                session.setAttribute( DAOUtilitaire.FAMILIES, families );
                session.setAttribute( DAOUtilitaire.SHEET_GROUPS, groups );

                this.getServletContext().getRequestDispatcher( DAOUtilitaire.SKILLS_SHEET_VIEW_FORWARD )
                        .forward( request, response );
            } else
            {
                response.sendRedirect( DAOUtilitaire.CONNEXION_VIEW_REDIRECT );
            }
        }
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        SkillSheetMetier skillSheetMetier = new SkillSheetMetier( skillSheetDao );

        List<Utilisateur> eleves = skillSheetMetier.getElevesByGroup( request );

        request.setAttribute( DAOUtilitaire.SHEET_ELEVES, eleves );

        this.getServletContext().getRequestDispatcher( DAOUtilitaire.SKILLS_SHEET_VIEW_FORWARD )
                .forward( request, response );
    }
}