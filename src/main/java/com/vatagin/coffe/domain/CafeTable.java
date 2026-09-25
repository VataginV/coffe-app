package com.vatagin.coffe.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cafe_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CafeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cafe_id", nullable = false)
    private Cafe cafe;

    @Column(name = "table_number", nullable = false)
    private String tableNumber;

    @Column(name = "qr_token", nullable = false, unique = true)
    private String qrToken;

    private Integer seats;
}
