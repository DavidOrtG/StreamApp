package co.edu.unab.srugeles435.stream;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class SonicMovie extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonic_movie);

        MediaController mediaController = new MediaController(this);

        videoView = findViewById(R.id.movie2);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/proyectomoviles-80e2f.appspot.com/o/y2mate.com%20-%20El%20Camino%20Una%20película%20de%20Breaking%20Bad%20%20Tráiler%20oficial%20%20Netflix.mp3?alt=media&token=b2e33b91-b1db-4e3c-9f03-b02310a500be");
        videoView.setVideoURI(uri);
        videoView.start();
    }
}