package com.kirilo.task.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

// https://github.com/eclipse-ee4j/jakartaee-tutorial-examples/tree/4f45630c81176895ce2a3cf414d79b9bdd5e640c/persistence/address-book/src/main/java/jakarta/tutorial/addressbook/ejb

public abstract class AbstractFacadeRepository<T> {
    private final Class<T> entityClass;

    @Inject
    public AbstractFacadeRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public T save(T entity) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "save: " + (entity) + "\n");
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "getEntityManager(): " + (null == getEntityManager() ? null : getEntityManager().getClass().getName()) + "\n");
        getEntityManager().persist(entity);
        return entity;
    }

    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public void deleteBy(Object param) {
        getEntityManager().remove(findBy(param));
    }

    public T findBy(Object param) {
        return getEntityManager().find(entityClass, param);
    }

    public T findById(Long id) {
        final T entity = findBy(id);
        if (entity == null) {
//            throw new NotFoundException(String.format("post id:%s not found!", id));
        }
        return entity;
    }

    public Optional<T> findOptionalById(Long id) {
        T task = getEntityManager().find(entityClass, id);
        return Optional.ofNullable(task);
    }

    public List<T> findBy(String param, Object obj) {
        final TypedQuery<T> query = getFindByTypedQuery(param, obj);

        return query.getResultList();
    }

    public T findSingleBy(String param, Object obj) {
        final TypedQuery<T> query = getFindByTypedQuery(param, obj);
        return query.getSingleResult();
    }

    private TypedQuery<T> getFindByTypedQuery(String param, Object o) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(entityClass);
        Root<T> c = q.from(entityClass);

        if (null != param && null != o) {
            q.where(cb.equal(c.get(param), o));
        }
        return getEntityManager().createQuery(q);
    }

    public List<T> findAll() {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }


}
