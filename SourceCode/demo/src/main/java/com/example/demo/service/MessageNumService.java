package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.MessageNum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MessageNumService
 * @Author ZhangFaTong
 * @create 2023/6/30 9:48
 */
@Service
public interface MessageNumService extends IService<MessageNum> {


    List<MessageNum>  selectById(int id);

    int creatMsgModel();

    int createCpuModel();
    int createMemoryModel();
    int createHardDiskModel();

    int dropModel(String model);

    int isModel(String model);

    int trainModel(String model);

    int add (MessageNum messageNum);
    int addPrediction (MessageNum messageNum);

    int importFile(List<MessageNum> list);

    Map<String, Integer> prediction();

}
