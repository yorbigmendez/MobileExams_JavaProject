package access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import exams.Exam;

/**
 * Created by Mendez Soto on 5/26/2016.
 */
public class ExamRepository implements IRepository<Exam> {
    private DBConnection connect;

    /**
     * ExamRepository constructor
     * @param context The context of the application
     */
    public ExamRepository(Context context){
        connect = new DBConnection(context);
    }

    /**
     * Saves a new register of an exam
     * @param o The exam object to be saved into the database
     * @return true: If error has occurred, false otherwise
     */
    @Override
    public boolean Save(Exam o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("name",o.getName());
                newData.put("points",o.getPoints());
                newData.put("author",o.getAuthor());
                //table, new , data
                db.insert("Exam", null, newData);
                connect.close();
                return false;//Sin errores
            }
        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }
        return true;
    }

    /**
     * Updates an exam, the points and the author that made it
     * @param o
     * @return
     */
    @Override
    public boolean Update(Exam o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues updateData = new ContentValues();
                updateData.put("name",o.getName());
                updateData.put("points",o.getPoints());
                updateData.put("author",o.getAuthor());
                db.update("Exam", updateData, "id=?", new String[]{String.valueOf(o.getId())});
                connect.close();
                return false;//Sin errores
            }
        }catch(Exception e){
            Log.d("Error",e.getMessage());
        }
        return true;
    }

    @Override
    public boolean Delete(Exam o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(o.getId())};
                db.delete("Exam","id=?",args);
                connect.close();
                return false;//WIthout errors
            }
        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<Exam> GetAll(int id) {
        ArrayList<Exam> exams = new ArrayList<>();
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                Cursor c = db.query("Exam",new String[]{"id","name","points","author","grade"},
                        null,null,null,null,"id desc",null);
                if(c.moveToFirst()){
                    do{
                        int idx = c.getInt(0);
                        String name = c.getString(1);
                        int points = c.getInt(2);
                        String author = c.getString(3);
                        Exam e = new Exam(idx,name,points,author);
                        exams.add(e);
                    }while(c.moveToNext());
                }
                connect.close();
                return exams;
            }
        }catch (Exception e){
            Log.d("ERROR",e.getMessage());
            e.printStackTrace();
        }
        return exams;
    }

    @Override
    public ArrayList<Exam> GetBy(Exam o) {
        return null;
    }
}
