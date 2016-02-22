package com.twiceyuan.ddmsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class SampleActivity extends AppCompatActivity {

    private static final int MENU_LIST    = 1001;
    private static final int MENU_CASCADE = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new DropdownListFragment())
                .commit();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item1 = menu.add(Menu.NONE, MENU_LIST, 0, R.string.menu_list);
        MenuItem item2 = menu.add(Menu.NONE, MENU_CASCADE, 1, R.string.menu_cascade);
        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == MENU_LIST) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new DropdownListFragment())
                    .commit();
            return true;
        }

        if (item.getItemId() == MENU_CASCADE) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new DropdownCascadeFragment())
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
