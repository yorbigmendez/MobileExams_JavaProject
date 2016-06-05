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
 * Adapter of the sections
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class SectionAdapter extends BaseAdapter {
    /**
     * Arrallist of sections
     */
    private ArrayList<Section> sectionList;
    /**
     * INflater to inflate
     */
    private LayoutInflater inflater;
    /**
     * Context of app
     */
    private Context context;

    /**
     * Constructor of class
     * @param appContext
     * @param sectionList
     */
    public SectionAdapter(Context appContext, ArrayList<Section> sectionList){
        this.context = appContext;
        this.sectionList = sectionList;
    }

    /**
     * Gets size
     * @return int size
     */
    @Override
    public int getCount() {
        return sectionList.size();
    }

    /**
     * Gets item
     * @param position position of the item
     * @return Object
     */
    @Override
    public Object getItem(int position) {
        return sectionList.get(position);
    }

    /**
     * Gets item id
     * @param position position of item to get
     * @return Long
     */
    @Override
    public long getItemId(int position) {
        return sectionList.get(position).getId_section();
    }

    /**
     * Get the view of a section
     * @param position Position of view
     * @param convertView View
     * @param parent View Group
     * @return View created
     */
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
