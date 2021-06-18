package dataObjects.json.user;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDataJson {
    @Expose
    private String cover;
}
