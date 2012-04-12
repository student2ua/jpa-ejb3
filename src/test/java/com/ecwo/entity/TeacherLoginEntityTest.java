package com.ecwo.entity;

import com.ecwo.service.CrudService;
import com.ecwo.service.CrudServiceBean;
import junit.framework.TestCase;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 04.04.12
 * Time: 17:42
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 */
public class TeacherLoginEntityTest extends TestCase {
    Context context =null;
      @Override
    public void setUp() throws Exception {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        props.put(Context.PROVIDER_URL, "localhost:1099");
        context = new InitialContext(props);
    }

    public void testCRUD() {
       /* EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecwo_test");
        EntityManager em = emf.createEntityManager();*/

        try {
            CrudService service = (CrudService) context.lookup(CrudService.class.getName());
            //CrudService service = new CrudServiceBean();

            // create and persist an employee
         //   em.getTransaction().begin();
            TeacherLoginEntity teacherLogin = new TeacherLoginEntity();
            teacherLogin.setHumanid(-1);
            teacherLogin.setLogin("ololo");
            teacherLogin.setPassword("gogogo");
            teacherLogin = service.create(teacherLogin);
          //  em.getTransaction().commit();
            assertNotNull(teacherLogin.getId());
            teacherLogin.setHumanid(null);
            System.out.println("Persisted " + teacherLogin);
            // find a specific employee
            teacherLogin = service.find(TeacherLoginEntity.class, teacherLogin.getId());
            System.out.println(teacherLogin);
            assertNotNull(teacherLogin.getHumanid());
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
           /* em.close();
            emf.close();*/
        }
    }

    public void testFindByLogin() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecwo_test");
        EntityManager em = emf.createEntityManager();
        String login = "ololo2";
        try {
            CrudService service = new CrudServiceBean();

            // create and persist an employee
            em.getTransaction().begin();
            TeacherLoginEntity teacherLogin = new TeacherLoginEntity();
            teacherLogin.setHumanid(-1);
            teacherLogin.setLogin(login);
            teacherLogin.setPassword("gogogo2");

            teacherLogin = service.create(teacherLogin);
            em.getTransaction().commit();
            assertNotNull(teacherLogin.getId());
            teacherLogin.setHumanid(null);
            System.out.println("Persisted " + teacherLogin);
            // find a specific employee
            Map map = new HashMap();
            map.put("name", login);
            teacherLogin = (TeacherLoginEntity) service.findWithNamedQuery(TeacherLoginEntity.FIND_BY_LOGIN_Q, map, 1).get(0);
            System.out.println(teacherLogin);
            assertNotNull(teacherLogin.getHumanid());
        } finally {
            em.close();
            emf.close();
        }
    }
}
