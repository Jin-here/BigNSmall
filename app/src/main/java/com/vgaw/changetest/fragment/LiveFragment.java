package com.vgaw.changetest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by caojin on 2016/10/17.
 */

public class LiveFragment extends ThumbnailBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(android.R.layout.test_list_item, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((TextView)getView().findViewById(android.R.id.text1)).setText("LiveFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("fuck", "live:onPause");
    }

    @Override
    public boolean askedForBig() {
        return false;
    }

    @Override
    public void preparedForSmall() {

    }
}
