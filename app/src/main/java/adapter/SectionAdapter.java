package adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sections.Section;
import cr.ac.itcr.examproject.R;

/**
 * Created by Admin on 03/06/2016.
 */
public class SectionAdapter extends BaseAdapter {
    private ArrayList<Section> sectionList;
    private LayoutInflater inflater;
    private Context context;

    public SectionAdapter(Context appContext, ArrayList<Section> sectionList){
        this.context = appContext;
        this.sectionList = sectionList;
    }
    @Override
    public int getCount() {
        return sectionList.size();
    }

    @Override
    public Object getItem(int position) {
        return sectionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sectionList.get(position).getId_section();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.section_item,parent,false);
        ImageView img;
        TextView title,description;
        img = (ImageView) v.findViewById(R.id.imgSection);
        title = (TextView)v.findViewById(R.id.txtSectionName);
        description = (TextView)v.findViewById(R.id.txtDescription);
        title.setText(sectionList.get(position).getName());
        description.setText(sectionList.get(position).getDescription());
        return v;
    }
}
