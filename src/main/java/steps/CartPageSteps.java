package steps;

import baseEntities.BaseStep;
import pages.CartPage;

public class CartPageSteps extends BaseStep<CartPage> {
    /**
     * @param openPageByUrl - if true page will be opened by url
     * @implNote - if page can not be opened by url, false value hardcoded to super constructor
     * e.g.
     * public ProfileMenuSteps() {
     * super(false);
     * }
     */
    public CartPageSteps(Boolean openPageByUrl) {
        super(openPageByUrl);
    }
}
