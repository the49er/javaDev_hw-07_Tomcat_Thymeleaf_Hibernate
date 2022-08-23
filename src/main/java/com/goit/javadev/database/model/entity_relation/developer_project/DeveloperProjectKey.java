package com.goit.javadev.database.model.entity_relation.developer_project;

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
public class DeveloperProjectKey {
    long developerId;
    long projectId;
}

