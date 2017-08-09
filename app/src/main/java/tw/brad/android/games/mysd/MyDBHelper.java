package tw.brad.android.games.mysd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/8/9.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    private final String createTable =
            "create table cust (_id integer primary key autoincrement,cname text,tel text,birthday date)";
    public MyDBHelper(Context context, String name,
                      SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
