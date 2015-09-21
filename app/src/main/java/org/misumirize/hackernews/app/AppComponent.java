package org.misumirize.hackernews.app;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(StoryListPresenter presenter);
}
