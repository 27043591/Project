package jb.tech.projectcarinfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddCars extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11,ed12,ed13,ed14,ed15,ed16,ed17,ed18;
    String year="",make="",model="",color="",submodel="",bodystyle="",vin="",plate="",engineno=""
            ,power="",vehicletype="",seats="",ccrating="",fuletype="",transmission="",assembely=
            "",country="",imageurl="";
    Button bt2;
    sqldatabase sdb = new sqldatabase(AddCars.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cars);
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
        tv2.setText("Add New Car");
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


        //add car
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
                plate       =  ed8.getText().toString();
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
                else if(plate.equals("")){
                    ed8.setError("Enter Plate !");
                    ed8.requestFocus();
                }
                else{
                    sdb.add(plate, year,make, model, color, submodel, bodystyle,
                            vin, plate.toUpperCase(),engineno, vehicletype, seats, ccrating, fuletype,
                            power, transmission, assembely, country, imageurl);

                    final Dialog dialog1 = new Dialog(AddCars.this);
                    dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog1.setContentView(R.layout.confirm);
                    dialog1.setCanceledOnTouchOutside(false);
                    Window window = dialog1.getWindow();
                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    TextView tv9 = (TextView)dialog1.findViewById(R.id.textView9);
                    TextView tv7 = (TextView)dialog1.findViewById(R.id.textView7);

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
}
