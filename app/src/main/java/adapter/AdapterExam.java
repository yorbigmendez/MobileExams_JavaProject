package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import exams.Exam;
import cr.ac.itcr.examproject.R;

/**
 * Adapter of the listView of exams
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class AdapterExam extends BaseAdapter {
    /**
     * Context of the app
     */
    private Context context;
    /**
     * Infalter used to inflate view
     */
    private LayoutInflater inflater;
    /**
     * Items to inflate
     */
    private ArrayList<Exam> items;

    /**
     * Constructor of class
     * @param context Context of app
     * @param items items to inflate
     */
    public AdapterExam(Context context, ArrayList<Exam> items)
    {
        this.context = context;
        this.items = items;
    }
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return items.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView  txtName;
        ImageView imgExam;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.exam_item,parent,false);
        txtName = (TextView) v.findViewById(R.id.txtExamName);
        //Set a random image to this
        imgExam = (ImageView) v.findViewById(R.id.imgExam);

        txtName.setText(items.get(position).getName());

        return v;
    }
}
