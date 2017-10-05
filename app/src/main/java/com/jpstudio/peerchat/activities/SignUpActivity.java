package com.jpstudio.peerchat.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.jpstudio.peerchat.R;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText userEmailEditText;
    private EditText userPWEditText;
    private EditText userPWConfirmEditText;
    private CheckBox subscriptionCheckBox;
    private Button signUpFinishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userEmailEditText = (EditText) findViewById(R.id.emailEditText);
        userPWEditText = (EditText) findViewById(R.id.pwEditText);
        userPWConfirmEditText = (EditText) findViewById(R.id.confirmPWEditText);
        subscriptionCheckBox = (CheckBox) findViewById(R.id.subscriptionCheckBox);
        signUpFinishButton = (Button) findViewById(R.id.signUpButton);

        signUpFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean emailEmpty = TextUtils.isEmpty(userEmailEditText.getText());
                boolean pwEmpty = TextUtils.isEmpty(userPWEditText.getText());
                boolean pwConfirmEmpty = TextUtils.isEmpty(userPWConfirmEditText.getText());
                if ( emailEmpty || pwEmpty || pwConfirmEmpty ) {
                    if (emailEmpty) {
                        userEmailEditText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    }
                    if (pwEmpty) {
                        userPWEditText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    }
                    if(pwConfirmEmpty) {
                        userPWConfirmEditText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    }
                } else {
                    signUpUser(userEmailEditText.getText().toString(), userPWEditText.getText().toString());
                }

            }
        });

    }

    public void signUpUser(String id, String password) {
        final QBUser user = new QBUser(id, password);

        QBUsers.signUp(user).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle args) {
                // success
                Toast.makeText(SignUpActivity.this, "success signup", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(QBResponseException error) {
                // error
                Toast.makeText(SignUpActivity.this, "error signup:" + error.getErrors(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
