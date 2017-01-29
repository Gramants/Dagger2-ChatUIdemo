package com.schibsted.android.chatbot.view;


import com.schibsted.android.chatbot.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent (modules = {ChatActivityModule.class})
public interface ChatActivityComponent {

    void inject(ChatActivity chatactivity);
}
