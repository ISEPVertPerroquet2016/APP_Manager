package entities;

import java.io.Serializable;

/**
 * bean used for a skill
 * 
 * @author Arnaud Tessandier
 */

public class SkillObject implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String            nameSkill;
    private String            observationTest;
    private int               coefficient;
    private String            basicSkill;
    private String            basicRequired;
    private String            basicFailed;
    private String            mediumSkill;
    private String            mediumRequired;
    private String            mediumFailed;
    private String            nameFamily;

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
     * @return the observationTest
     */
    public String getObservationTest()
    {
        return observationTest;
    }

    /**
     * @param observationTest
     *            the observationTest to set
     */
    public void setObservationTest( String observationTest )
    {
        this.observationTest = observationTest;
    }

    /**
     * @return the coefficient
     */
    public int getCoefficient()
    {
        return coefficient;
    }

    /**
     * @param coefficient
     *            the coefficient to set
     */
    public void setCoefficient( int coefficient )
    {
        this.coefficient = coefficient;
    }

    /**
     * @return the basicSkill
     */
    public String getBasicSkill()
    {
        return basicSkill;
    }

    /**
     * @param basicSkill
     *            the basicSkill to set
     */
    public void setBasicSkill( String basicSkill )
    {
        this.basicSkill = basicSkill;
    }

    /**
     * @return the basicRequired
     */
    public String getBasicRequired()
    {
        return basicRequired;
    }

    /**
     * @param basicRequired
     *            the basicRequired to set
     */
    public void setBasicRequired( String basicRequired )
    {
        this.basicRequired = basicRequired;
    }

    /**
     * @return the basicFailed
     */
    public String getBasicFailed()
    {
        return basicFailed;
    }

    /**
     * @param basicFailed
     *            the basicFailed to set
     */
    public void setBasicFailed( String basicFailed )
    {
        this.basicFailed = basicFailed;
    }

    /**
     * @return the mediumSkill
     */
    public String getMediumSkill()
    {
        return mediumSkill;
    }

    /**
     * @param mediumSkill
     *            the mediumSkill to set
     */
    public void setMediumSkill( String mediumSkill )
    {
        this.mediumSkill = mediumSkill;
    }

    /**
     * @return the mediumRequired
     */
    public String getMediumRequired()
    {
        return mediumRequired;
    }

    /**
     * @param mediumRequired
     *            the mediumRequired to set
     */
    public void setMediumRequired( String mediumRequired )
    {
        this.mediumRequired = mediumRequired;
    }

    /**
     * @return the mediumFailed
     */
    public String getMediumFailed()
    {
        return mediumFailed;
    }

    /**
     * @param mediumFailed
     *            the mediumFailed to set
     */
    public void setMediumFailed( String mediumFailed )
    {
        this.mediumFailed = mediumFailed;
    }

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
}
