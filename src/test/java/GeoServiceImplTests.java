import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTests {
    @ParameterizedTest
    @CsvSource({"172.oooo, RUSSIA",
                "96.jjjjj, USA"})
    public void testByIp(String ip, Country expectedCountry) {
        Country country = new GeoServiceImpl().byIp(ip).getCountry();

        Assertions.assertEquals(expectedCountry, country);
    }
}