package codestory

class CalculateTest extends GroovyTestCase {
    void testPlus() {
        def url = new URL("http://localhost:8080/?q=109+178")
        assert url.text =~ /^287$/
    }
    void testMinus() {
        def url = new URL("http://localhost:8080/?q=109-178")
        assert url.text =~ /^-69$/
    }
    void testDivision() {
        def url = new URL("http://localhost:8080/?q=109*2")
        assert url.text =~ /^218$/
    }
    void testMultiplication() {
        def url = new URL("http://localhost:8080/?q=109/2")
        assert url.text =~ /^54,5$/
    }
    void testComplex() {
        def url = new URL("http://localhost:8080/?q=(109/2)*4")
        assert url.text =~ /^218$/
    }
    void testDecimal() {
        def url = new URL("http://localhost:8080/?q=1,5*4")
        assert url.text =~ /^6$/
    }
    void testMiniDecimal() {
        def url = new URL("http://localhost:8080/?q=1,0000000000000000000000000000000000000000000000001*1,0000000000000000000000000000000000000000000000001")
        assert url.text =~ /^1,00000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000001$/
    }
}