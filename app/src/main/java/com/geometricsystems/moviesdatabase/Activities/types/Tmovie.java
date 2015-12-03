package com.geometricsystems.moviesdatabase.Activities.types;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dmitriy on 02.12.15.
 */
public class Tmovie {

    private boolean adult;
    private String backdrop_path;
    private ArrayList<Integer> genreIDS;
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private Date release_date;
    private String poster_path;
    private int popularity;
    private String title;
    private boolean video;
    private double vote_average;
    private int voteCount;
    private boolean selected;

    public Tmovie() {
    }

    public Tmovie(JSONObject json){
        try{
            this.adult = json.getBoolean("adult");
            this.backdrop_path = json.getString("backdrop_path");
            this.genreIDS = new ArrayList<Integer>();
//            for(int i=0;i<json.getJSONArray("genre_ids").length();i++){
//                this.genreIDS.add(json.getJSONArray("genre_ids").getInt(i));
//            }
            this.id = json.getInt("id");
            this.original_language = json.getString("original_language");
            this.original_title = json.getString("original_title");
            this.overview = json.getString("overview");
            this.release_date = new Date();
            this.poster_path = json.getString("poster_path");
            this.popularity = json.getInt("popularity");
            this.title = json.getString("title");
            this.video = json.getBoolean("video");
            this.vote_average = json.getDouble("vote_average");
            this.selected = false;
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public ArrayList<Integer> getGenreIDS() {
        return genreIDS;
    }

    public void setGenreIDS(ArrayList<Integer> genreIDS) {
        this.genreIDS = genreIDS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
