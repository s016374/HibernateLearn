package com.ztx.qa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by s016374 on 15/6/30.
 */
public class NewsTest {
    private SessionFactory sessionFactory;
    private Session session;
    Transaction transaction;

    @Before
    public void setUp() throws Exception {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @Test
    public void test() {
        News news = new News("dc", "ztx", new Date(new java.util.Date().getTime()));
        session.save(news);


    }

    @Test
    public void testSessionCache() {
        News news = (News) session.get(News.class, 1);
        System.out.println();
    }

    @After
    public void tearDown() throws Exception {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}