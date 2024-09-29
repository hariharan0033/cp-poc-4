import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount('admin', '12345678')  // Set your password here
instance.setSecurityRealm(hudsonRealm)
instance.save()
