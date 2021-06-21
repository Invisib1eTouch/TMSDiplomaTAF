package dataObjects.json.cart;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class PositionsToDeleteJson {
    @Expose
    private final List<PositionToDeleteJson> positions;
}