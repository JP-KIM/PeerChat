package com.jpstudio.peerchat;

import android.app.Application;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.StoringMechanism;

import io.realm.Realm;

/**
 * Created by jungpyokim on 2017. 9. 23..
 */
public class PeerChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        initQB();

    }

    static final String APP_ID = "62676";
    static final String AUTH_KEY = "9zXStZ93wNvaa8G";
    static final String AUTH_SECRET = "KPWVxWVOVwAYya2";
    static final String ACCOUNT_KEY = "WPKay5W2M6WDFoG4hBYc";

    private void initQB() {
        QBSettings.getInstance().setStoringMehanism(StoringMechanism.UNSECURED);
        QBSettings.getInstance().init(getApplicationContext(), APP_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);

        /*
        QBHttpConnectionConfig.setConnectTimeout(timeout); //timeout value in milliseconds.
        QBHttpConnectionConfig.setReadTimeout(timeout); //timeout value in milliseconds.
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.30.30.1", 3128));
        QBHttpConnectionConfig.setProxy(proxy);
        */


    }
}
