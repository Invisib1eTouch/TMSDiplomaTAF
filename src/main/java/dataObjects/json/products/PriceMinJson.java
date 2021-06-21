package dataObjects.json.products;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class PriceMinJson {
    @Expose
    private final String amount;
}