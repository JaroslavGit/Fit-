package cz.oauh.projekt;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DBNAME = "FitPlusDB";
    private final static String cvikT = "Cviky";
    private final static String zaznamT = "Zaznamy";
    private final static String zaznMaCvikT = "ZaznamMaCvik";
    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE " + cvikT + "(id integer primary key AUTOINCREMENT, nazev string)");
      db.execSQL("CREATE TABLE " + zaznamT + "(id integer primary key AUTOINCREMENT, datum Date)");
      db.execSQL("CREATE TABLE " + zaznMaCvikT + "(id integer primary key AUTOINCREMENT, pocetOpak int, splneno boolean, idCvik int, idZaznam int, FOREIGN KEY(idCvik) REFERENCES "+cvikT+ "(id), FOREIGN KEY(idZaznam) REFERENCES "+zaznamT+"(id))");
    }

    public boolean vlozCviky(String novyNazev)
    {
     SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement st = db.compileStatement("INSERT INTO "+cvikT+ " (nazev) VALUES(?)" );
        st.bindString(1, novyNazev);
        return (st.executeInsert()==0);
    }

    public void nastavCviky()
    {
      vlozCviky("Dřepy");
      vlozCviky("Sed-Leh");
      vlozCviky("Kliky");
    }

    public int vratPocetZaznamuCviku()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, cvikT);
    }

    public Cursor vratCviky()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+cvikT;
        return db.rawQuery(sql, null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
