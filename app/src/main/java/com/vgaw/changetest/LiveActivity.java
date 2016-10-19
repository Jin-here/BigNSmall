package com.vgaw.changetest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.vgaw.changetest.fragment.LiveFragment;
import com.vgaw.changetest.fragment.MapFragment;
import com.vgaw.changetest.fragment.ThumbnailBaseFragment;

/**
 * Advantage:you can change between two fragment without going through life cycle
 */
public class LiveActivity extends AppCompatActivity {
    private ViewGroup v_parent;

    private ViewGroup container_big;
    private ViewGroup container_thumbnail;
    private ThumbnailBaseFragment[] fragments = new ThumbnailBaseFragment[2];
    private ViewGroup[] viewGroups = new ViewGroup[2];
    // the big one
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v_parent = (ViewGroup) findViewById(R.id.v_parent);
        container_big = (ViewGroup) findViewById(R.id.container_big);
        container_thumbnail = (ViewGroup) findViewById(R.id.container_thumbnail);
        container_big.setOnClickListener(clickListener);
        container_thumbnail.setOnClickListener(clickListener);

        fragments[0] = new LiveFragment();
        fragments[1] = new MapFragment();
        viewGroups[0] = container_big;
        viewGroups[1] = container_thumbnail;

        getFragmentManager().beginTransaction()
                .add(R.id.container_big, fragments[0])
                .add(R.id.container_thumbnail, fragments[1])
                .commit();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == viewGroups[1 - currentIndex].getId()){
                if (!fragments[1 - currentIndex].askedForBig()){
                    // 位置1
                    fragments[currentIndex].preparedForSmall();
                    animateBig();
                }
            }
        }
    };

    private void animateBig(){
        final ViewGroup temp = viewGroups[1 - currentIndex];
        ValueAnimator animator = ValueAnimator.ofFloat(temp.getWidth(), v_parent.getWidth());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float width = (float) animation.getAnimatedValue();
                float height = width / v_parent.getWidth() * v_parent.getHeight();
                updateParams(temp, (int) width, (int) height);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                toBeSmall();
                v_parent.bringChildToFront(viewGroups[currentIndex]);
                currentIndex = 1 - currentIndex;
            }
        });
        animator.start();
    }

    private void toBeSmall(){
        // 位置2
        //fragments[currentIndex].preparedForSmall();
        updateParams(viewGroups[currentIndex], 300, 400);
    }

    private void updateParams(View view, int width, int height){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }
}
