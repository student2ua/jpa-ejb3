package com.ecwo.businessLogic;

import com.ecwo.entity.TeacherLoginEntity;
import junit.framework.TestCase;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 12.04.12
 * Time: 16:13
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * To change this template use File | Settings | File Templates.
 */
public class TLServiceTest extends TestCase {
    public static final String User_Login = "HomoSapience";
    public static final String User_PASS = "ThePass";
    EJBContainer container;
    Context context;
    DataSource dataSource;
    TLService service;


    public void setUp() throws Exception {
        container = EJBContainer.createEJBContainer();
        context = container.getContext();
        dataSource = (DataSource) context.lookup("java:ecwo_test_DS");
        service = (TLService) context.lookup("java:global/businessLogic/TLService");
    }

    public void tearDown() throws Exception {
        container.close();
    }

    public void testUser_Create() throws Exception {
        TeacherLoginEntity entity;
        entity = service.user_Create(User_Login, User_PASS);
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(entity.getLogin(), User_Login);
    }


    public void testUser_Del() throws Exception {
        TeacherLoginEntity entity = new TeacherLoginEntity();
        entity.setLogin(User_Login);
        entity.setPassword(User_PASS);
        entity = service.user_Create(entity);
        Integer pk = entity.getId();
        entity = new TeacherLoginEntity();
        entity.setId(pk);
        service.user_Del(entity);
        //@todo както по технологичние надо проверять без привязки к конкретной таблице
        Connection conn = dataSource.getConnection();
        		PreparedStatement pStmt = conn.prepareStatement("SELECT COUNT(1)  FROM UNIVERSITYAUDIT.TEACHERLOGIN WHERE ID=?");
        		pStmt.setInt(1, pk);
        		ResultSet rs = pStmt.executeQuery();
        		if (rs.next())
        		{
        			int rowCount = rs.getInt(1);
        			assertEquals(0, rowCount);
        		}


    }

    public void testUser_ChangePassword() throws Exception {

    }

    public void testUser_findByLogin() throws Exception {

    }

    public void testRole_Create() throws Exception {

    }


    public void testRole_Del() throws Exception {

    }

    public void testRole_reName() throws Exception {

    }

    public void testRole_findByName() throws Exception {

    }

    public void testRole_List() throws Exception {

    }

    public void testRoleGroup_Create() throws Exception {

    }


    public void testRoleGroup_Del() throws Exception {

    }

    public void testRoleGroup_findByName() throws Exception {

    }

    public void testRoleGroup_addRole() throws Exception {

    }
}
