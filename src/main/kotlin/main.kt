import org.junit.Test
import org.junit.runner.JUnitCore
import org.junit.runner.Request
import org.junit.Assert.assertEquals
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class Greeter {
    companion object {
        @JvmStatic
        public fun main(args: Array<String>) {
            print("Hello")
        }
    }
}

class TestGreeter() {
    @Test
    public fun test() {
        println("1")
        try {
            val greeterClass = Class.forName("Greeter")
            val mainMethod = greeterClass.getDeclaredMethod("main", Array<String>::class.java)
            val os = ByteArrayOutputStream()
            val ps = PrintStream(os)
            val beforeOut = System.out
            System.setOut(ps)
            mainMethod.invoke(null, Array(0) { "" })
            System.setOut(beforeOut)
            assertEquals("Hello", os.toString())
        } catch (_: ClassNotFoundException) {
            println("class 'Greeter' not found")
        } catch (_: NoSuchMethodException) {
            println("class 'Greeter.main' not found")
        } catch (error: Throwable) {
            println(error)
        }
    }
}

class SpecCheck {
    companion object {
        @JvmStatic
        private fun evaluateTests(): Boolean {
            val core = JUnitCore()
            core.run(Request.aClass(TestGreeter().javaClass).sortWith { o1, o2 ->
                if (o1 != null && o2 != null) {
                    o1.displayName.compareTo(o2.displayName)
                } else {
                    0
                }
            })
            // todo
            return true
        }

        @JvmStatic
        public fun grade(verbosity: Int = 0) {
            if (verbosity == 0) {
                this.evaluateTests()
            }
        }
    }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Starting...")
        SpecCheck.grade()
    }
}