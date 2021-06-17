package dataObjects.json.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PositionToDeleteJson {
    @Expose @SerializedName("position_id")
    private final String positionId;
    @Expose @SerializedName("product_id")
    private final String productId;
    @Expose @SerializedName("shop_id")
    private final String shopId;
}
