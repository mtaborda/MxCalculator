package models;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.*;

import static org.junit.Assert.*;
/**
 * Created by maximiliano on 15/10/14.
 */
public class CalculatorRepositoryTest {
    private SessionFactory sessionFactory;
    private org.hibernate.Session session;

    @Before
    public void before() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Session.class)
                .addAnnotatedClass(SessionEntry.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
                .setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void testSaveSession() {
        Session calcSession = new Session();
        SessionEntry entry = new SessionEntry("2+2", "4");
        calcSession.addEntry(entry);

        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(calcSession);
        session.getTransaction().commit();
        int calcSessionId = calcSession.getId();
        int entryId = entry.getId();
        calcSession = null;
        entry = null;

        assertEquals(null, calcSession);
        assertEquals(null, entry);

        session = sessionFactory.openSession();
        session.beginTransaction();
        calcSession = (Session) session.get(Session.class, calcSessionId);
        entry = (SessionEntry) session.get(SessionEntry.class, entryId);


        assertEquals(calcSessionId, calcSession.getId());
        assertFalse(calcSession.getEntries().isEmpty());
        assertEquals(entryId, entry.getId());
    }

    @After
    public void after() {
        session.close();
        sessionFactory.close();
    }
}
