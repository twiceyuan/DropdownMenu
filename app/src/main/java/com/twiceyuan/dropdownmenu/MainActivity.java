package com.twiceyuan.dropdownmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.twiceyuan.dropdown_menu.DropdownMenu;
import com.twiceyuan.dropdown_menu.OnDropdownItemClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DropdownMenu menu1 = (DropdownMenu) findViewById(R.id.dm_dropdown);
        final String[] strings = new String[]{"Iron Man", "Ant Man", "American Captain", "Hulk"};
        menu1.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                strings
        ));
        menu1.setOnItemClickListener(new OnDropdownItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getApplicationContext(), strings[position], Toast.LENGTH_SHORT).show();
            }
        });

        DropdownMenu menu2 = (DropdownMenu) findViewById(R.id.dm_dropdown2);
        final String[] strings2 = new String[]{"Red", "Yellow", "Blue", "White"};
        menu2.setAdapter(new ArrayAdapter<>(
                this,
                R.layout.simple_dropdown_item_1line,
                strings2
        ));
        menu2.setOnItemClickListener(new OnDropdownItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getApplicationContext(), strings2[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}