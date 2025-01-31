package com.example.AppAziendale.domains.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comunicazione_aziendale_scheduled")
@EntityListeners(AuditingEntityListener.class)
public class ComunicazioneAziendaleScheduled {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime publishTime;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String contenuto;

    @JoinColumn(name = "creator_id")
    @ManyToOne(optional = false)
    private Utente creatorId;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(name = "created_by")
    private Long createdBy;
    @LastModifiedDate
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private Long lastModifiedBy;
}
