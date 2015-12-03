package com.geometricsystems.moviesdatabase.Activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.geometricsystems.moviesdatabase.Activities.types.Tmovie;
import com.geometricsystems.moviesdatabase.Activities.utils.Zutils;
import com.geometricsystems.moviesdatabase.Activities.utils.Zvars;
import com.geometricsystems.moviesdatabase.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dmitriy on 02.12.15.
 */
public class AdapterMovies extends ArrayAdapter<Tmovie>{

    Context context;
    ArrayList<Tmovie> list;
    LayoutInflater inflater;
    int type;

    public AdapterMovies(Context context, int resource, ArrayList<Tmovie> objects,int type) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.type = type;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View view = inflater.inflate(R.layout.item_movie,null);
        CheckBox cbBoxFavorite = (CheckBox)view.findViewById(R.id.cbIsFavorite);
        if(type==Zvars.ADAPTER_VIEW_TYPE_POPUP){
            if(list.get(position).isSelected()){
                cbBoxFavorite.setChecked(true);
            }else{
                cbBoxFavorite.setChecked(false);
            }
            cbBoxFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        Zutils.addElement(context,list.get(position));
                        list.get(position).setSelected(true);
                    }else{
                        Zutils.removeElement(context,list.get(position));
                        list.get(position).setSelected(false);
                    }
                }
            });
        }else{
            cbBoxFavorite.setVisibility(View.GONE);
        }
        TextView tvTitle =(TextView)view.findViewById(R.id.tvItemMovieTitle);
        tvTitle.setText(list.get(position).getTitle());
        ((TextView)view.findViewById(R.id.tvItemMovieRating)).setText("Рейтинг: "+list.get(position).getVote_average());
        if(list.get(position).getPoster_path()!=null){
            Picasso.with(context).load(Zvars.URL_IMAGES_ROOT+list.get(position).getPoster_path()).into((ImageView)view.findViewById(R.id.ivItemMoviePoster));
        }
        return view;
    }
}
