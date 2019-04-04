package location;

import org.junit.Test;

import java.util.Optional;

import static external.Country.*;
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
}