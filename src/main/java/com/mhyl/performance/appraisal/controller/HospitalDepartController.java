package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.HospitalDepartDTO;
import com.mhyl.performance.appraisal.beans.HospitalDepartVO;
import com.mhyl.performance.appraisal.domain.entity.HospitalDepart;
import com.mhyl.performance.appraisal.domain.repository.HospitalDepartRepo;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Api(tags = "医院科室")
@RestController
@RequestMapping("hospitalDepart")
public class HospitalDepartController {
    @Autowired
    private HospitalDepartRepo hospitalDepartRepo;

    @ApiOperation("增加科室")
    @PostMapping("/save")
    public JsonResult save(@RequestBody HospitalDepartDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "科室名称");
        HospitalDepart hospitalDepart = BeanMapper.map(dto, HospitalDepart.class);
        hospitalDepartRepo.save(hospitalDepart);
        return JsonResult.success("success");
    }

    @ApiOperation("更新科室")
    @PostMapping("/update")
    public JsonResult update(@RequestBody HospitalDepartDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "科室id");
        HospitalDepart hospitalDepart = BeanMapper.map(dto, HospitalDepart.class);
        hospitalDepartRepo.updateById(hospitalDepart);
        return JsonResult.success();
    }

    @ApiOperation("删除科室")
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody String id){
        hospitalDepartRepo.removeById(id);
        return JsonResult.success();
    }

    @ApiOperation("科室列表")
    @PostMapping("/getList")
    public JsonResult getList(){
        QueryWrapper<HospitalDepart> hospitalDepartQueryWrapper = new QueryWrapper<>();
        hospitalDepartQueryWrapper.select("id,name,job_post_rate,depart_rate,create_time,update_time");
        List<HospitalDepart> list = hospitalDepartRepo.list(hospitalDepartQueryWrapper);
        List<HospitalDepartVO> data = BeanMapper.mapList(list, HospitalDepart.class, HospitalDepartVO.class);
        return JsonResult.success(data);
    }
}
