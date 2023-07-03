package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.demo.entity.MessageNum;
import com.example.demo.service.MessageNumService;
import com.example.demo.util.Response;
import com.example.demo.util.Result;
import com.example.demo.util.ResultEnum;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PredictController
 * @Author ZhangFaTong
 * @create 2023/6/29 17:24
 */
@Api(value = "Prediction of server configuration for entry")
@RestController
@RequestMapping("/ML")
public class PredictController {
    @Resource
    MessageNumService messageNumService;

    @ApiOperation(value = "2、Data initialization", notes = "The initialization is slow. You need to wait patiently", httpMethod = "POST")
    @RequestMapping(value = "/initML", method = RequestMethod.POST)
    public Result creatAllModel(){
        String msgModeName="MsgMode";
        int msgNm= messageNumService.isModel(msgModeName);
        if(msgNm==0){
            messageNumService.creatMsgModel();
        }
        String cpuModeName="CpuMode";
        int cpuNum= messageNumService.isModel(cpuModeName);
        if(cpuNum==0){
            messageNumService.createCpuModel();
        }
        String memoryModeName="MemoryMode";
        int memoryNum= messageNumService.isModel(memoryModeName);
        if(memoryNum==0){
            messageNumService.createMemoryModel();
        }
        String hardDiskModeModeName="HardDiskMode";
        int hardNum= messageNumService.isModel(hardDiskModeModeName);
        if(hardNum==0){
            messageNumService.createHardDiskModel();
        }
        messageNumService.trainModel("CpuMode");
        messageNumService.trainModel("MemoryMode");
        messageNumService.trainModel("HardDiskMode");
        messageNumService.trainModel("MsgMode");

        return Response.ok();
    }


//    @PostMapping(value = "/initML")
//    public Result initML(){
//        messageNumService.trainModel("CpuMode");
//        messageNumService.trainModel("MemoryMode");
//        messageNumService.trainModel("HardDiskMode");
//        messageNumService.trainModel("MsgMode");
//        return Response.ok();
//    }

//    @PostMapping(value = "/add")
//    public Result add(MessageNum messageNum){
//        int num = messageNumService.add(messageNum);
//        return Response.ok(num);
//    }

//    @PostMapping(value = "/addPrediction")
//    public Result addPrediction(@RequestBody MessageNum messageNum){
//        int num = messageNumService.addPrediction(messageNum);
//        return Response.ok(num);
//    }
    @ApiOperation(value = "3、Platform service admission configuration forecast", notes = "Enter the corresponding parameters to obtain the server prediction results", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceNum", value = "Number of platform access services", required = true),
            @ApiImplicitParam(name = "messageNum", value = "Number of platform daily flow messages (unit: one million time)", required = false),
            @ApiImplicitParam(name = "outPatientNum", value = "Daily outpatient volume of the hospital", required = true),
            @ApiImplicitParam(name = "mesSaveTime", value = "INumber of days to save messages", required = true)
    })
    @PostMapping(value = "/prediction")
    public Result addPrediction(@RequestParam("serviceNum") int serviceNum,@RequestParam("messageNum") int messageNum,
    @RequestParam("mesSaveTime") int mesSaveTime,@RequestParam("outPatientNum") int outPatientNum){
        MessageNum messageNums=new MessageNum();
        messageNums.setMessageNum(messageNum);
        messageNums.setServiceNum(serviceNum);
        messageNums.setMesSaveTime(mesSaveTime);
        messageNums.setOutPatientNum(outPatientNum);
        int num = messageNumService.addPrediction(messageNums);
        // prediction
        Map<String, Integer> prediction = new HashMap<String, Integer>();
        prediction = messageNumService.prediction();
        String msg= "The prediction server is configured as CPU :"+prediction.get("CpuMode")+ " Cores ,Memory :"+prediction.get("MemoryMode")
                +"G ,Hard disk:"+prediction.get("HardDiskMode") + "G"
                ;
        return Response.ok(msg);
    }

//    @PostMapping(value = "/prediction")
//    public Result prediction(){
//        Map<String, Integer> prediction = new HashMap<String, Integer>();
//        prediction = messageNumService.prediction();
//        String msg= "The prediction server is configured as CPU :"+prediction.get("CpuMode")+ " Cores ,Memory :"+prediction.get("MemoryMode")
//                                            +"G ,Hard disk:"+prediction.get("HardDiskMode") + "G"
//                ;
//        return Response.ok(msg);
//    }

    @ApiOperation(value = "1、 Model data import")
    @PostMapping(value = "/import")
    public Result importFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        if (file.isEmpty()) {
            return Response.error(ResultEnum.FILE_DOES_NOT_EXIST);
        }
        // 1.获取上传文件输入流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用用 hutool 方法读取数据 默认调用第一个sheet
        ExcelReader excelReader = ExcelUtil.getReader(inputStream);
        //从第二行开始获取数据   excelReader.read的结果是一个2纬的list，外层是行，内层是行对应的所有列
        List<List<Object>> read = excelReader.read(2, excelReader.getRowCount());
        List<MessageNum> excels = new ArrayList<>();
        //循环获取的数据
        for (int i = 0; i < read.size(); i++) {
            List list = read.get(i);
            MessageNum excel = new MessageNum();
            excel.setMessageNum(Integer.parseInt(list.get(0).toString()));
            excel.setOutPatientNum(Integer.parseInt(list.get(1).toString()));
            excel.setServiceNum(Integer.parseInt(list.get(2).toString()));
            excel.setMesSaveTime(Integer.parseInt(list.get(3).toString()));
            excel.setCpuCores(Integer.parseInt(list.get(4).toString()));
            excel.setMemorySize(Integer.parseInt(list.get(5).toString()));
            excel.setHardDiskSize(Integer.parseInt(list.get(6).toString()));
            messageNumService.add(excel);
            //excels.add(excel);
        }
        //messageNumService.importFile(excels);
        return Response.ok();
    }


//    @PostMapping(value = "/creatMsgModel")
//    public Result creatMsgModel(){
//        String modeName="MsgMode";
//        int num= messageNumService.isModel(modeName);
//        if(num==0){
//            messageNumService.creatMsgModel();
//        }
//        return Response.ok();
//    }
//
//    @PostMapping(value = "/createCpuModel")
//    public Result createCpuModel(){
//        String modeName="CpuMode";
//        int num= messageNumService.isModel(modeName);
//        if(num==0){
//            messageNumService.createCpuModel();
//        }
//        return Response.ok();
//    }
//
//    @PostMapping(value = "/createMemoryModel")
//    public Result createMemoryModel(){
//        String modeName="MemoryMode";
//        int num= messageNumService.isModel(modeName);
//        if(num==0){
//            messageNumService.createMemoryModel();
//        }
//        return Response.ok();
//    }
//
//    @PostMapping(value = "/createHardDiskModel")
//    public Result createHardDiskModel(){
//        String modeName="HardDiskMode";
//        int num= messageNumService.isModel(modeName);
//        if(num==0){
//            messageNumService.createHardDiskModel();
//        }
//        return Response.ok();
//    }

}

