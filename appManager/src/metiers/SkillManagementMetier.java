package metiers;

import java.util.List;

import dao.DAOException;
import dao.SkillManagementDao;
import entities.FamilyObject;

public class SkillManagementMetier
{
    private List<FamilyObject> families;
    private List<String>       familiesNames;
    private SkillManagementDao skillManagementDao;

    public SkillManagementMetier( SkillManagementDao skillManagementDao )
    {
        this.skillManagementDao = skillManagementDao;
    }

    public SkillManagementMetier()
    {

    }

    public List<String> getFamiliesName()
    {
        familiesNames = null;

        try
        {
            familiesNames = skillManagementDao.findFamiliesNames();

        } catch ( DAOException e )
        {
            e.printStackTrace();
        }

        return familiesNames;
    }

    public List<FamilyObject> getFamilies( List<String> familiesNames )
    {
        families = null;

        try
        {
            families = skillManagementDao.findFamilies( familiesNames );

        } catch ( DAOException e )
        {
            e.printStackTrace();
        }

        return families;
    }

}
