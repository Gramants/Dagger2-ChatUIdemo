package com.schibsted.android.chatbot;

import android.app.Application;


import com.schibsted.android.chatbot.view.ChatActivityComponent;
import com.schibsted.android.chatbot.di.messageprovider.DaggerMessagesNetworkComponent;
import com.schibsted.android.chatbot.di.messageprovider.MessagesNetworkComponent;
import com.schibsted.android.chatbot.di.messageprovider.MessagesNetworkModule;
import com.schibsted.android.chatbot.di.storageprovider.AppComponent;
import com.schibsted.android.chatbot.di.storageprovider.AppModule;
import com.schibsted.android.chatbot.di.storageprovider.DaggerAppComponent;
import com.schibsted.android.chatbot.util.Constant;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class App extends Application {

    private ChatActivityComponent mChatActivityComponent;
    private AppComponent mAppcomponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/robotoregular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }


    private void resolveDependency() {



        mAppcomponent =DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .messagesNetworkModule(new MessagesNetworkModule(Constant.MESSAGE_LIST_BASE_URL))
                .build();
    }




    public MessagesNetworkComponent getMessagesNetworkComponent() {
        return DaggerMessagesNetworkComponent.builder()
                .messagesNetworkModule(new MessagesNetworkModule(Constant.MESSAGE_LIST_BASE_URL))
                .build();
    }

    public ChatActivityComponent getMessagesApiComponent() {
        return mChatActivityComponent;
    }


    public AppComponent getAppComponent() {
        return mAppcomponent;
    }


}
