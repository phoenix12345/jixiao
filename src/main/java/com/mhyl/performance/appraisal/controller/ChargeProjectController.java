package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.ChargeProjectRateDTO;
import com.mhyl.performance.appraisal.beans.ChargeProjectSaveDTO;
import com.mhyl.performance.appraisal.beans.ChargeProjectUpdateDTO;
import com.mhyl.performance.appraisal.beans.ChargeProjectVO;
import com.mhyl.performance.appraisal.domain.entity.ChargeProject;
import com.mhyl.performance.appraisal.domain.repository.ChargeProjectRepo;
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

@Api(tags = "收费项目")
@RestController
@RequestMapping("chargeProject")
public class ChargeProjectController {
    @Autowired
    private ChargeProjectRepo chargeProjectRepo;

    @ApiOperation("添加收费项目")
    @PostMapping("/save")
    public JsonResult<String> save(@RequestBody ChargeProjectSaveDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "项目名称");
        checkChargeName(dto.getName());
        ChargeProject chargeProject = BeanMapper.map(dto, ChargeProject.class);
        chargeProjectRepo.save(chargeProject);
        return JsonResult.success("success");
    }

    private void checkChargeName(String name) {
        QueryWrapper<ChargeProject> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        int count = chargeProjectRepo.count(wrapper);
        ThrowException.CHARGE_DUPLICATE.ifNotEquals(count, 0);
    }

    @ApiOperation("修改收费项目")
    @PostMapping("/update")
    public JsonResult<String> update(@RequestBody ChargeProjectUpdateDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "项目id");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "项目名称");
        checkChargeName(dto.getName());
        ChargeProject chargeProject = BeanMapper.map(dto, ChargeProject.class);
        chargeProjectRepo.updateById(chargeProject);
        return JsonResult.success("success");
    }

    @ApiOperation("修改收费项目系数")
    @PostMapping("/updateRate")
    public JsonResult<String> updateRate(@RequestBody ChargeProjectRateDTO dto) {
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getId(), "项目id");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getMedicalCare(), "医疗");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getNursing(), "护理");
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getExecutiveDepart(), "执行科室");
        ChargeProject chargeProject = BeanMapper.map(dto, ChargeProject.class);
        chargeProjectRepo.updateById(chargeProject);
        return JsonResult.success("success");
    }

    @ApiOperation("获取收费项目列表")
    @PostMapping("/list")
    public JsonResult list() {
        List<ChargeProject> list = chargeProjectRepo.list();
        List<ChargeProjectVO> data = BeanMapper.mapList(list, ChargeProject.class, ChargeProjectVO.class);
        return JsonResult.success(data);
    }
}
