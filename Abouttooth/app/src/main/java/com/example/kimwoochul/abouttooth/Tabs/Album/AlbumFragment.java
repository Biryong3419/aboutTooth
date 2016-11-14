package com.example.kimwoochul.abouttooth.Tabs.Album;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.kimwoochul.abouttooth.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment
{
    GridView gv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_album, null);

        gv = (GridView)v.findViewById(R.id.gridView);


        return v;
    }
}
