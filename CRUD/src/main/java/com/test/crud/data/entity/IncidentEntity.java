package com.test.crud.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;

@Entity
@Table(name = "TB_INCIDENT")
@Getter
@Setter
@NoArgsConstructor
public class IncidentEntity {

    @Id
    @GeneratedValue(generator = "seqIncident", strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqIncident", sequenceName = "SEQ_INCIDENT",  allocationSize = 1)
    @Column private Long idIncident;
    @Column private String name;
    @Column private String description;
    @Column private @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") Calendar createdAt;
    @Column private @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") Calendar updatedAt;
    @Column private @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") Calendar closedAt;

}
