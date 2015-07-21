package com.ztx.qa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
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
        System.out.println(news);
    }

    @Test
    public void testSessionFlush() {
        News news = (News) session.get(News.class, 1);
        news.setAuthor("Oracle");
    }

    @Test
    public void testSave() {
        News news = new News();
        news.setAuthor("Tom");
        news.setTitle("Title1");
        news.setDate(new Date());
        news.setId(10);//ID是自增长，改了没用。
        System.out.println(news);
//        session.persist(news); //之前有setID会抛出异常不执行，save()能执行。
        session.save(news);
        System.out.println(news);
    }

    @Test
    public void testGet() {
        News news = (News) session.get(News.class, 3);//立即执行查找
        System.out.println(news);
    }

    @Test
    public void testLoad() {
        News news = (News) session.load(News.class, 3);//延迟查找，等要用的时候再执行。
        System.out.println(news.getClass().getName());
    }

    @Test
    public void testUpdate() {
        News news = (News) session.get(News.class, 2);
//        news.setTitle("setTitle"); //不需要update，因为commit时就update了。
        transaction.commit();
        session.close();

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        news.setAuthor("updateAuthor");
        session.update(news);//对游离对象强制更新为持久化对象
    }

    @Test
    public void testSaveOrUpdate() {
        News news = new News("hhh", "def", new Date());
        news.setId(6);//有这句话执行update，没有这句话执行save。
        session.saveOrUpdate(news);
        System.out.println(news);
    }

    @Test
    public void testDelete() {
//        News news = new News(); //删除游离对象
//        news.setId(3);
        News news = (News) session.get(News.class, 4);//删除持久化对象
        session.delete(news);
    }

    @Test
    public void testEvict() {
        News news1 = (News) session.get(News.class, 7);
        News news2 = (News) session.get(News.class, 8);
        news1.setAuthor("Evict");
        news2.setAuthor("Evict");
        session.evict(news1);
    }

    @Test
    public void testDoWork() {
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                System.out.println(connection);
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}