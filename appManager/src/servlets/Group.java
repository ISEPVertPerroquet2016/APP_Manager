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
import dao.classesDAO.GroupDao;
import entities.GroupObject;
import entities.Utilisateur;
import metiers.metier.GroupMetier;

/**
 * Servlet implementation class Group
 */
@WebServlet( "/Group" )
public class Group extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private GroupDao          groupDao;

    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.groupDao = ( (DAOFactory) getServletContext().getAttribute( DAOUtilitaire.DAO_FACTORY ) ).getGroupDao();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( DAOUtilitaire.USER );

        if ( utilisateur != null && DAOUtilitaire.RESPONSABLE.equals( utilisateur.getType() ) )
        {

            this.getServletContext().getRequestDispatcher( DAOUtilitaire.GROUP_VIEW_FORWARD ).forward( request,
                    response );
        } else if ( utilisateur != null && ( DAOUtilitaire.ELEVE.equals( utilisateur.getType() )
                || DAOUtilitaire.PROFESSEUR.equals( utilisateur.getType() ) ) )
        {
            response.sendRedirect( DAOUtilitaire.SKILLS_SHEET_VIEW_REDIRECT );
        } else
        {
            response.sendRedirect( DAOUtilitaire.CONNEXION_VIEW_REDIRECT );
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        GroupMetier groupMetier = new GroupMetier( groupDao );

        GroupObject group = groupMetier.createGroup( request );

        request.setAttribute( DAOUtilitaire.GROUP, group );

        request.setAttribute( DAOUtilitaire.ATT_FORM_GROUP, groupMetier );

        // Transmission vers la page en charge de l'affichage des résultats 
        this.getServletContext().getRequestDispatcher( DAOUtilitaire.GROUP_VIEW_FORWARD ).forward( request, response );
    }

}
