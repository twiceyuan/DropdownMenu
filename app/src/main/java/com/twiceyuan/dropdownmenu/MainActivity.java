package com.twiceyuan.dropdownmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.twiceyuan.dropdown_menu.ArrayDropdownAdapter;
import com.twiceyuan.dropdown_menu.DropdownMenu;
import com.twiceyuan.dropdown_menu.OnDropdownItemClickListener;
import com.twiceyuan.dropdown_menu.MenuManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DropdownMenu menu1 = (DropdownMenu) findViewById(R.id.dm_dropdown);
        final DropdownMenu menu2 = (DropdownMenu) findViewById(R.id.dm_dropdown2);

        final String[] hero = new String[]{
                "Iron Man", "Ant Man", "American Captain",
                "Hulk", "Thor", "Black Widow"};
        menu1.setAdapter(new ArrayDropdownAdapter(this, android.R.layout.simple_dropdown_item_1line, hero));
        menu1.setOnItemClickListener(new OnDropdownItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getApplicationContext(), hero[position], Toast.LENGTH_SHORT).show();
                menu2.collapse();
            }
        });

        final String[] strings2 = new String[]{
                "Red", "Yellow", "Blue", "White"};
        menu2.setAdapter(new ArrayDropdownAdapter(this, R.layout.simple_dropdown_item_1line, strings2));
        menu2.setOnItemClickListener(new OnDropdownItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getApplicationContext(), strings2[position], Toast.LENGTH_SHORT).show();
                menu1.collapse();
            }
        });

        MenuManager.single(menu1, menu2);
    }
}
