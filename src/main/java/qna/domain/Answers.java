package qna.domain;

import org.hibernate.annotations.Where;
import qna.exception.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(User loginUser) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }

    public boolean hasOtherOwnedAnswer(User loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }
}
