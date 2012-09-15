package dupondius.grails.skeleton

import org.codehaus.groovy.grails.commons.ApplicationHolder

class StatusController {

    def index() {
        render 'OK'
    }

    def version() {
        render ApplicationHolder.application.metadata['app.version'].toString()
    }
}
