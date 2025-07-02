import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalizationServiceTest {

    private LocalizationService localizationService;

    @BeforeEach
    void setUp() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    void testLocaleRussiaReturnsRussianText() {
        String message = localizationService.locale(Country.RUSSIA);
        assertEquals("Добрый день!", message);
    }

    @Test
    void testLocaleUSAReturnsEnglishText() {
        String message = localizationService.locale(Country.USA);
        assertEquals("Hello!", message);
    }

    @Test
    void testLocaleOtherReturnsDefaultEnglishText() {
        String message = localizationService.locale(Country.OTHER);
        assertEquals("Hello!", message);
    }
}
