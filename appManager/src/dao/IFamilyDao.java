package dao;

import entities.FamilyObject;

public interface IFamilyDao
{
    void create( FamilyObject family );

    FamilyObject find( String familyName );
}
