package teamone.tanfieldrailway;

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
        Question question7 = new Question("How long is the Tanfield line", "1.5 miles", "2 miles", "3 miles", "4 miles", "3 miles");
        Question question8 = new Question("What was Tanfield Railway originally used to transport?", "Coal", "Wood", "Iron", "Stone", "Coal");
        Question question9 = new Question("When did the conversion from wooden railed to steel railed begin?", "1833", "1834", "1836", "1837", "1837");
        Question question10 = new Question("When does the railway date back to?", "1725", "1845", "1760", "1900", "1725");
        Question question11 = new Question("What is the name of the World's oldest existing railway bridge?", "Causey Arch", "Casey Arch", "Cousey Arch", "Cascading Arch", "Causey Arch");
        Question question12 = new Question("How tall is the Arch of Causey Arch?", "80ft", "105ft", "50ft", "76ft", "80ft");
        Question question13 = new Question("What is the northern terminus of the railway called?", "Sunnyside", "Andrew's House", "North Tanfield", "Marley Hill", "Sunnyside");
        Question question14 = new Question("How heavy is Sir Cecil A Cochrane?", "16 Tons", "21 Tons", "15 Tons", "27 Tons", "21 Tons");
        Question question15 = new Question("What Engine is PLANET fitted with?", "85hp Saurer 6BLD", "A 77hp Dorman 4DL", "107hp Gardner 6LW", "95hp Saurer 6BXD", "A 77hp Dorman 4DL");
        Question question16 = new Question("The No.1 Huskey was one of how many small shunting Locomotives of it's type?", "1 of 18", "1 of 13", "1 of 25", "1 of 17", "1 of 13");
        Question question17 = new Question("Who built the No.3 TWIZELL?", "James Joicey & Co", "Robert Stephenson & Co", "Sir W G Armstrong", "WF & JR Shepherd Ltd", "Robert Stephenson & Co");
        Question question18 = new Question("How many 18' Cylinders does Nobels Explosives No.2 Have?", "8", "12", "16", "10", "12");
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
        ImageQuestion imageQuestion1 = new ImageQuestion("When was the Causey Arch completed?", R.drawable.slideshow5, "1723", "1726", "1729", "1731", "1726");
        ImageQuestion imageQuestion2 = new ImageQuestion("Name this train", R.drawable.quiz1, "No.3 TWIZELL", "Nobels Explosives No.2", "Sir Cecile A Cochrane ", "PLANET", "Nobels Explosives No.2");
        ImageQuestion imageQuestion3 = new ImageQuestion("What is the name of this Engine Shed?", R.drawable.quiz2, "Manly Hill", "Marley Hill", "Murkey Hut", "Causey Hill", "Marley Hill");
        ImageQuestion imageQuestion4 = new ImageQuestion("What is the name of this bridge?", R.drawable.quiz3, "Cosy Arch", "Causey Arch", "Marley Bridge", "Manly Bridge", "Causey Arch");
        ImageQuestion imageQuestion5 = new ImageQuestion("What time of year do we showcase the North Pole Express?", R.drawable.quiz4, "Easter", "Christmas", "Chinese New Year", "Eid", "Christmas");
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

