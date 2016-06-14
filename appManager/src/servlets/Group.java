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
import dao.GroupDao;
import entities.FamilyObject;
import entities.GroupObject;
import entities.Utilisateur;
import metiers.FamilyMetier;
import metiers.GroupMetier;


/**
 * Servlet implementation class Group
 */
@WebServlet("/Group")
public class Group extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CONNEXION_VIEW    = "/pages/index.jsp";
    public static final String SKILLS_SHEET_VIEW = "/appManager/SkillsSheet";
    public static final String GROUP            = "group";
    public static final String GROUP_VIEW            = "/pages/nouveauGroupe.jsp";
    public static final String ATT_FORM_GROUP           = "formGroup";
    
	
	public static final String USER              = "user";
	public static final String PROFESSEUR        = "professeur";
	public static final String ELEVE             = "eleve";
       
	public static final String DAO_FACTORY       = "daoFactory";
	private GroupDao           groupDao;
	 
	 
	 public void init() throws ServletException
	    {
	        // Récupération d'une instance de notre FamilyDAO 
	        this.groupDao = ( (DAOFactory) getServletContext().getAttribute( DAO_FACTORY ) ).getGroupDao();
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	    protected void doGet( HttpServletRequest request, HttpServletResponse response )
	            throws ServletException, IOException
	    {
	    	HttpSession session = request.getSession();
	        Utilisateur utilisateur = (Utilisateur) session.getAttribute( DAOUtilitaire.USER );

	        if ( utilisateur != null && PROFESSEUR.equals( utilisateur.getType() ) )
	        {

	            this.getServletContext().getRequestDispatcher( GROUP_VIEW ).forward( request, response );
	        } else if ( utilisateur != null && ELEVE.equals( utilisateur.getType() ) )
	        {
	            response.sendRedirect( DAOUtilitaire.SKILLS_SHEET_VIEW_REDIRECT );
	        } else
	        {
	            response.sendRedirect( DAOUtilitaire.CONNEXION_VIEW_REDIRECT );
	        }
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroupMetier groupMetier = new GroupMetier( groupDao );

        GroupObject group = groupMetier.createGroup( request );

        request.setAttribute( GROUP, group );
       
        request.setAttribute( ATT_FORM_GROUP, groupMetier );

        // Transmission vers la page en charge de l'affichage des résultats 
        this.getServletContext().getRequestDispatcher( GROUP_VIEW ).forward( request, response );
	}

}
