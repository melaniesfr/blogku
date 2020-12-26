package com.example.blogku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blogku.model.PostList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailPostActivity extends AppCompatActivity {

    ImageButton btnBack;

    TextView etJudul, etIsiPost, etPenulis, etTanggal, etKomentar;
    ImageView etFileGambar;
    String temp_judul, temp_fileGambar, temp_isiPost, temp_penulis, temp_tanggal, temp_komentar;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String judulIntent = intent.getStringExtra("judul");

        etJudul = findViewById(R.id.et_judul);
        etFileGambar = findViewById(R.id.et_file_gambar);
        etIsiPost = findViewById(R.id.et_isi_post);
        etPenulis = findViewById(R.id.et_penulis);
        etTanggal = findViewById(R.id.et_tanggal);
        etKomentar = findViewById(R.id.et_komentar);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("join").child(judulIntent);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                temp_judul = dataSnapshot.child("judul").getValue().toString();
                temp_fileGambar = dataSnapshot.child("file_gambar").getValue().toString();
                temp_isiPost = dataSnapshot.child("isi_post").getValue().toString();
                temp_penulis = dataSnapshot.child("penulis").getValue().toString();
                temp_tanggal = dataSnapshot.child("tgl_insert_post").getValue().toString();
                temp_komentar = dataSnapshot.child("komentar").getValue().toString();

                etJudul.setText(temp_judul);
                Picasso.get().load(temp_fileGambar).into(etFileGambar);
                etIsiPost.setText(temp_isiPost);
                etPenulis.setText(temp_penulis);
                etTanggal.setText(temp_tanggal);
                etKomentar.setText(temp_komentar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}