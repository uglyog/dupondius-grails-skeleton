def build
if (args) {
  jenkins = hudson.model.Hudson.instance
  job = jenkins.items.find { it.name == args[0] }
  build = job.builds.find { it.number == args[1] as Integer }
} else {
  build = currentBuild
}
cause = build.actions.find { it instanceof hudson.model.CauseAction }
upstreamCause = cause?.causes?.find { it instanceof hudson.model.Cause.UpstreamCause }
if (upstreamCause) {
  println "{"
  println "  upstreamProject='${upstreamCause.upstreamProject}',"
  println "  upstreamBuild=${upstreamCause.upstreamBuild}"
  println "}"
} else {
  println "ERROR - no upstreamCause found"
}