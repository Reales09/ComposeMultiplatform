import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ExampleTest {
    @Test
    fun sum_must_success(){
        // Given
        val x = 5
        val y = 10
        // When
        val result = x+y

        // Then
        assertEquals(15, result)
    }

    @Test
    fun sum_must_fail(){
        // Given
        val x = 5
        val y = 10
        // When
        val result = x+y

        // Then
        assertNotEquals(10, result)
    }
}