package ru.vasire.config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionManager {

    public final ThreadLocal session = new ThreadLocal();
    private final SessionFactory sessionFactory;

    public SessionManager(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }

    public Session currentSession() throws HibernateException {
        Session s = (Session) session.get();
        // открываем новую сессию Session, если она еще не открыта в потоке Thread
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if (s != null)
            s.close();
    }
}
