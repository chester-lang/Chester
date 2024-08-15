package chester.utils

def platformUseCRLF: Boolean = System.getProperty("os.name").toLowerCase.contains("win")
