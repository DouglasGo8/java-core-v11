package com.packtpub.jdk.core.eleven.chapter06.hibernate;

import com.packtpub.jdk.core.eleven.chapter06.hibernate.model.Person;
import org.junit.Test;

import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

import static java.lang.System.out;

/**
 *
 */
public class App {


    @Test
    public void howToHibernate() {

        var emf = Persistence.createEntityManagerFactory("jpa-demo");
        var em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            var person = new Person("Douglas", "Street: No Name", LocalDate.now(), 1);
            em.persist(person);
            em.getTransaction().commit();


            Query q = em.createQuery("select p from Person p", Person.class);
            List<Person> list = q.getResultList();
            list.forEach(out::println);

        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }

    }
}
