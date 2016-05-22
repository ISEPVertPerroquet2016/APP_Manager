package entities;

import java.util.List;

public class FamilyObject
{
    private String            nameFamily;
    private List<SkillObject> skills;

    /**
     * @return the nameFamily
     */
    public String getNameFamily()
    {
        return nameFamily;
    }

    /**
     * @param nameFamily
     *            the nameFamily to set
     */
    public void setNameFamily( String nameFamily )
    {
        this.nameFamily = nameFamily;
    }

    /**
     * @return the skills
     */
    public List<SkillObject> getSkills()
    {
        return skills;
    }

    /**
     * @param skills
     *            the skills to set
     */
    public void setSkills( List<SkillObject> skills )
    {
        this.skills = skills;
    }
}
