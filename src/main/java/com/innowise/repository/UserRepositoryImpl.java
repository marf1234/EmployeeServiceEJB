package com.innowise.repository;

import com.innowise.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> findAll() {
        Query query = entityManager.createQuery("from User", User.class);
        List<User> allUsers = query.getResultList();
        return allUsers;
    }

    @Override
    public User save(User user) {
        User newUser = entityManager.merge(user);
//        newUser.setId(user.getId());
        return newUser;
    }


    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from User " +
                "where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        User user = null;
        try {
            if (query != null){
                query.setParameter("username", username);
                user = query.getSingleResult();
            }
        } catch (NoResultException ignored) {

        }

        return user;
    }

}
//@Stateless
//public class UserRepositoryImpl implements UserRepository {
//
//    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
//    private EntityManager entityManager;
//
//    @Override
//    public List<User> findAll() {
//        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
//    }
//
//    @Override
//    public User save(User user) {
//        entityManager.persist(user);
//        return user;
//    }
//
//    @Override
//    public User findById(Long id) {
//        return entityManager.find(User.class, id);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        User user = entityManager.find(User.class, id);
//        if (user != null) {
//            entityManager.remove(user);
//        }
//    }
//
//    @Override
//    public Optional<User> findByUsername(String username) {
//        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
//        query.setParameter("username", username);
//        try {
//            return Optional.of(query.getSingleResult());
//        } catch (NoResultException | NonUniqueResultException ex) {
//            return Optional.empty();
//        }
//    }
//}