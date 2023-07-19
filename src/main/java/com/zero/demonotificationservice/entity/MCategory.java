package com.zero.demonotificationservice.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = MCategory.TABLE_NAME)
public class MCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID)
    private Integer id;

    @Column(name = COLUMN_NAME)
    private String name;

    public static final String TABLE_NAME = "m_category";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
}
