package com.example.kimwoochul.abouttooth.Tabs.Album;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kimwoochul.abouttooth.MainActivity;
import com.example.kimwoochul.abouttooth.R;
import com.example.kimwoochul.abouttooth.Tools.MyApplication;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by woocheol on 2016. 11. 18..
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    ArrayList<String> item;
    Context context;

     public AlbumAdapter(Context context, ArrayList<String> item){
         this.context = context;
         this.item = item;
     }

    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    private static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) { return 90; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {  return 180; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {  return 270; }
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try{
            ExifInterface exif = new ExifInterface(item.get(position));
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int rotationDegrees = exifToDegrees(rotation);

            Matrix matrix = new Matrix();
            if(rotation != 0f){
                matrix.preRotate(rotationDegrees);
            }

            File picture = new File(item.get(position));

            if(picture.exists()){
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(picture.getAbsolutePath(), options);
                Bitmap adjustedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                holder.iv_img.setImageBitmap(adjustedBitmap);
            }
        }catch (IOException e){
            e.printStackTrace();
        }




        /*File picture = new File(item.get(position));
        if(picture.exists()){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeFile(picture.getAbsolutePath(), options);
            holder.iv_img.setImageBitmap(bitmap);
        }*/

        //holder.iv_img.setImageURI(Uri.parse(item.get(position)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MyApplication.getsContext(), "Test " + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, PhotoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("img_uri", item.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_img;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView)itemView.findViewById(R.id.iv_img);
        }
    }
}
