package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOUtilitaire;
import dao.classesDAO.DAOFactory;
import dao.classesDAO.SkillSheetDao;
import entities.FamilyObject;
import entities.FicheObject;
import entities.GroupObject;
import entities.Utilisateur;
import metiers.metier.SkillSheetMetier;

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
                    || DAOUtilitaire.ELEVE.equals( utilisateur.getType() )
                    || DAOUtilitaire.RESPONSABLE.equals( utilisateur.getType() ) )
            {
                SkillSheetMetier skillSheetMetier = new SkillSheetMetier( skillSheetDao );

                List<FamilyObject> families = skillSheetMetier.getFamilies();

                session.setAttribute( DAOUtilitaire.FAMILIES, families );

                if ( !DAOUtilitaire.ELEVE.equals( utilisateur.getType() ) )
                {
                    List<GroupObject> groups = skillSheetMetier.getGroups();
                    session.setAttribute( DAOUtilitaire.SHEET_GROUPS, groups );
                }

                if ( DAOUtilitaire.ELEVE.equals( utilisateur.getType() ) )
                {
                    List<Utilisateur> eleves = skillSheetMetier.getElevesByGroup( request, utilisateur.getIdGroup() );
                    request.setAttribute( DAOUtilitaire.SHEET_ELEVES, eleves );
                }

                this.getServletContext().getRequestDispatcher( DAOUtilitaire.SKILLS_SHEET_VIEW_FORWARD )
                        .forward( request, response );
            }
        } else
        {
            response.sendRedirect( DAOUtilitaire.CONNEXION_VIEW_REDIRECT );
        }
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( DAOUtilitaire.USER );
        List<Utilisateur> eleves = null;
        Map<String, FicheObject> ficheSelected = null;
        Map<String, String> fichesCollectives = null;
        int groupSelected = 0;

        if ( utilisateur != null )
        {
            SkillSheetMetier skillSheetMetier = new SkillSheetMetier( skillSheetDao );

            if ( DAOUtilitaire.RESPONSABLE.equals( utilisateur.getType() )
                    || DAOUtilitaire.PROFESSEUR.equals( utilisateur.getType() ) )
            {
                eleves = skillSheetMetier.getElevesByGroup( request );
                if ( eleves != null && !eleves.isEmpty() )
                {
                    groupSelected = eleves.get( 0 ).getIdGroup();
                }
            } else
            {
                eleves = skillSheetMetier.getElevesByGroup( request, utilisateur.getIdGroup() );
                groupSelected = utilisateur.getIdGroup();
            }

            List<FamilyObject> families = (List<FamilyObject>) session.getAttribute( DAOUtilitaire.FAMILIES );
            FamilyObject familySelected = skillSheetMetier.getFamilyByName( request, families );
            int eleveSelectedID = skillSheetMetier.getEleveIDSelected( request );

            if ( familySelected != null && eleveSelectedID > 0 )
            {
                if ( DAOUtilitaire.RESPONSABLE.equals( utilisateur.getType() )
                        || DAOUtilitaire.PROFESSEUR.equals( utilisateur.getType() ) )
                {
                    skillSheetMetier.editFiche( request, familySelected, eleveSelectedID );
                }

                ficheSelected = skillSheetMetier.getFiche( familySelected.getNameFamily(),
                        eleveSelectedID );
            }

            if ( familySelected != null && groupSelected > 0 )
            {

                if ( DAOUtilitaire.RESPONSABLE.equals( utilisateur.getType() )
                        || DAOUtilitaire.PROFESSEUR.equals( utilisateur.getType() ) )
                {
                    //skillSheetMetier.editFicheCollective( request, familySelected, groupSelected );
                }

                fichesCollectives = skillSheetMetier.getFicheCollective( familySelected.getNameFamily(),
                        groupSelected );
            }

            request.setAttribute( DAOUtilitaire.FAMILY_SELECTED, familySelected );
            request.setAttribute( DAOUtilitaire.SHEET_ELEVES, eleves );
            request.setAttribute( DAOUtilitaire.ELEVE_SELECTED, eleveSelectedID );
            request.setAttribute( DAOUtilitaire.FICHE_SELECTED, ficheSelected );
            request.setAttribute( DAOUtilitaire.FICHE_COLLECTIVE_SELECTED, fichesCollectives );

            this.getServletContext().getRequestDispatcher( DAOUtilitaire.SKILLS_SHEET_VIEW_FORWARD )
                    .forward( request, response );
        }

    }
}