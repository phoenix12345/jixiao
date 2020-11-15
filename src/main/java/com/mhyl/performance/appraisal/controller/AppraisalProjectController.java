package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.AppraisalProjectVO;
import com.mhyl.performance.appraisal.domain.entity.AppraisalProject;
import com.mhyl.performance.appraisal.domain.repository.AppraisalProjectRepo;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "绩效考核")
@RestController
@RequestMapping("appraisalProject")
public class AppraisalProjectController {
    @Autowired
    private AppraisalProjectRepo appraisalProjectRepo;

    @ApiOperation("绩效考核列表")
    @PostMapping("/list")
    public JsonResult<List<AppraisalProjectVO>> list() {
        QueryWrapper<AppraisalProject> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sorted");
        List<AppraisalProject> list = appraisalProjectRepo.list(wrapper);
        List<AppraisalProjectVO> data = BeanMapper.mapList(list, AppraisalProject.class, AppraisalProjectVO.class);
        return JsonResult.success(data);
    }

}
