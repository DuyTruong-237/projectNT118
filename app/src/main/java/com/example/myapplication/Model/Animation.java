package com.example.myapplication.Model;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.example.myapplication.R;

public class Animation implements View.OnTouchListener{
    private GestureDetector mGestureDetector;

    public Animation(Context context, View view) {
        mGestureDetector = new GestureDetector(context, new SimpleGestureDetector(view));
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return mGestureDetector.onTouchEvent(motionEvent);
    }

    public  class SimpleGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private View mViewAnimation;
        private boolean isFinishAnimation = true;

        public SimpleGestureDetector(View mViewAnimation) {
            this.mViewAnimation = mViewAnimation;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (distanceY > 0) {
                hiddenView();
            } else {
                showView();
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        private void hiddenView() {
            if (mViewAnimation == null || mViewAnimation.getVisibility() == View.GONE) {
                return;
            }

            android.view.animation.Animation animationDown = AnimationUtils.loadAnimation(mViewAnimation.getContext(), R.anim.bottom_down);
            animationDown.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
                @Override
                public void onAnimationStart(android.view.animation.Animation animation) {
                    mViewAnimation.setVisibility(View.VISIBLE);
                    isFinishAnimation = false;
                }

                @Override
                public void onAnimationEnd(android.view.animation.Animation animation) {
                    mViewAnimation.setVisibility(View.GONE);
                    isFinishAnimation = true;
                }

                @Override
                public void onAnimationRepeat(android.view.animation.Animation animation) {

                }
            });
            if (isFinishAnimation) {
                mViewAnimation.startAnimation(animationDown);
            }
        }

        private void showView() {
            if (mViewAnimation == null || mViewAnimation.getVisibility() == View.VISIBLE) {
                return;
            }

            android.view.animation.Animation animationUp = AnimationUtils.loadAnimation(mViewAnimation.getContext(), R.anim.bottom_up);
            animationUp.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
                @Override
                public void onAnimationStart(android.view.animation.Animation animation) {
                    mViewAnimation.setVisibility(View.VISIBLE);
                    isFinishAnimation = false;
                }

                @Override
                public void onAnimationEnd(android.view.animation.Animation animation) {
                    isFinishAnimation = true;
                }

                @Override
                public void onAnimationRepeat(android.view.animation.Animation animation) {

                }
            });
            if (isFinishAnimation) {
                mViewAnimation.startAnimation(animationUp);
            }

        }
    }


}
