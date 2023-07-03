package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.MessageNumMapper;
import com.example.demo.entity.MessageNum;
import com.example.demo.service.MessageNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MessageNumServiceImpl
 * @Author ZhangFaTong
 * @create 2023/6/30 9:51
 */
@Service
public class MessageNumServiceImpl extends ServiceImpl<MessageNumMapper, MessageNum> implements MessageNumService {


    @Autowired
    MessageNumMapper messageNumMapper;

    @Override
    public List<MessageNum> selectById(int id) {
        return messageNumMapper.selectById(id);
    }

    @Override
    public int creatMsgModel() {
        return messageNumMapper.creatMsgModel();
    }

    @Override
    public int createCpuModel() {
        return messageNumMapper.createCpuModel();
    }

    @Override
    public int createMemoryModel() {
        return messageNumMapper.createMemoryModel();
    }

    @Override
    public int createHardDiskModel() {
        return messageNumMapper.createHardDiskModel();
    }

    @Override
    public int dropModel(String model) {
        return messageNumMapper.dropModel(model);
    }

    @Override
    public int isModel(String model) {
        return messageNumMapper.isModel(model);
    }

    @Override
    public int trainModel(String model) {
        return messageNumMapper.trainModel(model);
    }

    @Override
    public int add(MessageNum messageNum) {
        return messageNumMapper.addMessage(messageNum);
    }

    @Override
    public int addPrediction(MessageNum messageNum) {
        return messageNumMapper.addPrediction(messageNum);
    }

    @Override
    public int importFile(List<MessageNum> list) {
        return messageNumMapper.importFile(list);
    }

    @Override
    public Map<String, Integer> prediction() {
        return messageNumMapper.prediction();
    }


}
