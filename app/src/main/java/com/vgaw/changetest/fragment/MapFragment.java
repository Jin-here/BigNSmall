package com.vgaw.changetest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by caojin on 2016/10/17.
 */

public class MapFragment extends ThumbnailBaseFragment {
    // for test
    private boolean isBig;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(android.R.layout.test_list_item, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = (TextView)getView().findViewById(android.R.id.text1);
        tv.setText("MapFragment(you can click)");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "you clicked me!" + isBig, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("fuck", "map:onPause");
    }

    @Override
    public boolean askedForBig() {
        /*if (new Random().nextInt(3) == 1){
            return false;
        }
        return true;*/
        isBig = true;
        return false;
    }

    @Override
    public void preparedForSmall() {
        isBig = false;
    }
}
