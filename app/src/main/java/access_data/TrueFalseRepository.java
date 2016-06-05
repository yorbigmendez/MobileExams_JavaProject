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
 * Manages the TrueFalse question from database
 *
 * Access true false table.
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class TrueFalseRepository implements IRepository<TrueFalse> {
    /**
     * Connection to the database
     */
    private DBConnection connect;

    /**
     * Constructor of the class
     * @param context Context of the application
     */
    public TrueFalseRepository(Context context){
        connect = new DBConnection(context);
    }


    /**
     * Saves a truefalse quesiton
     * @param o Object of TrueFalse
     * @return False if errors, true otherwise
     */
    @Override
    public boolean Save(TrueFalse o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("question", o.getQuestion());
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

    /**
     * Updates a TrueFalse question
     * @param o Object of TruFalse to update
     * @return False if no errors, true otherwise
     */
    @Override
    public boolean Update(TrueFalse o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("question", o.getQuestion());
                newData.put("id_section", o.getId_section());
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

    /**
     * Deletes a True False question
     * @param o Object of TrueFalse to delete
     * @return False if no errors, true otherwise
     */
    @Override
    public boolean Delete(TrueFalse o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(o.getId_question())};
                db.delete("TrueFalse", "id=?", args);
                connect.close();
                return false;//WIthout errors
            }
        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }
        return false;
    }

    /**
     * Gets all the TrueFalse questions from a section
     * @param id id of the section to get TrueFalse Questions from
     * @return ArrayList of the TrueFalse questions
     */
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

}
