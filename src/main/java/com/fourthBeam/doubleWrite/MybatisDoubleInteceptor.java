package com.fourthBeam.doubleWrite;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.fourthBeam.api.BO.Employees;
import com.fourthBeam.api.service.impl.LearningServiceImpl;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class MybatisDoubleInteceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MybatisDoubleInteceptor.class);
    public static String DATA_SOURCE_FLAG  = "MYSQL";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        logger.info("into intercept");
        // 获取 MappedStatement 对象
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];

        Object arg = invocation.getArgs()[1];
        String jsonString = JSON.toJSONString(arg);
        logger.info(jsonString);

        if(arg instanceof Employees){
            logger.info("arg instanceof Employees");
        } else if (arg instanceof Map) {
            logger.info("arg instanceof Map");
        }


        // 获取 SQL 类型（INSERT, UPDATE, DELETE 等）
        SqlCommandType sqlCommandType = ms.getSqlCommandType();

        if (sqlCommandType == SqlCommandType.INSERT) {
            logger.info("拦截到 INSERT 操作");
        } else if (sqlCommandType == SqlCommandType.UPDATE) {
            logger.info("拦截到 UPDATE 操作");
        } else if (sqlCommandType == SqlCommandType.DELETE) {
            logger.info("拦截到 DELETE 操作");
        }

//        // 获取 Configuration（MyBatis 配置对象）
//        Configuration configuration = ms.getConfiguration();
//        // 判断该 Configuration 属于哪个 SqlSessionFactory
//        String environmentId = configuration.getEnvironment().getId();
//
//        logger.info("=========" + environmentId);


        // 获取调用栈信息
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String sqlId = ms.getId();
        logger.info("当前 SQL 对应的 Mapper 方法: " + sqlId);

        if("com.fourthBeam.mapper.mysql.EmployeesMapper.testUpate".equals(sqlId)){
            logger.info("============111111111111111111");
            callMapperMethod("com.fourthBeam.mapper.oracle.EmployeesOracleMapper", "testUpate", "firstname1", "lastname1");
        }

        // 调用原始方法
        return invocation.proceed();
    }


    public Object callMapperMethod(String mapperClassName, String methodName, Object... args) throws Exception {
        Class<?> mapperClass = Class.forName(mapperClassName);
        Object mapperBean = SpringContextHolder.getBean(mapperClass);

        // 找匹配参数数量的方法
        Method method = Arrays.stream(mapperBean.getClass().getMethods())
                .filter(m -> m.getName().equals(methodName) && m.getParameterCount() == args.length)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Method not found"));

        return method.invoke(mapperBean, args);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
