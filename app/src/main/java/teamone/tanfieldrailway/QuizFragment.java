package teamone.tanfieldrailway;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;
import java.util.Random;


public class QuizFragment extends Fragment implements FragmentTitle {

	LinearLayout startLayout;
	LinearLayout questionLayout;
	LinearLayout imageQuestionLayout;
	LinearLayout endLayout;

	RelativeLayout startLayoutStartButton;
	RelativeLayout endLayoutStartButton;

	RelativeLayout progressBar;
	RelativeLayout.LayoutParams progressBarParams;

	RelativeLayout answerOne;
	RelativeLayout answerTwo;
	RelativeLayout answerThree;
	RelativeLayout answerFour;

	TextView questionText;

	TextView answerOneText;
	TextView answerTwoText;
	TextView answerThreeText;
	TextView answerFourText;

	RelativeLayout answerOneImage;
	RelativeLayout answerTwoImage;
	RelativeLayout answerThreeImage;
	RelativeLayout answerFourImage;

	TextView questionTextImage;

	TextView answerOneTextImage;
	TextView answerTwoTextImage;
	TextView answerThreeTextImage;
	TextView answerFourTextImage;

	ImageView questionImage;

	TextView scoreText;

	TextView message;

	QuizManager quizManager;

	float displayWidth;
	float displayWidthIncrement;

	Boolean isUIResponsive = true;

	ArrayList<Integer> places = new ArrayList<>();

	ParticleSystem leftConfetti;
	ParticleSystem rightConfetti;
	View view;

	@Override
	public void onDestroy() {
		leftConfetti.cancel();
		rightConfetti.cancel();
		super.onDestroy();
	}

	@Override
	public void onPause() {
		leftConfetti.cancel();
		rightConfetti.cancel();
		super.onPause();
	}

	public QuizFragment() {
		// Required empty public constructor
	}

	public String getTitle(){
		return "Quiz";
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {


		view = inflater.inflate(R.layout.fragment_quiz, container, false);
		setUpEmitters();

		startLayout = (LinearLayout) view.findViewById(R.id.quiz_layout_start);
		questionLayout = (LinearLayout) view.findViewById(R.id.quiz_layout_question);
		questionLayout.setVisibility(View.GONE);

		imageQuestionLayout = (LinearLayout) view.findViewById(R.id.quiz_layout_image_question);
		imageQuestionLayout.setVisibility(View.GONE);

		endLayout = (LinearLayout) view.findViewById(R.id.quiz_layout_end);
		endLayout.setVisibility(View.GONE);

		startLayoutStartButton = (RelativeLayout) view.findViewById(R.id.quiz_start_button);

		endLayoutStartButton = (RelativeLayout) view.findViewById(R.id.quiz_end_start_button);


		quizManager = new QuizManager();


		displayWidth = getContext().getResources().getDisplayMetrics().widthPixels ;
		displayWidthIncrement = displayWidth / quizManager.getMaxQuestions();

		progressBar = (RelativeLayout) view.findViewById(R.id.quiz_progress_bar);
		progressBarParams = (RelativeLayout.LayoutParams) progressBar.getLayoutParams();
		progressBarParams.width = 0;
		progressBar.setLayoutParams(progressBarParams);



		answerOne = (RelativeLayout) view.findViewById(R.id.quiz_question_answer_1);
		answerTwo = (RelativeLayout) view.findViewById(R.id.quiz_question_answer_2);
		answerThree = (RelativeLayout) view.findViewById(R.id.quiz_question_answer_3);
		answerFour = (RelativeLayout) view.findViewById(R.id.quiz_question_answer_4);

		answerOneImage = (RelativeLayout) view.findViewById(R.id.quiz_image_question_answer_1);
		answerTwoImage = (RelativeLayout) view.findViewById(R.id.quiz_image_question_answer_2);
		answerThreeImage = (RelativeLayout) view.findViewById(R.id.quiz_image_question_answer_3);
		answerFourImage = (RelativeLayout) view.findViewById(R.id.quiz_image_question_answer_4);

		scoreText = (TextView) view.findViewById(R.id.quiz_final_score);

		message = (TextView) view.findViewById(R.id.quiz_end_message);

		questionText = (TextView) view.findViewById(R.id.quiz_question_text);
		questionTextImage = (TextView) view.findViewById(R.id.quiz_image_question_text);

		startLayoutStartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLayout.setVisibility(View.GONE);
				quizManager.getNextQuestion();
				setAnswerPlacesList();
				setQuestionView();
			}
		});

		endLayoutStartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				quizManager = new QuizManager();
				progressBarParams.width = 0;
				progressBar.setLayoutParams(progressBarParams);
				setUpQuizEnd();
			}
		});

		answerOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isUIResponsive) {
					if (quizManager.getQuestion().hasSelectedCorrectAnswer(answerOneText.getText().toString())) {
						quizManager.incrementScore();
					} else {
						answerOne.setBackgroundColor(getResources().getColor(R.color.colorRed));
					}
					onAnswerSelect();
				}
			}
		});

		answerTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isUIResponsive) {
					if(quizManager.getQuestion().hasSelectedCorrectAnswer(answerTwoText.getText().toString())) {
						quizManager.incrementScore();
					} else {
						answerTwo.setBackgroundColor(getResources().getColor(R.color.colorRed));
					}
					onAnswerSelect();
				}
			}

		});

		answerThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isUIResponsive) {
					if(quizManager.getQuestion().hasSelectedCorrectAnswer(answerThreeText.getText().toString())) {
						quizManager.incrementScore();
					} else {
						answerThree.setBackgroundColor(getResources().getColor(R.color.colorRed));
					}
					onAnswerSelect();
				}
			}
		});

		answerFour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isUIResponsive) {
					if(quizManager.getQuestion().hasSelectedCorrectAnswer(answerFourText.getText().toString())) {
						quizManager.incrementScore();
					} else {
						answerFour.setBackgroundColor(getResources().getColor(R.color.colorRed));
					}
					onAnswerSelect();
				}

			}
		});

		answerOneImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isUIResponsive) {
					if (quizManager.getCurrentImageQuestion().hasSelectedCorrectAnswer(answerOneTextImage.getText().toString())) {
						quizManager.incrementScore();
					} else {
						answerOneImage.setBackgroundColor(getResources().getColor(R.color.colorRed));
					}
					onAnswerSelect();
				}

			}
		});

		answerTwoImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isUIResponsive) {
					if(quizManager.getCurrentImageQuestion().hasSelectedCorrectAnswer(answerTwoTextImage.getText().toString())) {
						quizManager.incrementScore();
					} else {
						answerTwoImage.setBackgroundColor(getResources().getColor(R.color.colorRed));
					}
					onAnswerSelect();
				}
			}
		});

		answerThreeImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isUIResponsive) {
					if (quizManager.getCurrentImageQuestion().hasSelectedCorrectAnswer(answerThreeTextImage.getText().toString())) {
						quizManager.incrementScore();
					} else {
						answerThreeImage.setBackgroundColor(getResources().getColor(R.color.colorRed));
					}
					onAnswerSelect();
				}
			}
		});

		answerFourImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isUIResponsive) {
					if (quizManager.getCurrentImageQuestion().hasSelectedCorrectAnswer(answerFourTextImage.getText().toString())) {
						quizManager.incrementScore();
					} else {
						answerFourImage.setBackgroundColor(getResources().getColor(R.color.colorRed));
					}
					onAnswerSelect();
				}
			}
		});

		answerOneText = (TextView) view.findViewById(R.id.quiz_question_answer_text_1);
		answerTwoText = (TextView) view.findViewById(R.id.quiz_question_answer_text_2);
		answerThreeText = (TextView) view.findViewById(R.id.quiz_question_answer_text_3);
		answerFourText = (TextView) view.findViewById(R.id.quiz_question_answer_text_4);

		answerOneTextImage = (TextView) view.findViewById(R.id.quiz_image_question_answer_text_1);
		answerTwoTextImage = (TextView) view.findViewById(R.id.quiz_image_question_answer_text_2);
		answerThreeTextImage = (TextView) view.findViewById(R.id.quiz_image_question_answer_text_3);
		answerFourTextImage = (TextView) view.findViewById(R.id.quiz_image_question_answer_text_4);

		questionImage = (ImageView) view.findViewById(R.id.quiz_image_image);


		// Inflate the layout for this fragment
		return view;

	}

	private void setUpEmitters() {
		leftConfetti = new ParticleSystem(this.getActivity(), 80, R.drawable.confetti_green, 10000);
		leftConfetti.setSpeedModuleAndAngleRange(0f, 0.2f, 270, 450);
		leftConfetti.setRotationSpeed(144);
		leftConfetti.setRotationSpeedRange(1, 144);
		leftConfetti.setAcceleration(0.00005f, 90);
		leftConfetti.setScaleRange(0.5f, 1.1f);

		rightConfetti = new ParticleSystem(this.getActivity(), 80, R.drawable.confetti_cream, 10000);
		rightConfetti.setSpeedModuleAndAngleRange(0f, 0.2f, 90, 270);
		rightConfetti.setRotationSpeed(144);
		rightConfetti.setRotationSpeedRange(1, 144);
		rightConfetti.setAcceleration(0.00005f, 90);
		rightConfetti.setScaleRange(0.5f, 1.1f);
	}

	private void showCorrectAnswer() {
		if(quizManager.isImage) {
			if(answerOneTextImage.getText().equals(quizManager.getCurrentImageQuestion().getCorrectAnswer())) {
				answerOneImage.setBackgroundColor(getResources().getColor(R.color.colorGreen));
			} else if(answerTwoTextImage.getText().equals(quizManager.getCurrentImageQuestion().getCorrectAnswer())) {
				answerTwoImage.setBackgroundColor(getResources().getColor(R.color.colorGreen));
			} else if(answerThreeTextImage.getText().equals(quizManager.getCurrentImageQuestion().getCorrectAnswer())) {
				answerThreeImage.setBackgroundColor(getResources().getColor(R.color.colorGreen));
			} else if(answerFourTextImage.getText().equals(quizManager.getCurrentImageQuestion().getCorrectAnswer())) {
				answerFourImage.setBackgroundColor(getResources().getColor(R.color.colorGreen));
			}
		} else {
			if(answerOneText.getText().equals(quizManager.getQuestion().getCorrectAnswer())) {
				answerOne.setBackgroundColor(getResources().getColor(R.color.colorGreen));
			} else if(answerTwoText.getText().equals(quizManager.getQuestion().getCorrectAnswer())) {
				answerTwo.setBackgroundColor(getResources().getColor(R.color.colorGreen));
			} else if(answerThreeText.getText().equals(quizManager.getQuestion().getCorrectAnswer())) {
				answerThree.setBackgroundColor(getResources().getColor(R.color.colorGreen));
			} else if(answerFourText.getText().equals(quizManager.getQuestion().getCorrectAnswer())) {
				answerFour.setBackgroundColor(getResources().getColor(R.color.colorGreen));
			}
		}
	}

	private void resetAnswerColors() {
		answerOne.setBackgroundColor(getResources().getColor(R.color.colorBlack));
		answerTwo.setBackgroundColor(getResources().getColor(R.color.colorBlack));
		answerThree.setBackgroundColor(getResources().getColor(R.color.colorBlack));
		answerFour.setBackgroundColor(getResources().getColor(R.color.colorBlack));
		answerOneImage.setBackgroundColor(getResources().getColor(R.color.colorBlack));
		answerTwoImage.setBackgroundColor(getResources().getColor(R.color.colorBlack));
		answerThreeImage.setBackgroundColor(getResources().getColor(R.color.colorBlack));
		answerFourImage.setBackgroundColor(getResources().getColor(R.color.colorBlack));
	}

	private void setUpQuizEnd() {
		quizManager.getNextQuestion();
		setAnswerPlacesList();
		setQuestionView();
		endLayout.setVisibility(View.GONE);
		isUIResponsive = true;
		leftConfetti.stopEmitting();
		rightConfetti.stopEmitting();
		setUpEmitters();
	}

	private void setQuestionView() {
		if(quizManager.getIsImage()) {
			imageQuestionLayout.setVisibility(View.VISIBLE);
			placeAnswers(quizManager.getCurrentImageQuestion(), quizManager.getIsImage());
			questionTextImage.setText(quizManager.getCurrentImageQuestion().getQuestionString());
			questionImage.setImageResource(quizManager.getCurrentImageQuestion().getImageID());
		} else {
			questionLayout.setVisibility(View.VISIBLE);
			placeAnswers(quizManager.getQuestion(), quizManager.getIsImage());
			questionText.setText(quizManager.getQuestion().getQuestionString());
		}
	}

	private void onAnswerSelect() {
		showCorrectAnswer();
		isUIResponsive = false;
		new CountDownTimer(1000, 1000) {

			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {
				resetAnswerColors();
				if(!quizManager.isLastQuestion()) {
					isUIResponsive = true;
					quizManager.getNextQuestion();
					if(!quizManager.getIsImage()) {
						questionText.setText(quizManager.getQuestion().getQuestionString());
						setAnswerPlacesList();
						placeAnswers(quizManager.getQuestion(), quizManager.getIsImage());
						imageQuestionLayout.setVisibility(View.GONE);
						questionLayout.setVisibility(View.VISIBLE);
					} else {
						questionTextImage.setText(quizManager.getCurrentImageQuestion().getQuestionString());
						setAnswerPlacesList();
						placeAnswers(quizManager.getCurrentImageQuestion(), quizManager.getIsImage());
						questionImage.setImageResource(quizManager.getCurrentImageQuestion().getImageID());
						questionLayout.setVisibility(View.GONE);
						imageQuestionLayout.setVisibility(View.VISIBLE);
					}
				} else {
					questionLayout.setVisibility(View.GONE);
					imageQuestionLayout.setVisibility(View.GONE);
					endLayout.setVisibility(View.VISIBLE);
					scoreText.setText(Integer.toString(quizManager.getScore()));
					if(quizManager.getScore() < 5) {
						message.setText("Better luck next time!");
					} else if(quizManager.getScore() < 7) {
						message.setText("Good");
					} else {
						leftConfetti.emit(view.findViewById(R.id.quiz_emitter_top_left), 8);
						rightConfetti.emit(view.findViewById(R.id.quiz_emitter_top_right), 8);
						message.setText("YAAAAAAAAAAY!");
					}
				}
			}
		}.start();
		progressBarParams.width += displayWidthIncrement;
		progressBar.setLayoutParams(progressBarParams);
	}

	public ArrayList<Integer> setAnswerPlacesList() {
		places = new ArrayList<>();
		places.add(1);
		places.add(2);
		places.add(3);
		places.add(4);
		return places;
	}

	public void placeAnswers(Question question, Boolean isImage) {
		Random r = new Random();
		for(int i = 0; i < question.getAnswers().length; i++) {
			int rand = r.nextInt(places.size());
			int answerPlace = places.get(rand);
			String answer = question.getAnswers()[i];
			places.remove(rand);
			if(isImage) {
				switch(answerPlace) {
					case 1:
						answerOneTextImage.setText(answer);
						break;

					case 2:
						answerTwoTextImage.setText(answer);
						break;

					case 3:
						answerThreeTextImage.setText(answer);
						break;

					case 4:
						answerFourTextImage.setText(answer);
						break;
					default: break;
				}
			} else {
				switch(answerPlace) {
					case 1:
						answerOneText.setText(answer);
						break;

					case 2:
						answerTwoText.setText(answer);
						break;

					case 3:
						answerThreeText.setText(answer);
						break;

					case 4:
						answerFourText.setText(answer);
						break;
					default: break;
				}
			}

		}

	}

}
