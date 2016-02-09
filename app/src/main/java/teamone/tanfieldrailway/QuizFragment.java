package teamone.tanfieldrailway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class QuizFragment extends Fragment {

    LinearLayout startLayout;
    LinearLayout questionLayout;
    LinearLayout imageQuestionLayout;
    LinearLayout endLayout;

    RelativeLayout startLayoutStartButton;

    RelativeLayout answerOne;
    RelativeLayout answerTwo;
    RelativeLayout answerThree;
    RelativeLayout answerFour;

    TextView questionText;

    TextView answerOneText;
    TextView answerTwoText;
    TextView answerThreeText;
    TextView answerFourText;

    TextView scoreText;

    QuizManager quizManager;

    ArrayList<Integer> places = new ArrayList<Integer>();

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        quizManager = new QuizManager();

        startLayout = (LinearLayout) view.findViewById(R.id.quiz_layout_start);
        questionLayout = (LinearLayout) view.findViewById(R.id.quiz_layout_question);
        questionLayout.setVisibility(View.GONE);

        imageQuestionLayout = (LinearLayout) view.findViewById(R.id.quiz_layout_image_question);
        imageQuestionLayout.setVisibility(View.GONE);

        endLayout = (LinearLayout) view.findViewById(R.id.quiz_layout_end);
        endLayout.setVisibility(View.GONE);

        startLayoutStartButton = (RelativeLayout) view.findViewById(R.id.quiz_start_button);

        answerOne = (RelativeLayout) view.findViewById(R.id.quiz_question_answer_1);
        answerTwo = (RelativeLayout) view.findViewById(R.id.quiz_question_answer_2);
        answerThree = (RelativeLayout) view.findViewById(R.id.quiz_question_answer_3);
        answerFour = (RelativeLayout) view.findViewById(R.id.quiz_question_answer_4);

        scoreText = (TextView) view.findViewById(R.id.quiz_final_score);

        startLayoutStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLayout.setVisibility(View.GONE);
                questionLayout.setVisibility(View.VISIBLE);
            }
        });

        answerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizManager.getQuestion().hasSelectedCorrectAnswer(answerOneText.getText().toString())) {
                    quizManager.incrementScore();
                }
                if(!quizManager.isLastQuestion()) {
                    quizManager.getNextQuestion();
                    questionText.setText(quizManager.getQuestion().getQuestionString());
                    setAnswerPlacesList();
                    placeAnswers(quizManager.getQuestion());
                } else {
                    questionLayout.setVisibility(View.GONE);
                    endLayout.setVisibility(View.VISIBLE);
                    scoreText.setText(Integer.toString(quizManager.score));
                }
            }
        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizManager.getQuestion().hasSelectedCorrectAnswer(answerTwoText.getText().toString())) {
                    quizManager.incrementScore();
                }
                if(!quizManager.isLastQuestion()) {
                    quizManager.getNextQuestion();
                    questionText.setText(quizManager.getQuestion().getQuestionString());
                    setAnswerPlacesList();
                    placeAnswers(quizManager.getQuestion());
                } else {
                    questionLayout.setVisibility(View.GONE);
                    endLayout.setVisibility(View.VISIBLE);
                    scoreText.setText(Integer.toString(quizManager.score));
                }
            }
        });

        answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizManager.getQuestion().hasSelectedCorrectAnswer(answerThreeText.getText().toString())) {
                    quizManager.incrementScore();
                }
                if(!quizManager.isLastQuestion()) {
                    quizManager.getNextQuestion();
                    questionText.setText(quizManager.getQuestion().getQuestionString());
                    setAnswerPlacesList();
                    placeAnswers(quizManager.getQuestion());
                } else {
                    questionLayout.setVisibility(View.GONE);
                    endLayout.setVisibility(View.VISIBLE);
                    scoreText.setText(Integer.toString(quizManager.score));
                }

            }
        });

        answerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizManager.getQuestion().hasSelectedCorrectAnswer(answerFourText.getText().toString())) {
                    quizManager.incrementScore();
                }
                if(!quizManager.isLastQuestion()) {
                    quizManager.getNextQuestion();
                    questionText.setText(quizManager.getQuestion().getQuestionString());
                    setAnswerPlacesList();
                    placeAnswers(quizManager.getQuestion());
                } else {
                    questionLayout.setVisibility(View.GONE);
                    endLayout.setVisibility(View.VISIBLE);
                    scoreText.setText(Integer.toString(quizManager.score));
                }
            }
        });

        questionText = (TextView) view.findViewById(R.id.quiz_question_text);
        questionText.setText(quizManager.getQuestion().getQuestionString());

        answerOneText = (TextView) view.findViewById(R.id.quiz_question_answer_text_1);
        answerTwoText = (TextView) view.findViewById(R.id.quiz_question_answer_text_2);
        answerThreeText = (TextView) view.findViewById(R.id.quiz_question_answer_text_3);
        answerFourText = (TextView) view.findViewById(R.id.quiz_question_answer_text_4);

        setAnswerPlacesList();
        placeAnswers(quizManager.getQuestion());


        // Inflate the layout for this fragment
        return view;

    }

    public ArrayList<Integer> setAnswerPlacesList() {
        places = new ArrayList<Integer>();
        places.add(1);
        places.add(2);
        places.add(3);
        places.add(4);
        return places;
    }

    public void placeAnswers(Question question) {
        Random r = new Random();
        for(int i = 0; i < question.getAnswers().length; i++) {
            int rand = r.nextInt(places.size());
            int answerplace = places.get(rand);
            String answer = question.getAnswers()[i];
            places.remove(rand);
            if(answerplace == 1) {

                answerOneText.setText(answer);

            } else if (answerplace == 2) {

                answerTwoText.setText(answer);

            } else if (answerplace == 3) {

                answerThreeText.setText(answer);

            } else if (answerplace == 4) {

                answerFourText.setText(answer);

            }
        }

    }

}
