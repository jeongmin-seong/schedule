package com.schedule.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    @Lob
    private String content;


    @Column(nullable = false)
    private String author;


    @Column(nullable = false)
    private String password; // 요청 요구사항에 따라 평문 저장. 실제 서비스에서는 해시 권장


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;


    @PrePersist
    public void prePersist() {
        if (this.modifiedAt == null) {
            this.modifiedAt = this.createdAt;
        }
    }
}