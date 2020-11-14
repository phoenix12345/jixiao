package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.HospitalDepartDTO;
import com.mhyl.performance.appraisal.beans.HospitalDepartSaveDTO;
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
    public JsonResult save(@RequestBody HospitalDepartSaveDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "科室名称");
        QueryWrapper<HospitalDepart> hospitalDepartQueryWrapper = new QueryWrapper<>();
        hospitalDepartQueryWrapper.select("id,name,create_time");
        hospitalDepartQueryWrapper.eq("name",dto.getName());
        List<HospitalDepart> list = hospitalDepartRepo.list(hospitalDepartQueryWrapper);
        if (list.size() > 0){
            return JsonResult.error(500,"与其他科室名称重复，请重新输入");
        }
        HospitalDepart hospitalDepart = BeanMapper.map(dto, HospitalDepart.class);
        hospitalDepartRepo.save(hospitalDepart);
        return JsonResult.success("success");
    }

    @ApiOperation("修改科室")
    @PostMapping("/update")
    public JsonResult update(@RequestBody HospitalDepartDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "科室id");
        QueryWrapper<HospitalDepart> hospitalDepartQueryWrapper = new QueryWrapper<>();
        hospitalDepartQueryWrapper.select("name");
        hospitalDepartQueryWrapper.eq("name",dto.getName());
        List list = hospitalDepartRepo.list(hospitalDepartQueryWrapper);
        if (list.size() > 0){
            return JsonResult.error(500,"与其他科室名称重复，请重新输入");
        }
        HospitalDepart hospitalDepart = BeanMapper.map(dto, HospitalDepart.class);
        hospitalDepartRepo.updateById(hospitalDepart);
        return JsonResult.success();
    }

    @ApiOperation("删除科室")
    @GetMapping("/delete")
    public JsonResult delete(@RequestParam String id){
        //删除该科室内的所有员工
        QueryWrapper<HospitalDoctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id");
        queryWrapper.in("depart_id",id);
        hospitalDoctorRepo.remove(queryWrapper);
        //删除科室
        hospitalDepartRepo.removeById(id);
        return JsonResult.success();
    }

    @ApiOperation("根据科室名称查询科室信息")
    @GetMapping("/getByName")
    public JsonResult<Integer> getByName(@RequestParam String name){
        QueryWrapper<HospitalDepart> hospitalDepartQueryWrapper = new QueryWrapper<>();
        hospitalDepartQueryWrapper.select("id,name,create_time");
        hospitalDepartQueryWrapper.eq("name",name);
        List<HospitalDepart> list = hospitalDepartRepo.list(hospitalDepartQueryWrapper);
        return JsonResult.success(list.size());
    }

    @ApiOperation("科室列表")
    @PostMapping("/getList")
    public JsonResult<List<HospitalDepartVO>> getList(){
        QueryWrapper<HospitalDepart> hospitalDepartQueryWrapper = new QueryWrapper<>();
        hospitalDepartQueryWrapper.select("id,name,job_post_rate,depart_rate,create_time,update_time");
        List<HospitalDepart> list = hospitalDepartRepo.list(hospitalDepartQueryWrapper);
        List<HospitalDepartVO> data = BeanMapper.mapList(list, HospitalDepart.class, HospitalDepartVO.class);
        return JsonResult.success(data);
    }
}
