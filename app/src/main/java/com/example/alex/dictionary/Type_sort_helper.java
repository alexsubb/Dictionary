package com.example.alex.dictionary;

import java.util.Comparator;

public class Type_sort_helper implements Comparator<Type> {

    public int compare(Type lhs, Type rhs) {
       return lhs.translate.compareTo(rhs.translate);
    }
}
