package com.jpstudio.peerchat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jpstudio.peerchat.R;
import com.quickblox.auth.session.QBSession;
import com.quickblox.auth.session.QBSessionManager;
import com.quickblox.auth.session.QBSessionParameters;
import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

/**
 * Created by jungpyokim on 2017. 9. 22..
 */
public class LoginActivity extends AppCompatActivity {

    EditText mEdit_id;
    EditText mEdit_password;
    Button mButton_login;
    Button mButton_signup;
    Button mButton_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEdit_id = (EditText) findViewById(R.id.edit_id);
        mEdit_password = (EditText) findViewById(R.id.edit_password);
        mButton_login = (Button) findViewById(R.id.button_login);
        mButton_signup = (Button) findViewById(R.id.button_signup);
        mButton_logout = (Button) findViewById(R.id.button_logout);
        mButton_logout.setVisibility(View.GONE);

        mButton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEdit_id.getText().length() == 0
                        || mEdit_password.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, "ID or PW is empty", Toast.LENGTH_LONG).show();
                    return;
                }

                loginUser(mEdit_id.getText().toString(), mEdit_password.getText().toString());
            }
        });

        mButton_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEdit_id.getText().length() == 0
                        || mEdit_password.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, "ID or PW is empty", Toast.LENGTH_LONG).show();
                    return;
                }

                signUpUser(mEdit_id.getText().toString(), mEdit_password.getText().toString());
            }
        });

        mButton_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        checkSignIn();
    }

    public void signUpUser(String id, String password) {
        final QBUser user = new QBUser(id, password);

        QBUsers.signUp(user).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle args) {
                // success
                Toast.makeText(LoginActivity.this, "success signup", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onError(QBResponseException error) {
                // error
                Toast.makeText(LoginActivity.this, "error signup:" + error.getErrors(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loginUser(String id, String password) {
        final QBUser user = new QBUser(id, password);

        QBUsers.signIn(user).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle args) {
                // success
                Toast.makeText(LoginActivity.this, "success login", Toast.LENGTH_LONG).show();

                mEdit_id.setEnabled(false);
                mEdit_password.setEnabled(false);
                mEdit_password.setText("");

                mButton_login.setVisibility(View.GONE);
                mButton_signup.setVisibility(View.GONE);
                mButton_logout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(QBResponseException error) {
                // error
                Toast.makeText(LoginActivity.this, "error login:" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void logoutUser() {
        QBUsers.signOut().performAsync(new QBEntityCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid, Bundle bundle) {
                Toast.makeText(LoginActivity.this, "success logout", Toast.LENGTH_LONG).show();

                mEdit_id.setEnabled(true);
                mEdit_id.setText("");
                mEdit_password.setEnabled(true);

                mButton_login.setVisibility(View.VISIBLE);
                mButton_signup.setVisibility(View.VISIBLE);
                mButton_logout.setVisibility(View.GONE);
            }

            @Override
            public void onError(QBResponseException error) {
                Toast.makeText(LoginActivity.this, "error logout:" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void checkSignIn() {

        QBSessionParameters sessionParameters = QBSessionManager.getInstance().getSessionParameters();
        if (sessionParameters != null) {
            mEdit_id.setText(sessionParameters.getUserLogin());
            mEdit_id.setEnabled(false);
            mEdit_password.setEnabled(false);

            mButton_login.setVisibility(View.GONE);
            mButton_signup.setVisibility(View.GONE);
            mButton_logout.setVisibility(View.VISIBLE);

            String str = sessionParameters.getUserId() + ", " + sessionParameters.getAccessToken();
            Toast.makeText(LoginActivity.this, "Already logined." + str, Toast.LENGTH_LONG).show();
        }

    }
}
