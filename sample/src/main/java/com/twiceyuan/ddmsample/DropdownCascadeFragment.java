package com.twiceyuan.ddmsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.twiceyuan.ddmsample.widget.CascadeView;
import com.twiceyuan.dropdownmenu.ArrayDropdownAdapter;
import com.twiceyuan.dropdownmenu.DropdownMenu;
import com.twiceyuan.dropdownmenu.OnDropdownItemClickListener;

import java.util.ArrayList;

/**
 * Created by twiceYuan on 2/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 *
 * 二级联动下拉菜单
 */
public class DropdownCascadeFragment extends Fragment {

    private static final String[] LEFT_STRINGS  = {"十", "二十", "三十", "四十", "五十", "六十", "七十", "八十"};
    private static final String[] RIGHT_STRINGS = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dropdown_cascade, container, false);

        final DropdownMenu menu = (DropdownMenu) view.findViewById(R.id.dm_dropdown);
        final CascadeView cascadeView = new CascadeView(getContext());
        final TextView textContext = (TextView) view.findViewById(R.id.textContent);

        final ArrayAdapter<String> leftAdapter = new ArrayAdapter<>(
                getContext(),
                R.layout.cascade_left,
                LEFT_STRINGS);

        // 这个需要传入到 setCustomView 方法中，因此需要实现 DropdownMenuAdapter 接口，这里使用了默认实现
        final ArrayDropdownAdapter rightAdapter = new ArrayDropdownAdapter(
                getContext(),
                R.layout.cascade_right,
                new ArrayList<String>());

        cascadeView.setLeftAdapter(leftAdapter);
        cascadeView.setRightAdapter(rightAdapter);

        // 设置左右列所占比例
        cascadeView.setScale(1, 2);

        cascadeView.setLeftOnSelected(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rightAdapter.clear();
                rightAdapter.addAll(appendString(LEFT_STRINGS[position], RIGHT_STRINGS));
                rightAdapter.notifyDataSetChanged();
            }
        });

        menu.setCustomView(cascadeView, cascadeView.getFinalListView(), new OnDropdownItemClickListener() {

            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                if (adapter instanceof ArrayDropdownAdapter) {
                    textContext.setText(((ArrayDropdownAdapter) adapter).getTitleString(position));
                }
            }
        });

        return view;
    }

    public String[] appendString(String base, String[] append) {
        String[] newStrings = new String[append.length];
        for (int i = 0; i < append.length; i++) {
            newStrings[i] = base + append[i];
        }
        return newStrings;
    }
}
