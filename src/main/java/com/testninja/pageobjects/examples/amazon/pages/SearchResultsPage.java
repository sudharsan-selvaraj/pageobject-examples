package com.testninja.pageobjects.examples.amazon.pages;

import com.testninja.pageobjects.examples.amazon.components.searchfilters.CheckBoxFilter;
import com.testninja.pageobjects.examples.amazon.components.searchfilters.PriceRangeFilter;
import com.testninja.pageobjects.wrappers.ScriptHelper;
import com.testninja.pageobjects.wrappers.annotations.Page;

import java.util.Arrays;
import java.util.List;

@Page
public class SearchResultsPage extends AmazonBasePage {

    public SearchResultsPage(ScriptHelper scriptHelper) {
        super(scriptHelper);
    }

    public CheckBoxFilter applyPrimeFilter() {
        CheckBoxFilter filter = new CheckBoxFilter(scriptHelper, "Amazon Prime", true);
        filter.apply(Arrays.asList("Prime Eligible"));
        return filter;
    }

    public CheckBoxFilter applyBrandFilter(List<String> brands) {
        CheckBoxFilter filter = new CheckBoxFilter(scriptHelper, "Brand");
        filter.apply(brands);
        return filter;
    }

    public PriceRangeFilter applyPriceFilter(String minPrice, String maxPrice) {
        PriceRangeFilter filter = new PriceRangeFilter(scriptHelper);
        filter.apply(minPrice, maxPrice);
        return filter;
    }
}
