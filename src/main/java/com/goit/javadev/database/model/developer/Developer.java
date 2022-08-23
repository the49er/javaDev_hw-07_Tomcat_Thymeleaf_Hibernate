package com.goit.javadev.database.model.developer;

import com.goit.javadev.database.model.project.Project;
import com.goit.javadev.database.model.skill.Skill;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.management.ConstructorParameters;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "salary")
    int salary;

    @Column(name = "company_id")
    int companyId;

    @ManyToMany(targetEntity = Skill.class, cascade = { CascadeType.ALL })
    @JoinTable(
    name = "developer_skill",
    joinColumns = {@JoinColumn (name = "developer_id") },
    inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )

    private Set<Skill> skills = new HashSet<>();

    @ManyToMany(targetEntity = Project.class, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "developer_project",
            joinColumns = {@JoinColumn (name = "developer_id") },
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private Set<Project> projects = new HashSet<>();

    public Developer(long id, String name, int age, Gender gender, int salary, int companyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", salary=" + salary +
                ", companyId=" + companyId +
                '}';
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}
