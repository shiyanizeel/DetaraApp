package com.skad.myapplication.Models.HomeModel;

import java.util.List;

public class DrawerRingModel {
    String drawerRingList;
    int drawerRingArrowForword;
    private boolean isSelected;
    private boolean isExpanded;
    private List<String> subItems;



    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public DrawerRingModel(String drawerRingList, int drawerRingArrowForword, boolean isSelected, List<String> subItems) {
        this.drawerRingList = drawerRingList;
        this.drawerRingArrowForword = drawerRingArrowForword;
        this.isSelected = isSelected;
        this.isExpanded = false;
        this.subItems = subItems;
    }

    public List<String> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<String> subItems) {
        this.subItems = subItems;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getDrawerRingList() {
        return drawerRingList;
    }

    public void setDrawerRingList(String drawerRingList) {
        this.drawerRingList = drawerRingList;
    }

    public int getDrawerRingArrowForword() {
        return drawerRingArrowForword;
    }

    public void setDrawerRingArrowForword(int drawerRingArrowForword) {
        this.drawerRingArrowForword = drawerRingArrowForword;
    }
}
