package com.techstarvation.learningmosby;

import android.os.Bundle;
import butterknife.ButterKnife;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import android.graphics.Color;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.techstarvation.learningmosby.mvp.presenter.HelloWorldPresenter;
import com.techstarvation.learningmosby.mvp.view.HelloWorldView;

public class MainActivity extends MvpActivity<HelloWorldView,HelloWorldPresenter>
implements HelloWorldView
{
    @Bind(R.id.greetingTextView) TextView greetingTextView;

    @Override
    protected void onCreate(Bundle savedState){
        super.onCreate(savedState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);
    }

    @Override // Called internally by Mosby
    public HelloWorldPresenter createPresenter(){
        return new HelloWorldPresenter();
    }

    @OnClick(R.id.helloButton)
    public void onHelloButtonClicked(){
        presenter.greetHello();
    }

    @OnClick(R.id.goodbyeButtonClicked)
    public void onGoodbyeButtonClicked(){
        presenter.greetGoodbye();
    }

    @Override
    public void showHello(String greetingText){
        greetingTextView.setTextColor(Color.RED);
        greetingTextView.setText(greetingText);
    }

    @Override
    public void showGoodbye(String greetingText){
        greetingTextView.setTextColor(Color.BLUE);
        greetingTextView.setText(greetingText);
    }
}
