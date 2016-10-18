package com.vgaw.changetest.fragment;

import android.app.Fragment;

import com.vgaw.changetest.LiveActivity;

/**
 * Created by caojin on 2016/10/17.
 */

public abstract class ThumbnailBaseFragment extends Fragment {
    /**
     * @return true:自己吸收
     *          false:由{@link LiveActivity}处理
     */
    public abstract boolean askedForBig();

    /**
     * 为缩小做准备，例如：隐藏不必要的view，以防影响缩略图显示效果
     */
    public abstract void preparedForSmall();
}
