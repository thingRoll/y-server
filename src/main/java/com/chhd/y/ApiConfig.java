package com.chhd.y;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@EnableWebMvc
class ApiConfig extends WebMvcConfigurationSupport {

    private static final String ADMIN_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlblVpZCI6ImQwZWMwYjkxLWIyYWUtNDg2Ny1hODhiLWVhODZiMTkzM2U3MSIsImlkIjoxLCJleHAiOjE1NDQ1OTc1MTcsInVzZXJuYW1lIjoiYWRtaW4ifQ.xwl-4_lVnrEHQrMQHFmW-2xS5gFJjBOYhVipHkLL0tY";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .build()
                .globalOperationParameters(buildGlobalParams())
                .globalResponseMessage(RequestMethod.GET, buildGlobalMessage())
                .globalResponseMessage(RequestMethod.POST, buildGlobalMessage())
                .apiInfo(buildApiInfo());
    }

    private List<Parameter> buildGlobalParams() {
        List<Parameter> parameterList = new ArrayList<>();
        Parameter tokenParam = new ParameterBuilder()
                .name("token").defaultValue(ADMIN_TOKEN).description("令牌").modelRef(new ModelRef("string")).parameterType("header").build();
        parameterList.add(tokenParam);
        Parameter osParam = new ParameterBuilder()
                .name("os").defaultValue("1").description("平台").modelRef(new ModelRef("string")).parameterType("header").build();
        parameterList.add(osParam);
        Parameter deviceParam = new ParameterBuilder()
                .name("device").defaultValue("api").description("设备").modelRef(new ModelRef("string")).parameterType("header").build();
        parameterList.add(deviceParam);
        return parameterList;
    }

    private List<ResponseMessage> buildGlobalMessage() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(0).message("操作成功").responseModel(new ModelRef("Response")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(1).message("操作失败").responseModel(new ModelRef("Response")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(2).message("请求参数错误").responseModel(new ModelRef("Response")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(3).message("os/device/token任一个header参数不能为空").responseModel(new ModelRef("Response")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(4).message("token过期，请重新登录").responseModel(new ModelRef("Response")).build());
        return responseMessageList;
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("爆炸糖")
                .description("接口文档，在线测试")
//                .termsOfServiceUrl("http://192.168.1.80:8000/swagger-ui.html")
                .version("1.0")
                .build();
    }
}
