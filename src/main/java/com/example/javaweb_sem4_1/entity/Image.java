package com.example.javaweb_sem4_1.entity;

import java.util.Base64;

public class Image {
    private int id;
    private byte[] data;

    public Image(int id, byte[] data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public String getBase64Data() {
        return Base64.getEncoder().encodeToString(data);
    }
}