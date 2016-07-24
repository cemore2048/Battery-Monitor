package com.demoji.rafa.demoji;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rafa on 7/24/16.
 */
public class Fold extends Visibility {
    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createFoldAnimator(view, false);
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createFoldAnimator(view, true);
    }

    public Animator createFoldAnimator(View view, boolean folding) {
        int start = view.getTop();
        int end = view.getTop() + view.getMeasuredHeight() - 1;
        if (folding) {
            int temp = start;
            start = end;
            end = temp;
        }
        view.setBottom(start);

        ObjectAnimator animator = ObjectAnimator.ofInt(view, "bottom", start, end);
        return animator;
    }
}
