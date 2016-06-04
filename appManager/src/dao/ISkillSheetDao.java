package dao;

import java.util.List;

import entities.GroupObject;

public interface ISkillSheetDao
{
    List<GroupObject> findGroups();
}
