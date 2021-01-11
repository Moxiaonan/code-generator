package com.xy.code.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author moxiaonan
 * @description
 * @date 2020/12/10
 */
public class CodeGenerator {
    /**
     * 项目地址
     */
    public static final String projectPath = "/Users/xiaonanmo/IdeaProjects/platform-supply-chain-manage-system/platform-supply-chain-service/platform-supply-chain-admin-service";
    public static final String dbUrl = "jdbc:mysql://localhost:3306/supply_chain?useUnicode=true&useSSL=false&characterEncoding=utf8";
    public static final String dbDriverName = "com.mysql.cj.jdbc.Driver";
    public static final String dbUsername = "root";
    public static final String dbPassword = "666666";
    public static final String author = "moxiaonan";

    public static final String sourceDir = "/src/main/java";
    public static final String serviceName = "%sService";

    public static final String parentPackage = "cn.codemao.service.platform.supplychain.admin";
    public static final String controllerPackage = "controller.api";
    public static final String entityPackage = "domain.entity";

    public static final String mapperXmlPath = "/src/main/resources/db/mysql/mapper";

    public static final String superEntityClass = "cn.codemao.cloud.core.common.domain.entity.StandardBaseEntity";
    public static final String[] superEntityColumns = new String[]{"create_by","update_by","create_time","update_time"};

    public static final String tablePrefix = "tbl_";


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(projectPath + sourceDir);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setServiceName(serviceName);
        gc.setIdType(IdType.AUTO);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        dsc.setUrl(dbUrl);
        dsc.setDriverName(dbDriverName);
        dsc.setUsername(dbUsername);
        dsc.setPassword(dbPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage);
        pc.setController(controllerPackage);
        pc.setEntity(entityPackage);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/vm/sup/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + mapperXmlPath + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setController("/vm/sup/controller.java");
        templateConfig.setService("/vm/sup/service.java");
        templateConfig.setServiceImpl("/vm/sup/serviceImpl.java");
        templateConfig.setMapper("/vm/sup/mapper.java");
        templateConfig.setEntity("/vm/sup/entity.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(superEntityClass);
        strategy.setEntityLombokModel(true);
        strategy.setEntityBuilderModel(false);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns(superEntityColumns);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityColumnConstant(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setTablePrefix(tablePrefix);
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}

