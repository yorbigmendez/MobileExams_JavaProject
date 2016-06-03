package access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import exams.Exam;
import sections.Section;

/**
 * Created by Mendez Soto on 5/26/2016.
 */
public class SectionRepository implements IRepository<Section> {
    private DBConnection connect;

    public SectionRepository(Context context){
        connect = new DBConnection(context);
    }

    @Override
    public boolean Save(Section o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues newData = new ContentValues();
                newData.put("name",o.getName());
                newData.put("description",o.getDescription());
                newData.put("id_exam",o.getId_exam());
                db.insert("Section", null, newData);
                connect.close();
                return false;//Sin errores
            }
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
        return true;
    }

    @Override
    public boolean Update(Section o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                ContentValues updateData = new ContentValues();
                updateData.put("name",o.getName());
                updateData.put("description",o.getDescription());
                updateData.put("id_exam",o.getId_exam());
                db.update("Section", updateData, "id=?", new String[]{String.valueOf(o.getId_section())});
                connect.close();
                return false;//Sin errores
            }
        }catch(Exception e){
            Log.d("Error",e.getMessage());
        }
        return true;
    }

    @Override
    public boolean Delete(Section o) {
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(o.getId_section())};
                db.delete("Section","id=?",args);
                connect.close();
                return false;//WIthout errors
            }
        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<Section> GetAll(int id) {
        ArrayList<Section> sections = new ArrayList<>();
        try{
            SQLiteDatabase db = connect.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(id)};
                Cursor c = db.query("Section",new String[]{"id","name","description"},
                        "id_exam=?",args,null,null,"id asc",null);
                if(c.moveToFirst()){
                    do{
                        int idx = c.getInt(0);
                        String name = c.getString(1);
                        String description = c.getString(2);
                        //THIRD ELEMENT IS THE ID OF THE EXAM
                        Section s = new Section(idx,name,description,id);
                        sections.add(s);
                    }while(c.moveToNext());
                }
                connect.close();
                return sections;
            }
        }catch (Exception e){
            Log.d("ERROR",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Section> GetBy(Section o) {
        return null;
    }
}
