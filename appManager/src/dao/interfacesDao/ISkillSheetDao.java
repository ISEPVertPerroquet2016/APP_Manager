package dao.interfacesDao;

import java.util.List;

import entities.GroupObject;
import entities.Utilisateur;

public interface ISkillSheetDao
{
    List<GroupObject> findGroups();

    List<Utilisateur> findElevesByGroup( int id_group );
}
