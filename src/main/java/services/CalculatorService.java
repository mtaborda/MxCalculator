package services;

import models.Calculation;
import models.Calculator;
import models.Session;
import models.SessionEntry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by maximiliano on 13/10/14.
 */
@Stateless
public class CalculatorService {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager entityManager = factory.createEntityManager();
    Calculator calculator = new Calculator();

    public Calculation calculate(String expression) {

        return calculator.calculateExpression(expression);
    }

    public void save(List<Calculation> calculations) {

        Session session = new Session();
        for (Calculation calculation : calculations) {
            session.addEntry(new SessionEntry(calculation));
        }

        entityManager.getTransaction().begin();
        entityManager.persist(session);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
}
