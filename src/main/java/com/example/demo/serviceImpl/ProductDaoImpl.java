package com.example.demo.serviceImpl;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductDaoService;

import lombok.extern.slf4j.Slf4j;
//import org.hibernate.Criteria;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
@Transactional("transactionManager")
@Repository
public class ProductDaoImpl implements ProductDaoService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private CacheManager cacheManager;

    private SimpleValueWrapper simpleValueWrapper;

    private Cache cache;

    @Override

    public List<Product> getProductList() {
        log.info("FETCH THE PRODUCT LIST.....");
        /*DetachedCriteria criteria = DetachedCriteria.forClass(Product.class)
                .add(Restrictions.eq("product_id", 2));
        Criteria productCriteria = criteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().getCurrentSession())
                .setTimeout(30);*/
        List<Product> productList;

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        Predicate productIdPredicate = cb.equal(root.get("product_id"), 2);
        query.where(productIdPredicate);

        cacheManager.getCacheNames().forEach(s -> log.info("AVAILABLE CACHE OBJECTS " + s));
        cache = cacheManager.getCache("com.example.demo.entity.Product");

        if(cache.get(2L) == null) {
            log.info("***** GETTING DATA FROM DATABASE *****");
            productList = session.createQuery(query).stream().toList();
            cache.put(2L, productList);
            this.simpleValueWrapper = (SimpleValueWrapper) cache.get(2L);
        }
        else {
            log.info("***** GETTING DATA FROM CACHE *****");
            productList = (List<Product>) simpleValueWrapper.get();
        }

        return productList;
    }
}
