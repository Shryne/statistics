import org.junit.Test
import statistics.privateMethods
import kotlin.test.assertEquals

/**
 * @since 0.7
 */
class PrivateMethodsTest {
    @Test
    fun simplest() = assertEquals(privateMethods("private void x( "), "private methods = 1")

    @Test
    fun oneSpace() = assertEquals(privateMethods("private void x ( "), "private methods = 1")

    @Test
    fun notVariable() = assertEquals(privateMethods("private void x = "), "private methods = 0")

    @Test
    fun otherType() = assertEquals(privateMethods("private X x( "), "private methods = 1")

    @Test
    fun final() = assertEquals(privateMethods("private final X x( "), "private methods = 1")

    @Test
    fun multipleLetters() = assertEquals(privateMethods("private final XZSDE abcd( "), "private methods = 1")

    @Test
    fun inComment() = assertEquals(privateMethods("/* private X x( "), "private methods = 0")

    @Test
    fun outOfComment() = assertEquals(privateMethods("/**/ private X x( "), "private methods = 1")

    @Test
    fun notConstructor() = assertEquals(privateMethods("private X( "), "private methods = 0")

    @Test
    fun notStaticMethods() =
        assertEquals(
            privateMethods("private static void initIDs( "),
            "private methods = 0"
        )

    @Test
    fun notStaticNativeMethods() =
        assertEquals(
            privateMethods("private static native void initIDs( "),
            "private methods = 0"
        )
}
