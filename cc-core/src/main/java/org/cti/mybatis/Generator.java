package org.cti.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2020/10/20
 *
 * @see <a href="http://www.mybatis.org/generator/running/runningWithJava.html">Running MyBatis Generator With Java</a>
 *
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>(2);
        ConfigurationParser cp = new ConfigurationParser(warnings);

        File configFile = new File("generatorConfig.xml");
        Configuration config = cp.parseConfiguration(configFile);

        DefaultShellCallback callback = new DefaultShellCallback(true);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
