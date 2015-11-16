package com.techstarvation.learningmosby.mvp.presenter;

/**
 * Created by administrator on 12/11/15.
 */

import com.techstarvation.learningmosby.mvp.view.HelloWorldView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.techstarvation.learningmosby.mvp.model.GreetingGeneratorTask;

public class HelloWorldPresenter extends MvpBasePresenter<HelloWorldView> {

    // Greeting Task is "business logic"
    private GreetingGeneratorTask greetingTask;

    private void cancelGreetingTaskIfRunning(){
        if (greetingTask != null){
            greetingTask.cancel(true);
        }
    }

    public void greetHello(){
        cancelGreetingTaskIfRunning();

        greetingTask = new GreetingGeneratorTask("Hello", new GreetingGeneratorTask.GreetingTaskListener(){
            public void onGreetingGenerated(String greetingText){
                if (isViewAttached())
                    getView().showHello(greetingText);
            }
        });
        greetingTask.execute();
    }

    public void greetGoodbye(){
        cancelGreetingTaskIfRunning();

        greetingTask = new GreetingGeneratorTask("Goodbye", new GreetingGeneratorTask.GreetingTaskListener(){
            public void onGreetingGenerated(String greetingText){
                if (isViewAttached())
                    getView().showGoodbye(greetingText);
            }
        });
        greetingTask.execute();
    }

    // Called when Activity gets destroyed, so cancel running background task
    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
        if (!retainPresenterInstance){
            cancelGreetingTaskIfRunning();
        }
    }
}