package jb.tech.projectcarinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.Circle;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // This is used to hide Action bar
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pb = (ProgressBar)findViewById(R.id.progressBar);

        Circle cc = new Circle();
        cc.setColor(getResources().getColor(R.color.colorWhite));
        pb.setIndeterminateDrawable(cc);

        // this is a thread for splash screen
        // it is used for delay in open next page  on splash page

        Thread threadobject = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Thread.sleep(2000);
                    // intent is used to move from this to next activity or can say Screen.
                    Intent nextPage = new Intent(MainActivity.this,ChooseAccount.class);
                    startActivity(nextPage);
                    finish();
                    // finish is used to destroy or close current screen
                }
                catch (Exception e)
                {

                }
            }
        });

        threadobject.start();
    }
}
