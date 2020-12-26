package com.example.blogku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.blogku.adapter.PostAdapter;
import com.example.blogku.model.PostList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecvK2Activity extends AppCompatActivity {

    ImageButton btnBack;

    List<PostList> itemPost;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recv_k2);

        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.list_kategori2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        itemPost = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("post");
        getImageData();
    }

    private void getImageData() {
        databaseReference.orderByChild("idkategori").equalTo("2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String judul = ds.child("judul").getValue().toString();
                    String file_gambar = ds.child("file_gambar").getValue().toString();
                    String isi_post = ds.child("isi_post").getValue().toString();

                    PostList data = new PostList(judul, file_gambar, isi_post);
                    itemPost.add(data);
                }

                PostAdapter adapter = new PostAdapter(itemPost, getApplicationContext());
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String judul = itemPost.get(position).getJudul();
                        startActivity(new Intent(RecvK2Activity.this, DetailPostActivity.class).putExtra("judul", judul));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RecvK2Activity.this, "Maaf terjadi kesalahan... coba beberapa saat lagi!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}