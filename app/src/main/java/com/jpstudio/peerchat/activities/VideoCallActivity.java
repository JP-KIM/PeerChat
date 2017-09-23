package com.jpstudio.peerchat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jpstudio.peerchat.R;
import com.quickblox.videochat.webrtc.QBRTCSession;
import com.quickblox.videochat.webrtc.QBSignalingSpec;
import com.quickblox.videochat.webrtc.callbacks.QBRTCClientSessionCallbacks;
import com.quickblox.videochat.webrtc.callbacks.QBRTCSessionStateCallback;
import com.quickblox.videochat.webrtc.callbacks.QBRTCSignalingCallback;
import com.quickblox.videochat.webrtc.exception.QBRTCSignalException;

import java.util.Map;

public class VideoCallActivity extends AppCompatActivity implements QBRTCClientSessionCallbacks, QBRTCSessionStateCallback, QBRTCSignalingCallback {
        //OnCallEventsController, IncomeCallFragmentCallbackListener, ConversationFragmentCallbackListener, NetworkConnectionChecker.OnConnectivityChangedListener, ScreenShareFragment.OnSharingEvents {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videocall);
    }


    // QBRTCClientSessionCallbacks start
    @Override
    public void onReceiveNewSession(QBRTCSession qbrtcSession) {

    }

    // QBRTCSessionEventsCallback start
    @Override
    public void onUserNotAnswer(QBRTCSession qbrtcSession, Integer integer) {

    }

    @Override
    public void onCallRejectByUser(QBRTCSession qbrtcSession, Integer integer, Map<String, String> map) {

    }

    @Override
    public void onCallAcceptByUser(QBRTCSession qbrtcSession, Integer integer, Map<String, String> map) {

    }

    @Override
    public void onReceiveHangUpFromUser(QBRTCSession qbrtcSession, Integer integer, Map<String, String> map) {

    }
    // QBRTCSessionEventsCallback end

    @Override
    public void onUserNoActions(QBRTCSession qbrtcSession, Integer integer) {

    }

    @Override
    public void onSessionStartClose(QBRTCSession qbrtcSession) {

    }

    @Override
    public void onSessionClosed(QBRTCSession qbrtcSession) {

    }
    // QBRTCClientSessionCallbacks end

    // QBRTCSessionStateCallback start
    @Override
    public void onConnectedToUser(QBRTCSession qbrtcSession, Integer integer) {

    }

    @Override
    public void onConnectionClosedForUser(QBRTCSession qbrtcSession, Integer integer) {

    }

    @Override
    public void onDisconnectedFromUser(QBRTCSession qbrtcSession, Integer integer) {

    }
    // QBRTCSessionStateCallback end



    @Override
    public void onSuccessSendingPacket(QBSignalingSpec.QBSignalCMD qbSignalCMD, Integer integer) {

    }

    @Override
    public void onErrorSendingPacket(QBSignalingSpec.QBSignalCMD qbSignalCMD, Integer integer, QBRTCSignalException e) {

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_videocall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
