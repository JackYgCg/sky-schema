package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.schema.JSON;
import org.schema.json.ArraySchema;
import org.schema.json.StringSchema;
import org.schema.json.base.Schema;
import org.schema.verify.Verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {

    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\"name\": \"\", \"age\" : 30}";
        Schema schema = JSON.object()
                .attr("name", JSON.string())
                .attr("age", JSON.number().max(20))
                .attr("school", JSON.string())
                        .require("name", "age");
        System.out.println(Verify.verify(json, schema));

//        Schema schema = JSON.array().item(
//                JSON.object()
//                    .attr("name", JSON.string())
//                    .attr("age", JSON.number().max(30))
//                    .attr("school", JSON.string())
//                    .require("name", "age")
//        );
//
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (int i = 0; i < 200; i++){
//            final int o = i;
//            list.add(new HashMap<>(){
//                {
//                    put("name", "张" + o);
//                    put("age", 1 + o);
//                    put("school", "大学" + o);
//                }
//            });
//        }

//        Verify.verify(new ObjectMapper().writeValueAsString(list), schema);
    }

    @Test
    void complexTest(){
        String json = """
                {
                              
                "aaa":{
                "name":8848,
                "google":"ash"
                              
                },
                "bbb":[1,2,3,5]
                
                
                }""";
        Schema schema = JSON.object().attr("aaa",JSON.object().attr("name",JSON.number().min(9966)
                        .error(new RuntimeException("141"))
                )

        ).attr("bbb",JSON.array().sizeBound(141).error(new RuntimeException("喂！")));
        System.out.println(Verify.verify(json, schema));


    }

}
