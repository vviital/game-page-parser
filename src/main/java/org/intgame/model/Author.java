package org.intgame.model;

import org.intgame.model.interfaces.Bindable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vviital on 5/13/16.
 */
@Entity
@Table(name = "authors")
public class Author implements Bindable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id", nullable = false)
    private Long id;

    @Column(name = "author", nullable = false)
    private String author;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Question> questions = new HashSet<>();

    public Author() {

    }

    public Author(String author, Question question) {
        this.author = author;
        this.questions.add(question);
    }

    public Author(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public void bind() {
        for(Question question : questions) {
            question.setAuthor(this);
        }
    }
}
