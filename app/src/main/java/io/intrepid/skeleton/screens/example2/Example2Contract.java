package io.intrepid.skeleton.screens.example2;

import io.intrepid.skeleton.base.BaseContract;

class Example2Contract {
    public interface View extends BaseContract.View {

        void showIpAddress(String text);
    }

    public interface Presenter extends BaseContract.Presenter<View> {

    }
}
