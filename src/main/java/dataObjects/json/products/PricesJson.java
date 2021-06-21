package dataObjects.json.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PricesJson {
    @Expose
    @SerializedName("price_min")
    private final PriceMinJson priceMin;
}