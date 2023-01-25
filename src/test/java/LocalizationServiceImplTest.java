import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    @ParameterizedTest
    @CsvSource({"RUSSIA, Добро пожаловать",
                "USA, Welcome"})
    public void testLocale(Country country, String expectedMsg) {

        String msg = new LocalizationServiceImpl().locale(country);

        Assertions.assertEquals(expectedMsg, msg);
    }
}
