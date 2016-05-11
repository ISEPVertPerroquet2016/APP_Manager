package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Eleve
{
    @Id
    @Column( name = "student_number" )
    private long   StudentNumber;

    @Column( name = "firstname" )
    private String firstName;

    @Column( name = "surname" )
    private String surname;

    @Column( name = "id_group" )
    private long   idGroup;

    public long getStudentNumber()
    {
        return StudentNumber;
    }

    public void setStudentNumber( long studentNumber )
    {
        StudentNumber = studentNumber;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname( String surname )
    {
        this.surname = surname;
    }

    public long getIdGroup()
    {
        return idGroup;
    }

    public void setIdGroup( long idGroup )
    {
        this.idGroup = idGroup;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

}
