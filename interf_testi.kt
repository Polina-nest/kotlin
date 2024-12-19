//testInputUrl: Проверяет, что URL корректно вводится в поле.
//testDownloadButtonVisibility: Проверяет, что кнопка загрузки отображается.
//testDownloadButtonFunctionality: Проверяет, что при нажатии на кнопку появляется сообщение об успешной загрузке.
//testEmptyUrlInput: Проверяет, что при нажатии на кнопку с пустым полем появляется сообщение об ошибке.
//testInvalidUrlInput: Проверяет, что при вводе невалидного URL появляется сообщение об ошибке.




import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testInputUrl() {
        val url = "http://example.com/image.jpg"
        onView(withId(R.id.imageUrlEditText)).perform(typeText(url))
        onView(withId(R.id.imageUrlEditText)).check(matches(withText(url)))
    }

    @Test
    fun testDownloadButtonVisibility() {
        onView(withId(R.id.downloadButton)).check(matches(isDisplayed()))
    }

    @Test
    fun testDownloadButtonFunctionality() {
        val url = "http://example.com/image.jpg"
        onView(withId(R.id.imageUrlEditText)).perform(typeText(url))
        onView(withId(R.id.downloadButton)).perform(click())

        // Проверка, что появляется сообщение об успешной загрузке
        onView(withText("Изображение сохранено!")).check(matches(isDisplayed()))
    }

    @Test
    fun testEmptyUrlInput() {
        onView(withId(R.id.downloadButton)).perform(click())

        // Проверка, что появляется сообщение об ошибке
        onView(withText("Введите URL изображения")).check(matches(isDisplayed()))
    }

    @Test
    fun testInvalidUrlInput() {
        val invalidUrl = "invalid-url"
        onView(withId(R.id.imageUrlEditText)).perform(typeText(invalidUrl))
        onView(withId(R.id.downloadButton)).perform(click())

        // Проверка, что появляется сообщение об ошибке
        onView(withText("Ошибка загрузки изображения")).check(matches(isDisplayed()))
    }
}
