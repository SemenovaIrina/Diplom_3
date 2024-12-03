package edu.practicum;

import org.junit.Assert;
import org.junit.Test;

public class Constructor extends PrepareUtilsAndSteps {

    @Test
    public void sauceSelectionInConstructorIsCorrect() {
        //переходим в раздел Соусы
        homePage.clickSauceRefInConstructor();
        Assert.assertTrue("Перехода в раздел Соусы не происходит", homePage.checkIsVisibleSauceSectionInConstructor());
    }

    @Test
    public void fillingSelectionInConstructorIsCorrect() {
        //переходим в раздел Начинки
        homePage.clickFillingRefInConstructor();
        Assert.assertTrue("Перехода в раздел Соусы не происходит", homePage.checkIsVisibleFillingSectionInConstructor());
    }

    @Test
    public void bunSelectionInConstructorIsCorrect() {
        //так как этот раздел должен загружаться изначально, то сначало перейдем в другой раздел, а потом вернемся
        //например перейдем в раздел Начинки
        homePage.clickFillingRefInConstructor();
        //переходим в раздел Булки
        homePage.clickBunRefInConstructor();
        Assert.assertTrue("Перехода в раздел Булки не происходит", homePage.checkIsVisibleBunSectionInConstructor());
    }

}
