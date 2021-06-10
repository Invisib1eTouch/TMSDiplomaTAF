package enums;

import core.PropertyReader;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UrlPrefix {
    CATALOG_PREFIX(PropertyReader.getUrlPrefix("catalog.url.prefix")),
    PROFILE_PREFIX(PropertyReader.getUrlPrefix("profile.url.prefix")),
    CART_PREFIX(PropertyReader.getUrlPrefix("cart.url.prefix"));

    @Getter
    private final String value;
}
