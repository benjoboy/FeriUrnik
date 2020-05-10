package com.example.libdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class MyData {
    private String name;
    ArrayList<urnikSchema> list;

    public static final String GetID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public MyData(String name) {
        this.name = name;
        list = new ArrayList<>();
    }

    public void add(urnikSchema urnik){
        list.add(0,urnik);
    }

    public static Date stringToDate(String date, String time){
        Date datum = new Date(100);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            datum = sdf.parse(date+" "+time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (datum);
    }

    public static String dateToStringTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public static String dateToStringDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd/MM/yyyy");
        return sdf.format(date);
    }

    public static String dateToStringDay(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        return sdf.format(date);
    }


    public static MyData GenerateTestTasks() {
        MyData data = new MyData("RandomName");

        String[] sku = {"A","B","C"};
        lokacijaSchema loc = new lokacijaSchema("Zgradba", "G2");
        prostorSchema prostor = new prostorSchema("E-102", "RV", loc);
        izvajalecSchema izv = new izvajalecSchema("Janez", "Novak", "b.ruh@um.si", "idk", stringToDate("17/06/2020", "17:00"), stringToDate("17/06/2020", "18:00"),prostor);
        izpitSchema izp = new izpitSchema(stringToDate("17/06/2020", "17:00"), stringToDate("13/06/2020", "17:00"), prostor);
        izvajalecSchema[] izvA = {izv};
        izpitSchema[] izpA = {izp};
        predmetSchema pred = new predmetSchema("Preizkusanje racunalniske opreme",sku ,izvA, izpA );
        predmetSchema pred2 = new predmetSchema("Umetna inteligenca",sku ,izvA, izpA );
        predmetSchema pred3 = new predmetSchema("Sociolocki in poklicni vidiki",sku ,izvA, izpA );

        data.add(new urnikSchema("RV", stringToDate("11/05/2020", "12:00"), stringToDate("11/05/2020", "14:00"), pred, izv, prostor));
        data.add(new urnikSchema("PR", stringToDate("11/05/2020", "14:00"), stringToDate("11/05/2020", "15:00"), pred2, izv, prostor));
        //data.add(new urnikSchema("PR", stringToDate("11/05/2020", "15:00"), stringToDate("11/05/2020", "17:00"), pred3, izv, prostor));
        //data.add(new urnikSchema("RV", stringToDate("11/05/2020", "17:00"), stringToDate("11/05/2020", "18:00"), pred, izv, prostor));

        data.add(new urnikSchema("RV", stringToDate("12/05/2020", "12:00"), stringToDate("11/05/2020", "16:00"), pred, izv, prostor));
        data.add(new urnikSchema("PR", stringToDate("12/05/2020", "14:00"), stringToDate("11/05/2020", "17:00"), pred2, izv, prostor));
        data.add(new urnikSchema("PR", stringToDate("12/05/2020", "15:00"), stringToDate("11/05/2020", "17:00"), pred3, izv, prostor));
        data.add(new urnikSchema("RV", stringToDate("12/05/2020", "15:00"), stringToDate("11/05/2020", "16:00"), pred3, izv, prostor));
        data.add(new urnikSchema("RV", stringToDate("12/05/2020", "16:00"), stringToDate("11/05/2020", "17:00"), pred3, izv, prostor));
        data.add(new urnikSchema("RV", stringToDate("12/05/2020", "17:00"), stringToDate("11/05/2020", "18:00"), pred, izv, prostor));

        data.add(new urnikSchema("RV", stringToDate("13/05/2020", "12:00"), stringToDate("11/05/2020", "14:00"), pred, izv, prostor));
        data.add(new urnikSchema("PR", stringToDate("13/05/2020", "14:00"), stringToDate("11/05/2020", "15:00"), pred2, izv, prostor));

        data.add(new urnikSchema("RV", stringToDate("18/05/2020", "11:00"), stringToDate("18/05/2020", "12:00"), pred, izv, prostor));
        data.add(new urnikSchema("RV", stringToDate("18/05/2020", "12:00"), stringToDate("11/05/2020", "16:00"), pred, izv, prostor));
        data.add(new urnikSchema("RV", stringToDate("19/05/2020", "18:00"), stringToDate("11/05/2020", "19:00"), pred, izv, prostor));
        data.add(new urnikSchema("PR", stringToDate("19/05/2020", "14:00"), stringToDate("11/05/2020", "17:00"), pred2, izv, prostor));
        data.add(new urnikSchema("PR", stringToDate("20/05/2020", "15:00"), stringToDate("11/05/2020", "17:00"), pred3, izv, prostor));

        data.add(new urnikSchema("RV", stringToDate("25/05/2020", "15:00"), stringToDate("11/05/2020", "16:00"), pred3, izv, prostor));
        data.add(new urnikSchema("RV", stringToDate("26/05/2020", "17:00"), stringToDate("11/05/2020", "18:00"), pred, izv, prostor));
        data.add(new urnikSchema("RV", stringToDate("27/05/2020", "15:00"), stringToDate("11/05/2020", "16:00"), pred3, izv, prostor));
        data.add(new urnikSchema("RV", stringToDate("28/05/2020", "17:00"), stringToDate("11/05/2020", "18:00"), pred, izv, prostor));
        data.add(new urnikSchema("RV", stringToDate("29/05/2020", "15:00"), stringToDate("11/05/2020", "16:00"), pred3, izv, prostor));

        return data;
    }

    public ArrayList<urnikSchema> getList() {
        return list;
    }
}
