package com.innowise.repository;

import com.innowise.entity.Authorization;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRepositoryImpl implements AuthorizationRepository {
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    public List<Authorization> findAll() {
        TypedQuery<Authorization> query = entityManager.createQuery("from Authorization", Authorization.class);
        List<Authorization> list = query.getResultList();
        return list;
    }

    @Override
    public Authorization save(Authorization authority) {
        Authorization newAuthority = entityManager.merge(authority);
        authority.setId(newAuthority.getId());
        return newAuthority;
    }

    @Override
    public Authorization findById(Long id) {
        Authorization authority = entityManager.find(Authorization.class, id);
        return authority;
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Authorization " + "where id =:authorityId");
        query.setParameter("authorityId", id);
        query.executeUpdate();
    }

    @Override
    public Authorization findByName(String name) {
        Query query = entityManager.createQuery("SELECT u FROM Authority u WHERE u.name = :name");
        return (Authorization) query.getSingleResult();
    }
}
//@Stateless
//@AllArgsConstructor
//public class AuthorityRepositoryImpl implements AuthorityRepository {
//
//    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
//    private EntityManager entityManager;
//
//    @Override
//    public List<Authority> findAll() {
//        return entityManager.createQuery("SELECT a FROM Authority a", Authority.class).getResultList();
//    }
//
//    @Override
//    public Authority save(Authority authority) {
//        entityManager.persist(authority);
//        return authority;
//    }
//
//    @Override
//    public Authority findById(Long id) {
//        return entityManager.find(Authority.class, id);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        Authority authority = entityManager.find(Authority.class, id);
//        if (authority != null) {
//            entityManager.remove(authority);
//        }
//    }
//
//    @Override
//    public Optional<Authority> findByName(String name) {
//        TypedQuery<Authority> query = entityManager.createQuery("SELECT a FROM Authority a WHERE a.name = :name", Authority.class);
//        query.setParameter("name", name);
//        try {
//            return Optional.of(query.getSingleResult());
//        } catch (NoResultException | NonUniqueResultException ex) {
//            return Optional.empty();
//        }
//    }
//}