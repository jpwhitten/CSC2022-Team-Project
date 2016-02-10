package teamone.tanfieldrailway;

/**
 * Created by Joe on 09/02/2016.
 */
public class ImageQuestion extends Question {

    int imageID;

    public ImageQuestion(String questionString, int imageID, String possibleAnswerOne, String possibleAnswerTwo, String possibleAnswerThree, String possibleAnswerFour, String correctAnswer) {
        super(questionString, possibleAnswerOne, possibleAnswerTwo, possibleAnswerThree, possibleAnswerFour, correctAnswer);
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }
}

