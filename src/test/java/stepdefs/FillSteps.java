package stepdefs;

import cucumber.api.java.ru.Тогда;
import io.cucumber.datatable.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import ru.sbtqa.tag.pagefactory.exceptions.PageInitializationException;

import java.util.List;


public class FillSteps {

    private static Logger logger = LoggerFactory.getLogger(FillSteps.class);

    @Тогда("^пользователь заполняет поля данными$")
    public static void fillFields(DataTable dataTable) throws NoSuchMethodException, PageException {

        List<List<String>> table = dataTable.cells();
        logger.info("пользователь заполняет поля данными :");
        for (List m : table) {

            logger.info(m.get(0).toString() + " " + m.get(1).toString());
            PageFactory.getInstance().getCurrentPage().getElementByTitle(m.get(0).toString()).sendKeys(m.get(1).toString());
        }
        logger.info("пользователь заполнил поля");
    }
}