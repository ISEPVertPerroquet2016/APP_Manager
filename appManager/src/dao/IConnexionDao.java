package dao;

import entities.Utilisateur;

public interface IConnexionDao
{
    Utilisateur find( int number );
}
