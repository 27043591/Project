package jb.tech.projectcarinfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditCar extends AppCompatActivity {

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

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11,ed12,ed13,ed14,ed15,ed16,ed17,ed18;
    String year="",make="",model="",color="",submodel="",bodystyle="",vin="",plate="",engineno=""
            ,power="",vehicletype="",seats="",ccrating="",fuletype="",transmission="",assembely=
            "",country="",imageurl="";
    Button bt2;
    sqldatabase sdb = new sqldatabase(EditCar.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);
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
        tv2.setText("Update Car");
        ImageView iv5 = (ImageView)view.findViewById(R.id.imageView5);
        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ed1  = (EditText)findViewById(R.id.editText);
        ed2  = (EditText)findViewById(R.id.editText2);
        ed3  = (EditText)findViewById(R.id.editText3);
        ed4  = (EditText)findViewById(R.id.editText4);
        ed5  = (EditText)findViewById(R.id.editText5);
        ed6  = (EditText)findViewById(R.id.editText6);
        ed7  = (EditText)findViewById(R.id.editText7);
        ed8  = (EditText)findViewById(R.id.editText8);
        ed9  = (EditText)findViewById(R.id.editText9);
        ed10 = (EditText)findViewById(R.id.editText10);
        ed11 = (EditText)findViewById(R.id.editText11);
        ed12 = (EditText)findViewById(R.id.editText12);
        ed13 = (EditText)findViewById(R.id.editText13);
        ed14 = (EditText)findViewById(R.id.editText14);
        ed15 = (EditText)findViewById(R.id.editText15);
        ed16 = (EditText)findViewById(R.id.editText16);
        ed17 = (EditText)findViewById(R.id.editText17);
        ed18 = (EditText)findViewById(R.id.editText18);
        bt2  = (Button)findViewById(R.id.button2);


        // get plarte no from previous activity

        Intent get = getIntent();
        plate      = get.getStringExtra("carid");

        // pass car id to that method to fetch required data
        fetchdata(plate);


        //update car
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Data from text fields

                year        =  ed1.getText().toString();
                make        =  ed2.getText().toString();
                model       =  ed3.getText().toString();
                color       =  ed4.getText().toString();
                submodel    =  ed5.getText().toString();
                bodystyle   =  ed6.getText().toString();
                vin         =  ed7.getText().toString();
             // plate       =  ed8.getText().toString();
                engineno    =  ed9.getText().toString();
                power       =  ed10.getText().toString();
                vehicletype =  ed11.getText().toString();
                seats       =  ed12.getText().toString();
                ccrating    =  ed13.getText().toString();
                fuletype    =  ed14.getText().toString();
                transmission=  ed15.getText().toString();
                assembely   =  ed16.getText().toString();
                country     =  ed17.getText().toString();
                imageurl    =  ed18.getText().toString();

                // Validates required data

                if(imageurl.equals(""))
                {
                    imageurl="https://www.freeiconspng.com/uploads/no-image-icon-11.PNG";
                }

                if(year.equals("")){
                    ed1.setError("Enter Year !");
                    ed1.requestFocus();
                }
                else if(make.equals("")){
                    ed2.setError("Enter Make !");
                    ed2.requestFocus();
                }
                else if(model.equals("")){
                    ed3.setError("Enter Model !");
                    ed3.requestFocus();
                }
                else if(color.equals("")){
                    ed4.setError("Enter color !");
                    ed4.requestFocus();
                }

                else{
                    sdb.updatecar(plate.toUpperCase(), year,make, model, color, submodel,
                            bodystyle,
                            vin, plate.toUpperCase(),engineno, vehicletype, seats, ccrating, fuletype,
                            power, transmission, assembely, country, imageurl);

                    final Dialog dialog1 = new Dialog(EditCar.this);
                    dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog1.setContentView(R.layout.confirm);
                    dialog1.setCanceledOnTouchOutside(false);
                    Window window = dialog1.getWindow();
                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    TextView tv9 = (TextView)dialog1.findViewById(R.id.textView9);
                    TextView tv7 = (TextView)dialog1.findViewById(R.id.textView7);

                    tv7.setText("Car Updated Sucessfully");
                    tv9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog1.dismiss();
                            finish();
                        }
                    });
                    dialog1.show();

                }

            }

        });

    }

    public  void fetchdata(String carplate)
    {
        Cursor cursor = sdb.findcar(carplate);
        try {
            if (!cursor.isAfterLast()) {
                // get and save all data from Sqllite into ArrayLists
                ed1.setText(cursor.getString(cursor.getColumnIndex(Key_year)));
                ed2.setText(cursor.getString(cursor.getColumnIndex(Key_make)));
                ed3.setText(cursor.getString(cursor.getColumnIndex(Key_model)));
                ed4.setText(cursor.getString(cursor.getColumnIndex(Key_color)));
                ed5.setText(cursor.getString(cursor.getColumnIndex(Key_submodel)));
                ed6.setText(cursor.getString(cursor.getColumnIndex(Key_bodystyle)));
                ed7.setText(cursor.getString(cursor.getColumnIndex(Key_vin)));
                ed8.setText(cursor.getString(cursor.getColumnIndex(Key_plate)));
                ed9.setText(cursor.getString(cursor.getColumnIndex(Key_engineno)));
                ed10.setText(cursor.getString(cursor.getColumnIndex(Key_power)));
                ed11.setText(cursor.getString(cursor.getColumnIndex(Key_vehicletype)));
                ed12.setText(cursor.getString(cursor.getColumnIndex(Key_seats)));
                ed13.setText(cursor.getString(cursor.getColumnIndex(Key_ccrating)));
                ed14.setText(cursor.getString(cursor.getColumnIndex(Key_fueltype)));
                ed15.setText(cursor.getString(cursor.getColumnIndex(Key_transmission)));
                ed16.setText(cursor.getString(cursor.getColumnIndex(Key_asstype)));
                ed17.setText(cursor.getString(cursor.getColumnIndex(Key_countryoforigin)));
                ed18.setText(cursor.getString(cursor.getColumnIndex(Key_image)));
            }
            else
            {
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),String.valueOf(e),Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            this.finish();

        }
        return super.onKeyDown(keyCode, event);
    }
}




