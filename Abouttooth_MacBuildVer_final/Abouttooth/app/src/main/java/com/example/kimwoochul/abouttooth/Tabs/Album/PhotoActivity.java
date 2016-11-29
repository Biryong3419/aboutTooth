package com.example.kimwoochul.abouttooth.Tabs.Album;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.kimwoochul.abouttooth.R;

import java.io.File;
import java.io.IOException;

public class PhotoActivity extends AppCompatActivity {

    ImageView iv_photo;
    String img_uri;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        intent = getIntent();
        img_uri = intent.getStringExtra("img_uri");

        try{
            ExifInterface exif = new ExifInterface(img_uri);
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int rotationDegrees = exifToDegrees(rotation);

            Matrix matrix = new Matrix();
            if(rotation != 0f){
                matrix.preRotate(rotationDegrees);
            }

            File picture = new File(img_uri);

            if(picture.exists()){
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(picture.getAbsolutePath(), options);
                Bitmap adjustedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                iv_photo = (ImageView)findViewById(R.id.iv_photo);
                iv_photo.setImageBitmap(adjustedBitmap);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        /*iv_photo = (ImageView)findViewById(R.id.iv_photo);
        iv_photo.setImageURI(Uri.parse(img_uri));*/
    }

    private static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) { return 90; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {  return 180; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {  return 270; }
        return 0;
    }
}
