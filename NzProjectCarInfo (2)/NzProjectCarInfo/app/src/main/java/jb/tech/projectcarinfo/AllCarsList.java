package jb.tech.projectcarinfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.Circle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllCarsList extends AppCompatActivity {

    ListView lv;
    ArrayList<String> carname,carpic,carplate,carmodel;
    ProgressBar pb;
    sqldatabase sql ;
    ImageView iv8;
    String Key_image="image";
    String Key_model="cmodel";
    String Key_plate="cplate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cars_list);
        pb = (ProgressBar)findViewById(R.id.progressBar4);
        iv8 = (ImageView)findViewById(R.id.imageView8);
        Circle cc = new Circle();
        pb.setIndeterminateDrawable(cc);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        // For Custom Action Bar
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        final LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.action2, null);
        getSupportActionBar().setCustomView(v, layoutParams);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        TextView tv2 = (TextView)view.findViewById(R.id.textView2);
        tv2.setText("All Cars");
        ImageView iv5 = (ImageView)view.findViewById(R.id.imageView5);
        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        lv = (ListView)findViewById(R.id.allcarslist);
        sql  = new sqldatabase(AllCarsList.this);

        carplate   = new ArrayList<>();
        carname    = new ArrayList<>();
        carpic     = new ArrayList<>();
        carmodel   = new ArrayList<>();


        // fetch all the data
        try
        {
            Cursor cursor = sql.chk();
if(cursor.moveToFirst()) {
    while (!cursor.isAfterLast()) {
        if (!carplate.contains(cursor.getString(cursor.getColumnIndex(Key_plate)))) {
            carname.add("");
            // carname.add(cursor.getString(cursor.getColumnIndex("ccarname")));
            carplate.add(cursor.getString(cursor.getColumnIndex(Key_plate)));
            carmodel.add(cursor.getString(cursor.getColumnIndex(Key_model)));
            carpic.add(cursor.getString(cursor.getColumnIndex(Key_image)));

              }

        //set adapter
        showcarsall sc = new showcarsall(AllCarsList.this, carname, carpic, carplate, carmodel);
        sc.notifyDataSetChanged();
        lv.setAdapter(sc);

        cursor.moveToNext();
    }
}
cursor.close();

        }
        catch (Exception e)
        {

        }

        //add new car
        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent next = new Intent(AllCarsList.this,AddCars.class);
                startActivity(next);

            }
        });

    }

    public class showcarsall extends ArrayAdapter {
        Activity activity;
        ArrayList<String> acarname,acarpic,acarplate,acarmodel;

        public showcarsall(Activity activity, ArrayList<String> acarname, ArrayList<String> acarpic,
                               ArrayList<String> acarplate, ArrayList<String> acarmodel
                          ) {
            super(activity, R.layout.allcars, acarname);

            this.activity = activity;
            this.acarname = acarname;
            this.acarpic = acarpic;
            this.acarplate = acarplate;
            this.acarmodel = acarmodel;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater lf = activity.getLayoutInflater();
            View v = lf.inflate(R.layout.allcars,null,true);

            TextView  tt7        = (TextView)v.findViewById(R.id.textView7);
            TextView  tt8        = (TextView)v.findViewById(R.id.textView8);
            TextView  tt9        = (TextView)v.findViewById(R.id.textView9);
            TextView  tt10        = (TextView)v.findViewById(R.id.textView10);
           ImageView iv4 = (ImageView)v.findViewById(R.id.imageView4);
            ConstraintLayout cl = (ConstraintLayout)v.findViewById(R.id.c);

            try
            {

                tt7.setText(acarplate.get(position));
                tt8.setText(acarmodel.get(position));
                Picasso.with(activity).load(acarpic.get(position)).fit().placeholder(getResources().getDrawable(R.drawable.loading)).into(iv4);
                //edit
                tt9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent next = new Intent(AllCarsList.this,EditCar.class);
                        // Using Intent pass car id to next page
                        next.putExtra("carid",acarplate.get(position));
                        startActivity(next);

                    }
                });

                //delete
                tt10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog1 = new Dialog(AllCarsList.this);
                        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog1.setContentView(R.layout.delete);
                        dialog1.setCanceledOnTouchOutside(false);
                        Window window = dialog1.getWindow();
                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        TextView tv9 = (TextView)dialog1.findViewById(R.id.textView9);
                        TextView tv14 = (TextView)dialog1.findViewById(R.id.textView14);

                        //delete
                        tv9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sql.delete(acarplate.get(position));
                                dialog1.dismiss();
                            }
                        });

                        //cancel
                        tv14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog1.dismiss();
                            }
                        });
                        dialog1.show();

                    }
                });
            }
            catch (Exception e)
            {

            }

            return  v;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

      loadData();
    }

    public  void loadData()
    {
        try
        {
            Cursor cursor = sql.chk();
            if(cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    if (!carplate.contains(cursor.getString(cursor.getColumnIndex(Key_plate)))) {
                        carname.add("");
                        // carname.add(cursor.getString(cursor.getColumnIndex("ccarname")));
                        carplate.add(cursor.getString(cursor.getColumnIndex(Key_plate)));
                        carmodel.add(cursor.getString(cursor.getColumnIndex(Key_model)));
                        carpic.add(cursor.getString(cursor.getColumnIndex(Key_image)));

                    }

                    //set adapter
                    showcarsall sc = new showcarsall(AllCarsList.this, carname, carpic, carplate, carmodel);
                    sc.notifyDataSetChanged();
                    lv.setAdapter(sc);

                    cursor.moveToNext();
                }
            }
            cursor.close();

        }
        catch (Exception e)
        {

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

    if(keyCode==KeyEvent.KEYCODE_BACK)
    {
        finish();
    }
        return super.onKeyDown(keyCode, event);
    }
}
