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

    public Map<String, String> findFichesCollectives( String nameFamily, int groupID );

    void updateFiche( String nameSkill, int userID, String niveau, String observation );

    void createFiche( String nameFamily, String nameSkill, int userID, String niveau, String observation );

    void deleteFiche( String nameSkill, int userID );
}
