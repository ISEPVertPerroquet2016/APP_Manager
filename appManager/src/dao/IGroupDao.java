package dao;

import entities.GroupObject;
import entities.SkillObject;


public interface IGroupDao {
	
	GroupObject find( int groupID );
	void create( GroupObject group );

}
