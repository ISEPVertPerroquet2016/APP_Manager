package dao;

import entities.SkillObject;

public interface ISkillDao
{
    void create( SkillObject skill );

    SkillObject find( String skillName );
}
