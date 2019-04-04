package location;

import com.google.common.collect.ImmutableBiMap;

import java.util.Optional;

import static external.Country.*;
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
    
    static Optional<Country> toDomainCountry(external.Country countryExternal) {
        return Optional.ofNullable(COUNTRY_MAPPING.get(countryExternal));
    }    
    
    static Optional<external.Country> toExternalCountry(Country countryInternal) {
        return Optional.ofNullable(COUNTRY_MAPPING.inverse().get(countryInternal));
    }
}

