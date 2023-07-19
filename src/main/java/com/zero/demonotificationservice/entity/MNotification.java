package com.zero.demonotificationservice.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = MNotification.TABLE_NAME)
public class MNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID)
    private Integer id;

    @Column(name = COLUMN_TITLE)
    private String title;

    @Column(name = COLUMN_DETAIL, columnDefinition = "TEXT")
    private String detail;

    @Column(name = COLUMN_CREATED_AT)
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = COLUMN_USER_ID)
    private MUser user;

    public static final String TABLE_NAME = "m_notification";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DETAIL = "detail";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_USER_ID = "user_id";
}
