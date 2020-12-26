package com.example.blogku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.blogku.adapter.PostAdapter;
import com.example.blogku.model.PostList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<PostList> itemPost;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        itemPost = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("hasilJoin");
        getImageData();
    }

    // Inisialisasi objek untuk ke firebase
    private void getImageData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Iterasi mengambil postingan
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String judul = ds.child("judul").getValue().toString();
                    String file_gambar = ds.child("file_gambar").getValue().toString();
                    String isi_post = ds.child("isi_post").getValue().toString();

                    PostList data = new PostList(judul, file_gambar, isi_post);
                    itemPost.add(data);
                }

                PostAdapter adapter = new PostAdapter(itemPost, getApplicationContext());
                recyclerView.setAdapter(adapter);

                // Pindah activity apabila item pada list di click
                adapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String judulIntent = itemPost.get(position).getJudul();
                        Intent intent = new Intent(MainActivity.this, DetailPostActivity.class);
                        intent.putExtra("judul", judulIntent);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Maaf terjadi kesalahan... coba beberapa saat lagi!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void pindah(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                startActivity(new Intent(MainActivity.this, PencarianActivity.class));
                break;
            case R.id.btn_kategori:
                startActivity(new Intent(MainActivity.this, KategoriActivity.class));
                break;
        }
    }
}