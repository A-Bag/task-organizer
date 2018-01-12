package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/*@NamedQuery(
        name = "Task.findTasksByFirstThreeLetters",
        query = "FROM Task WHERE SUBSTRING(title, 1, 3) = :threeLetters"
)*/
@NamedNativeQuery(
        name = "Task.findTasksByFirstThreeLetters",
        query = "SELECT * FROM tasks WHERE name LIKE CONCAT(:beginLetters, '%')",
        resultClass = Task.class
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;
}
