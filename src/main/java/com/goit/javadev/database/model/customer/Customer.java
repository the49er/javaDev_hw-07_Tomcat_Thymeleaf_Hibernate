package com.goit.javadev.database.model.customer;

import com.goit.javadev.database.model.skill.Skill;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
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

    @ManyToMany(targetEntity = Customer.class, cascade = { CascadeType.ALL})
    @JoinTable(
            name = "company_customer",
            joinColumns = {@JoinColumn(name = "company_id") },
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private Set<Customer> customers = new HashSet<>();
}
