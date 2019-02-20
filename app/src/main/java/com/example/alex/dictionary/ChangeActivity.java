package com.example.alex.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;

import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChangeActivity extends AppCompatActivity {

    public EditText word;
    EditText translate;
    Button changeButton;
    DataBaseHelper helper;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_change, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            Type type = Words.dictionary.get(MainActivity.position);
            helper.remove(type.id);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Type type = Words.dictionary.get(MainActivity.position);
        word = findViewById(R.id.ChangeWord);
        translate = findViewById(R.id.ChangeTranslate);
        changeButton = findViewById(R.id.changeButton);
        word.setText(type.word);
        translate.setText(type.translate);
        helper = DataBaseHelper.getInstance(this);
        list = new ArrayList<>();
        listView = findViewById(R.id._ldynamic);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        helper = DataBaseHelper.getInstance(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                String s = list.get(i);
                                                translate.setText(s);
                                            }
                                        }
        );
        addListenerOnButton();
    }

    @Override
    protected void onResume() {
        word = findViewById(R.id.ChangeWord);
        translate = findViewById(R.id.ChangeTranslate);
        listView = findViewById(R.id._ldynamic);

        word.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.clear();
                if (word.getText().length() != 0) {
                    List<String> strings;
                    strings = JsonHelper.getJsonStringYandex(word.getText().toString());

                    listView.setAdapter(adapter);
                    assert strings != null;
                    list.addAll(strings);
                }
            }
        });
        super.onResume();
    }

    public void addListenerOnButton() {
        changeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        translate.setText("");
                    }
                }
        );
        changeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                }
        );
    }
}
