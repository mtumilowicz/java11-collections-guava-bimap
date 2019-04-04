package location;

import com.google.common.collect.ImmutableBiMap;

import java.util.Optional;

import static external.Country.*;
import static location.Capital.*;
import static location.Country.*;

/**
 * Created by mtumilowicz on 2019-04-04.
 */
class Location {
    private final static ImmutableBiMap<external.Country, Country> COUNTRY_MAPPING = ImmutableBiMap.of(
            POLAND, PL,
            GERMANY, DE,
            JAPAN, JPN
    );

    private final static ImmutableBiMap<Capital, String> CAPITAL_MAPPING = ImmutableBiMap.of(
            BUENOS_AIRES, "buenos-aires",
            MEXICO_CITY, "mexico city",
            WASHINGTON, "washington, dc"
    );
    
    static Optional<Country> toDomainCountry(external.Country countryExternal) {
        return Optional.ofNullable(COUNTRY_MAPPING.get(countryExternal));
    }    
    
    static Optional<external.Country> toExternalCountry(Country countryInternal) {
        return Optional.ofNullable(COUNTRY_MAPPING.inverse().get(countryInternal));
    }
    
    static Optional<Capital> toDomainCapital(String capitalExternal) {
        return Optional.ofNullable(CAPITAL_MAPPING.inverse().get(capitalExternal));
    }
    
    static Optional<String> toExternalCapital(Capital capital) {
        return Optional.ofNullable(CAPITAL_MAPPING.get(capital));
    }
}

