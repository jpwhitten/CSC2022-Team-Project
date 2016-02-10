package teamone.tanfieldrailway;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by Joe on 08/02/2016.
 */
public class Question {

    private String questionString;
    private String correctAnswer;

    private String[] answers = new String[4];

    public Question(String questionString, String possibleAnswerOne, String possibleAnswerTwo, String possibleAnswerThree, String possibleAnswerFour, String correctAnswer){
        this.questionString = questionString;
        this.correctAnswer = correctAnswer;
        answers[0] = possibleAnswerOne;
        answers[1] = possibleAnswerTwo;
        answers[2] = possibleAnswerThree;
        answers[3] = possibleAnswerFour;
    }

    public Boolean hasSelectedCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    public String getQuestionString() {
        return questionString;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getAnswers() {
        return answers;
    }
}

