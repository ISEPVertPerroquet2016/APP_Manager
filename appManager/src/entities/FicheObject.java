package entities;

public class FicheObject
{
    private String nameFamily;

    private String nameSkill;

    private int    userID;

    private String niveau;

    private String observation;

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
     * @return the nameSkill
     */
    public String getNameSkill()
    {
        return nameSkill;
    }

    /**
     * @param nameSkill
     *            the nameSkill to set
     */
    public void setNameSkill( String nameSkill )
    {
        this.nameSkill = nameSkill;
    }

    /**
     * @return the userID
     */
    public int getUserID()
    {
        return userID;
    }

    /**
     * @param userID
     *            the userID to set
     */
    public void setUserID( int userID )
    {
        this.userID = userID;
    }

    /**
     * @return the level
     */
    public String getNiveau()
    {
        return niveau;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setNiveau( String niveau )
    {
        this.niveau = niveau;
    }

    /**
     * @return the observation
     */
    public String getObservation()
    {
        return observation;
    }

    /**
     * @param observation
     *            the observation to set
     */
    public void setObservation( String observation )
    {
        this.observation = observation;
    }

}
