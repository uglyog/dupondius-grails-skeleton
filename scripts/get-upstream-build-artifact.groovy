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

while (build && upstreamCause?.upstreamProject && upstreamCause?.upstreamProject != 'Deployment_Test') {
  job = jenkins.items.find { it.name == upstreamCause?.upstreamProject }
  build = job.builds.find { it.number == upstreamCause.upstreamBuild as Integer }
  cause = build.actions.find { it instanceof hudson.model.CauseAction }
  upstreamCause = cause?.causes?.find { it instanceof hudson.model.Cause.UpstreamCause }
}

if (upstreamCause) {
  println "${upstreamCause.upstreamProject}/${upstreamCause.upstreamBuild}"
} else {
  println "ERROR - no upstreamCause found"
}
