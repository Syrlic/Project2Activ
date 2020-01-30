package com.example.project2activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project2activity.adapter.NameAdapter;
import com.example.project2activity.pojo.FamilyNames;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ListViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        // получаем объект из вьюхи
        ListView listView = (ListView) findViewById(R.id.listview);
        // получаем созданный список
        List<FamilyNames> names = getItems();
        // создаем объект списка с настройками
        NameAdapter adapter = new NameAdapter(this, names);
        // устанавливаем объект в виде списка во вьюху
        listView.setAdapter(adapter);
    }
    private List<FamilyNames> getItems(){
        List<FamilyNames> list = new ArrayList<>();
        list.add(new FamilyNames("A", "A", "A"));
        list.add(new FamilyNames("B", "B", "B"));
        list.add(new FamilyNames("C", "C", "C"));
        list.add(new FamilyNames("D", "D", "D"));
        return list;
    }

    // второй вариант инициализировать и заполнить список данными
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.list_layout);
//        // получаем объект из вьюхи
//        ListView listView = (ListView) findViewById(R.id.listview);
//        List<String> items = getItems();
//        // создаем объект списка с настройками
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
//                (this, android.R.layout.simple_list_item_1, items);
//        // устанавливаем объект в виде списка во вьюху
//        listView.setAdapter(arrayAdapter);
//    }
//
//    private List<String> getItems(){
//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.add("d");
//        list.add("e");
//        list.add("f");
//        list.add("g");
//        return list;
//    }

    // третий способ инициализировать и заполнить список данными
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.list_layout);
//        // получаем объект из вьюхи
//        ListView listView = (ListView) findViewById(R.id.listview);
//        // создаем контент для списка
//        String [] names = {"a", "b", "c", "d", "e", "f"};
//        // создаем объект списка с настройками
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
//                (this, android.R.layout.simple_list_item_1, names);
//        // устанавливаем объект в виде списка во вьюху
//        listView.setAdapter(arrayAdapter);
//    }

}
