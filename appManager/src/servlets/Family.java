package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.FamilyDao;
import entities.FamilyObject;
import metiers.FamilyMetier;

/**
 * Servlet implementation class Family
 */
@WebServlet( "/Family" )
public class Family extends HttpServlet
{
    private static final long  serialVersionUID = 1L;

    public static final String DAO_FACTORY      = "daoFactory";
    public static final String ATT_FORM_FAMILY  = "formFamily";
    public static final String FAMILY           = "family";
    public static final String FAMILY_METIER    = "familyMetier";
    public static final String VUE              = "/pages/nouvelleFamille.jsp";

    private FamilyDao          familyDao;

    /**
     * methode instanciée une seule fois à la création du servlet lors du
     * lancement de l'application,
     */
    public void init() throws ServletException
    {
        // Récupération d'une instance de notre FamilyDAO 
        this.familyDao = ( (DAOFactory) getServletContext().getAttribute( DAO_FACTORY ) ).getFamilyDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        // Transmission vers la page en charge de l'affichage des résultats 
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {

        FamilyMetier familyMetier = new FamilyMetier( familyDao );

        FamilyObject family = familyMetier.createFamily( request );

        request.setAttribute( FAMILY, family );
        request.setAttribute( ATT_FORM_FAMILY, familyMetier );

        // Transmission vers la page en charge de l'affichage des résultats 
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

}
