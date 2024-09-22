package com.example.demo.serviceImpl;

import com.example.demo.entity.Sales;
import com.example.demo.service.SalesDaoService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
@Slf4j
public class SalesDaoImpl implements SalesDaoService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Sales> getSalesDetails() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Sales.class)
                .add(Restrictions.eq("seller_id", 1001));
        Criteria salesCriteria = criteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().getCurrentSession()).setTimeout(30);
        return (List<Sales>)salesCriteria.list();
    }

    public void validateParam()
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Sales.class)
                .add(Restrictions.eq("seller_id", 1001));
        Criteria salesCriteria = criteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().getCurrentSession()).setTimeout(30);
        List<Sales> s = (List<Sales>)salesCriteria.list();
        log.info("SIZE OF FETCHED ITEM ---> {}",s.size());
    }
}
