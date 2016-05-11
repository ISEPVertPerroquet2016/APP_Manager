package entities;

import java.io.Serializable;

/**
 * Utilisateur récupéré de OpenLDAP
 * 
 * @author Gilles Carpentier
 * @version 13/12/2013
 *
 */
public class LDAPObject implements Serializable
{
    private static final long serialVersionUID = 1L;
    public String             nom;
    public String             nomFamille;
    public String             prenom;
    public String             employeeType;
    private String            employeeNumber;
    public String             login;
    public String             password;
    public String             mail;

    public LDAPObject( String login, String password, String nom, String nomFamille, String prenom, String type,
            String numero, String mail )
    {
        this.nom = nom;
        this.nomFamille = nomFamille;
        this.prenom = prenom;
        this.employeeType = type;
        this.employeeNumber = numero;
        this.login = login;
        this.password = password;
        this.mail = mail;
    }

    public LDAPObject()
    {
        super();
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom( String nom )
    {
        this.nom = nom;
    }

    public String getLogin()
    {
        return login;
    }

    public String getEmployeeType()
    {
        return employeeType;
    }

    public String getEmployeeNumber()
    {
        return employeeNumber;
    }

    public String getMail()
    {
        return mail;
    }

    public String getPassword()
    {
        return password;
    }

    public String toString()
    {
        return "login = " + login + " nom = " + nom + " type = " + employeeType + " id = " + employeeNumber;
    }

    public String getNomFamille()
    {
        return nomFamille;
    }

    public void setNomFamille( String nomFamille )
    {
        this.nomFamille = nomFamille;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom( String prenom )
    {
        this.prenom = prenom;
    }
}

// aaa