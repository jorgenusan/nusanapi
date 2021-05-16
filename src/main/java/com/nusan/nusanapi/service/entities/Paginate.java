package com.nusan.nusanapi.service.entities;

public class Paginate {
    private int numPage;
    private int sizePage;
    private String sortBy;
    private boolean ascending;

    public Paginate(int numPage, int sizePage, String sortBy, boolean ascending){
        this.numPage=numPage;

        if(sizePage==0){
            this.sizePage = 10;
        }else{
            this.sizePage=sizePage;
        }

        if(sortBy==null){
            this.sortBy="id";
        }else{
            this.sortBy=sortBy;
        }

        if(ascending==false){
            this.ascending=false;
        }else{
            this.ascending=ascending;
        }

    }

    public int getNumPage(){
        return this.numPage;
    }

    public int getSizePage(){
        return this.sizePage;
    }

    public String getSortBy(){
        return this.sortBy;
    }

    public boolean isAscending(){
        return this.ascending;
    }
}