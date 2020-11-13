package com.mhyl.performance.appraisal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author ArchieDing
 * @since 2020/11/02
 */
public class CodeGenerator {

    public static void main(String[] args) {
        run();
    }

    private static GlobalConfig getGlobalConfig(String projectPath) {
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setAuthor("Berger Lan");
        gc.setOpen(false);
        // 是否开启二级缓存
        gc.setEnableCache(false);
        // 是否添加swagger配置
        gc.setSwagger2(true);

		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("%sRepo");
		gc.setServiceImplName("%sRepoImpl");
        return gc;
    }

    public static void run() {
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        GlobalConfig gc = getGlobalConfig(projectPath);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = getDataSourceConfig();
        mpg.setDataSource(dsc);

        // 包名配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = getStrategyConfig();
        mpg.setStrategy(strategy);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        templateConfig.setController(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        mpg.execute();
    }

    private static StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否为lombok模型
        strategy.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // 生成 @RestController 控制器
        strategy.setRestControllerStyle(false);

        if ("N".equalsIgnoreCase(scanner("是否生成全部表(Y/N)"))) {
            // 需要生成的表
            strategy.setInclude(scanner("表名，多个英文逗号分割").trim().split(","));
        } else {
            strategy.setExclude("flyway_schema_history");
        }
        strategy.setLogicDeleteFieldName("deleted");
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("deleted", FieldFill.INSERT));
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_time", FieldFill.UPDATE));
        strategy.setTableFillList(tableFillList);
        return strategy;
    }

    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        Properties properties = new Properties();
        try {
            File file = new ClassPathResource("/MyBatisPlusGenerator.properties").getFile();
            properties.load(new FileInputStream(file));
            dsc.setUrl(properties.getProperty("jdbc.url"));
            dsc.setSchemaName("databaseName");
            dsc.setDriverName(properties.getProperty("jdbc.driver"));
            dsc.setUsername(properties.getProperty("jdbc.username"));
            dsc.setPassword(properties.getProperty("jdbc.password"));
        } catch (IOException e) {
            throw new RuntimeException("数据源配置错误", e);
        }
        return dsc;
    }

    private static PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.mhyl.performance.appraisal.domain");
        pc.setService("repository");
        pc.setServiceImpl("repository.impl");
        return pc;
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!StringUtils.isBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
