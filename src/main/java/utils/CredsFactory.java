package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dataObjects.json.CredsJson;
import dataObjects.json.cart.PositionToDeleteJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CredsFactory {
    protected static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private final ConcurrentHashMap<CredsJson, Boolean> creds;

    public CredsFactory() {
        this.creds = new ConcurrentHashMap<>();
        List<CredsJson> credsList = gson.fromJson(FileHelper.readFromJson("creds.json"), new TypeToken<ArrayList<CredsJson>>() {
        }.getType());

        credsList.forEach(el -> this.creds.put(el, true));
    }

    public CredsJson getFreeCreds(){
        var freeCreds = creds.entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .findFirst()
                .orElseThrow();

        this.creds.put(freeCreds.getKey(), false);
        return freeCreds.getKey();
    }

    public void returnCreds(CredsJson creds){
        this.creds.put(creds, true);
    }
}
