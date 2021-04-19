# What is Page Object?
Page Object is a Design Pattern which has become popular in test automation for enhancing test maintenance and reducing code duplication. A page object is an object-oriented class that serves as an interface to a page of your AUT. The tests then use the methods of this page object class whenever they need to interact with the UI of that page. The benefit is that if the UI changes for the page, the tests themselves don’t need to change, only the code within the page object needs to change. Subsequently all changes to support that new UI are located in one place.

# Advantages:

- There is a clean separation between test code and page specific code such as locators (or their use if you’re using a UI Map) and layout.
- There is a single repository for the services or operations offered by the page rather than having these services scattered throughout the tests.

# Example:
> A page object does not necessarily need to represent all the parts of a page itself. The same principles used for page objects can be used to create “Page Component Objects” that represent discrete chunks of the page and can be included in page objects. These component objects can provide references the elements inside those discrete chunks, and methods to leverage the functionality provided by them. You can even nest component objects inside other component objects for more complex pages. If a page in the AUT has multiple components, or common components used throughout the site (e.g. a navigation bar), then it may improve maintainability and reduce code duplication.
-- Quote from selenium offical site

Let's use https://www.amazon.in and see how UI elements can be represented as reusable webcomponents using page objects.

<img src="https://i.imgur.com/udJq7GZ.png" width="800" height="400">

Below snippet is the representation of filter component in selenium pageobject:
```java
public class CheckBoxFilter extends BaseProductFilter {
    
    public CheckBoxFilter(ScriptHelper scriptHelper, String filterHeader) {
        super(scriptHelper, filterHeader);
    }

    public void apply(List<String> optionsToSelect) {
        optionsToSelect
                .stream()
                .forEach(option -> {
                    waitForFilterToAppear();
                    clickSeeMore();
                    selectOption(getOptionCheckbox(option));
                });
    }

    public List<String> getSelectedOptions() {
        waitForFilterToAppear();
        clickSeeMore();

        return  getChildElement()
                .findElements(By.xpath(".//li/descendant::a[.//input[@type='checkbox'][@checked]]/descendant::span"))
                .stream()
                .map(interactions::getText)
                .filter(text -> !text.trim().equals(""))
                .collect(Collectors.toList());

    }

    private void selectOption(WebElement element) {
        if (!interactions.isSelected(element)) {
            interactions.javaScriptClick(element);
        }
    }

    private void deselectOption(WebElement element) {
        if (interactions.isSelected(element)) {
            interactions.click(element);
        }
    }

    private WebElement getOptionCheckbox(String label) {
         return getChildElement().findElement(By.xpath(".//li/descendant::a[.//span[text()='" + label + "']]/descendant::input[@type='checkbox']"));
    }
}
```

Now the component can be used in the tests like:

```java
/* Test Material filter */
List<String> material = Arrays.asList("Canvas", "Rubber");
CheckBoxFilter materialFilter = new CheckBoxFilter(scriptHelper, "Material");
materialFilter.apply(material);

assertEquals(materialFilter.getSelectedOptions(), material);
assertTrue(materialFilter.isClearLinkPresent());
assertFalse(materialFilter.isSeeMoreLinkPresent());

/* Test Brand filter */

List<String> brands = Arrays.asList("Campus");
CheckBoxFilter brandFilter = new CheckBoxFilter(scriptHelper, "Brand");
brandFilter.apply(brands);

assertEquals(brandFilter.getSelectedOptions(), brands);
assertTrue(brandFilter.isClearLinkPresent());
assertTrue(brandFilter.isSeeMoreLinkPresent());
```