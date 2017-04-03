package com.saberrr.openchina.presenter;

import com.saberrr.openchina.ui.fragment.JumpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saberrr on 2017-04-02.
 */

public class JumpPresenterImpl implements JumpPresenter {


    private JumpView mJumpView;
    private List mList = new ArrayList();

    public JumpPresenterImpl(JumpView jumpView) {
        mJumpView = jumpView;
    }

}
