package com.example.puestadelsol;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import androidx.fragment.app.Fragment;

public class SunsetFragment extends Fragment {
    private View mSceneView;
    private View mSunView;
    private View mSkyView;

    private int mBlueSkyColor, mGreySkyColor, mOrangeSkyColor, mBlackSkyColor;

    private boolean clicked;

    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunset, container, false);

        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mSkyView = view.findViewById(R.id.sky);

        Resources resources = getResources();
        mBlueSkyColor = resources.getColor(R.color.blue_sky);
        mGreySkyColor = resources.getColor(R.color.grey_sky);
        mOrangeSkyColor = resources.getColor(R.color.orange_sky);
        mBlackSkyColor = resources.getColor(R.color.black_sky);

        clicked = true;

        mSceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == true) {
                    startAnimation();
                    clicked = false;
                } else {
                    clicked = true;
                }

            }
        });

        return view;
    }

    private void startAnimation() {
        float sunStart = mSunView.getTop();
        float sunEnd = mSkyView.getHeight();
        ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(mSunView, "y", sunStart, sunEnd).setDuration(3000);
        heightAnimator.setInterpolator(new AccelerateInterpolator());

        // The blue sky changes to grey
        ObjectAnimator GreySkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mBlueSkyColor, mGreySkyColor).setDuration(1500);
        GreySkyAnimator.setEvaluator(new ArgbEvaluator());

        // The grey sky changes to orange
        ObjectAnimator OrangeSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mGreySkyColor, mOrangeSkyColor).setDuration(1500);
        OrangeSkyAnimator.setEvaluator(new ArgbEvaluator());

        // The orange sky changes to black
        ObjectAnimator BlackSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mOrangeSkyColor, mBlackSkyColor).setDuration(1500);
        BlackSkyAnimator.setEvaluator(new ArgbEvaluator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(heightAnimator).with(GreySkyAnimator).with(OrangeSkyAnimator).before(BlackSkyAnimator);
        animatorSet.start();

    }
}
