package com.ecwo.entity;

import org.junit.Before;
import org.junit.BeforeClass;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 24.04.12
 * Time: 16:38
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * Инициализируем БД и зпаускаем JBoss
 */
public abstract class AbstractTestCase {
    private JNDIUtil jndiUtil = new JNDIUtil();
    	private InMemoryDBUtil databaseUtil = new InMemoryDBUtil();

    	@BeforeClass
    	public static void beforeClass() {
    		(new JNDIUtil()).initializeJNDI();
    		(new InMemoryDBUtil()).removeDatabase();
    	}

    	@Before
    	public void beforeEachTest() {
    		jndiUtil.initializeJNDI();
    		databaseUtil.initializeDatabase(getInitialChangeLog());
    	}

    	protected abstract String getInitialChangeLog();

    	protected JNDIUtil getJndiUtil() {
    		return jndiUtil;
    	}

    	protected InMemoryDBUtil getDatabaseUtil() {
    		return databaseUtil;
    	}

    	protected Connection getConnection() {
    		try {
    			DataSource dataSource = databaseUtil.getDataSource();
    			return dataSource.getConnection();
    		} catch (SQLException e) {
    			throw new RuntimeException("Failed to create db connection.", e);
    		}
    	}

    	protected void removeDatabase() {
    		databaseUtil.removeDatabase();
    	}

}
