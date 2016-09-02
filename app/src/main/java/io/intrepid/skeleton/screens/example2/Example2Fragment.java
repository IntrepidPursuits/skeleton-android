package io.intrepid.skeleton.screens.example2;

import android.support.annotation.NonNull;
import android.widget.TextView;

import butterknife.BindView;
import io.intrepid.skeleton.R;
import io.intrepid.skeleton.base.BaseFragment;
import io.intrepid.skeleton.base.PresenterConfiguration;

public class Example2Fragment extends BaseFragment<Example2Contract.Presenter> implements Example2Contract.View {

    @BindView(R.id.example2_text)
    TextView textView;

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
    public void showIpAddress(String ip) {
        textView.setText("Your Ip address is " + ip);
    }
}
