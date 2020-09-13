package com.task.api.business;


//Класс для хеширования.
public class Hasher {
    //Хеш-функция.
    public static int hashString(String str){
        int hash = 0;
        int simpleNumber = 17;
        int bigSimpleNumber = 16769023;
        for(int i = 0; i < str.length(); i++){
            hash += (int)str.charAt(i) * simpleNumber;
        }
        return hash % bigSimpleNumber;
    }
}
