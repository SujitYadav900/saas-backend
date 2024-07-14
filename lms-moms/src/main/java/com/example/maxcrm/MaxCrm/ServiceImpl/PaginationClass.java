package com.example.maxcrm.MaxCrm.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaginationClass {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected int findCount(String query, Connection con) throws SQLException {
        int found = 0;
        try {
            logger.info("Finding count for Pagination : {} ",query);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                found = rs.getInt(1);
            }
        } catch (SQLException sql) {
            throw sql;
        }
        return found;

    }
}
