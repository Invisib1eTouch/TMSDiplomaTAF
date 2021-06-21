package dataObjects.json.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ProductJson {
        @Expose
        @SerializedName("full_name")
        private final String fullName;
        @Expose
        @SerializedName("extended_name")
        private final String extendedName;
        @Expose
        private final String description;
        @Expose
        private final PricesJson prices;
        @Expose
        @SerializedName("html_url")
        private final String productUrl;
}
