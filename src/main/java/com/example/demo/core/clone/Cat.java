package com.example.demo.core.clone;

import lombok.Data;

import java.io.Serializable;

// 深克隆 需要克隆的 对象类 必须实现 Serializable
@Data
public class Cat implements Serializable {
    private String name;
    private int age;
    // 对象的对象也需要序列化
    private Dog dog;
}