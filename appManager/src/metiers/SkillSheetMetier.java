package metiers;

import java.util.List;

import dao.DAOException;
import dao.SkillSheetDao;
import entities.GroupObject;

public class SkillSheetMetier extends SkillManagementMetier
{
    private SkillSheetDao skillSheetDao;

    public SkillSheetMetier( SkillSheetDao skillSheetDao )
    {
        super( skillSheetDao );
        this.skillSheetDao = skillSheetDao;
    }

    public List<GroupObject> getGroups()
    {
        List<GroupObject> groups = null;

        try
        {
            groups = skillSheetDao.findGroups();

        } catch ( DAOException e )
        {
            e.printStackTrace();
        }

        return groups;
    }
}
