package dao;

import java.util.List;

import entities.FamilyObject;

public interface ISkillManagementDao
{
    public List<String> findFamiliesNames();

    public List<FamilyObject> findFamilies( List<String> familiesNames );
}
