package io.intrepid.getaprint.screens.example2;

import io.intrepid.getaprint.base.BaseContract;

class Example2Contract {
    public interface View extends BaseContract.View {

        void showCurrentIpAddress(String text);

        void showPreviousIpAddress(String text);

        void hidePreviousIpAddress();
    }

    public interface Presenter extends BaseContract.Presenter<View> {

    }
}
