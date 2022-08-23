package com.goit.javadev.database.model.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name ="description")
    private String description;

    @Column(name = "date_contract")
    private LocalDate date;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "company_id")
    private int companyId;

    LocalDate getDate(String str){
        return LocalDate.parse(str.format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-mm-dd"))));
    }
}
