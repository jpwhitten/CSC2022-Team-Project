package teamone.tanfieldrailway;

import android.media.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Joe on 08/02/2016.
 */
public class QuizManager {

    private ArrayList<Question> questions = new ArrayList<Question>();
    private ArrayList<ImageQuestion> imageQuestions = new ArrayList<ImageQuestion>();

    private ImageQuestion currentImageQuestion;
    private Question currentQuestion;

    private int questionCount = 0;
    private int score = 0;
    private int maxQuestions;

    Boolean isImage;

    public QuizManager() {
        setUpQuestions();
        setUpImageQuestions();
        this.maxQuestions = 10;
    }

    private void setUpQuestions() {
        Question question1 = new Question("How many wheels does a carriage have?", "4", "6", "8", "12", "4");
        Question question2 = new Question("When was the oldest carriage in Tanfield built?", "1868", "1875", "1883", "1888", "1875");
        Question question3 = new Question("When was the newest carriage in Tanfield built?", "1911", "1919", "1946", "1956", "1956");
        Question question4 = new Question("Where was the Veranda Saloon built?", "York", "Swindon", "Pickering", "Doncaster", "Swindon");
        Question question5 = new Question("What is the carriage shed called?", "Fawley Hill", "Marley Hill", "Tanfield Hill", "Crossley Hill", "Marley Hill");
        Question question6 = new Question("Tanfield Railway is the World's ________ Railway.", "Quickest", "Biggest", "Oldest", "Youngest", "Oldest");
        Question question7 = new Question("How lomg is the Tanfield line", "1.5 miles", "2 miles", "3 miles", "4 miles", "3 miles");
        Question question8 = new Question("What was Tanfield Railway originally used to transport?", "Coal", "Wood", "Iron", "Stone", "Coal");
        Question question9 = new Question("When did the conversion from wooden railed to steel railed begin?", "1833", "1834", "1836", "1837", "1837");
        Question question10 = new Question("question10", "1", "2", "3", "4", "2");
        Question question11 = new Question("question11", "1", "2", "3", "4", "2");
        Question question12 = new Question("question12", "1", "2", "3", "4", "2");
        Question question13 = new Question("question13", "1", "2", "3", "4", "2");
        Question question14 = new Question("question14", "1", "2", "3", "4", "2");
        Question question15 = new Question("question15", "1", "2", "3", "4", "2");
        Question question16 = new Question("question16", "1", "2", "3", "4", "2");
        Question question17 = new Question("question17", "1", "2", "3", "4", "2");
        Question question18 = new Question("question17", "1", "2", "3", "4", "2");
        Question question19 = new Question("question18", "1", "2", "3", "4", "2");
        Question question20 = new Question("question19", "1", "2", "3", "4", "2");
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
        questions.add(question11);
        questions.add(question12);
        questions.add(question13);
        questions.add(question14);
        questions.add(question15);
        questions.add(question16);
        questions.add(question17);
        questions.add(question18);
        questions.add(question19);
        questions.add(question20);
    }

    private void setUpImageQuestions() {
        ImageQuestion imageQuestion1 = new ImageQuestion("Question: When was the Causey Arch completed?", R.drawable.slideshow5, "1723", "1726", "1729", "1731", "1726");
        ImageQuestion imageQuestion2 = new ImageQuestion("imagequestion2", R.drawable.slideshow2, "1", "2", "3", "4", "2");
        ImageQuestion imageQuestion3 = new ImageQuestion("imagequestion3", R.drawable.slideshow3, "1", "2", "3", "4", "2");
        ImageQuestion imageQuestion4 = new ImageQuestion("imagequestion4", R.drawable.slideshow4, "1", "2", "3", "4", "2");
        ImageQuestion imageQuestion5 = new ImageQuestion("imagequestion5", R.drawable.slideshow5, "1", "2", "3", "4", "2");
        imageQuestions.add(imageQuestion1);
        imageQuestions.add(imageQuestion2);
        imageQuestions.add(imageQuestion3);
        imageQuestions.add(imageQuestion4);
        imageQuestions.add(imageQuestion5);
    }

    public void getNextQuestion() {
        Random r = new Random();
        int i = r.nextInt(3);
        if(i < 2  || imageQuestions.isEmpty()) {
            isImage = false;
            int questionNumber = r.nextInt(questions.size());
            currentQuestion = questions.get(questionNumber);
            questions.remove(questionNumber);
        } else {
            isImage = true;
            int questionNumber = r.nextInt(imageQuestions.size());
            currentImageQuestion = imageQuestions.get(questionNumber);
            imageQuestions.remove(questionNumber);
        }
        incrementCount();
    }

    public int getScore() {
        return score;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public int getMaxQuestions() {
        return maxQuestions;
    }

    public Boolean getIsImage() {
        return isImage;
    }

    public ImageQuestion getCurrentImageQuestion() {
        return currentImageQuestion;
    }

    public Question getQuestion() {

        return currentQuestion;
    }

    public void incrementScore() {

        score++;
    }

    public void incrementCount() {

        questionCount++;
    }

    public Boolean isLastQuestion() {
        return questionCount == maxQuestions;
    }

}

