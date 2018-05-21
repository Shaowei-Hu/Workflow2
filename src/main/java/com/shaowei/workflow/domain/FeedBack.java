package com.shaowei.workflow.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FeedBack.
 */
@Entity
@Table(name = "feed_back")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FeedBack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_1")
    private String question1;

    @Column(name = "question_2")
    private String question2;

    @Column(name = "question_3")
    private String question3;

    @Column(name = "question_4")
    private String question4;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion1() {
        return question1;
    }

    public FeedBack question1(String question1) {
        this.question1 = question1;
        return this;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public FeedBack question2(String question2) {
        this.question2 = question2;
        return this;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public FeedBack question3(String question3) {
        this.question3 = question3;
        return this;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public FeedBack question4(String question4) {
        this.question4 = question4;
        return this;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeedBack feedBack = (FeedBack) o;
        if (feedBack.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feedBack.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeedBack{" +
            "id=" + getId() +
            ", question1='" + getQuestion1() + "'" +
            ", question2='" + getQuestion2() + "'" +
            ", question3='" + getQuestion3() + "'" +
            ", question4='" + getQuestion4() + "'" +
            "}";
    }
}
