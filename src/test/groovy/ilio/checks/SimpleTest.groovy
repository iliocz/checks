package ilio.checks

import spock.lang.Specification
import spock.lang.Unroll


/**
 * @author qizhong
 */
class SimpleTest extends Specification {

    @Unroll
    def "test"() {
        when:
        def result = a

        then:
        result == RESULT

        where:
        a || RESULT
        1 || 1
        2 || 2
    }
}
