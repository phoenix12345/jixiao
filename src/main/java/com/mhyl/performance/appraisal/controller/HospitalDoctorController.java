package com.mhyl.performance.appraisal.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.HospitalDoctorDTO;
import com.mhyl.performance.appraisal.beans.HospitalDoctorExcel;
import com.mhyl.performance.appraisal.beans.HospitalDoctorSaveDTO;
import com.mhyl.performance.appraisal.beans.HospitalDoctorVO;
import com.mhyl.performance.appraisal.domain.entity.HospitalDoctor;
import com.mhyl.performance.appraisal.domain.repository.HospitalDepartRepo;
import com.mhyl.performance.appraisal.domain.repository.HospitalDoctorRepo;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Api(tags = "医生管理")
@RestController
@RequestMapping("hospitalDoctor")
public class HospitalDoctorController {
    @Autowired
    private HospitalDoctorRepo hospitalDoctorRepo;
    @Autowired
    private HospitalDepartRepo hospitalDepartRepo;

    @ApiOperation("添加员工到对应科室")
    @PostMapping("/save")
    public JsonResult<String> save(@RequestBody HospitalDoctorSaveDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "医生姓名");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getIdCard(), "身份证号码");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobTitle(), "职称");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobPost(), "职务");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getDepartId(), "所在科室");
        HospitalDoctor hospitalDoctor = BeanMapper.map(dto, HospitalDoctor.class);
        //身份证号码重复提示
        checkDoctorIdCard(dto.getIdCard());
        //重名提示
        checkDoctorName(dto.getDepartId(), dto.getName());
        setSorted(hospitalDoctor);
        hospitalDoctorRepo.save(hospitalDoctor);
        return JsonResult.success("success");
    }

    private void setSorted(HospitalDoctor hospitalDoctor) {
        if (!hospitalDoctor.getJobPost().equals("无") && !hospitalDoctor.getJobTitle().equals("无")) {
            hospitalDoctor.setSorted(0);
            return;
        }

        if ((!hospitalDoctor.getJobPost().equals("无"))) {
            hospitalDoctor.setSorted(1);
            return;
        }
        if (!hospitalDoctor.getJobTitle().equals("无")) {
            hospitalDoctor.setSorted(2);
            return;
        }
        hospitalDoctor.setSorted(3);
    }

    private void checkDoctorIdCard(String idCard) {
        QueryWrapper<HospitalDoctor> wrapper = new QueryWrapper<>();
        wrapper.eq("id_card", idCard);
        int count = hospitalDoctorRepo.count(wrapper);
        ThrowException.DOCTOR_NAME_DUPLICATE.ifNotEquals(count, 0);
    }

    private void checkDoctorName(Long departId, String name) {
        QueryWrapper<HospitalDoctor> wrapper = new QueryWrapper<>();
        wrapper.eq("depart_id", departId);
        wrapper.eq("name", name);
        int count = hospitalDoctorRepo.count(wrapper);
        ThrowException.DOCTOR_DUPLICATE.ifNotEquals(count, 0, name);
    }


    @ApiOperation("修改医生信息")
    @PostMapping("/update")
    public JsonResult<String> update(@RequestBody  HospitalDoctorDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "医生id");
        HospitalDoctor hospitalDoctor = BeanMapper.map(dto, HospitalDoctor.class);
        //身份证号码重复提示
        checkDoctorIdCard(dto.getIdCard());
        //重名提示
        checkDoctorName(dto.getDepartId(), dto.getName());
        setSorted(hospitalDoctor);
        hospitalDoctorRepo.updateById(hospitalDoctor);
        return JsonResult.success("success");
    }

    @ApiOperation("获取当前科室医生列表")
    @PostMapping("/list")
    public JsonResult<List<HospitalDoctorVO>> list(@RequestParam String departId) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(departId, "科室id");
        QueryWrapper<HospitalDoctor> hospitalDoctorQueryWrapper = new QueryWrapper<>();
        hospitalDoctorQueryWrapper.eq("depart_id",departId);
        //优先级排序
        hospitalDoctorQueryWrapper.orderByAsc("sorted", "create_time");
        List<HospitalDoctor> list = hospitalDoctorRepo.list(hospitalDoctorQueryWrapper);
        List<HospitalDoctorVO> data = BeanMapper.mapList(list, HospitalDoctor.class, HospitalDoctorVO.class);
        return JsonResult.success(data);
    }

    @ApiOperation("批量导入医生")
    @PostMapping("/import")
    public JsonResult<String> importDoctors(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        ImportParams params = new ImportParams();
        //excel有两个标题栏
        params.setTitleRows(2);
        //excel有一个表头
        params.setHeadRows(1);
        List<HospitalDoctorExcel> result = ExcelImportUtil.importExcel(multipartFile.getInputStream(),HospitalDoctorExcel.class, params);
        //得到excel表格中所在科室字段
        for (HospitalDoctorExcel hospitalDoctorExcel : result) {
            ThrowException.ARG_IS_EMPTY.ifEmpty(hospitalDoctorExcel.getDepartId(), "所在科室");
            ThrowException.ARG_IS_EMPTY.ifEmpty(hospitalDoctorExcel.getIdCard(), "身份证号码");
            ThrowException.ARG_IS_EMPTY.ifEmpty(hospitalDoctorExcel.getName(), "姓名");
            hospitalDoctorExcel.setSorted(0);
            if (hospitalDoctorExcel.getJobPost().equals("无") && hospitalDoctorExcel.getJobTitle().equals("无")){
                hospitalDoctorExcel.setSorted(3);
            }
            if ((!hospitalDoctorExcel.getJobPost().equals("无")) && hospitalDoctorExcel.getJobTitle().equals("无")){
                hospitalDoctorExcel.setSorted(1);
            }
            if (hospitalDoctorExcel.getJobPost().equals("无") && (!hospitalDoctorExcel.getJobTitle().equals("无"))){
                hospitalDoctorExcel.setSorted(2);
            }
        }
        //检测填写的科室是否存在
//        QueryWrapper<HospitalDepart> queryWrapper = new QueryWrapper<>();
//        for (String s : departIdList) {
//            queryWrapper.select("id,name");
//            queryWrapper.eq("name", s);
//            HospitalDepart departName = hospitalDepartRepo.getOne(queryWrapper);
//            if (departName == null){
//                return JsonResult.error(500,"所在科室不存在");
//            }
//        }
//        List<HospitalDoctor> hospitalDoctors = BeanMapper.mapList(result, HospitalDoctorExcel.class, HospitalDoctor.class);
        //将excel中数据插入数据库
        Collection collections = new ArrayList<>();
        collections.addAll(result);
        hospitalDoctorRepo.saveBatch(collections);
        System.out.println(JSONUtil.toJsonStr(result));
        return JsonResult.success("批量导入成功");
    }

}
