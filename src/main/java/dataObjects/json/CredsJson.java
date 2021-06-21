package dataObjects.json;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class CredsJson {

    @Expose
    private final String login;

    @Expose
    private final String password;
}