package jb.tech.projectcarinfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.text.InputType;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    sqldatabase sqll = new sqldatabase(Home.this);
    SearchView sv;
    ProgressBar pb;
    ImageView  im2;
    ConstraintLayout founddata;
    LinearLayout nodatafound;
    ListView lv;
    ArrayList<String>subject,values;

    String carid ="";

     String Key_image="image";
     String Key_year="cyear";
     String Key_make="cmake";
     String Key_model="cmodel";
     String Key_color="ccolor";
     String Key_submodel="csubmodel";
     String Key_bodystyle="cbodystyle";
     String Key_vin="cvin";
     String Key_plate="cplate";
     String Key_engineno="cengineno";
     String Key_vehicletype="cvehicletype";
     String Key_seats="cseats";
     String Key_ccrating="cccrating";
     String Key_fueltype="cfueltype";
     String Key_power="cpower";
     String Key_transmission="ctransmission";
     String Key_asstype="cassemblytype";
     String Key_countryoforigin="ccountryoforigin";
     String key_carid="ccarid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        // For Custom Action Bar
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        final LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.action, null);
        getSupportActionBar().setCustomView(v, layoutParams);
        // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();

        // link elements from Xml file to java file.
        founddata     = (ConstraintLayout)findViewById(R.id.constraintLayout);
        nodatafound   =(LinearLayout)findViewById(R.id.nodata);
        im2           = (ImageView)findViewById(R.id.imageView2);
        sv            = (SearchView)findViewById(R.id.searchView);
        sv.setQueryHint("Enter Plate No");
        lv = (ListView)findViewById(R.id.listview);
        pb = (ProgressBar)findViewById(R.id.progressBar2);
        // By defaulty both are not visible on Screen
        nodatafound.setVisibility(View.GONE);
        founddata.setVisibility(View.GONE);
       // Used to query with SQLLite
        final Cursor cc = sqll.chk();
        //sqll.deleteAll();
        if(cc.isAfterLast()) {

            // When app is opened on new device first time
            // then list of cars is added in database
            // same process repeats after clearing storage of App
            AddCarsToSQL adddcars = new AddCarsToSQL(Home.this);
        }
        else{
              }


        // initialize all the array Lists it is maindateory before using arrayLists
        // otherwise error will occur


        subject        = new ArrayList();
        values         = new ArrayList();


        // Work of Searching
        sv.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Cursor cursor = sqll.findcar(query.toUpperCase());

                try {
                    if (!cursor.isAfterLast()) {
                        // get and save all data from Sqllite into ArrayLists
                        nodatafound.setVisibility(View.GONE);
                        founddata.setVisibility(View.VISIBLE);
                        String image = cursor.getString(cursor.getColumnIndex(Key_image));

                        subject.clear();
                        values.clear();

                        subject.add("Year");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_year)));
                        subject.add("Make");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_make)));
                        subject.add("Model");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_model)));
                        subject.add("Color");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_color)));
                        subject.add("SubModel");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_submodel)));
                        subject.add("Body Style");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_bodystyle)));
                        subject.add("VIN");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_vin)));
                        subject.add("Plate");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_plate)));
                        subject.add("Engine No.");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_engineno)));
                        subject.add("Power");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_power)));
                        subject.add("Vehicle Type");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_vehicletype)));
                        subject.add("Seats");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_seats)));
                        subject.add("CC Rating");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_ccrating)));
                        subject.add("Fuel Type");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_fueltype)));
                        subject.add("Transmission");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_transmission)));
                        subject.add("Assembly Type");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_asstype)));
                        subject.add("Country");
                        values.add(cursor.getString(cursor.getColumnIndex(Key_countryoforigin)));

                        showCarDetail ssc = new showCarDetail(Home.this, subject, values,image);
                        ssc.notifyDataSetChanged();
                        lv.setAdapter(ssc);
                        pb.setVisibility(View.GONE);

                        // to hide or dismiss keyboard
                        InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                    }
                    else
                    {
                        founddata.setVisibility(View.GONE);
                        nodatafound.setVisibility(View.VISIBLE);
                        subject.clear();
                        values.clear();

                        InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),String.valueOf(e),Toast.LENGTH_LONG).show();
                }
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                subject.clear();
                values.clear();

                nodatafound.setVisibility(View.GONE);
                founddata.setVisibility(View.GONE);
                return true;
            }
        });

    }

    public class showCarDetail extends ArrayAdapter {
        Activity activity;
        ArrayList<String> asubject, avalues;
        String imageuri;

        public showCarDetail(Activity activity, ArrayList<String> asubject,
                               ArrayList<String> avalues,String imageuri) {
            super(activity, R.layout.showdatadesign, asubject);

            this.activity  = activity;
            this.asubject  = asubject;
            this.avalues   = avalues;
            this.imageuri  = imageuri;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater lf = activity.getLayoutInflater();
            View v = lf.inflate(R.layout.showdatadesign,null,true);

            TextView  tt5        = (TextView)v.findViewById(R.id.textView5);
            TextView  tt6        = (TextView)v.findViewById(R.id.textView6);
            ImageView im3        = (ImageView)v.findViewById(R.id.imageView3);

            im3.setVisibility(View.GONE);

            ConstraintLayout cl = (ConstraintLayout)v.findViewById(R.id.clt);

            try
            {

                if(position==0)
                {
                    im3.setVisibility(View.VISIBLE);
                    Picasso.with(Home.this).load(imageuri).placeholder(getResources().getDrawable(R.drawable.loading)).into(im3);

                }
                tt5.setText(asubject.get(position));
                tt6.setText(avalues.get(position));

                if(asubject.get(position).equals("Plate") || asubject.get(position).equals(
                        "Engine No."))
                {
                    tt5.setTextColor(getResources().getColor(R.color.colorPrimary));
                    tt6.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
            }
            catch (Exception e)
            {

            }

            return  v;
        }
    }


}//
