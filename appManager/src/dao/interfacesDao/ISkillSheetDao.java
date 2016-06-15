package dao.interfacesDao;

import java.util.List;
import java.util.Map;

import entities.FicheObject;
import entities.GroupObject;
import entities.Utilisateur;

public interface ISkillSheetDao
{
    List<GroupObject> findGroups();

    List<Utilisateur> findElevesByGroup( int id_group );

    Map<String, FicheObject> findFiches( String nameFamily, int userID );
}
