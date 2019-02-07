package com.example.alex.dictionary;
import java.util.ArrayList;
import java.util.Collections;

public class Words {
    static ArrayList<Type> dictionary = new ArrayList<>();
    public static void add(Type s) {
    dictionary.add(s);
    }
    public static  void Set(int index,Type type)
    {
        dictionary.set(index,type);
    }
    public static void Sort()
    {
        Collections.sort(dictionary,new Type_sort_helper());
    }
    public static void Remove(int index)
    {
        dictionary.remove(index);
    }
    public static void clear()
    {
        dictionary.clear();
    }
}