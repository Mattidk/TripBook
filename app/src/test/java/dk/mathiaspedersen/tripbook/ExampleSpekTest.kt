package dk.mathiaspedersen.tripbook

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class ExampleSpekTest: Spek({

    describe("a simple test") {

        it("should be equal to each other") {
            23.should.equal(23)
        }

        it("should not contain scale") {
            "Kotlin".should.not.contain("Scala")
        }

        it("should have a size above 3") {
            listOf(1, 2, 3).should.have.size.above(2)
        }
    }

})