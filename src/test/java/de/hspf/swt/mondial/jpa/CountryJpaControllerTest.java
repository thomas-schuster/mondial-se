package de.hspf.swt.mondial.jpa;

import de.hspf.swt.mondial.dao.Country;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ThomasSchuster
 */
public class CountryJpaControllerTest {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private CountryJpaController instance;

    public CountryJpaControllerTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new CountryJpaController();
    }

    @AfterEach
    public void tearDown() {
        instance.closeConnection();
    }

    @Test
    public void testGetCountries() {
        LOGGER.log(Level.INFO, "getCountries");

        Collection<Country> result = instance.getCountries();

        LOGGER.log(Level.INFO, "-----------------------------");
        LOGGER.log(Level.INFO, "List of retrieved countries: ");
        result.forEach((country) -> {
            LOGGER.log(Level.INFO, country.getName());
        });

        assertTrue(244 == result.size());

    }

    @Test
    public void testGetCountry() {
        LOGGER.log(Level.INFO, "getCountry");

        String code = "D";
        Country result = instance.getCountry(code);

        assertEquals("Germany", result.getName());
    }

    @Test
    public void testFindCountry() {
        LOGGER.log(Level.INFO, "findCountry (Germany)");

        String code = "D";
        Country result = instance.findCountry(code);

        assertEquals("Germany", result.getName());
    }

    @Test
    public void testFindCountries() {
        LOGGER.log(Level.INFO, "findCountries with max size and given start");

        List<Country> results = instance.findCountryEntities(12, 2);

        LOGGER.log(Level.INFO, "first country: {0}", results.get(0).getName());

        assertEquals(12, results.size());
        assertEquals("Macedonia", results.get(0).getName());
    }

    @Test
    public void testCountCountries() {
        LOGGER.log(Level.INFO, "findCountries with max size and given start");
        Long count = instance.getCountryCount();
        
        assertTrue(244 == count);
    }
}
