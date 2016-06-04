package entities;

public class Utilisateur
{
    private int    userID;

    private String firstname;

    private String surname;

    private int    idGroup;

    private String type;

    public int getUserID()
    {
        return userID;
    }

    public void setUserID( int userID )
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

    public int getIdGroup()
    {
        return idGroup;
    }

    public void setIdGroup( int idGroup )
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
