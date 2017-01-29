package com.schibsted.android.chatbot.view;



import com.schibsted.android.chatbot.ActivityScope;
import com.schibsted.android.chatbot.net.ChatMessageListService;
import com.schibsted.android.chatbot.presenter.ChatPresenterContract;
import com.schibsted.android.chatbot.presenter.ChatViewContract;
import com.schibsted.android.chatbot.presenter.impl.ChatActivityPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class ChatActivityModule {

    public final ChatViewContract view;

    public ChatActivityModule(ChatViewContract view) {
        this.view = view;
    }


    @Provides
    @ActivityScope
    ChatMessageListService provideChatMessageListService (Retrofit retrofit) {
        return retrofit.create(ChatMessageListService.class);
    }


    @Provides
    @ActivityScope
    ChatViewContract provideMainView() {
        return this.view;
    }


    @Provides
    @ActivityScope
    ChatPresenterContract provideChatActivityPresenter(ChatActivityPresenter presenter) {
        return presenter;
    }

}
