/**
 *    Copyright 2006-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;

import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * RenameModelClassPlugin
 *
 * @author Chongming Zhou
 */
public class RenameModelClassPlugin extends PluginAdapter {

    private String modelSuffix;

    @Override
    public boolean validate(List<String> warnings) {
        JavaModelGeneratorConfiguration configuration = context.getJavaModelGeneratorConfiguration();
        this.modelSuffix = configuration.getProperty("modelSuffix");
        return stringHasValue(this.modelSuffix);
    }

    /**
     * 表生成实体类添加自定义后缀
     */
    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String originalType = introspectedTable.getBaseRecordType();
        System.out.println("表对应实体类名添加后缀: " + this.modelSuffix);
        String newType = originalType + this.modelSuffix;
        introspectedTable.setBaseRecordType(newType);
    }

}
