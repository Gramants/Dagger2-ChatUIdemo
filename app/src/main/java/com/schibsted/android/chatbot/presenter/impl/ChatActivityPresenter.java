package com.schibsted.android.chatbot.presenter.impl;


import com.schibsted.android.chatbot.App;
import com.schibsted.android.chatbot.model.Chat;
import com.schibsted.android.chatbot.model.ChatMessage;
import com.schibsted.android.chatbot.net.ChatMessageListService;
import com.schibsted.android.chatbot.persistence.PersistentStorageProxy;
import com.schibsted.android.chatbot.presenter.ChatPresenterContract;
import com.schibsted.android.chatbot.presenter.ChatViewContract;
import com.schibsted.android.chatbot.view.ChatActivity;
import java.util.List;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;


public class ChatActivityPresenter implements ChatPresenterContract {


    private ChatViewContract callback;
    private ChatMessageListService chatWebService;
    private PersistentStorageProxy StorageProxy;
    private Subscription subscriptionmessages;

    @Inject
    public ChatActivityPresenter(ChatViewContract mChatView,PersistentStorageProxy mStorageProxy,ChatMessageListService chatWebService) {
        this.callback=mChatView;
        this.StorageProxy=mStorageProxy;
        this.chatWebService=chatWebService;

    }


    @Override
    public void CurrentMessages()  {

        callback.startLoadMessages();

        if (subscriptionmessages != null) subscriptionmessages.unsubscribe();
        subscriptionmessages = chatWebService.fetchFilteredResults()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ChatMessage>() {

                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable error) {}

                    @Override
                    public void onNext(ChatMessage chatMessage) {
                        callback.showChatMessages(chatMessage.getChats());
                    }

                });
    }

}
