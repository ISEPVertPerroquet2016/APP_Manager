package metiers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.DAOException;

import dao.GroupDao;
import entities.FamilyObject;
import entities.GroupObject;

public class GroupMetier {
	private static final String GROUP    = "groupe";
    

    private GroupDao           groupDao;
    private String              resultat;
    private Map<String, String> erreurs   = new HashMap<String, String>();

    public GroupMetier( GroupDao groupDao )
    {
        this.groupDao = groupDao;
    }
  
    
    public GroupObject createGroup( HttpServletRequest request )
    {
    	int groupID = Integer.parseInt(getValeurChamp( request, GROUP ));
    	
        GroupObject group = new GroupObject();

        try
        {
        	group.setGroupID(groupID);
        	
           // traiterName( groupeName, group );
            if ( erreurs.isEmpty() )
            {
                groupDao.create( group );
                resultat = "Succès de l'opération, le groupe: " + groupID + " a bien été ajoutée";
            } else
            {
                resultat = "Echec de l'opération";
            }
        } catch ( DAOException e )
        {
            resultat = "Echec de l'opération, une erreur imprévue est survenue";
            e.printStackTrace();
        }

        return group;
    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp )
    {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 )
        {
            return null;
        } else
        {
            return valeur;
        }
    }
   
}
