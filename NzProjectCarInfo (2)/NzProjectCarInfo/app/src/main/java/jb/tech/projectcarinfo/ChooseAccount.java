package jb.tech.projectcarinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseAccount extends AppCompatActivity {

    ConstraintLayout cl3, cl4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_account);

        cl3 = (ConstraintLayout)findViewById(R.id.constraintLayout3);
        cl4 = (ConstraintLayout)findViewById(R.id.constraintLayout4);

        //user
        cl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(ChooseAccount.this,Home.class);
                startActivity(nextPage);
                finish();
            }
        });

        //admin
        cl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(ChooseAccount.this,AdminLogin.class);
                startActivity(nextPage);
                finish();
            }
        });
    }
}
