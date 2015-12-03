package com.geometricsystems.moviesdatabase.Activities.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import com.geometricsystems.moviesdatabase.Activities.types.Tmovie;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by dmitriy on 02.12.15.
 */
public class Zutils {

    public static ArrayList<Tmovie> getSavedFavoritesList(Context context) {
        ArrayList<Tmovie> movies = new ArrayList<Tmovie>();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String str = preferences.getString(Zvars.PREF_SAVED_FAVORITES_LIST,"");
        if(!str.equals("")){
            try{
                JSONArray jsonArray = new JSONArray(str);
                for(int i=0;i<jsonArray.length();i++){
                    movies.add(new Tmovie(jsonArray.getJSONObject(i)));
                    movies.get(i).setSelected(true);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return movies;
        }else{
            return movies;
        }
    }

    public static void saveListArray(Context context,ArrayList<Tmovie> movies){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        String js = new Gson().toJson(movies);
        editor.putString(Zvars.PREF_SAVED_FAVORITES_LIST,js);
        editor.commit();
    }

    public static void addElement(Context context,Tmovie item){
        ArrayList<Tmovie> tempList = getSavedFavoritesList(context);
        tempList.add(item);
        saveListArray(context,tempList);
    }

    public static void removeElement(Context context,Tmovie item){
        ArrayList<Tmovie> tempList = getSavedFavoritesList(context);
        for(int i=0;i<tempList.size();i++){
            if(tempList.get(i).getId()==item.getId()){
                tempList.remove(i);
            }
        }
        saveListArray(context,tempList);
    }

    public static int dpToPx(Context context,int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }


}
