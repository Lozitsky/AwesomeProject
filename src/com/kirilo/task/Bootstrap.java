package com.kirilo.task;

import com.kirilo.task.entities.Task;
import com.kirilo.task.repositories.TaskRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://github.com/hantsy/jakartaee-faces-sample/tree/22169c003b85f634086b31c564dd855538c0cf2b/src/main/java/com/example
@Startup
@Singleton
public class Bootstrap {
    @Inject
    Logger LOG;

    @Inject
    TaskRepository taskRepository;

    @PostConstruct
    public void init() {
        LOG.log(Level.INFO, "bootstraping application...");

        Stream.of("first", "second")
                .map(s -> {
                    Task task = new Task();
                    task.setName("My " + s + " task");
                    task.setDescription("The description of my " + s + " task");
                    task.setStatus(Status.TODO);
                    return task;
                })
                .map(task -> {
                    if (null == taskRepository) {
                        LOG.log(Level.INFO, " Null!! \n");
                    }
                    return taskRepository.save(task);
                })
                .collect(Collectors.toList())
                .forEach(task -> LOG.log(Level.INFO, " task saved: {0}", new Object[]{task}));
    }

}
