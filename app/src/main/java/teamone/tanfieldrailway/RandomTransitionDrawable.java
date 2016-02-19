package teamone.tanfieldrailway;

/**
 * NOTICE
 * Based on the TransitionDrawable code from Android Open Source Project
 * http://source.android.com/
 */

//JW: Implemented random behaviour

        import android.graphics.Canvas;
        import android.graphics.drawable.Drawable;
        import android.graphics.drawable.LayerDrawable;
        import android.os.SystemClock;
        import android.widget.ImageView;

        import java.util.Random;

public class RandomTransitionDrawable extends LayerDrawable implements Drawable.Callback {
    protected enum TransitionState {
        STARTING,
        PAUSED, RUNNING
    }

    protected Drawable[] drawables;
    protected int currentDrawableIndex;
    protected int alpha = 0;
    protected int fromAlpha;
    protected int toAlpha;
    protected long duration;
    protected long startTimeMillis;
    protected long pauseDuration;
    private Random r = new Random();
    private Boolean shouldSelect = true;
    int nextDrawableIndex = 0;

    protected TransitionState transitionStatus;

    public RandomTransitionDrawable(Drawable[] drawables) {
        super(drawables);
        this.drawables = drawables;
    }

    public void startTransition(int durationMillis, int pauseTimeMillis) {
        fromAlpha = 0;
        toAlpha = 255;
        duration = durationMillis;
        pauseDuration = pauseTimeMillis;
        startTimeMillis = SystemClock.uptimeMillis();
        transitionStatus = TransitionState.PAUSED;
        currentDrawableIndex = getRandomInt();

        invalidateSelf();
    }

    public int getRandomInt() {
        return r.nextInt(drawables.length);
    }

    public int getNextRandomInt() {
        int i;
        i = r.nextInt(drawables.length);
        if(i == currentDrawableIndex) {
            return getNextRandomInt();
        } else {
            return i;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        boolean done = true;

        switch (transitionStatus) {
            case STARTING:
                done = false;
                transitionStatus = TransitionState.RUNNING;
                break;

            case PAUSED:
                if ((SystemClock.uptimeMillis() - startTimeMillis) < pauseDuration)
                    break;
                else {
                    done = false;
                    startTimeMillis = SystemClock.uptimeMillis();
                    transitionStatus = TransitionState.RUNNING;
                }

            case RUNNING:
                break;
        }

        // Determine position within the transition cycle
        if (startTimeMillis >= 0) {
            float normalized = (float) (SystemClock.uptimeMillis() - startTimeMillis) / duration;
            done = normalized >= 1.0f;
            normalized = Math.min(normalized, 1.0f);
            alpha = (int) (fromAlpha + (toAlpha - fromAlpha) * normalized);
        }

        if (transitionStatus == TransitionState.RUNNING) {
            // Cross fade the current
            if(shouldSelect) {
                nextDrawableIndex = getNextRandomInt();
                shouldSelect = false;
            }

            Drawable currentDrawable = getDrawable(currentDrawableIndex);
            Drawable nextDrawable = getDrawable(nextDrawableIndex);

            // Apply cross fade and draw the current drawable
            currentDrawable.setAlpha(255 - alpha);
            currentDrawable.draw(canvas);
            currentDrawable.setAlpha(0xFF);

            if (alpha > 5) {
                nextDrawable.setAlpha(alpha);
                nextDrawable.draw(canvas);
                nextDrawable.setAlpha(0xFF);
            }

            // If we have finished, move to the next transition
            if (done) {
                currentDrawableIndex = nextDrawableIndex;
                startTimeMillis = SystemClock.uptimeMillis();
                shouldSelect = true;

                transitionStatus = TransitionState.PAUSED;
            }
        }
        else
            getDrawable(currentDrawableIndex).draw(canvas);

        invalidateSelf();
    }
}