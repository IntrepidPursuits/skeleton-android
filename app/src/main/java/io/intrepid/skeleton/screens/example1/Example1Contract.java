package io.intrepid.skeleton.screens.example1;

import io.intrepid.skeleton.base.BaseContract;

public class Example1Contract {
    public interface View extends BaseContract.View {

        void gotoExample2();
    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void onButtonClicked();
    }
}
