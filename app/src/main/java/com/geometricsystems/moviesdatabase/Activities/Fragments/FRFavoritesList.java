package com.geometricsystems.moviesdatabase.Activities.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.geometricsystems.moviesdatabase.Activities.adapters.AdapterMovies;
import com.geometricsystems.moviesdatabase.Activities.types.Tmovie;
import com.geometricsystems.moviesdatabase.Activities.utils.Zutils;
import com.geometricsystems.moviesdatabase.Activities.utils.Zvars;
import com.geometricsystems.moviesdatabase.R;

import java.util.ArrayList;

/**
 * Created by dmitriy on 02.12.15.
 */

public class FRFavoritesList extends Fragment{

    Activity activity;
    ListView lvMainList;
    AdapterMovies adapter;
    ArrayList<Tmovie> list = new ArrayList<Tmovie>();
    Handler handler = new Handler();

    public static FRFavoritesList newInstance(){
        Bundle args = new Bundle();
        FRFavoritesList fragment = new FRFavoritesList();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fr_favorites_movies,null);
        activity = getActivity();
        lvMainList = (ListView)rootView.findViewById(R.id.lvFavorites);
        list = Zutils.getSavedFavoritesList(activity);
        adapter = new AdapterMovies(activity,R.layout.item_movie,list, Zvars.ADAPTER_VIEW_TYPE_FAVORITE);
        lvMainList.setAdapter(adapter);

        return rootView;
    }

    public void updateListState() {
        if(adapter!=null){
            list.clear();
            ArrayList<Tmovie> tempArray = Zutils.getSavedFavoritesList(activity);

            list.addAll(tempArray);
            handler.post(new Runnable() {
                @Override
                public void run(){
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
