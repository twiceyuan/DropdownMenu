package com.twiceyuan.ddmsample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.twiceyuan.dropdownmenu.ArrayDropdownAdapter;
import com.twiceyuan.dropdownmenu.DropdownMenu;
import com.twiceyuan.dropdownmenu.MenuManager;
import com.twiceyuan.dropdownmenu.OnDropdownItemClickListener;

public class SampleActivity extends AppCompatActivity {

    final String[] HEROES = new String[]{"Iron Man", "Ant Man", "American Captain", "Hulk", "Thor", "Black Widow"};
    final String[] COLORS = new String[]{"Red", "Yellow", "Blue", "White"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DropdownMenu menu1 = (DropdownMenu) findViewById(R.id.dm_dropdown);
        final DropdownMenu menu2 = (DropdownMenu) findViewById(R.id.dm_dropdown2);

        menu1.setAdapter(new ArrayDropdownAdapter(this, R.layout.light_dropdown_item_1line, HEROES));
        menu1.getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        menu1.getListView().setDivider(ContextCompat.getDrawable(this, R.drawable.inset_divider));
        menu1.getListView().setDividerHeight(1);
        menu1.setOnItemClickListener(new OnDropdownItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), HEROES[position], Toast.LENGTH_SHORT).show();
            }
        });

        menu2.setAdapter(new ArrayDropdownAdapter(this, R.layout.dark_dropdown_item_1line, COLORS));
        menu2.setOnItemClickListener(new OnDropdownItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), COLORS[position], Toast.LENGTH_SHORT).show();
            }
        });

        // 将多个 DropdownMenu 成组，在其中一个弹出的时候隐藏另外的
        MenuManager.group(menu1, menu2);
    }
}
