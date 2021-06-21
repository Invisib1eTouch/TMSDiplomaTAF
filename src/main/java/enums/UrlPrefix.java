package enums;

import core.PropertyReader;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UrlPrefix {
    CATALOG(PropertyReader.getUrlPrefix("catalog.url.prefix")),
    PROFILE(PropertyReader.getUrlPrefix("profile.url.prefix")),
    CART(PropertyReader.getUrlPrefix("cart.url.prefix")),
    DEFAULT(PropertyReader.getUrlPrefix("default.url.prefix"));

    @Getter
    private final String value;
}