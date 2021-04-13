package com.testning.pageobjects.examples.amazon;

import com.testning.pageobjects.wrappers.BaseTest;
import com.testninja.pageobjects.examples.amazon.components.searchfilters.CheckBoxFilter;
import com.testninja.pageobjects.examples.amazon.components.searchfilters.MultiChoiceButtonFilter;
import com.testninja.pageobjects.examples.amazon.pages.HomePage;
import com.testninja.pageobjects.examples.amazon.pages.SearchResultsPage;
import com.testninja.pageobjects.wrappers.annotations.PageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class AmazonSearchFiltersTest extends BaseTest {

    @PageObject
    HomePage amazonHomePage;

    @PageObject
    private SearchResultsPage resultsPage;

    @BeforeMethod
    public void openAmazon() {
        driver.get("https://www.amazon.in");
        amazonHomePage
                .searchProduct("shoes");
    }


    @Test
    public void BrandFilterTest() {
        List<String> brands = Arrays.asList("Campus");
        CheckBoxFilter brandFilter = new CheckBoxFilter(scriptHelper, "Brand");
        brandFilter.apply(brands);

        assertEquals(brandFilter.getSelectedOptions(), brands);
        assertTrue(brandFilter.isClearLinkPresent());
        assertTrue(brandFilter.isSeeMoreLinkPresent());
    }

    @Test
    public void MaterialFilterTest() {
        List<String> material = Arrays.asList("Canvas", "Rubber");
        CheckBoxFilter materialFilter = new CheckBoxFilter(scriptHelper, "Material");
        materialFilter.apply(material);

        assertEquals(materialFilter.getSelectedOptions(), material);
        assertTrue(materialFilter.isClearLinkPresent());
        assertFalse(materialFilter.isSeeMoreLinkPresent());

        /*
        CheckBoxFilter component can be further reused to test some of other below filter types:
        1. Delivery Day
        2. Deals
        3. Material
        4. Shoes Fashion Brands
        5. Running Surface
         */
    }

    @Test
    public void SizeFilterTest() {
        List<String> size = Collections.singletonList("8.5");

        MultiChoiceButtonFilter sizeFilter = new MultiChoiceButtonFilter(scriptHelper, "Size");
        assertFalse(sizeFilter.isSeeMoreLinkPresent());
        sizeFilter.apply(size);

        assertEquals(sizeFilter.getSelectedOptions(), size);
        assertTrue(sizeFilter.isClearLinkPresent());
        assertFalse(sizeFilter.isSeeMoreLinkPresent());
    }

}
