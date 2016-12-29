package manschwa.shootinglog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import manschwa.shootinglog.discipline.DisciplineListActivity;
import manschwa.shootinglog.event.EventListActivity;

/**
 * Created by root on 28.12.16.
 */

public class DrawerItemListener implements NavigationView.OnNavigationItemSelectedListener {

    private AppCompatActivity activity;
    private DrawerLayout drawerLayout;

    public DrawerItemListener(AppCompatActivity activity, DrawerLayout drawerLayout) {
        this.activity = activity;
        this.drawerLayout = drawerLayout;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent = null;
        this.drawerLayout.openDrawer(GravityCompat.START);
        switch (menuItem.getItemId()) {
            case R.id.drawer_disciplines:
//                this.drawerLayout.openDrawer(GravityCompat.START);
//                Snackbar.make(content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
//                menuItem.setChecked(true);
//                this.drawerLayout.closeDrawers();
                intent = new Intent(this.activity, DisciplineListActivity.class);
//                activity.startActivity(intent);
//                return true;
                break;
            case R.id.drawer_events:
//                this.drawerLayout.openDrawer(GravityCompat.START);
//                Snackbar.make(content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);
//                this.drawerLayout.closeDrawers();
                intent = new Intent(this.activity, EventListActivity.class);
//                activity.startActivity(intent);
//                return true;
                break;
            case R.id.drawer_settings:
//                this.drawerLayout.openDrawer(GravityCompat.START);
//                Snackbar.make(content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
//                menuItem.setChecked(true);
//                this.drawerLayout.closeDrawers();
//                return true;
                break;
            default:
                return false;
        }
        menuItem.setChecked(true);
        this.drawerLayout.closeDrawers();
        activity.startActivity(intent);
        return true;
    }
}
