package io.intrepid.skeleton.screens.example1;

import io.intrepid.skeleton.base.BasePresenter;
import io.intrepid.skeleton.base.BaseView;

public class Example1Contract {
    public interface View extends BaseView {

        void gotoExample2();
    }

    public interface Presenter extends BasePresenter<View> {

        void onButtonClicked();
    }
}
