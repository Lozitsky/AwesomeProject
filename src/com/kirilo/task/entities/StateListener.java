package com.kirilo.task.entities;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class StateListener {

    @PrePersist
    public void prePersist(Task task) {
        Audit audit = task.getAudit();
        if (audit == null) {
            audit = new Audit();
            task.setAudit(audit);
            audit.setCreatedDate(LocalDateTime.now());
            audit.setLastModifiedDate(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Task task) {
        Audit audit = task.getAudit();
        audit.setLastModifiedDate(LocalDateTime.now());
    }
}
