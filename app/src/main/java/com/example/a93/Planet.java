package com.example.a93;

import java.util.ArrayList;

public class Planet {

    String name;
    ArrayList<Planet> planetas = new ArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Planet> getPlanetas() {
        return planetas;
    }

    public void setPlanetas(ArrayList<Planet> planetas) {
        this.planetas = planetas;
    }
}