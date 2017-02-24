# 1.0.3

Bugfix release

* For the seven special characters (" \ backspace formfeed newline carriage-return tab), encode with backslash instead
of the actual control character.
* For all other control characters, throw exception, because they are illegal in JSON

# 1.0.2

Documentation release

* Add documentation to each public function

# 1.0.1

Bugfix/Performance release

* Improve performance by reusing same StringBuilder
* Properly handles Long/Short/Byte/Float

# 1.0.0

Initial release

* Compose json list/map into string
* Throw exception if unsupported object is provided. Supported classes are: Boolean, Integer, Double, String, Map, Collection