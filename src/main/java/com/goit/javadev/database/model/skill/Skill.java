package com.goit.javadev.database.model.skill;


import com.goit.javadev.database.model.developer.Developer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "programming_lang")
    @Enumerated(EnumType.STRING)
    ProgramLang programLang;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    SkillLevel skillLevel;

    @ManyToMany(mappedBy = "skillsSet")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Set<Developer> developerSet = new HashSet<>();

    public Skill(long id, ProgramLang programLang, SkillLevel skillLevel){
        this.id = id;
        this.programLang = programLang;
        this.skillLevel = skillLevel;
    }
}
