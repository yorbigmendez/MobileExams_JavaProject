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
 * Created by Mendez Soto on 5/26/2016.
 */
public class SingleSelectionRepository implements IRepository<SingleSelection> {

    private DBConnection connect;
    public SingleSelectionRepository(Context context){
        connect = new DBConnection(context);
    }
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
                newData.put("option4",o.getOpc4());
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
                newData.put("option4",o.getOpc4());
                newData.put("answer",o.getAnswer());
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

    @Override
    public boolean Delete(SingleSelection o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(o.getId_question())};
                db.delete("SingleSelection","id=?",args);
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

    @Override
    public ArrayList GetBy(SingleSelection o) {
        return null;
    }
}
