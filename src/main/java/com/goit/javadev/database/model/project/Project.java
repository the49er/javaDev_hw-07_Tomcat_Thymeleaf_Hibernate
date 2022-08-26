package com.goit.javadev.database.model.project;

import com.goit.javadev.database.model.developer.Developer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


@Data
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

    @ManyToMany(mappedBy = "projectsSet")
    private Set<Developer> developersSet = new HashSet<>();

    public Project(long id, String name, String description, LocalDate date, int customerId, int companyId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.customerId = customerId;
        this.companyId = companyId;
    }

    LocalDate getDate(String str){
        return LocalDate.parse(str.format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-mm-dd"))));
    }
}
