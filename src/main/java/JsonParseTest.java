import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class JsonParseTest {

  private static final String filePath = "emaitza.json";

  public static void main(String[] args) {

    Gson gson = new Gson();
    String content = "";
    try {
      content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filePath).getFile())));
      JsonElement jsonElement = new JsonParser().parse(content);

      JsonObject jsonObject = jsonElement.getAsJsonObject();
      jsonObject = jsonObject.getAsJsonObject("result");
      System.out.println(jsonObject);
      JsonArray jarray = jsonObject.getAsJsonArray("tags");

      for (int i = 0; i < jarray.size(); i++) {
        jsonObject = jarray.get(i).getAsJsonObject();
        Integer confidence = jsonObject.get("confidence").getAsInt();
        jsonObject = jsonObject.getAsJsonObject("tag");
        String en_tag = jsonObject.get("en").getAsString();

        System.out.println(confidence + " " + en_tag);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}