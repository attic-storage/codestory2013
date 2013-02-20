package codestory

import groovy.json.JsonSlurper

class ScalaskelChangeTest extends GroovyTestCase {
    void test1() {
        def url = new URL("http://localhost:8080/scalaskel/change/1")
        def json = new JsonSlurper().parseText(url.text)
        assert json.size() == 1
        assert json[0].foo == 1
        assert json[0].bar == null
        assert json[0].qix == null
        assert json[0].baz == null
    }

    // TODO faire plus de tests
}