package jb.tech.projectcarinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminLogin extends AppCompatActivity {

    Button bt;
    EditText et2;
    userdb udb = new userdb(AdminLogin.this);
    static String aa = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_login);

        bt = (Button)findViewById(R.id.button);
        et2 = (EditText)findViewById(R.id.editText2);

        Cursor eet = udb.chk();
        if (!eet.isAfterLast()) {
            aa = eet.getString(eet.getColumnIndex("nzcarpassword"));
            eet.moveToNext();
        }


        //continue
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = et2.getText().toString();
                if(password.equals("")){
                    et2.setError("Enter Password !");
                    et2.requestFocus();
                }
                else if (password.length()<5){
                    et2.setError("Password should be of 5 digits !");
                    et2.requestFocus();
                }
                else{
                    if(aa.equals("")){
                        udb.add(password);
                        Intent i = new Intent(AdminLogin.this, AllCarsList.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                            if (password.equals(aa)) {
                                Intent i = new Intent(AdminLogin.this, AllCarsList.class);
                                startActivity(i);
                                finish();
                            }
                            else {
                                et2.setError("Password didn't match !");
                                et2.requestFocus();
                            }
                        }


                   }

            }
        });
    }
}
