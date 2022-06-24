package uz.jl.testing;/*
  @author "Doniyor Erkinov"
  @since 6/22/2022 5:46 PM (Wednesday)
  trello_VU/IntelliJ IDEA
*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import uz.jl.configs.HibernateConfigurer;
import uz.jl.domains.auth.Subject;

public class Test {
    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.setSubject_name("Math");
        subject.setCreatedBy(1L);
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();

        currentSession.persist(subject);
        currentSession.getTransaction().commit();

    }
}
