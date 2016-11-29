package com.example.kimwoochul.abouttooth.Tabs.Album;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kimwoochul.abouttooth.R;
import com.example.kimwoochul.abouttooth.TabTools.TabFragment;
import com.example.kimwoochul.abouttooth.Tools.MyApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.jar.Manifest;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment
{
    RecyclerView recyclerView;
    ArrayList<String> images;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_album, null);

        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MyApplication.getsContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        fab = (FloatingActionButton)v.findViewById(R.id.album_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 3000);
                //startActivity(intent);
            }
        });


        // 파일 읽기 권한
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.M){
            int permissionResult = MyApplication.getsContext().checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE);

            if(permissionResult == PackageManager.PERMISSION_DENIED){
                if(shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("권한이 필요합니다.")
                            .setMessage("이 기능을 사용하기 위해서는 단말기의 \"파일 읽기\" 권한이 필요합니다. 계속하시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
                                    }
                                }
                            })
                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MyApplication.getsContext(), "기능을 취소했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create()
                            .show();
                }
                else {
                    requestPermissions(new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
                }
            }
            else{
                images = getPathOfAllImage();
            }
        }
        else{
            images = getPathOfAllImage();
        }

        // 파일 쓰기 권한
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.M){
            int permissionResult = MyApplication.getsContext().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if(permissionResult == PackageManager.PERMISSION_DENIED){
                if(shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("권한이 필요합니다.")
                            .setMessage("이 기능을 사용하기 위해서는 단말기의 \"파일 쓰기\" 권한이 필요합니다. 계속하시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2000);
                                    }
                                }
                            })
                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MyApplication.getsContext(), "기능을 취소했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create()
                            .show();
                }
                else {
                    requestPermissions(new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2000);
                }
            }
            else{
                // TODO
                loadImg();
            }
        }
        else{
            // TODO
            loadImg();
        }


        return v;
    }

    private ArrayList<String> getPathOfAllImage(){
        ArrayList<String> result = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME};

        Cursor cursor = MyApplication.getsContext().getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int columnDisplayname = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

        int lastIndex;
        while(cursor.moveToNext()){
            String absolutePathOfImage = cursor.getString(columnIndex);
            String nameOfFile = cursor.getString(columnDisplayname);
            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile);
            lastIndex = lastIndex >= 0 ? lastIndex : nameOfFile.length() - 1;

            if(!TextUtils.isEmpty(absolutePathOfImage)){
                result.add(absolutePathOfImage);
            }
        }
        for(String string : result){
            Log.e("Tag", "|" + string + "|");
        }
        return result;
    }

    public void loadImg(){
        AlbumAdapter adapter = new AlbumAdapter(MyApplication.getsContext(), images);
        recyclerView.setAdapter(adapter);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 3000) {
            Log.e("TAG", "Camera finish!!");
            //Toast.makeText(MyApplication.getsContext(), "Camera finish!!", Toast.LENGTH_SHORT).show();
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.setAdapter(recyclerView.getAdapter());

            TabFragment.viewPager.getAdapter().notifyDataSetChanged();
            TabFragment.viewPager.setAdapter(TabFragment.viewPager.getAdapter());
            TabFragment.viewPager.setCurrentItem(1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(MyApplication.getsContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    images = getPathOfAllImage();
                }
            }
            else {
                Toast.makeText(MyApplication.getsContext(), "권한 요청을 거부했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == 2000){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(MyApplication.getsContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO
                    loadImg();
                }
            }
            else {
                Toast.makeText(MyApplication.getsContext(), "권한 요청을 거부했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
