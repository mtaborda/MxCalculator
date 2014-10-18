package services;

import models.Session;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Created by maximiliano on 13/10/14.
 */
@Stateless
public class SessionService {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager entityManager = factory.createEntityManager();

    public List<Session> retrieveAll() {
        TypedQuery<Session> query = entityManager.createQuery("SELECT s FROM Session s", Session.class);
        return query.getResultList();
    }

    public Session retrieve(int id) {

        return entityManager.find(Session.class, id);

    }
}
