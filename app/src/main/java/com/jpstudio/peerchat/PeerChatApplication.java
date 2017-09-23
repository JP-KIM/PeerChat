package com.jpstudio.peerchat;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by jungpyokim on 2017. 9. 23..
 */
public class PeerChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
