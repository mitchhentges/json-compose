# Json Compose [![Build Status](https://travis-ci.org/mitchhentges/json-compose.svg?branch=master)](https://travis-ci.org/mitchhentges/json-compose)

A tool to quickly compose Java maps and lists of primitives into JSON strings.

## Usage

```
List<Object> list = Arrays.listOf(1, "two", null);
Map<String, Object> map = new HashMap<>();
map.put("key", "value");

JsonCompose.compose(list); //[1,\"two\",null]
JsonCompose.compose(map); //{\"key\":\"value\"}
```

## Getting the dependency

**Maven**
```
<dependency>
    <groupId>ca.fuzzlesoft</groupId>
    <artifactId>json-compose</artifactId>
    <version>1.0.2</version>
</dependency>
```

**Gradle**
```
compile 'ca.fuzzlesoft:json-compose:1.0.2'
```

## Features

### Composes nested Java containers of primitives

So long as only primitives, `Map`s or `Collection`s are provided, any nested model can be composed into a JSON string.

### Thread safe

Call `JsonCompose` from as many different threads as you want, it will handle it like a champ.

## FAQ

* Like [Json-Parse](https://github.com/mitchhentges/json-parse), does this provide a "path" to composition errors?

That will be implemented in the future, but not yet

## License
[MIT License (Expat)](http://www.opensource.org/licenses/mit-license.php)
