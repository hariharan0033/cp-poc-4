import jenkins.model.*
import hudson.security.*
import jenkins.install.*
import hudson.util.*

// Get the Jenkins instance
def instance = Jenkins.getInstance()

// Set up security realm with admin user
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("admin", "admin")  // Set the admin credentials
instance.setSecurityRealm(hudsonRealm)

// Set up authorization strategy (full control for admin)
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
instance.setAuthorizationStrategy(strategy)

// Install plugins
def plugins = ['git', 'sonar', 'pipeline']
def pluginManager = instance.getPluginManager()
def updateCenter = instance.getUpdateCenter()

def installed = false

plugins.each { plugin ->
    if (!pluginManager.getPlugin(plugin)) {
        println("Installing plugin: ${plugin}")
        def deployment = updateCenter.getPlugin(plugin).deploy()
        
        // Wait until the plugin is installed
        while (!deployment.isComplete()) {
            println("Waiting for ${plugin} plugin to install...")
            sleep(5000)  // Wait 5 seconds before checking again
        }
    } else {
        println("${plugin} plugin is already installed.")
    }
}

// Skip setup wizard
instance.setInstallState(InstallState.INITIAL_SETUP_COMPLETED)
instance.save()

println("Jenkins setup completed successfully.")
