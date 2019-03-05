package com.twiceyuan.ddmsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.twiceyuan.ddmsample.custom.AnimatedHeader;
import com.twiceyuan.ddmsample.custom.CustomListContent;
import com.twiceyuan.dropdownmenu.DropdownMenu;
import com.twiceyuan.dropdownmenu.widget.DropListContent;
import com.twiceyuan.dropdownmenu.widget.TextViewHeader;

import java.util.Arrays;

public class SampleActivity extends AppCompatActivity {

    private final String[] HEROES = {
            "Iron Man",
            "Ant Man",
            "American Captain",
            "Hulk",
            "Thor",
            "Black Widow",
            "一个长度特别长的用来测试最大长度的英雄"
    };

    private final String[] COLORS = {
            "Red", "Yellow", "Blue", "White"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvChooseHero = findViewById(R.id.tv_hero);

        final TextView textContent = findViewById(R.id.textContent);

        // 默认样式 + 默认头部
        new DropdownMenu.Builder<String>()
                .header(new TextViewHeader(tvChooseHero))
                .content(new DropListContent(this, Arrays.asList(HEROES)))
                .build()
                .setOnChooseListener(textContent::setText);

        // 自定义样式 + 带动画指示器的头部
        new DropdownMenu.Builder<String>()
                .header(new AnimatedHeader(findViewById(R.id.layout_color_header)))
                .content(new CustomListContent(this, Arrays.asList(COLORS)))
                .build()
                .setOnChooseListener(textContent::setText);
    }
}
