import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeoServiceTest {

    private GeoService geoService;

    @BeforeEach
    void setUp() {
        geoService = new GeoServiceImpl();
    }

    @Test
    void testRussianIpReturnsRussiaLocation() {
        String ip = "172.20.10.5";
        Location location = geoService.byIp(ip);
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Moscow", location.getCity());
    }

    @Test
    void testAmericanIpReturnsUSALocation() {
        String ip = "96.45.67.89";
        Location location = geoService.byIp(ip);
        assertEquals(Country.USA, location.getCountry());
        assertEquals("New York", location.getCity());
    }

    @Test
    void testOtherIpReturnsOtherLocation() {
        String ip = "8.8.8.8";
        Location location = geoService.byIp(ip);
        assertEquals(Country.OTHER, location.getCountry());
    }
}