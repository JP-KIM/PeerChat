package com.jpstudio.peerchat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jpstudio.peerchat.ChatItem;
import com.jpstudio.peerchat.R;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import io.realm.Realm;
import io.realm.RealmResults;

public class IDSearchActivity extends AppCompatActivity {

    EditText mEdit_id;
    Button mButton_search;
    Button mButton_add;

    private String mSearched_id;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idsearch);

        realm = Realm.getDefaultInstance();

        mEdit_id = (EditText) findViewById(R.id.edit_id);
        mButton_search = (Button) findViewById(R.id.button_idsearch);
        mButton_add = (Button) findViewById(R.id.button_idadd);
        mButton_add.setVisibility(View.GONE);

        mButton_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEdit_id.getText().length() == 0) {
                    Toast.makeText(IDSearchActivity.this, "ID is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                searchId(mEdit_id.getText().toString());
            }
        });

        mButton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIdToChatlist();
            }
        });


    }

    public void searchId(final String id) {
        QBUsers.getUserByLogin(id).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle args) {
                // success
                Toast.makeText(IDSearchActivity.this, "success search", Toast.LENGTH_LONG).show();
                mSearched_id = id;
                mButton_add.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(QBResponseException error) {
                // error
                Toast.makeText(IDSearchActivity.this, "error search:" + error.getErrors(), Toast.LENGTH_LONG).show();
                mSearched_id = null;
                mButton_add.setVisibility(View.GONE);
            }
        });
    }

    public void addIdToChatlist() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int match = realm.where(ChatItem.class).equalTo("title", mSearched_id).findAll().size();
                if (match == 0) {
                    ChatItem item = realm.createObject(ChatItem.class);
                    item.setTitle(mSearched_id);
                    item.setLastchat("NOTHING");
                    final ChatItem managedItem = realm.copyToRealm(item);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(IDSearchActivity.this, "success id", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_idsearch, menu);
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
