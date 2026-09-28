package com.vatagin.coffe.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "guest_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "table_id", nullable = false)
    private CafeTable table;

    @Column(name = "started_at", nullable = false)
    private Instant startedAt;

    @Column(name = "closed_at")
    private Instant closedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatus status = SessionStatus.OPEN;

    @PrePersist
    void onCreate() {
        if (startedAt == null) {
            startedAt = Instant.now();
        }
        if (status == null) {
            status = SessionStatus.OPEN;
        }
    }

    /** Пометить сессию закрытой. */
    public void close() {
        this.closedAt = Instant.now();
        this.status = SessionStatus.CLOSED;
    }
}