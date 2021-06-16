package dataObjects.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ProductsModelJson {
    @Expose
    private final List<Product> products;

    @Data
    static class Product {
        @Expose @SerializedName("full_name")
        private final String fullName;
        @Expose @SerializedName("extended_name")
        private final String extendedName;
        @Expose
        private final String description;
        @Expose
        private final Prices prices;

        @Data
        static class Prices {
            @Expose @SerializedName("price_min")
            private final PriceMin priceMin;

            @Data
            static class PriceMin {
                @Expose
                private final String amount;
            }
        }
    }
}

