package com.goit.javadev.database.model.customer;

import com.goit.javadev.database.model.company.Company;
import com.goit.javadev.database.model.project.Project;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customers")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "business_sphere")
    private String businessSphere;

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "company_customer",
            joinColumns = {@JoinColumn(name = "company_id") },
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private Set<Company> companiesSet = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Project> projects;

    public Customer(long id, String name, String businessSphere){
        this.id = id;
        this.name = name;
        this.businessSphere = businessSphere;
    }
}
