package com.schibsted.android.chatbot.di.storageprovider;


import com.schibsted.android.chatbot.view.ChatActivityComponent;
import com.schibsted.android.chatbot.view.ChatActivityModule;
import com.schibsted.android.chatbot.di.messageprovider.MessagesNetworkModule;
import com.schibsted.android.chatbot.view.LoginActivity;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, MessagesNetworkModule.class})
public interface AppComponent {
    void inject(LoginActivity login);
    ChatActivityComponent plus(ChatActivityModule module);
}