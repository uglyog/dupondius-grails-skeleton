package dupondius.grails.skeleton

import grails.test.mixin.*
import org.junit.*
import org.codehaus.groovy.grails.commons.ApplicationHolder

@TestFor(StatusController)
class StatusControllerTests {

    void testIndex() {
      controller.index()
	  assert controller.response.contentAsString == 'OK'
    }

    void testVersion() {
      controller.version()
	  assert controller.response.contentAsString == ApplicationHolder.application.metadata['app.version'].toString()
    }
}
