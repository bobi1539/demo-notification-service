package com.zero.demonotificationservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = MUser.TABLE_NAME)
public class MUser {
    @Id
    @Column(name = COLUMN_ID, nullable = false)
    private UUID id;

    @Column(name = COLUMN_NAME, nullable = false)
    private String name;

    public static final String TABLE_NAME = "m_user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
}
