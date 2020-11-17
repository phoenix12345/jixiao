package com.mhyl.performance.appraisal.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.google.common.collect.Maps;
import com.mhyl.performance.appraisal.beans.WorkEquivalentVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExcelService {

    public void exportWorkEquivalentExcel(HttpServletResponse response, String tempPath, List<WorkEquivalentVO> list, String fileName) {
        try {
            //获取excel模板
            TemplateExportParams params = new TemplateExportParams(tempPath);
            //获取所有数据
            Map<String, Object> map = new HashMap<>();
            List<Map<String, String>> mapList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                WorkEquivalentVO vo = list.get(i);
                Map<String, String> ml = new HashMap<>();
                ml.put("appraisalProjectName", vo.getAppraisalProjectName());
                ml.put("sorted", vo.getSorted().toString());
                ml.put("serviceProjectName", vo.getServiceProjectName());
                ml.put("unit", vo.getUnit());
                ml.put("remark", vo.getRemark());
                ml.put("jobContent", vo.getJobContent().toString());
                ml.put("technicalContent", vo.getTechnicalContent().toString());
                ml.put("technicalDifficulty", vo.getTechnicalDifficulty().toString());
                ml.put("jobRisk", vo.getJobRisk().toString());
                ml.put("unitEquivalent", vo.getUnitEquivalent().toString());
                mapList.add(ml);
            }
            map.put("maplist", mapList);
            //执行excel导出
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
            //修改模板内容导出新模板
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
            //取得输出流
            OutputStream out = response.getOutputStream();
            //写入文件
            workbook.write(out);
            //关闭流
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
