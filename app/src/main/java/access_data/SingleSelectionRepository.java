package access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import questions.DoubleSelection;
import questions.SingleSelection;
import sections.Section;

/**
 * Activity for loading layout resources
 *
 * This activity is used to display different layout resources for a tutorial on user interface design.
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class SingleSelectionRepository implements IRepository<SingleSelection> {
    /**
     * Connection to the databse
     */
    private DBConnection connect;

    /**
     * Constructor of the class
     * @param context Context of the application
     */
    public SingleSelectionRepository(Context context){
        connect = new DBConnection(context);
    }

    /**
     * Saves a SingleSelection question
     * @param o Object of single selection
     * @return False if no errors, True otherwise
     */
    @Override
    public boolean Save(SingleSelection o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("question",o.getQuestion());
                newData.put("option1",o.getOpc1());
                newData.put("option2",o.getOpc2());
                newData.put("option3",o.getOpc3());
                newData.put("option4", o.getOpc4());
                newData.put("answer",o.getAnswer());
                newData.put("id_section",o.getId_section());
                db.insert("SingleSelection", null, newData);
                connect.close();
                return false;//Sin errores
            }
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
        return true;
    }

    /**
     * Updates a SingleSelection object
     * @param o Object SingleSelection to update
     * @return Fasle if no erros, true otherwise
     */
    @Override
    public boolean Update(SingleSelection o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("question",o.getQuestion());
                newData.put("option1",o.getOpc1());
                newData.put("option2",o.getOpc2());
                newData.put("option3",o.getOpc3());
                newData.put("option4", o.getOpc4());
                newData.put("answer", o.getAnswer());
                newData.put("id_section",o.getId_section());
                db.update("SingleSelection", newData, "id=?", new String[]{String.valueOf(o.getId_question())});
                connect.close();
                return false;//Sin errores
            }
        }catch(Exception e){
            Log.d("Error",e.getMessage());
        }
        return true;
    }

    /**
     * Delets a question
     * @param o SingleSelection to delte
     * @return False if no errors, true otherwise
     */
    @Override
    public boolean Delete(SingleSelection o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(o.getId_question())};
                db.delete("SingleSelection", "id=?", args);
                connect.close();
                return false;//WIthout errors
            }
        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }
        return false;
    }

    /**
     * Gets all question of section id
     * @param id id of the seciton
     * @return Arraylist of questions
     */
    @Override
    public ArrayList GetAll(int id) {
        ArrayList<SingleSelection> questions = new ArrayList<>();
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(id)};
                Cursor c = db.query("SingleSelection",new String[]{"id","question","option1","option2","option3","option4","answer","id_section"},
                        "id_section=?",args,null,null,"id asc",null);
                if(c.moveToFirst()){
                    do{
                        int idx = c.getInt(0);
                        String question = c.getString(1);
                        String opc1 = c.getString(2);
                        String opc2 = c.getString(3);
                        String opc3 = c.getString(4);
                        String opc4 = c.getString(5);
                        String answer = c.getString(6);
                        int indxSection = c.getInt(7);
                        //THIRD ELEMENT IS THE ID OF THE EXAM
                        SingleSelection s = new SingleSelection(idx,question,opc1,opc2,opc3,opc4,answer,indxSection);
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
