package de.focus_shift.tests;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayManager;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.HolidayCalendar.NETHERLANDS;
import static de.focus_shift.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.ManagerParameters.create;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayNLTest extends AbstractCountryTestBase {

  @Property
  void ensuresThatNewYearOnFirstIsConfiguredSince1967(@ForAll @YearRange(min = 1967) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatNewYearOnSecondIsNotConfiguredUntil1966(@ForAll @YearRange(max = 1966) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatAllLiberationIsNotConfiguredUntil1944(@ForAll @YearRange(max = 1944) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), MAY, 5), "LIBERATION", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatAllLiberationIsConfiguredEveryFiveYearsSince1945Until1989(@ForAll @YearRange(min = 1945, max = 1989) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    if (year.getValue() % 5 == 0) {
      assertThat(holidays)
        .isNotEmpty()
        .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 5), "LIBERATION", OFFICIAL_HOLIDAY));
    }
  }

  @Property
  void ensuresThatAllLiberationIsNotConfiguredBetweenEveryFiveYearsSince1945Until1989(@ForAll @YearRange(min = 1945, max = 1989) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    if (year.getValue() % 5 != 0) {
      assertThat(holidays)
        .isNotEmpty()
        .doesNotContain(new Holiday(LocalDate.of(year.getValue(), MAY, 5), "LIBERATION", OFFICIAL_HOLIDAY));
    }
  }

  @Property
  void ensuresThatAllLiberationIsConfiguredUntil1990(@ForAll @YearRange(min = 1990) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 5), "LIBERATION", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatChristmasIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatStephensIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 26), "STEPHENS", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatGoodFridayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.GOOD_FRIDAY");
  }

  @Property
  void ensuresThatEasterIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER");
  }

  @Property
  void ensuresThatEasterMondayIsConfiguredSince1642(@ForAll @YearRange(min = 1642) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER_MONDAY");
  }

  @Property
  void ensuresThatEasterMondayIsNotConfiguredUntil1641(@ForAll @YearRange(min = 0, max = 1641) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("christian.EASTER_MONDAY");
  }

  @Property
  void ensuresThatAscensionDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.ASCENSION_DAY");
  }

  @Property
  void ensuresThatPentecostIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.PENTECOST");
  }

  @Property
  void ensuresThatWhitMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.WHIT_MONDAY");
  }
}
