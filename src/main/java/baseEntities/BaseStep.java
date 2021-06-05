package baseEntities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * @param <T> - generics used to connect step with corresponding page and get access to page methods
 *            so that incorrect methods of other pages con not be used in steps
 */
public abstract class BaseStep<T extends BasePage> {
    //    Instance of the page
    protected T page;

    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    public BaseStep(boolean openPageByUrl) {
//        Set page instance
        this.getPageInstance();

        if (openPageByUrl) {
            this.page.open();
        }

        this.page.verifyCorrectPageOpened();
    }

    /**
     * @return page instance
     */
    @SuppressWarnings("unchecked")
    public T getPageInstance() {
        if (this.page == null) {
//            Defining page class
            Class<T> pageClass =
                    (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            try {
//                Create page instance based on defined class
                this.page = pageClass.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException("Page instance was not initialised" + e.getMessage());
            }
        }
        return this.page;
    }

    /**
     * @return - step where method is implemented
     * return type has to be changed during implementation to corresponding step class to save chain of invocation
     * e.g. from:
     * public BaseStep<T> openPage();
     * to:
     * public LoginPageSteps openPage();
     */
    public abstract BaseStep<T> openPage();
}