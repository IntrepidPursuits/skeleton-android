package io.intrepid.skeleton.screens.example1;

import io.intrepid.skeleton.base.BaseContract;

interface Example1Contract {
    interface View extends BaseContract.View {

        void gotoExample2();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void onButtonClick();
    }
}
