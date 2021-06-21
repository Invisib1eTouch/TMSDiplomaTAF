package baseEntities;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * @param <Page> - generics used to connect step with corresponding page and get access to page methods so that
 *               incorrect methods of other pages con not be used in steps
 */
@Slf4j
public abstract class BaseStep<Page extends BasePage> {
    //    Instance of the page
    protected Page page;

    /**
     * @param openPageByUrl - if true page will be opened by url
     * @implNote - if page can not be opened by url, false value hardcoded to super constructor e.g. public
     * ProfileMenuSteps() { super(false); }
     */
    public BaseStep(Boolean openPageByUrl) {
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
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public Page getPageInstance() {
        if (this.page == null) {
//            Defining page class
            Class<Page> pageClass =
                    (Class<Page>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            try {
//                Create page instance based on defined class
                this.page = pageClass.getConstructor().newInstance();
            } catch (Exception e) {
                var errMes = "Page instance was not initialised:" + e.getMessage();
                log.error(errMes);
                throw new Exception(errMes);
            }
        }
        return this.page;
    }

    /**
     * @param callerStepsClass - steps class, that calls method; necessary to know to maintain invocation chain
     * @param <Steps>          - method caller steps class type
     * @return - instance of Steps class from which method was invoked
     */

    @Step("Open page.")
    public <Steps extends BaseStep<Page>> Steps openPage(Class<Steps> callerStepsClass) {
        this.page.open();
        return this.getStepsObjectInstance(callerStepsClass);
    }

    /**
     * @param stepsClassType steps class
     * @param <Steps>        - steps class type
     * @return - instance of Steps class
     */
    @SneakyThrows
    protected <Steps extends BaseStep<Page>> Steps getStepsObjectInstance(Class<Steps> stepsClassType) {
        try {
            return stepsClassType.getConstructor(Boolean.class).newInstance(false);
        } catch (Exception e) {
            var errMes = String.format("Couldn't instantiate %s class instance. \nDetailed message: \n%s",
                    stepsClassType.getSimpleName(), e.getMessage());
            log.error(errMes);
            throw new Exception(errMes);
        }
    }
}