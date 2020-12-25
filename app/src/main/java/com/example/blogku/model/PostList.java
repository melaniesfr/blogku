package com.example.blogku.model;

public class PostList {
    public String judul;
    public String file_gambar;
    public String isi_post;
    public String penulis;
    public String tanggal;
    public String komentar;

    public PostList() {

    }

    public PostList(String judul, String file_gambar, String isi_post, String penulis, String tanggal, String komentar) {
        this.judul = judul;
        this.file_gambar = file_gambar;
        this.isi_post = isi_post;
        this.penulis = penulis;
        this.tanggal = tanggal;
        this.komentar = komentar;
    }

    public PostList(String judul, String file_gambar, String isi_post) {
        this.judul = judul;
        this.file_gambar = file_gambar;
        this.isi_post = isi_post;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getFileGambar() {
        return file_gambar;
    }

    public void setFileGambar(String file_gambar) {
        this.file_gambar = file_gambar;
    }

    public String getIsiPost() {
        return isi_post;
    }

    public void setIsiPost(String isi_post) {
        this.isi_post = isi_post;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}