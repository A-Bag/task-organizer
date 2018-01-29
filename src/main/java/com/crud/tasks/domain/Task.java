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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        return content != null ? content.equals(task.content) : task.content == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
