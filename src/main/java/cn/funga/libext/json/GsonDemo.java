package cn.funga.libext.json;

import com.google.gson.*;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Gson类库示例
 * Created by FunGa on 16/4/22.
 */
public class GsonDemo {

    public static void main(String[] args) {
        createDemo();


    }

    /**
     * 使用GSON创建一个JSON数据
     */
    public static void createDemo(){
        JsonObject json = new JsonObject();
        // addProperty()方法添加基本类型元素
        json.addProperty("name","FunGa");
        json.addProperty("age",25);

        JsonArray array = new JsonArray();
        array.add("游泳");
        array.add("羽毛球");

        // add()方法添加对象元素
        json.add("hobby",array);

        System.out.println(json.toString());
        System.out.println("-------------------------");
        try {
            parserDemo();
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }

    }

    /**
     * 解析JSON数据
     */
    public static void parserDemo()throws FileNotFoundException{

        JsonParser parser = new JsonParser();
        // 可以直接传入JSON串,这里采用读取JSON文本
        JsonObject json = (JsonObject)parser.parse(new FileReader("gson.json"));

        System.out.println("姓名:"+json.get("name").getAsString());
        System.out.println("年龄:"+json.get("age").getAsInt());

        JsonArray hobbys = json.get("hobby").getAsJsonArray();
        System.out.print("爱好:");
        for(JsonElement hobby : hobbys){
            System.out.print(hobby.getAsString()+" ");
        }
        System.out.println();

        JsonArray skills = json.get("skills").getAsJsonArray();
        System.out.println("技能:");
        for(JsonElement element : skills){
            JsonObject j = element.getAsJsonObject();
            System.out.println("    使用"+j.get("ide").getAsString()+"编写"+j.get("lang").getAsString()+"代码");
        }


    }
}
