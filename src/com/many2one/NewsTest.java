package com.many2one;

import com.ztx.qa.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    @After
    public void tearDown() throws Exception {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testSave() {
        Customer customer = new Customer("Tom");
        Order order1 = new Order("Book", customer);
        Order order2 = new Order("Car", customer);
        customer.getOrders().add(order1);
        customer.getOrders().add(order2);
        session.save(customer);
        session.save(order1);
        session.save(order2);
    }

    @Test
    public void testGet() {
        Customer customer = (Customer) session.get(Customer.class, 1);
        System.out.println(customer.getCustomerName());
        System.out.println(customer.getOrders().getClass());
    }

    @Test
    public void testUpdate() {
        Customer customer = (Customer) session.get(Customer.class, 1);
        customer.getOrders().iterator().next().setOrderName("Modify");
    }
}