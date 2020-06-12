package com.example.demo.core.clone;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class CloneDemo {

    @Test
    public void cloneDeep(){
        Cat cat = new Cat();
        cat.setName("阿喵");
        cat.setAge(3);
        Dog dog = new Dog();
        dog.setName("旺财");
        dog.setAge(5);
        cat.setDog(dog);
        Dog dog1 = cat.getDog();
        log.info("cat : {}", cat);
        // 进行深克隆  ObjectUtil.cloneByStream(obj) 前提是对象必须实现Serializable接口。
        Cat mother = ObjectUtil.cloneByStream(cat);
        Dog dog2 = mother.getDog();
        if (dog1 != dog2) {
            log.info("附属属性对象引用地址不同");
        }
        if (cat != mother) {
            log.info("深克隆成功");
        }
    }
}
