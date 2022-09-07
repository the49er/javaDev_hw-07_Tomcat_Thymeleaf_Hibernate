package com.goit.javadev.database.model.project;

import com.goit.javadev.database.model.company.Company;
import com.goit.javadev.database.model.customer.Customer;
import com.goit.javadev.database.model.developer.Developer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


@Data
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date_contract")
    private LocalDate date;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "company_id")
    private long companyId;

    @ManyToMany(mappedBy = "projectsSet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Developer> developersSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    public Project(long id, String name, String description, LocalDate date, int customerId, int companyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.customerId = customerId;
        this.companyId = companyId;
    }

    LocalDate getDate(String str) {
        return LocalDate.parse(str.format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-mm-dd"))));
    }
}
