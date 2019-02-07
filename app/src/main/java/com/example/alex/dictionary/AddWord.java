package com.example.alex.dictionary;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.alex.dictionary.JsonHelper.getJsonStringYandex;

public class AddWord extends AppCompatActivity {
    private Button Add;
    private EditText Word;
    private  EditText Translate;
    private ListView listView;
    DataBaseHelper helper;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        list=new ArrayList<>();
        listView = (ListView)findViewById(R.id._translateList);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        helper= DataBaseHelper.getInstance(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                String s = list.get(i);
                                                Translate.setText(s.toString());
                                            }
                                        }
        );
        addListenerOnButton();
    }
    public void  addListenerOnButton() {
        Word = (EditText)findViewById(R.id.word);
        Translate = (EditText)findViewById(R.id.translate);
        Add = (Button)findViewById(R.id.AddButton);
        Add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String word = Word.getText().toString();
                        String translate = Translate.getText().toString();
                        Type p = new Type(word,translate,1);
                            helper.add(p);
                            Intent intent = new Intent(AddWord.this, MainActivity.class);
                            startActivity(intent);

                    }
                }
        );
        }
    @Override
    protected void onResume() {
        Word = (EditText)findViewById(R.id.word);
        Translate = (EditText)findViewById(R.id.translate);
        listView = (ListView)findViewById(R.id._translateList);

        Word.addTextChangedListener(new TextWatcher() {
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
                if(Word.getText().length()!=0) {
                    List<String> strings = null;
                    try {
                        strings = JsonHelper.getJsonStringYandex(Word.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    listView.setAdapter(adapter);
                    Log.d("mLog", String.valueOf(strings.size()));
                    for (String str : strings)
                       list.add(str);
                }
            }});
        super.onResume();
}
}
