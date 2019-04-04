# java11-collections-guava-bimap
_Reference_: https://google.github.io/guava/releases/27.1-jre/api/docs/com/google/common/collect/BiMap.html  
_Reference_: https://google.github.io/guava/releases/27.1-jre/api/docs/com/google/common/collect/ImmutableBiMap.html  
_Reference_: https://google.github.io/guava/releases/27.1-jre/api/docs/com/google/common/collect/EnumBiMap.html  
_Reference_: https://google.github.io/guava/releases/27.1-jre/api/docs/com/google/common/collect/HashBiMap.html  
_Reference_: https://en.wikipedia.org/wiki/Bidirectional_map

# introduction
* one to one correspondence between `X` and `Y`
* bidirectional map between types `X` and `Y` is a bijection `f: X -> Y`
* guava useful classes:
    * `ImmutableBiMap`
    * `EnumBiMap`
    * `HashBiMap`

# usage
We will cover only `ImmutableBiMap` (in our opinion - most useful)
1. to define `ImmutableBiMap` we have:
    * factory methods - up to 5 keys
        ```
        static <K, V> ImmutableBiMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5)
        ```
        ```
        ImmutableBiMap<external.Country, Country> COUNTRY_MAPPING = ImmutableBiMap.of(
                    POLAND, PL,
                    GERMANY, DE,
                    JAPAN, JPN
        );
        ```
    * builder
        ```
        ImmutableBiMap<Capital, String> CAPITAL_MAPPING = ImmutableBiMap.<Capital, String>builder()
                    .put(BUENOS_AIRES, "buenos-aires")
                    .put(MEXICO_CITY, "mexico city")
                    .put(WASHINGTON, "washington, dc")
                    .build();
        ```
1. it can be used like ordinary map
    ```
    COUNTRY_MAPPING.get(...)
    ```
1. or we can "inverse" it in `O(1)` - inversed map is cached in the field
    ```
    COUNTRY_MAPPING.inverse().get(...)
    ```

# project description
1. in short: we want bijection between our domain objects and similar external objects
    1. suppose we have incoming object with external for our domain `Country` class as a field
        ```
        public enum Country {
            POLAND,
            GERMANY,
            JAPAN
        }
        ```
    1. however in our domain, we have only abbreviations
        ```
        enum Country {
            PL,
            DE,
            JPN
        }
        ```
    1. we want to set-up one to one correspondence between them
        ```
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
        ```
1. in short: we want to have one to one correspondence between incoming `String` and our domain enum
    1. suppose that incoming `String` has different notation (ex. camelcase vs lowercase, "-" instead of "_", etc)
        from used in our domain
    1. we want to establish one to one correspondence between incoming strings and our domain enum
        ```
        enum Capital {
            BUENOS_AIRES,
            MEXICO_CITY,
            WASHINGTON
        }
        ```
        ```
        class Location {
            private final static ImmutableBiMap<Capital, String> CAPITAL_MAPPING = ImmutableBiMap.<Capital, String>builder()
                    .put(BUENOS_AIRES, "buenos-aires")
                    .put(MEXICO_CITY, "mexico city")
                    .put(WASHINGTON, "washington, dc")
                    .build();
        
            static Optional<Capital> toDomainCapital(String capitalExternal) {
                return Optional.ofNullable(CAPITAL_MAPPING.inverse().get(capitalExternal));
            }
        
            static Optional<String> toExternalCapital(Capital capital) {
                return Optional.ofNullable(CAPITAL_MAPPING.get(capital));
            }
        }
        ```
* tests
    * toDomainCountry
        ```
        assertThat(Optional.of(PL), is(Location.toDomainCountry(POLAND)));
        assertThat(Optional.of(DE), is(Location.toDomainCountry(GERMANY)));
        assertThat(Optional.of(JPN), is(Location.toDomainCountry(JAPAN)));
        ```
    * toExternalCountry
        ```
        assertThat(Optional.of(POLAND), is(Location.toExternalCountry(PL)));
        assertThat(Optional.of(GERMANY), is(Location.toExternalCountry(DE)));
        assertThat(Optional.of(JAPAN), is(Location.toExternalCountry(JPN)));
        ```
    * toDomainCapital
        ```
        assertThat(Optional.of(BUENOS_AIRES), is(Location.toDomainCapital("buenos-aires")));
        assertThat(Optional.of(MEXICO_CITY), is(Location.toDomainCapital("mexico city")));
        assertThat(Optional.of(WASHINGTON), is(Location.toDomainCapital("washington, dc")));
        ```
    * toExternalCapital
        ```
        assertThat(Optional.of("buenos-aires"), is(Location.toExternalCapital(BUENOS_AIRES)));
        assertThat(Optional.of("mexico city"), is(Location.toExternalCapital(MEXICO_CITY)));
        assertThat(Optional.of("washington, dc"), is(Location.toExternalCapital(WASHINGTON)));
        ```