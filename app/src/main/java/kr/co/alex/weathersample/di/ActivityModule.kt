package kr.co.alex.weathersample.di

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import kr.co.alex.weathersample.MainActivity
import javax.inject.Scope

@Module(subcomponents = [MainActivitySubComponent::class])
abstract class ActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(
        factory: ActivityModule_BindsMainActivity.MainActivitySubcomponent.Factory: AndroidInjector.Factory<*>
    )

}

@Scope
annotation class PerActivity
