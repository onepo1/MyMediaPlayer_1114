package com.example.edu.mymediaplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_play, btn_pause;
    MediaPlayer mediaPlayer;
    final int REQUEST_CODE_WRITE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE);
                }
                
        btn_play = (Button)findViewById(R.id.btn_play);
        btn_play.setOnClickListener(this);
        btn_pause = (Button)findViewById(R.id.btn_pause);
        btn_pause.setOnClickListener(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.thunder_002) ;

    }
       @Override
           public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
               super.onRequestPermissionsResult(requestCode, permissions, grantResults);
               switch (requestCode) {
                   case REQUEST_CODE_WRITE:
                       if (grantResults.length > 0 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                           Log.i("", "Permission has been granted by user");
                       }
               }
           }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.btn_play:
                  mediaPlayer.start();
                  
                  btn_play.setEnabled(true);
                  break;
              case R.id.btn_pause:
                  mediaPlayer.pause();
                  btn_pause.setEnabled(true);
                  btn_play.setEnabled(true);
                  break;
          }
    }
}
