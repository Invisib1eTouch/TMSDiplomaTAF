package dataObjects.json.products;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class ProductsJson {
    @Expose
    private final List<ProductJson> products;
}