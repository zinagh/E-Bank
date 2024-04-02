package com.mfbank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "t_notification")
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    private String reference;
    private String sender;
    private String receiver;
    private String type;
    private LocalDateTime sentTime;
    @Column(nullable = false)
    private Boolean isRead;
    @ManyToMany(mappedBy="notifications")
    private Set<User> users;

    // Generate a universally unique identifier (UUID) for robust uniqueness
    @PrePersist
    public void generateReference() {
        reference = UUID.randomUUID().toString();
    }
}
