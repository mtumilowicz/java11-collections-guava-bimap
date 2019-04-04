package location;

import org.junit.Test;

import java.util.Optional;

import static external.Country.*;
import static location.Capital.*;
import static location.Country.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by mtumilowicz on 2019-04-04.
 */
public class LocationTest {

    @Test
    public void domain() {
        assertThat(Optional.of(PL), is(Location.toDomainCountry(POLAND)));
        assertThat(Optional.of(DE), is(Location.toDomainCountry(GERMANY)));
        assertThat(Optional.of(JPN), is(Location.toDomainCountry(JAPAN)));
    }

    @Test
    public void external() {
        assertThat(Optional.of(POLAND), is(Location.toExternalCountry(PL)));
        assertThat(Optional.of(GERMANY), is(Location.toExternalCountry(DE)));
        assertThat(Optional.of(JAPAN), is(Location.toExternalCountry(JPN)));
    }


    @Test
    public void toDomainCapital() {
        assertThat(Optional.of(BUENOS_AIRES), is(Location.toDomainCapital("buenos-aires")));
        assertThat(Optional.of(MEXICO_CITY), is(Location.toDomainCapital("mexico city")));
        assertThat(Optional.of(WASHINGTON), is(Location.toDomainCapital("washington, dc")));
    }

    @Test
    public void toExternalCapital() {
        assertThat(Optional.of("buenos-aires"), is(Location.toExternalCapital(BUENOS_AIRES)));
        assertThat(Optional.of("mexico city"), is(Location.toExternalCapital(MEXICO_CITY)));
        assertThat(Optional.of("washington, dc"), is(Location.toExternalCapital(WASHINGTON)));
    }
}