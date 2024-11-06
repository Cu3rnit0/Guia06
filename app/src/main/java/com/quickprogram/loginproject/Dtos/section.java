package com.quickprogram.loginproject.Dtos;

import com.quickprogram.loginproject.Models.Presupuesto;

import java.util.List;

public class section {
    private String sectionName;
    private List<Presupuesto> sectionItems;
    public section(String sectionName, List<Presupuesto> sectionItems) {
        this.sectionName = sectionName;
        this.sectionItems = sectionItems;
    }
    public String getSectionName() {
        return sectionName;
    }
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public List<Presupuesto> getSectionItems() {
        return sectionItems;
    }
    public void setSectionItems(List<Presupuesto> sectionItems) {
        this.sectionItems = sectionItems;
    }
    @Override
    public String toString() {
        return "Section{" +
                "sectionName='" + sectionName + '\'' +
                ", sectionItems=" + sectionItems +
                '}';
    }

}
