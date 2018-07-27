package com.example.enelson.newsfeed;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by elizabeth on 7/27/18.
 */

public class NewsAdapter extends ArrayAdapter<News>{

    public NewsAdapter(Context context, List<News> news){
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)


}
