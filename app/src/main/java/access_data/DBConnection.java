package access_data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SQLiteOpenHelper for the connection to the local database
 *
 * This class creates the Database and manages on create of database
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class DBConnection extends SQLiteOpenHelper {
    /**
     * Version of the database
     */
    private static final int VERSION_BD = 1;
    /**
     * Name of the database
     */
    private static final String NAME_BD = "examdb";

    /**
     *  Invokes the super SQLiteOpenHelper class with the name and the version of the database
     * @param context Context: The context of the application
     */
    public DBConnection(Context context){
        super(context, NAME_BD,null,VERSION_BD);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            StringBuilder sql = new StringBuilder();
            String sqlCreateExam = "CREATE TABLE IF NOT EXISTS Exam (id INTEGER PRIMARY KEY AUTOINCREMENT, name CHAR(100), points INTEGER, author CHAR(100), grade REAL)";
            String sqlCreateSection = "CREATE TABLE IF NOT EXISTS Section (id INTEGER PRIMARY KEY AUTOINCREMENT, name CHAR(100), description CHAR(100), id_exam INTEGER)";
            String sqlCreateTrueFlase = "CREATE TABLE IF NOT EXISTS TrueFalse (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "question CHAR(100), answer CHAR(100),id_section INTEGER)";
            String sqlCreateSingleSelection = "CREATE TABLE IF NOT EXISTS DoubleSelection (id INTEGER PRIMARY KEY AUTOINCREMENT, question CHAR(100), option1 CHAR(100)," +
                    "option2 CHAR(100), option3 CHAR(100), option4 CHAR(100), answer1 CHAR(100), answer2 CHAR(100), id_section INTEGER)";
            String sqlCreateDoubleSelection = "CREATE TABLE IF NOT EXISTS SingleSelection (id INTEGER PRIMARY KEY AUTOINCREMENT, question CHAR(100), option1 CHAR(100), " +
                    "option2 CHAR(100), option3 CHAR(100), option4 CHAR(100), answer CHAR(100), id_section INTEGER)";

            sql.append(sqlCreateExam);
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append(sqlCreateSection);
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append(sqlCreateTrueFlase);
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append(sqlCreateSingleSelection);
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append(sqlCreateDoubleSelection);
            db.execSQL(sql.toString());
        }catch (Exception e){
            Log.d("DB Error", e.getMessage());
        }
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
