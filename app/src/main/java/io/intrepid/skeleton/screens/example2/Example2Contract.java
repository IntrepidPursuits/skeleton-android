package io.intrepid.skeleton.screens.example2;

import io.intrepid.skeleton.base.BaseContract;

interface Example2Contract {
    interface View extends BaseContract.View {

        void showCurrentIpAddress(String text);

        void showPreviousIpAddress(String text);

        void hidePreviousIpAddress();
    }

    interface Presenter extends BaseContract.Presenter<View> {

    }
}
