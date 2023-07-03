package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.MessageNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @ClassName MessageNumMapper
 * @Author ZhangFaTong
 * @create 2023/6/30 9:40
 */
@Repository
@Mapper
public interface MessageNumMapper extends BaseMapper<MessageNum> {

    List<MessageNum> selectById(@Param("id") int id);


    int creatMsgModel();

    int createCpuModel();

    int createMemoryModel();

    int createHardDiskModel();


    int dropModel(@Param("model") String model);

    int isModel(@Param("model") String model);

    int trainModel(@Param("model") String model);

    int addMessage(MessageNum messageNum);


    int addPrediction(MessageNum messageNum);

    int importFile(List<MessageNum> list);

    Map<String, Integer>prediction();



}
