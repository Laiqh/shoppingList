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
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);

        if (product != null) {
            return product;
        } else {
            throw new NoSuchElementException("No item found for ID " + id);
        }
    }

    @Override
    public void remove(Long id) {

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
