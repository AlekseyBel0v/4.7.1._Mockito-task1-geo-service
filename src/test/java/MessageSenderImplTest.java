import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.stream.Stream;

public class MessageSenderImplTest {
    @ParameterizedTest
    @MethodSource("sourceAdd")
    public void testSend(String ip, Country country, String expectedMessage) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(new Location("ok", country, "ok", 1));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(country)).thenReturn(expectedMessage);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        HashMap<String , String> header = new HashMap<>();
        header.put("x-real-ip", ip);
        String message = messageSender.send(header);

        Assertions.assertEquals(expectedMessage, message);
    }

    public static Stream<Arguments> sourceAdd() {
        return Stream.of(
                Arguments.of("172.sdfjdsj", Country.RUSSIA, "Добро пожаловать"),
                Arguments.of("96.....ыва", Country.USA, "Welcome"),
                Arguments.of(".....ыва", Country.USA, "Welcome")
        );
    }
}