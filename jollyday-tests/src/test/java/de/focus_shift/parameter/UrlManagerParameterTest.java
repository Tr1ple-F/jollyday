package de.focus_shift.parameter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

class UrlManagerParameterTest {

  private UrlManagerParameter urlManagerParameter;
  private URL url;

  @BeforeEach
  void setup() throws MalformedURLException {
    url = new URL("http://www.google.de");
    Properties properties = new Properties();
    urlManagerParameter = new UrlManagerParameter(url, properties);
  }

  @Test
  void testCreateCacheKey() {
    assertThat(urlManagerParameter.createCacheKey()).isEqualTo("http://www.google.de");
  }

  @Test
  void testGetDisplayName() {
    assertThat(urlManagerParameter.getDisplayName()).isEqualTo("http://www.google.de");
  }

  @Test
  void testCreateResourceUrl() {
    assertThat(urlManagerParameter.createResourceUrl()).isEqualTo(url);
  }
}
