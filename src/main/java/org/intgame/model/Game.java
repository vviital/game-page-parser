package org.intgame.model;

import org.intgame.model.interfaces.Bindable;

import javax.persistence.*;
import java.util.*;

/**
 * Created by vviital on 5/12/16.
 */
@Entity
@Table(name = "games")
public class Game implements Bindable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id", nullable = false)
    private Long id;

    @Column(name = "game_title")
    private String gameTitle;

    @Column(name = "source_url")
    private String sourceUrl;

    @Temporal(TemporalType.DATE)
    @Column(name = "addition_date", nullable = false)
    private Date additionDate;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Date getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }

    @Override
    public void bind() {
        for(Question question : questions) {
            question.setGame(this);
            question.bind();
        }
    }
}
