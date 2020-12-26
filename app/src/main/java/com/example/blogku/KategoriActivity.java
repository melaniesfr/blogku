package com.example.blogku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class KategoriActivity extends AppCompatActivity {

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void pindah(View v) {
        switch (v.getId()) {
            case R.id.kategori1:
                startActivity(new Intent(KategoriActivity.this, RecvK1Activity.class));
                break;
            case R.id.kategori2:
                startActivity(new Intent(KategoriActivity.this, RecvK2Activity.class));
                break;
            case R.id.kategori3:
                startActivity(new Intent(KategoriActivity.this, RecvK3Activity.class));
                break;
        }
    }
}