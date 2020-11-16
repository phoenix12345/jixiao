package com.mhyl.performance.appraisal.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.HospitalDoctorDTO;
import com.mhyl.performance.appraisal.beans.HospitalDoctorExcel;
import com.mhyl.performance.appraisal.beans.HospitalDoctorSaveDTO;
import com.mhyl.performance.appraisal.beans.HospitalDoctorVO;
import com.mhyl.performance.appraisal.domain.entity.HospitalDepart;
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
import java.time.LocalDateTime;
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
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "科室名称");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getIdCard(), "身份证号码");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobTitle(), "职称");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobPost(), "职务");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getDepartId(), "所在科室");
        HospitalDoctor hospitalDoctor = BeanMapper.map(dto, HospitalDoctor.class);
        //身份证号码重复提示
        QueryWrapper<HospitalDoctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id_card");
        queryWrapper.eq("id_card",dto.getIdCard());
        List<HospitalDoctor> list = hospitalDoctorRepo.list(queryWrapper);
        if (list.size() > 0){
            return JsonResult.error(500,"身份证号码与已有员工重复，请更改后提交");
        }
        //重名提示
        queryWrapper.clear();
        queryWrapper.select("name");
        queryWrapper.eq("name",dto.getName());
        HospitalDoctor one = hospitalDoctorRepo.getOne(queryWrapper);
        if (one != null){
            return JsonResult.error(500,"重复人员名称：" + one.getName());
        }
        hospitalDoctor.setCreateTime(System.currentTimeMillis());
        hospitalDoctor.setSorted(0);
        if (dto.getJobPost().equals("无") && dto.getJobTitle().equals("无")){
            hospitalDoctor.setSorted(3);
        }
        if ((!dto.getJobPost().equals("无")) && dto.getJobTitle().equals("无")){
            hospitalDoctor.setSorted(1);
        }
        if (dto.getJobPost().equals("无") && (!dto.getJobTitle().equals("无"))){
            hospitalDoctor.setSorted(2);
        }
        hospitalDoctorRepo.save(hospitalDoctor);
        return JsonResult.success("success");
    }

    @ApiOperation("修改医生信息")
    @PostMapping("/update")
    public JsonResult<String> update(@RequestBody  HospitalDoctorDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "医生id");
        HospitalDoctor hospitalDoctor = BeanMapper.map(dto, HospitalDoctor.class);
        //身份证号码重复提示
        QueryWrapper<HospitalDoctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id_card");
        queryWrapper.eq("id_card",dto.getIdCard());
        List<HospitalDoctor> list = hospitalDoctorRepo.list(queryWrapper);
        if (list.size() > 0){
            return JsonResult.error(500,"身份证号码与已有员工重复，请更改后提交");
        }
        //重名提示
        queryWrapper.clear();
        queryWrapper.select("name");
        queryWrapper.eq("name",dto.getName());
        HospitalDoctor one = hospitalDoctorRepo.getOne(queryWrapper);
        if (one != null){
            return JsonResult.error(500,"重复人员名称：" + one.getName());
        }
        hospitalDoctor.setCreateTime(System.currentTimeMillis());
        hospitalDoctor.setSorted(0);
        if (dto.getJobPost().equals("无") && dto.getJobTitle().equals("无")){
            hospitalDoctor.setSorted(3);
        }
        if ((!dto.getJobPost().equals("无")) && dto.getJobTitle().equals("无")){
            hospitalDoctor.setSorted(1);
        }
        if (dto.getJobPost().equals("无") && (!dto.getJobTitle().equals("无"))){
            hospitalDoctor.setSorted(2);
        }
        hospitalDoctorRepo.updateById(hospitalDoctor);
        return JsonResult.success("success");
    }

    @ApiOperation("获取当前科室医生列表")
    @GetMapping("/getList")
    public JsonResult<List<HospitalDoctorVO>> getList(@RequestParam  String departId){
        ThrowException.ARG_IS_EMPTY.ifEmpty(departId, "科室id");
        QueryWrapper<HospitalDoctor> hospitalDoctorQueryWrapper = new QueryWrapper<>();
        hospitalDoctorQueryWrapper.select("id,name,id_card,job_title,job_post,depart_id");
        hospitalDoctorQueryWrapper.eq("depart_id",departId);
        //优先级排序
        hospitalDoctorQueryWrapper.orderByAsc("sorted","create_time");
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
        List<HospitalDoctor> hospitalDoctorList = new ArrayList<>();
        QueryWrapper<HospitalDepart> queryWrapper = new QueryWrapper<>();
        //得到excel表格中所在科室字段
        for (HospitalDoctorExcel hospitalDoctorExcel : result) {
            ThrowException.ARG_IS_EMPTY.ifEmpty(hospitalDoctorExcel.getDepartId(), "所在科室");
            ThrowException.ARG_IS_EMPTY.ifEmpty(hospitalDoctorExcel.getIdCard(), "身份证号码");
            ThrowException.ARG_IS_EMPTY.ifEmpty(hospitalDoctorExcel.getName(), "姓名");
            HospitalDoctor hospitalDoctor = new HospitalDoctor();
            //检查填写科室是否存在
            queryWrapper.select("id");
            queryWrapper.eq("name",hospitalDoctorExcel.getDepartId());
            HospitalDepart one = hospitalDepartRepo.getOne(queryWrapper);
            if (one == null){
                return JsonResult.error(500,"系统检测到您提交的表格模板中出现重名情况，系统建议在其名称后面添加数字，具体内容如下表所示，点击确认将此名称录入系统");
            }else {
                hospitalDoctor.setDepartId(one.getId());
            }
            hospitalDoctor.setCreateTime(System.currentTimeMillis());
            hospitalDoctor.setSorted(0);
            if (hospitalDoctorExcel.getJobPost().equals("无") && hospitalDoctorExcel.getJobTitle().equals("无")){
                hospitalDoctor.setSorted(3);
            }
            if ((!hospitalDoctorExcel.getJobPost().equals("无")) && hospitalDoctorExcel.getJobTitle().equals("无")){
                hospitalDoctor.setSorted(1);
            }
            if (hospitalDoctorExcel.getJobPost().equals("无") && (!hospitalDoctorExcel.getJobTitle().equals("无"))){
                hospitalDoctor.setSorted(2);
            }
            hospitalDoctor.setIdCard(hospitalDoctorExcel.getIdCard());
            hospitalDoctor.setDepartRate(hospitalDoctorExcel.getDepartRate());
            hospitalDoctor.setJobPost(hospitalDoctorExcel.getJobPost());
            hospitalDoctor.setJobPostRate(hospitalDoctorExcel.getJobPostRate());
            hospitalDoctor.setJobTitle(hospitalDoctorExcel.getJobTitle());
            hospitalDoctor.setName(hospitalDoctorExcel.getName());
            hospitalDoctor.setRemark(hospitalDoctorExcel.getRemark());
            hospitalDoctorList.add(hospitalDoctor);
        }
        //将excel中数据插入数据库
        Collection collections = new ArrayList<>();
        collections.addAll(hospitalDoctorList);
        hospitalDoctorRepo.saveBatch(collections);
        return JsonResult.success("批量导入成功");
    }

}
