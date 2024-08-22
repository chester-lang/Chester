package chester.utils

import chester.utils.env.Env

def platformUseCRLF: Boolean = Env.getOS.useCRLF
