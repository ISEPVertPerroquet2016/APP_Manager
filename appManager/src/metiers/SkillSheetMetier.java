package metiers;

import dao.SkillSheetDao;

public class SkillSheetMetier extends SkillManagementMetier
{

    public SkillSheetMetier( SkillSheetDao skillSheetDao )
    {
        super( skillSheetDao );
    }
}
