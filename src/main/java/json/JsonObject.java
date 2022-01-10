package json;

import java.util.HashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private HashMap<String, Json> jsonObject;
    public JsonObject(JsonPair... jsonPairs) {
        // ToDo
        this.jsonObject = new HashMap<String, Json>();
        for (JsonPair pair: jsonPairs){
            add(pair);
        }
    }

    @Override
    public String toJson() {
        // ToDo
        String jsonString = "{";
        for (String key : jsonObject.keySet()) {
            if (!jsonString.equals("{")){
                jsonString+=',';
            }
            String keyString = new JsonString(key).toJson();
            if (isPresent(key)) {
                String valueString = jsonObject.get(key).toJson();
                jsonString += keyString + ':' + valueString;
            }
        }
        return jsonString+'}';
    }

    public void add(JsonPair jsonPair) {
        // ToDo
        jsonObject.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        // ToDo
        return jsonObject.get(name);
    }
    public boolean isPresent(String name){
        if (find(name) == null) {
            return false;
        }
        return true;
    }

    public JsonObject projection(String... names) {
        // ToDo
        JsonObject jsonObjectProjection = new JsonObject();
        for (String name: names){
            if (isPresent(name)) {
                JsonPair jsonPair = new JsonPair(name, find(name));
                jsonObjectProjection.add(jsonPair);
            }
        }
//        for (String key : jsonObject.keySet()) {
//            for (String name: names){
//                if (key.equals(name)){
//                    JsonPair jsonPair = new JsonPair(name, find(name));
//                    jsonObjectProjection.add(jsonPair);
//                }
//            }
//        }
        for (String key : jsonObject.keySet()) {
            for (String name: names){
                if (key.equals(name)){
                    JsonPair jsonPair = new JsonPair(name, find(name));
                    jsonObjectProjection.add(jsonPair);
                }
            }
        }
        return jsonObjectProjection;
    }
}
