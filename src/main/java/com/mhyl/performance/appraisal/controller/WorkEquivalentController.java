package com.mhyl.performance.appraisal.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.mhyl.performance.appraisal.beans.WorkEquivalentDTO;
import com.mhyl.performance.appraisal.beans.WorkEquivalentVO;
import com.mhyl.performance.appraisal.exception.ThrowException;
import com.mhyl.performance.appraisal.http.JsonResult;
import com.mhyl.performance.appraisal.service.ExcelService;
import com.mhyl.performance.appraisal.service.WorkEquivalentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "工作当量标准模块")
@RestController
@RequestMapping("/workEquivalent")
public class WorkEquivalentController {

    @Autowired
    private WorkEquivalentService workEquivalentService;
    @Autowired
    private ExcelService excelService;

    @ApiOperation("更新某个考核项目下服务项目的工作当量")
    @PostMapping("/updateListByAppraisalId")
    public JsonResult<String> getListByAppraisalId(@RequestBody List<WorkEquivalentDTO> list){
        ThrowException.ARG_IS_EMPTY.ifEmpty(list,"参数");
        return workEquivalentService.updateListByAppraisalId(list);
    }

    @ApiOperation("选择考核项目批量导出服务项目的工作当量")
    @PostMapping("/exportExcel")
    public JsonResult<String> exportExcel(HttpServletResponse response,String appraisalIds){
        ThrowException.ARG_IS_EMPTY.ifEmpty(appraisalIds,"所属项目ID");
        List<WorkEquivalentVO> data = workEquivalentService.queryDataByAppraisalIds(appraisalIds);
        String tempPath = "templates/excel/workEquivalent.xls";
        excelService.exportWorkEquivalentExcel(response,tempPath,data,"绩效考核当量标准统计表");
        return JsonResult.success();
    }

//    @RestController
//    public class Controller {
//        @GetMapping("/downloadExcel")
//        @ResponseBody
//        public void downLoadFile(HttpServletResponse response) {
//            List<Employee> employees = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                employees.add(new Employee(i + 18, "a" + i));
//            }
//
//            ExcelWriter writer = ExcelUtil.getWriter();
//            writer.addHeaderAlias("name", "姓名");
//            writer.addHeaderAlias("age", "年龄");
//            writer.merge(1, "员工信息表");
//            writer.write(employees, true);
//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//
//            String name = StringUtils.toUtf8String("XXX国际贸易公司");
//
//            response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
//            ServletOutputStream out = null;
//            try {
//                out = response.getOutputStream();
//                writer.flush(out, true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                writer.close();
//            }
//            IoUtil.close(out);
//        }
//
//    }




}
