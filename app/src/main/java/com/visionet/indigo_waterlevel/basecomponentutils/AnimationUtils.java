package com.visionet.indigo_waterlevel.basecomponentutils;

import android.animation.ObjectAnimator;
import android.view.View;

import com.github.aakira.expandablelayout.Utils;

public class AnimationUtils {

    public static ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }
}
