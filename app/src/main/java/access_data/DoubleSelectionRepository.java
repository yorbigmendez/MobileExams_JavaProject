package access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import questions.DoubleSelection;
import sections.Section;

/**
 * Repository for access to data
 *
 * Used to access all the double selection questions from the database
 *
 * @author Yorbi Mendez Soto
 * @version 6/04/2015
 * @since 1.0
 */
public class DoubleSelectionRepository implements  IRepository<DoubleSelection>{
    /**
     * The connection of the DBConnection Class
     */
    private DBConnection connect;

    /**
     * Constructor of class
     * @param context context of the app
     */
    public DoubleSelectionRepository(Context context){
        connect = new DBConnection(context);
    }

    /**
     * Saves a double selection question to the database
     * @param o DoubleSelection question instance
     * @return False if no errors, true otherwise
     */
    @Override
    public boolean Save(DoubleSelection o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("question",o.getQuestion());
                newData.put("option1",o.getOpc1());
                newData.put("option2",o.getOpc2());
                newData.put("option3",o.getOpc3());
                newData.put("option4",o.getOpc4());
                newData.put("answer1",o.getAnswer1());
                newData.put("answer2",o.getAnswer2());
                newData.put("id_section",o.getId_section());
                db.insert("DoubleSelection", null, newData);
                connect.close();
                return false;//Sin errores
            }
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
        return true;
    }

    /**
     * Updates a sections info
     * @param o Double Selection instance
     * @return False if no fail, true otherwise
     */
    @Override
    public boolean Update(DoubleSelection o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("question",o.getQuestion());
                newData.put("option1",o.getOpc1());
                newData.put("option2",o.getOpc2());
                newData.put("option3",o.getOpc3());
                newData.put("option4",o.getOpc4());
                newData.put("answer1",o.getAnswer1());
                newData.put("answer2",o.getAnswer2());
                newData.put("id_section",o.getId_section());
                db.update("DoubleSelection", newData, "id=?", new String[]{String.valueOf(o.getId_question())});
                connect.close();
                return false;//Sin errores
            }
        }catch(Exception e){
            Log.d("Error",e.getMessage());
        }
        return true;
    }

    /**
     * Deletes a question of DoubleSelection
     * @param o The DoubleSelection question to eliminate
     * @return False if errors, True otherwise
     */
    @Override
    public boolean Delete(DoubleSelection o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(o.getId_question())};
                db.delete("DoubleSelection","id=?",args);
                connect.close();
                return false;//WIthout errors
            }
        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }
        return false;
    }

    /**
     * Gets all the questions base on the section id give
     * @param id Section id to get the questions from
     * @return ArrayList<DoubleSelection> Arraylist of DoubleSelection questions
     */
    @Override
    public ArrayList GetAll(int id) {
        ArrayList<DoubleSelection> questions = new ArrayList<>();
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(id)};
                Cursor c = db.query("DoubleSelection",new String[]{"id","question","option1","option2","option3","option4","answer1","answer2","id_section"},
                        "id_section=?",args,null,null,"id asc",null);
                if(c.moveToFirst()){
                    do{
                        int idx = c.getInt(0);
                        String question = c.getString(1);
                        String option1 = c.getString(2);
                        String options2 = c.getString(3);
                        String option3 = c.getString(4);
                        String option4 = c.getString(5);
                        String answer1 = c.getString(6);
                        String answer2 = c.getString(7);
                        int idSection= c.getInt(8);
                        //THIRD ELEMENT IS THE ID OF THE EXAM
                        DoubleSelection s = new DoubleSelection(idx,question,option1,options2,option3,option4,answer1,answer2,idSection);
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
