package org.intgame.model;

import org.intgame.model.interfaces.Bindable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vviital on 5/12/16.
 */

@Entity
@Table(name = "questions")
public class Question implements Bindable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id", nullable = false)
    private Long id;

    @Column(name = "question", columnDefinition = "TEXT")
    private String question;

    @Column(name = "answer", columnDefinition = "TEXT")
    private String answer;

    @Column(name = "pass_criteria", columnDefinition = "TEXT")
    private String passCriteria;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "sources", columnDefinition = "TEXT")
    private String sources;

    @Column(name = "notices", columnDefinition = "TEXT")
    private String notices;

    @Column(name = "materials", columnDefinition = "TEXT")
    private String materials;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPassCriteria() {
        return passCriteria;
    }

    public void setPassCriteria(String passCriteria) {
        this.passCriteria = passCriteria;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getNotices() {
        return notices;
    }

    public void setNotices(String notices) {
        this.notices = notices;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public void bind() {
        for(Image image : images) {
            image.setQuestion(this);
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n");

        buffer.append("Id: ");
        buffer.append(getId());
        buffer.append("\n");

        buffer.append("Question: ");
        buffer.append(getQuestion());
        buffer.append("\n");

        buffer.append("Answer: ");
        buffer.append(getAnswer());
        buffer.append("\n");

        buffer.append("PassCriteria: ");
        buffer.append(getPassCriteria());
        buffer.append("\n");

        buffer.append("Comments: ");
        buffer.append(getComments());
        buffer.append("\n");

        buffer.append("Sources: ");
        buffer.append(getSources());
        buffer.append("\n");

        buffer.append("\n");
        return buffer.toString();
    }
}
