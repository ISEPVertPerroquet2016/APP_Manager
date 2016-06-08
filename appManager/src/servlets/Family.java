package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.DAOUtilitaire;
import dao.FamilyDao;
import entities.FamilyObject;
import entities.Utilisateur;
import metiers.FamilyMetier;

/**
 * Servlet implementation class Family
 */
@WebServlet( "/Family" )
public class Family extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private FamilyDao         familyDao;

    /**
     * methode instanciée une seule fois à la création du servlet lors du
     * lancement de l'application,
     */
    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.familyDao = ( (DAOFactory) getServletContext().getAttribute( DAOUtilitaire.DAO_FACTORY ) ).getFamilyDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( DAOUtilitaire.USER );

        if ( utilisateur != null && DAOUtilitaire.RESPONSABLE.equals( utilisateur.getType() ) )
        {

            this.getServletContext().getRequestDispatcher( DAOUtilitaire.FAMILY_VIEW_FORWARD ).forward( request,
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

        FamilyMetier familyMetier = new FamilyMetier( familyDao );

        FamilyObject family = familyMetier.createFamily( request );

        request.setAttribute( DAOUtilitaire.FAMILY, family );
        request.setAttribute( DAOUtilitaire.ATT_FORM_FAMILY, familyMetier );

        // Transmission vers la page en charge de l'affichage des résultats 
        this.getServletContext().getRequestDispatcher( DAOUtilitaire.FAMILY_VIEW_FORWARD ).forward( request, response );
    }

}
