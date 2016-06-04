package org.intgame.model;

import javax.persistence.*;

/**
 * Created by vviital on 5/12/16.
 */
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id", nullable = false)
    private Long id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
