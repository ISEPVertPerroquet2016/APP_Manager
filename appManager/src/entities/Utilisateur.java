package entities;

public class Utilisateur
{
    private Long   userID;

    private String firstname;

    private String surname;

    private Long   idGroup;

    private String type;

    public Long getUserID()
    {
        return userID;
    }

    public void setUserID( Long userID )
    {
        this.userID = userID;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname( String surname )
    {
        this.surname = surname;
    }

    public Long getIdGroup()
    {
        return idGroup;
    }

    public void setIdGroup( Long idGroup )
    {
        this.idGroup = idGroup;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname( String firstname )
    {
        this.firstname = firstname;
    }

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType( String type )
    {
        this.type = type;
    }

}
