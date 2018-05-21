package com.shaowei.workflow.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FeedBack entity.
 */
public class FeedBackDTO implements Serializable {

    private Long id;

    private String question1;

    private String question2;

    private String question3;

    private String question4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeedBackDTO feedBackDTO = (FeedBackDTO) o;
        if (feedBackDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feedBackDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeedBackDTO{" +
            "id=" + getId() +
            ", question1='" + getQuestion1() + "'" +
            ", question2='" + getQuestion2() + "'" +
            ", question3='" + getQuestion3() + "'" +
            ", question4='" + getQuestion4() + "'" +
            "}";
    }
}
