package com.dao.daoFactory;

import com.dao.CrudDao;
import com.dao.MySQLManDAO;

public class MySQLDAOFactory extends DAOFactory {
    public CrudDao getManDAO() {
        return new MySQLManDAO();
    }
}
