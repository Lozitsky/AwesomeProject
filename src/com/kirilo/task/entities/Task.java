package com.kirilo.task.entities;

import com.kirilo.task.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
// https://github.com/hantsy/jakartaee-faces-sample/blob/22169c003b85f634086b31c564dd855538c0cf2b/src/main/java/com/example/domain/Task.java
// https://stackoverflow.com/a/60360233/9586230
// https://stackoverflow.com/a/11178016/9586230
@Entity
@Table(name = "task", schema = "awesome_project")
@EntityListeners(StateListener.class)
public class Task implements Serializable {
    private static final long serialVersionUID = -4997538174943919962L;
    public static Comparator<Task> COMPARATOR = Comparator
            .comparing(Task::getName)
            .thenComparing(Task::getDescription);
    public static Function<Task, String> TO_STRING = t
            -> "Post["
            + "\n title:" + t.getName()
            + "\n content:" + t.getDescription()
            + "\n status:" + t.getStatus()
            + "\n createdAt:" + t.audit.getCreatedDate()
            + "\n lastModifiedAt:" + t.audit.getLastModifiedDate()
            + "]";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name", length = 100)
    private String name;
    @Basic
    @Column(name = "description", length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    private Status status = Status.TODO;
    @Embedded
    private Audit audit;

    public Task() {
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) && name.equals(task.name)
                && description.equals(task.description)
                && status == task.status
                && audit.getCreatedDate().equals(task.getAudit().getCreatedDate())
                && audit.getLastModifiedDate().equals(task.getAudit().getLastModifiedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, audit.getCreatedDate(),
                audit.getLastModifiedDate());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", description='" + description + "'" +
                ", status='" + status + "'" +
                Optional.ofNullable(audit).orElse(new Audit())
                +
                '}';
    }
}
