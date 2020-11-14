package com.mhyl.performance.appraisal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mhyl.performance.appraisal.beans.AppraisalProjectDTO;
import com.mhyl.performance.appraisal.beans.AppraisalProjectVO;
import com.mhyl.performance.appraisal.domain.entity.AppraisalProject;
import com.mhyl.performance.appraisal.domain.repository.AppraisalProjectRepo;
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

@Api(tags = "绩效考核")
@RestController
@RequestMapping("appraisalProject")
public class AppraisalProjectController {
    @Autowired
    private AppraisalProjectRepo appraisalProjectRepo;

    @ApiOperation("添加绩效考核项目")
    @PostMapping("/save")
    public JsonResult save(@RequestBody AppraisalProjectDTO dto){
        ThrowException.ARG_IS_EMPTY.ifEmpty(dto.getName(), "项目名称");
        QueryWrapper<AppraisalProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.eq("name",dto.getName());
        List<AppraisalProject> list = appraisalProjectRepo.list(queryWrapper);
        if (list.size() > 0){
            return JsonResult.error(500,"存在重复的项目名称：" + list.get(0).getName());
        }
        AppraisalProject appraisalProject = BeanMapper.map(dto, AppraisalProject.class);
        appraisalProjectRepo.save(appraisalProject);
        return JsonResult.success("success");
    }

    @ApiOperation("绩效考核列表")
    @PostMapping("/getList")
    public JsonResult<List<AppraisalProjectVO>> getList(){
        QueryWrapper<AppraisalProject> appraisalProjectQueryWrapper = new QueryWrapper<>();
        appraisalProjectQueryWrapper.select("id,name,create_time,update_time");
        List<AppraisalProject> list = appraisalProjectRepo.list(appraisalProjectQueryWrapper);
        List<AppraisalProjectVO> data = BeanMapper.mapList(list, AppraisalProject.class, AppraisalProjectVO.class);
        return JsonResult.success(data);
    }

}
