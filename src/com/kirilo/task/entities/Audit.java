package com.kirilo.task.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@Table(name = "task")
public class Audit {
    @Column(name = "created_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate;
    }
}
