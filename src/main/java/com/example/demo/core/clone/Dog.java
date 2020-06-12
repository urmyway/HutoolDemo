package com.example.demo.core.clone;

import lombok.Data;

import java.io.Serializable;

// 附属属性也需要序列化
@Data
public class Dog implements Serializable {
    private String name;
    private int age;
}
