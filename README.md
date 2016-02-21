# bitkointlin
## A project to study Kotlin and Bitcoin

---

There are 3 sample projects, one in Kotlin one in Java and one for Android, see they in samples folder.

# How to

- Add JitPack.io to repositories. "If you are going to use [Fuel](https://github.com/kittinunf/Fuel) http client implementation you need to add jcenter too"

```
repositories {
    mavenCentral()
    jcenter()
    maven {
        url "https://jitpack.io"
    }
}
```

- add this project in your dependencies. "We're in development so you can use the commit hash instead the traditional semantic versioning"

```
dependencies {
    compile "com.github.ademar111190:bitkointlin:333e4ca"
}
```

- Create a `Bitkointlin` instance.
  - httpAddress, user and passowrd need follow the bitcoind configuration. More information [see it](https://bitcoin.org/en/full-node#ubuntu-daemon)
  - httpClient is an Network Interface, there is 2 default implementations [Fuel](https://github.com/kittinunf/Fuel) for Kotlin and Java, and [OkHttp](http://square.github.io/okhttp/) for Android. Naturally you can make your own too

```
val bitkointlin = Bitkointlin(
        user = "your_user",
        password = "your_password",
        httpAddress = "http://127.0.0.1:8332/",
        httpClient = HttpClientImplFuel())
```

- Now you just need call the method from your bitkointlin instance, today the list of implemented method are:

[GetBalance](https://bitcoin.org/en/developer-reference#getbalance)
[GetBestBlockHash](https://bitcoin.org/en/developer-reference#getbestblockhash)
[GetDifficulty](https://bitcoin.org/en/developer-reference#getdifficulty)
[GetInfo](https://bitcoin.org/en/developer-reference#getinfo)
