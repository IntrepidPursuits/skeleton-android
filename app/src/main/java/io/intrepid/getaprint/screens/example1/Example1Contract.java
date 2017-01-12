package io.intrepid.getaprint.screens.example1;

import io.intrepid.getaprint.base.BaseContract;

class Example1Contract {
    public interface View extends BaseContract.View {

        void gotoExample2();
    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void onButtonClicked();
    }
}
