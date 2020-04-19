package com.aier.ihrm.common.service;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * <p>Title: com.aier.ihrm.common.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 11:27
 * Description: No Description
 */
public class BaseService<T> {

    protected Specification<T> getSpec(String companyId) {
        /**
         * 抽象服务层代码实现
         * 用户构造查询条件
         *   1、只查询companyId为条件的数据 （因为多租户问题）
         *   2、很多地方需要根据companyId来做查询
         *   3、很多的对象中都具有companyId
         */
        Specification<T> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("companyId").as(String.class),companyId);
            }
        };
        return specification;
    }
}
