package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MessageNum
 * @Author ZhangFaTong
 * @create 2023/6/29 18:02
 */
@Data
@TableName(value = "JHSER_Config_DB.MessageNum")
public class MessageNum implements Serializable {

    private static final long serialVersionUID = 1L;

//    @TableField(value = "id")
//    private Integer ID;

    @TableField(value = "CpuCores")
    private Integer CpuCores;

    @TableField(value = "MemorySize")
    private Integer MemorySize;

    @TableField(value = "HardDiskSize")
    private Integer HardDiskSize;

    @TableField(value = "MesSaveTime")
    private Integer MesSaveTime;

    @TableField(value = "MessageNum")
    private Integer MessageNum;

    @TableField(value = "OutPatientNum")
    private Integer OutPatientNum;

    @TableField(value = "ServiceNum")
    private Integer ServiceNum;




}
