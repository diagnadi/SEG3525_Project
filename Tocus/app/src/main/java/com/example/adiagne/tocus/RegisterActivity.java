package com.example.adiagne.tocus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.app.ActivityOptions;
import android.widget.EditText;
import android.widget.Toast;
import com.example.adiagne.tocus.R;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void cancel(View view) {
        Intent btm = new Intent(this, MyActivity.class);
        startActivity(btm);

    }

    public void submitRegister(View view)
    {

        EditText editext1 = (EditText)findViewById(R.id.editText);
        String message1 = editext1.getText().toString();
        EditText editext2 = (EditText)findViewById(R.id.editText2);
        String message2 = editext2.getText().toString();
        EditText editext3 = (EditText)findViewById(R.id.editText3);
        String message3 = editext3.getText().toString();
        EditText editext4 = (EditText)findViewById(R.id.editText4);
        String message4 = editext4.getText().toString();

        if(!message3.equals(message4)||message3.length()<6 || message4.length()<6 || message2.length()<6 ||message3==null || message4==null || message2 == null )
        {
            if(!message3.equals(message4))
            {
                Toast.makeText(this, "Vos mots de passe ne correspondent pas. Retapez votre mot de passe",Toast.LENGTH_SHORT).show();
            }
            if(message1.length()<6 || message3.length()<6 || message3.length()<6||message1==null || message2==null || message4 == null)
            {
                Toast.makeText(this, "Tous les champs doivent être remplis avant de poursuivre l'inscription",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Intent slideactivity = new Intent(RegisterActivity.this, ConfirmationActivity.class);
            Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.toright,R.anim.toleft).toBundle();
            startActivity(slideactivity, bndlanimation);
        }


    }



}
