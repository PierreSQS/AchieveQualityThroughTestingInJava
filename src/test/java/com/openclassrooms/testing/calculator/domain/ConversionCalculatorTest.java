package com.openclassrooms.testing.calculator.domain;

import static java.lang.Math.PI;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
public class ConversionCalculatorTest {

    private ConversionCalculator calculatorUnderTest = new ConversionCalculator();


    @Test
    public void celsiusToFahrenheit_returnsAFahrenheitTempurature_whenCelsiusIsZero() {
        Double actualFahrenheit = calculatorUnderTest.celsiusToFahrenheit(0.0 );
        assertThat( actualFahrenheit, is(equalTo(32.0)) );
    }

    @Test
    public void fahrenheitToCelsius_returnsZeroCelciusTempurature_whenThirtyTwo() {
        Double actualCelsius = calculatorUnderTest.fahrenheitToCelsius(32.0);
        assertThat( actualCelsius, is(equalTo(0.0)));
    }

    @Test
    public void litresToGallons_returnsOneGallon_whenConvertingTheEquivalentLitres() {
        Double actualLitres = calculatorUnderTest.litresToGallons(3.78541);
        assertThat( actualLitres, is(equalTo(1.0)));
    }

    @Test
    public void radiusToAreaOfCircle_returnsPi_whenWeHaveARadiusOfOne() {
        Double actualArea = calculatorUnderTest.radiusToAreaOfCircle(1.0);
        assertThat( actualArea, is(equalTo(PI)));
    }
}