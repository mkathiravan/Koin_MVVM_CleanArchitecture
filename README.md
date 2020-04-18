# Koin_MVVM_CleanArchitecture

### What is Koin ?
Koin is a pragmatic lightweight dependency injection framework for Kotlin developers to whom we will give the responsibility to instantiate the different objects of our application.

#### First, we have to know some useful notes about koin syntax:

i) get() to resolve an instance in a Koin module, just use the get() function to the requested needed component instance. this get() function is usually used into the constructor, to inject constructor values.

ii) factory factory component declaration is a definition that will gives you a new instance each time you ask for this definition.

iii) single provide a singleton bean definition (also aliased as bean)

iv) name = is used to name definitions. This is required when you want to have multiple instances of the same class with different types.

### Define a Koin Module

In Koin a module is a component in which we are going to declare all the dependencies that will be injected in another components, for example the View Model will be use in the activity, so we have declare a module that will create for us an instance of a View Model. we will see that it’s operation that koin will do for us by reducing the Boiler plate code we create when we want to create an instance of a View Model by default, thank to Koin for that

For creating module you have to use a function called module that take a lambda as parameter and we have to keep the reference of the module in the variable

The below snippet of koin module


    val viewModelModule = module {
      single { UserViewModel(get()) }
      }


    val apiModule = module {

    fun providerUserApi(retrofit: Retrofit) : UserApi
    {
        return retrofit.create(UserApi::class.java)
    }
      single { providerUserApi(get()) }
    }

    val netModule = module {

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }


    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }

    single { provideHttpClient(get()) }

    single { provideGson() }

    single { provideRetrofit(get(), get()) }
      }

      val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "eds.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: AppDatabase): UserDao {
        return database.userDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    }

    val repositoryModule = module {
    fun provideUserRepository(api: UserApi, dao: UserDao): UserRepository {
        return UserRepository(api, dao)
    }

      single { provideUserRepository(get(), get()) }
    }
