package com.registroPonto.registroPonto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.security.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "punch_clock")
public class PunchClock {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;
    @Enumerated(EnumType.STRING)
    private Types type;
    private Instant timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users user;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public Types getType() {
        return type;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Users getUser() {
        return user;
    }
}
