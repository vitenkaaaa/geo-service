import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import javax.xml.stream.Location;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MessageSenderTest {

    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSender messageSender;

    @BeforeEach
    void setUp() {
        geoService = mock(GeoService.class);
        localizationService = mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    void testRussianIpSendsRussianMessage() {
        String ip = "172.16.0.1"; // российский IP
        Location location = new Location("Moscow", Country.RUSSIA, null, 0);
        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добрый день!");

        String message = messageSender.send(ip);

        assertEquals("Добрый день!", message);
        verify(geoService).byIp(ip);
        verify(localizationService).locale(Country.RUSSIA);
    }

    @Test
    void testAmericanIpSendsEnglishMessage() {
        String ip = "96.123.45.67"; // американский IP
        Location location = new Location("New York", Country.USA, null, 0);
        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.USA)).thenReturn("Hello!");

        String message = messageSender.send(ip);

        assertEquals("Hello!", message);
        verify(geoService).byIp(ip);
        verify(localizationService).locale(Country.USA);
    }

    @Test
    void testOtherIpSendsEnglishMessage() {
        String ip = "8.8.8.8";
        Location location = new Location(null, Country.OTHER, null, 0);
        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.OTHER)).thenReturn("Hello!");

        String message = messageSender.send(ip);

        assertEquals("Hello!", message);
        verify(geoService).byIp(ip);
        verify(localizationService).locale(Country.OTHER);
    }
}
