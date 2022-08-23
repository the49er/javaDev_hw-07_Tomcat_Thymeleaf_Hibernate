package com.goit.javadev.database.model.entity_relation.developer_skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DeveloperSkillKey {
    long developerId;
    long skillId;
}

