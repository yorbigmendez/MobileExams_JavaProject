package cr.ac.itcr.examproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * MainActivity is the first page shown, used to give a first view to the user before entering the management of exams
 * This class extends Activity and it will, upon a button click, call the next view necesary for the management of exams
 */
public class MainActivity extends Activity {
    private TextView title;
    private TextView title2;
    private ImageButton imgButton;

    /**
     * The onCreate sets the content view, links the widgets with the class´s defined attributes.
     * It also creates the onClick action that will be taken one the image button has been clicked.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgButton = (ImageButton) findViewById(R.id.btnNext);
        title = (TextView)findViewById(R.id.textView);
        title2 = (TextView) findViewById(R.id.textView2);
        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/androidnation_i.ttf");
        title.setTypeface(type);
        title2.setTypeface(type);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ExamManagementDrawer.class);
                startActivity(i);
            }
        });
    }
}
