package com.zero.demonotificationservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = MUser.TABLE_NAME)
public class MUser {

    public static final String TABLE_NAME = "m_user";
    public static final String COLUMN_ID = "id";
}
