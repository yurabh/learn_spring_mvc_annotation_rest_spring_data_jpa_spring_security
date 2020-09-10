package com.dao.daoFactory;

import com.dao.CrudDao;

public abstract class DAOFactory {
    public static final int MYSQL = 1;

    public abstract CrudDao getManDAO();

    public static DAOFactory getDaoFactory(int factoryId) {
        switch (factoryId) {
            case MYSQL:
                return new MySQLDAOFactory();
            default:
                return new MySQLDAOFactory();
        }
    }
}