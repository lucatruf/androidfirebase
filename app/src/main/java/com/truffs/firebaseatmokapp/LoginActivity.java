package com.truffs.firebaseatmokapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class LoginActivity extends ActionBarActivity {

    private Firebase fbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activuty);

        Firebase.setAndroidContext(this);
        final Button button = (Button) findViewById(R.id.signin);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.email);
                EditText pass = (EditText) findViewById(R.id.password);

                fbRef = new Firebase("https://yourfirebasedb.firebaseio.com/");
                fbRef.authWithPassword(email.getText().toString(), pass.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        getIn();
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        System.out.println("User Authentication ERROR!!!");
                        Toast toast = Toast.makeText(LoginActivity.this, "Wrong user/pass", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        });
    }

    private void getIn(){
        fbRef.unauth();
        fbRef = null;

        EditText email = (EditText) findViewById(R.id.email);
        EditText pass = (EditText) findViewById(R.id.password);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("EMAIL", email.getText().toString());
        intent.putExtra("PASS", pass.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_activuty, menu);
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

