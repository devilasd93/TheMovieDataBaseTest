package com.geometricsystems.moviesdatabase.Activities.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.geometricsystems.moviesdatabase.Activities.adapters.AdapterMovies;
import com.geometricsystems.moviesdatabase.Activities.types.Tmovie;
import com.geometricsystems.moviesdatabase.Activities.utils.Zutils;
import com.geometricsystems.moviesdatabase.Activities.utils.Zvars;
import com.geometricsystems.moviesdatabase.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dmitriy on 02.12.15.
 */
public class FRMainList extends Fragment {

    OkHttpClient okClient = new OkHttpClient();
    Handler handled = new Handler();
    ArrayList<Tmovie> list = new ArrayList<Tmovie>();
    ListView lvMainMovies;
    AdapterMovies adapter;
    Activity activity;
    SwipeRefreshLayout swpMainList;

    public static FRMainList newInstance() {
        Bundle args = new Bundle();
        FRMainList fragment = new FRMainList();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fr_popul_movies,null);
        activity = getActivity();
        adapter = new AdapterMovies(activity,R.layout.item_movie,list,Zvars.ADAPTER_VIEW_TYPE_POPUP);
        lvMainMovies = (ListView)view.findViewById(R.id.lvMainPopup);
        swpMainList = (SwipeRefreshLayout)view.findViewById(R.id.swpMainList);
        swpMainList.setColorSchemeColors(R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimary);

        swpMainList.setEnabled(false);
        handled.post(new Runnable() {
            @Override
            public void run() {
                swpMainList.setRefreshing(true);
            }
        });

        lvMainMovies.setAdapter(adapter);
        loadMainListDatabaseMovies(activity);
        return view;
    }

    public void loadMainListDatabaseMovies(Context context){
        final ArrayList<Tmovie> temList = Zutils.getSavedFavoritesList(context);
        Request request = new Request.Builder()
                .url(Zvars.URL_MOST_POPULER_MOVIES)
                .get()
                .build();
        okClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException{
                final String str = response.body().string();
                list.clear();
                if(response.isSuccessful()){
                    try{
                        handled.post(new Runnable(){
                            @Override
                            public void run() {
                                swpMainList.setRefreshing(false);
                            }
                        });
                        JSONObject json = new JSONObject(str);
                        for(int i=0;i<json.getJSONArray("results").length();i++){
                            list.add(new Tmovie(json.getJSONArray("results").getJSONObject(i)));
                            for(int j=0;j<temList.size();j++){
                                if(list.get(i).getId()==temList.get(j).getId()){
                                    list.get(i).setSelected(true);
                                }
                            }
                        }
                        handled.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }else{

                }
            }
        });

    }

}
