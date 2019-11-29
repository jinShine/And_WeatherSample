package kr.co.alex.weathersample.di


//안드로이드에서 AndroidInjectionModule::class를 넣어줘야된다
//@Singleton
//@Component(modules = [
//    AndroidInjectionModule::class,
//    AppModule::class,
//    ViewModelFactoryModule::class
//])
//interface AppComponent {
//
//    fun inject(app: App)
//}
//
//@Module
//abstract class AppModule {
//
//    @PerActivity
//    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityModule2::class])
//    abstract fun bindMainActivity(): MainActivity
//}
//
//@Module
//class MainActivityModule {
//
//    @Provides
//    @Named("testMessage")
//    fun provideTestMessage(): String = "TestMessage"
//
//    @Provides
//    @PerActivity
//    fun provideDummy(): String = "Dummy"
//
//}
//
//@Module
//abstract class MainActivityModule2 {
//
//    @Binds
//    @PerActivity
//    @IntoMap
//    @ViewModelKey(WeatherViewModel::class)
//    abstract fun bindsWeatherViewModel(viewModel: WeatherViewModel): ViewModel
//}
//
//@Scope
//annotation class PerActivity
