package dao;

import java.util.List;

import entities.FamilyObject;

public interface ISkillManagementDao
{
    public List<FamilyObject> findFamiliesNames();

    public List<FamilyObject> findFamilies( List<FamilyObject> families );
}
