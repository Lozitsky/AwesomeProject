package com.kirilo.task.repositories;

import com.kirilo.task.entities.Task;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// https://github.com/eclipse-ee4j/jakartaee-tutorial-examples/tree/4f45630c81176895ce2a3cf414d79b9bdd5e640c/persistence/address-book/src/main/java/jakarta/tutorial/addressbook/ejb

//@Stateful
@Stateless
public class TaskRepository extends AbstractFacadeRepository<Task> {

    @PersistenceContext(unitName = "Task")
    private EntityManager em;


    public TaskRepository() {
        super(Task.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
