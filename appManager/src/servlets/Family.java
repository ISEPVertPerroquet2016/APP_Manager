package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metiers.FamilyMetier;

/**
 * Servlet implementation class Family
 */
@WebServlet( "/Family" )
public class Family extends HttpServlet
{
    private static final long  serialVersionUID = 1L;

    public static final String FAMILY           = "family";
    public static final String VUE              = "/pages/nouvelleFamille.jsp";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        /* Transmission vers la page en charge de l'affichage des résultats */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        /* Initialisation de l'objet Java et récupération des messages */
        FamilyMetier familyMetier = new FamilyMetier();

        String family = familyMetier.createFamily( request );

        /* Enregistrement de la famille */
        request.setAttribute( FAMILY, family );

        /* Transmission vers la page en charge de l'affichage des résultats */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

}
