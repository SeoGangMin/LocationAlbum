package com.locationalbum.locationalbum.data.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author seogangmin
 * @version 0.0.1 2016. 3. 24. 생성
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME        = "location_albumn.db";

    private static final String TL_TABLE_NAME        = "tb_location";
    private static final String TL_COLUMN_IDX        = "idx";
    private static final String TL_COLUMN_LAT        = "lat";
    private static final String TL_COLUMN_LON        = "lon";
    private static final String TL_COLUMN_PHOTO_URI  = "photo_uri";

    private final StringBuilder SB_LOGGER = new StringBuilder();



    //define tb_location table column
    private static final Map<String, String> TL_COLUMNS = new HashMap<String, String>(){
        {
            TL_COLUMNS.put(TL_COLUMN_IDX,"INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
            TL_COLUMNS.put(TL_COLUMN_LAT,"INTEGER NOT NULL, ");
            TL_COLUMNS.put(TL_COLUMN_LON,"INTEGER NOT NULL, ");
            TL_COLUMNS.put(TL_COLUMN_PHOTO_URI," ,TEXT NOT NULL");
        }
    };

    //set tables
    private static final Map<String, Map> TABLES = new HashMap<String, Map>(){
        {
            TABLES.put(TL_TABLE_NAME, TL_COLUMNS);
        }
    } ;


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(getTableCreationQuery(TL_TABLE_NAME));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(getDropTableQuery(TL_TABLE_NAME));
        onCreate(db);
    }

    private String getTableCreationQuery(String tableName){
        Map<String,String> columns = TABLES.get(tableName);

        Iterator<String> keys      = columns.keySet().iterator();

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(tableName);
        sb.append(" (");

        while (keys.hasNext()){
            String columnName = keys.next();
            String grant      = columns.get(columnName);

            sb.append(columnName+" "+grant);
        }

        sb.append(")");

        appendLogger(tableName + " creation >> " + sb.toString());

        return sb.toString();
    }

    private String getDropTableQuery(String tableName){
        return "DROP TABLE IF EXISTS " + tableName;
    }


    private void appendLogger(String msg){
        SB_LOGGER.append(msg);
        SB_LOGGER.append("\n");
    }

    /*
    public boolean insertContact  (String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    */
}
