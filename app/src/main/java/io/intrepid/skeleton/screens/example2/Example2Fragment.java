package io.intrepid.skeleton.screens.example2;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import io.intrepid.skeleton.R;
import io.intrepid.skeleton.base.BaseFragment;
import io.intrepid.skeleton.base.PresenterConfiguration;

public class Example2Fragment extends BaseFragment<Example2Contract.Presenter> implements Example2Contract.View {

    @BindView(R.id.example2_current_ip)
    TextView currentIpView;

    @BindView(R.id.example2_previous_ip)
    TextView previousIpView;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_example2;
    }

    @NonNull
    @Override
    public Example2Contract.Presenter createPresenter(PresenterConfiguration configuration) {
        return new Example2Presenter(this, configuration);
    }

    @Override
    public void showCurrentIpAddress(String ip) {
        // This should be extracted to string resource in a real app, but we are inlining this for the
        // example so that string.xml is not cluttered up with example texts
        currentIpView.setText("Your current Ip address is " + ip);
    }

    @Override
    public void showPreviousIpAddress(String ip) {
        previousIpView.setVisibility(View.VISIBLE);
        previousIpView.setText("Your previous Ip address is " + ip);
    }

    @Override
    public void hidePreviousIpAddress() {
        previousIpView.setVisibility(View.GONE);
    }
}
