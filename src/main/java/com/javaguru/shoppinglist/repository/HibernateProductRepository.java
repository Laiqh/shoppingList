package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@Profile("hibernate")
@Transactional
public class HibernateProductRepository implements Repository<Product> {
    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long insert(Product product) {
        return (Long) sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public Product get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);

        if (product != null) {
            return product;
        } else {
            throw new NoSuchElementException("No record found for ID " + id);
        }
    }

    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Long id = product.getId();

        if (session.get(Product.class, id) == null) {
            throw new NoSuchElementException("No record found for ID " + id);
        }

        session.clear();

        session.update(product);
    }

    @Override
    public void remove(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);

        if (product != null) {
            session.remove(product);
        } else {
            throw new NoSuchElementException("No record found for ID " + id);
        }
    }

    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> productRoot = criteria.from(Product.class);
        criteria.select(productRoot);
        List<Product> result = session.createQuery(criteria).getResultList();
        return result;
    }
}
