package io.intrepid.getaprint.screens.example1;

import android.content.Intent;
import android.support.annotation.NonNull;

import butterknife.OnClick;
import io.intrepid.getaprint.R;
import io.intrepid.getaprint.base.BaseMvpActivity;
import io.intrepid.getaprint.base.PresenterConfiguration;
import io.intrepid.getaprint.screens.example1.Example1Contract.View;
import io.intrepid.getaprint.screens.example2.Example2Activity;

public class Example1Activity extends BaseMvpActivity<Example1Contract.Presenter> implements View {
    @NonNull
    @Override
    public Example1Contract.Presenter createPresenter(PresenterConfiguration configuration) {
        return new Example1Presenter(this, configuration);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_example1;
    }

    @OnClick(R.id.example1_button)
    void onButtonClicked() {
        presenter.onButtonClicked();
    }

    @Override
    public void gotoExample2() {
        Intent intent = new Intent(this, Example2Activity.class);
        startActivity(intent);
    }
}
