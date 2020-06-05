package jb.tech.projectcarinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqldatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="admincar";
    private static final int Version=1;
    private static final String TABLE_NAME="cartable";
    private static final String Key_Pass="eml";
    private static final String Key_Name="unaam";
    private static final String Key_Scode="fid";


    private static final String Key_image="image";
    private static final String Key_year="cyear";
    private static final String Key_make="cmake";
    private static final String Key_model="cmodel";
    private static final String Key_color="ccolor";
    private static final String Key_submodel="csubmodel";
    private static final String Key_bodystyle="cbodystyle";
    private static final String Key_vin="cvin";
    private static final String Key_plate="cplate";
    private static final String Key_engineno="cengineno";
    private static final String Key_vehicletype="cvehicletype";
    private static final String Key_seats="cseats";
    private static final String Key_ccrating="cccrating";
    private static final String Key_fueltype="cfueltype";
    private static final String Key_power="cpower";
    private static final String Key_transmission="ctransmission";
    private static final String Key_asstype="cassemblytype";
    private static final String Key_countryoforigin="ccountryoforigin";
    private static final String key_carid="ccarid";


    public sqldatabase(Context context) {
        super(context,DATABASE_NAME,null,Version);
    }

    @Override
    public void onCreate(SQLiteDatabase bs) {
        String CREATE_TABLE=
                "CREATE TABLE "+TABLE_NAME+"("+key_carid+" TEXT,"
                        +Key_year+" TEXT,"
                        +Key_make+" TEXT,"
                        +Key_model+" TEXT,"
                        +Key_color+" TEXT,"
                        +Key_submodel+" TEXT,"
                        +Key_bodystyle+" TEXT,"
                        +Key_vin+" TEXT,"
                        +Key_plate+" TEXT,"
                        +Key_engineno+" TEXT,"
                        +Key_vehicletype+" TEXT,"
                        +Key_seats+" TEXT,"
                        +Key_ccrating+" TEXT,"
                        +Key_fueltype+" TEXT,"
                        +Key_power+" TEXT,"
                        +Key_transmission+" TEXT,"
                        +Key_asstype+" TEXT,"
                        +Key_countryoforigin+" TEXT,"
                        +Key_image+" TEXT" +
                        ")";


        bs.execSQL(CREATE_TABLE);

    }

    @Override

    public void onUpgrade(SQLiteDatabase bs, int i, int i1) {
        bs.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(bs);
    }
    public int add(String carid, String year, String make,
                   String model, String color, String submodel,
                   String bodystyle, String vin, String plate,
                   String engno, String vehcltype, String seats,
                   String ccrate, String fuletype, String power,
                   String transmission, String assemblytype, String countryoforigin, String image){
        SQLiteDatabase oo = this.getWritableDatabase();
        ContentValues jk = new ContentValues();
        jk.put(key_carid,carid);
        jk.put(Key_year,year);
        jk.put(Key_make,make);
        jk.put(Key_model,model);
        jk.put(Key_color,color);
        jk.put(Key_submodel,submodel);
        jk.put(Key_bodystyle,bodystyle);
        jk.put(Key_vin,vin);
        jk.put(Key_plate,plate);
        jk.put(Key_engineno,engno);
        jk.put(Key_vehicletype,vehcltype);
        jk.put(Key_seats,seats);
        jk.put(Key_ccrating,ccrate);
        jk.put(Key_fueltype,fuletype);
        jk.put(Key_power,power);
        jk.put(Key_transmission,transmission);
        jk.put(Key_asstype,assemblytype);
        jk.put(Key_countryoforigin,countryoforigin);
        jk.put(Key_image,image);
        oo.insert(TABLE_NAME,null,jk);
        oo.close();

        return 1;
    }

    public Cursor view(){
        String[] a = {key_carid,Key_year,Key_make,Key_model,Key_color,Key_submodel, Key_bodystyle,
        Key_vin,Key_plate,Key_engineno,Key_vehicletype,Key_seats,Key_ccrating,Key_fueltype,
                Key_power,Key_transmission,Key_asstype,Key_countryoforigin, Key_image
        };
        Cursor ab = getReadableDatabase().query(TABLE_NAME,a,null,null,null,null,null);
        if(ab !=null){
            ab.moveToNext();
        }
        return ab;

    }

    public Cursor chk(){
        SQLiteDatabase sd=this.getReadableDatabase();
        String[] s = {key_carid,Key_year,Key_make,Key_model,Key_color,Key_submodel, Key_bodystyle,
                Key_vin,Key_plate,Key_engineno,Key_vehicletype,Key_seats,Key_ccrating,Key_fueltype,
                Key_power,Key_transmission,Key_asstype,Key_countryoforigin,Key_image
        };
        Cursor c=sd.query(TABLE_NAME,s,null,null,null,null,null);
        if(c!=null) {
            c.moveToFirst();
        }

        return c;
    }



    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME);
        db.close();
    }

    public void delete(String carplate){
        SQLiteDatabase sd =getWritableDatabase();
        String [] ar={carplate};
        sd.delete(TABLE_NAME,Key_plate+"=?",ar);
        sd.close();
    }

    public Cursor findcar(String iid){
        SQLiteDatabase sd=this.getReadableDatabase();
        String s[]={key_carid,Key_year,Key_make,Key_model,Key_color,Key_submodel, Key_bodystyle,
                Key_vin,Key_plate,Key_engineno,Key_vehicletype,Key_seats,Key_ccrating,Key_fueltype,
                Key_power,Key_transmission,Key_asstype,Key_countryoforigin,Key_image
        };
        String val[]={iid};
        Cursor c=sd.query(TABLE_NAME,s,Key_plate+"=?",val,null,null,null);
        if(c!=null) {
            c.moveToFirst();

            return c;
        }
        else
        {
            return null;
        }
        }

    public void updatecar(String carid, String year, String make,
                          String model, String color, String submodel,
                          String bodystyle, String vin, String plate,
                          String engno, String vehcltype, String seats,
                          String ccrate, String fuletype, String power,
                          String transmission, String assemblytype, String countryoforigin, String image){
        ContentValues jk = new ContentValues();
        jk.put(Key_year,year);
        jk.put(Key_make,make);
        jk.put(Key_model,model);
        jk.put(Key_color,color);
        jk.put(Key_submodel,submodel);
        jk.put(Key_bodystyle,bodystyle);
        jk.put(Key_vin,vin);
        jk.put(Key_plate,plate);
        jk.put(Key_engineno,engno);
        jk.put(Key_vehicletype,vehcltype);
        jk.put(Key_seats,seats);
        jk.put(Key_ccrating,ccrate);
        jk.put(Key_fueltype,fuletype);
        jk.put(Key_power,power);
        jk.put(Key_transmission,transmission);
        jk.put(Key_asstype,assemblytype);
        jk.put(Key_countryoforigin,countryoforigin);
        jk.put(Key_image,image);

        SQLiteDatabase sq=getWritableDatabase();


        String [] ar={carid};
        sq.update(TABLE_NAME,jk,Key_plate+"=?",ar);
    }

}
