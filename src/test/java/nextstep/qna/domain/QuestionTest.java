package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("질문 관련 기능")
public class QuestionTest {
    private Question Q1;
    private Question Q2;

    @BeforeEach
    void setUp() {
        this.Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        this.Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }

    @DisplayName("질문 삭제시 로그인 사용자와 질문한 사람이 같을 경우 예외를 발생하지 않는다.")
    @Test
    void when_LoginUserIsEqualToQuestionWriter_Expects_DoesNotThrowException() {
        assertThatNoException()
                .isThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI));
    }

    @DisplayName("질문 삭제시 로그인 사용자와 질문한 사람이 같지 않을 경우 예외를 발생한다.")
    @Test
    void when_LoginUserIsNotEqualToQuestionWriter_Expects_ThrowException() {

        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);

    }
}
