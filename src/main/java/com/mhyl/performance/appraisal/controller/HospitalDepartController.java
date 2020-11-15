package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.HospitalDepartRateDTO;
import com.mhyl.performance.appraisal.beans.HospitalDepartSaveDTO;
import com.mhyl.performance.appraisal.beans.HospitalDepartUpdateDTO;
import com.mhyl.performance.appraisal.beans.HospitalDepartVO;
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

import java.util.List;


@Api(tags = "医院科室")
@RestController
@RequestMapping("hospitalDepart")
public class HospitalDepartController {
    @Autowired
    private HospitalDepartRepo hospitalDepartRepo;
    @Autowired
    private HospitalDoctorRepo hospitalDoctorRepo;

    @ApiOperation("增加科室")
    @PostMapping("/save")
    public JsonResult save(@RequestBody HospitalDepartSaveDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "科室名称");
        checkDepartName(dto.getName());
        HospitalDepart hospitalDepart = BeanMapper.map(dto, HospitalDepart.class);
        hospitalDepartRepo.save(hospitalDepart);
        return JsonResult.success("success");
    }

    private void checkDepartName(String name) {
        QueryWrapper<HospitalDepart> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        int count = hospitalDepartRepo.count(wrapper);
        ThrowException.DEPART_DUPLICATE.ifNotEquals(count, 0);
    }

    @ApiOperation("修改科室")
    @PostMapping("/update")
    public JsonResult update(@RequestBody HospitalDepartUpdateDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "科室id");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "科室名称");
        checkDepartName(dto.getName());
        HospitalDepart hospitalDepart = BeanMapper.map(dto, HospitalDepart.class);
        hospitalDepartRepo.updateById(hospitalDepart);
        return JsonResult.success();
    }

    @ApiOperation("修改科室系数")
    @PostMapping("/updateRate")
    public JsonResult updateRate(@RequestBody HospitalDepartRateDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "科室id");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getJobPostRate(), "岗位系数总量");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getDepartRate(), "科室系数总量");
        HospitalDepart hospitalDepart = BeanMapper.map(dto, HospitalDepart.class);
        hospitalDepartRepo.updateById(hospitalDepart);
        return JsonResult.success();
    }

    @ApiOperation("删除科室")
    @GetMapping("/delete")
    public JsonResult delete(@RequestParam String id) {
        //删除该科室内的所有员工
        QueryWrapper<HospitalDoctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("depart_id", id);
        hospitalDoctorRepo.remove(queryWrapper);
        //删除科室
        hospitalDepartRepo.removeById(id);
        return JsonResult.success();
    }

    @ApiOperation("科室列表")
    @PostMapping("/list")
    public JsonResult<List<HospitalDepartVO>> list() {
        List<HospitalDepart> list = hospitalDepartRepo.list();
        List<HospitalDepartVO> data = BeanMapper.mapList(list, HospitalDepart.class, HospitalDepartVO.class);
        return JsonResult.success(data);
    }
}
