package cr.ac.itcr.examproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import access_data.DoubleSelectionRepository;
import access_data.ExamRepository;
import access_data.SectionRepository;
import access_data.SingleSelectionRepository;
import access_data.TrueFalseRepository;
import exams.Exam;

/**
 * The core class of the application, this ExamManagementDrawer is used to display fragments that will add or eliminate questions and exams
 */
public class ExamManagementDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ExamDetailFragment.OnFragmentInteractionListener,
        ExamListFragment.OnFragmentInteractionListener,
        NewExam.OnFragmentInteractionListener,
        SectionsListFragment.OnFragmentInteractionListener,
        NewSection.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener,
        SectionDetailFragment.OnFragmentInteractionListener,
        SingleSelectionDetailFragment.OnFragmentInteractionListener
{
    private int exam_pos;
    private ArrayList<Exam> examList;
    private ExamRepository examRepo;
    private SectionRepository sectionRepo;
    private SingleSelectionRepository singleSelectionRepo;
    private DoubleSelectionRepository doubleSelectionRepo;
    private TrueFalseRepository trueFalseRepo;


    /**
     * OnCreate links the toolbar, the drawer, the view and any other widgets that will be used in the view.
     * @param savedInstanceState The saved instance state upon call.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_management_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("About");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_dashboard, new AboutFragment());
        tx.commit();
    }

    /**
     * This method determines that action to be taken when there is a back press on the phone.
     * It will close the drawer and open the starting activity that called this drawer.
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /**
     * Inflates the menu which adds items to the action bar if it is present.
     * @param menu Menu to be assigned to the activity.
     * @return True
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exam_management_drawer, menu);
        return true;
    }

    /**
     * Handles the action bar click. The action bar will automatically handle clicks on the Home/Up button,
     * so long as you specify a parent activity in AndroidManifest.xml
     * @param item The menu item that has been selected.
     * @return Returns the action upon the on item selection.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfyoutuStatement
        if (id == R.id.menuDelete) {
            return true;
        }
        if (id == R.id.menuAdd) {
            return false;
        }
        if (id == R.id.menuSettings) {
            return true;
        }
        return false;
    }

    /**
     * Handle navigation view upon item clicks.
     * @param item The clicked item
     * @return The fragment that will be displayed or true if none.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_add_exam) {
            Fragment fragment = new NewExam();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();

            // Handle the camera action
        } else if (id == R.id.menu_evaluate_exam) {
            Fragment fragment = new ExamListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
        } else if (id == R.id.menu_sign_out) {
            super.onBackPressed();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
