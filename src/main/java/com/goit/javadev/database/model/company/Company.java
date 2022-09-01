package com.goit.javadev.database.model.company;

import com.goit.javadev.database.model.customer.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

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
@Table(name = "companies")
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "specialization")
    String specialization;

    @ManyToMany(mappedBy = "companiesSet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<Customer> customerSet = new HashSet<>();

    public Company(long id, String name, String specialization){
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}
