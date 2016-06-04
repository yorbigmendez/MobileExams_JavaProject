package access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import questions.DoubleSelection;
import questions.TrueFalse;

/**
 * Created by Mendez Soto on 5/26/2016.
 */
public class TrueFalseRepository implements IRepository<TrueFalse> {

    private DBConnection connect;
    public TrueFalseRepository(Context context){
        connect = new DBConnection(context);
    }


    @Override
    public boolean Save(TrueFalse o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("question",o.getQuestion());
                newData.put("id_section",o.getId_section());
                newData.put("answer",o.getAnswer());
                db.insert("TrueFalse", null, newData);
                connect.close();
                return false;//Sin errores
            }
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
        return true;
    }

    @Override
    public boolean Update(TrueFalse o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("question",o.getQuestion());
                newData.put("id_section",o.getId_section());
                newData.put("answer",o.getAnswer());
                db.update("TrueFalse", newData, "id=?", new String[]{String.valueOf(o.getId_question())});
                connect.close();
                return false;//Sin errores
            }
        }catch(Exception e){
            Log.d("Error",e.getMessage());
        }
        return true;
    }

    @Override
    public boolean Delete(TrueFalse o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(o.getId_question())};
                db.delete("TrueFalse","id=?",args);
                connect.close();
                return false;//WIthout errors
            }
        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList GetAll(int id) {
        ArrayList<TrueFalse> questions = new ArrayList<>();
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(id)};
                Cursor c = db.query("TrueFalse",new String[]{"id","question","answer","id_section"},
                        "id_section=?",args,null,null,"id asc",null);
                if(c.moveToFirst()){
                    do{
                        int idx = c.getInt(0);
                        String question = c.getString(1);
                        String answer = c.getString(2);
                        int section = c.getInt(3);
                        //THIRD ELEMENT IS THE ID OF THE EXAM
                        TrueFalse s = new TrueFalse(idx,question,answer,section);
                        questions.add(s);
                    }while(c.moveToNext());
                }
                connect.close();
                return questions;
            }
        }catch (Exception e){
            Log.d("ERROR",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList GetBy(TrueFalse o) {
        return null;
    }
}
