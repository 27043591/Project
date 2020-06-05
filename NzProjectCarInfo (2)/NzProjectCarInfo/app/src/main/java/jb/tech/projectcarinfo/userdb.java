package jb.tech.projectcarinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class userdb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="nzadmin";
    private static final int Version=1;
    private static final String TABLE_NAME="nzcartable";
    private static final String Key_Pass="nzcarpassword";


    public userdb(Context context) {
        super(context,DATABASE_NAME,null,Version);
    }

    @Override
    public void onCreate(SQLiteDatabase bs) {

        String CREATE_TABLE=
                "CREATE TABLE "+TABLE_NAME+"("+Key_Pass+" TEXT"+
                ")";
        bs.execSQL(CREATE_TABLE);

    }

    @Override

    public void onUpgrade(SQLiteDatabase bs, int i, int i1) {
        bs.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(bs);
    }
    public int add(String pas){
        SQLiteDatabase oo = this.getWritableDatabase();
        ContentValues jk = new ContentValues();
        jk.put(Key_Pass,pas);
        oo.insert(TABLE_NAME,null,jk);
        oo.close();

        return 1;
    }

    public Cursor view(){
        String[] a = {Key_Pass};
        Cursor ab = getReadableDatabase().query(TABLE_NAME,a,null,null,null,null,null);
        if(ab !=null){
            ab.moveToNext();
        }
        return ab;

    }

    public Cursor chk(){
        SQLiteDatabase sd=this.getReadableDatabase();
        String[] s = {Key_Pass};
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

}
