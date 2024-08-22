package chester.utils

import chester.utils.env

def platformUseCRLF: Boolean = env.getOS.useCRLF
