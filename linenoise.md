# linenoise

Taken from https://github.com/msteveb/jimtcl/ master branch https://github.com/msteveb/jimtcl/commit/3a23947a6d6e794b50c8d48497849a05415f1b3f

linenoise-win32.c is renamed to linenoise-win32.h

`#include <jim-config.h>` -> `#include "jim-config.h"`

created jim-config.h to enable utf8

for linenoise.h added `#include <stddef.h>` for size_t

`_unicode_mapping.c` generated with `./configure` and `make _unicode_mapping.c` and renamed to `_unicode_mapping.h`